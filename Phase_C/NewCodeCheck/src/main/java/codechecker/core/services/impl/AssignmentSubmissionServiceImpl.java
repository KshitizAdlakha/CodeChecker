package codechecker.core.services.impl;

import codechecker.core.services.impl.visitors.*;
import com.github.javaparser.JavaParser;
import com.github.javaparser.ast.CompilationUnit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import codechecker.core.models.entities.AssignmentSubmission;
import codechecker.core.repositories.AssignmentSubmissionRepo;
import codechecker.core.services.AssignmentSubmissionService;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import  org.apache.commons.io.FileUtils;

/**
 * Assignment Submission Service Implementation class
 */
@Service
@Transactional
public class AssignmentSubmissionServiceImpl implements AssignmentSubmissionService {

    @Autowired
    private AssignmentSubmissionRepo entryRepo;

    /**
     * @param id with which the assignmentSubmission will be found
     * @return the found assignmentSubmission with given id
     */
    @Override
    public AssignmentSubmission findAssignmentSubmission(Long id) {
        return entryRepo.findAssignmentSubmission(id);
    }

    /**
     * @param id with which the assignmentSubmission will be deleted
     * @return the remaining assignmentSubmission list without the deleted assignmentSubmission
     */
    @Override
    public AssignmentSubmission deleteAssignmentSubmission(Long id) {
        return entryRepo.deleteAssignmentSubmission(id);
    }

    /**
     * @param id with which the assignmentSubmission will be updated
     * @param data of assignmentSubmission to be updated
     * @return the updated assignmentSubmission with given id and data
     */
    @Override
    public AssignmentSubmission updateAssignmentSubmission(Long id, AssignmentSubmission data) {
        return entryRepo.updateAssignmentSubmission(id, data);
    }

    /**
     * @param assignmentId with which the assignmentSubmission will be compared
     * @param otherAssignmentId of assignmentSubmission to be compared against
     * @return the checked assignmentSubmission of the given two assignmentIds
     */
    @Override
    public String compareAssignmentSubmissions(Long assignmentId, Long otherAssignmentId) {
        FileInputStream in1;
        FileInputStream in2;
        String similarityPercent="0";

        try {
			/*
			 * Below is sample code until the following code is functional:
			 *  in1 = new FileInputStream(firstSubmission.getAssociatedFile());
			 *  in2 = new FileInputStream(secondSubmission.getAssociatedFile());
			 */
            in1 = new FileInputStream(new File("src//main//webapp//app//app//upload//"+assignmentId+".java"));
            in2 = new FileInputStream(new File("src//main//webapp//app//app//upload//"+otherAssignmentId+".java"));

            // parse the file
            CompilationUnit cu1 = JavaParser.parse(in1);
            CompilationUnit cu2 = JavaParser.parse(in2);

	        /*
	         * Visitors for removal of comments and obtaining the hash codes
	         */
            CommentRemovalVisitor crv1 = new CommentRemovalVisitor();
            CommentRemovalVisitor crv2 = new CommentRemovalVisitor();

            FunctionOrderingVisitor nov1 = new FunctionOrderingVisitor();
            FunctionOrderingVisitor nov2 = new FunctionOrderingVisitor();

            VariableOrderingInClassVisitor vov1 = new VariableOrderingInClassVisitor();
            VariableOrderingInClassVisitor vov2 = new VariableOrderingInClassVisitor();

            VariableOrderInMethodVisitor vomv1 = new VariableOrderInMethodVisitor();
            VariableOrderInMethodVisitor vomv2 = new VariableOrderInMethodVisitor();

            FunctionStandardizationVisitor fsv1 = new FunctionStandardizationVisitor();
            FunctionStandardizationVisitor fsv2 = new FunctionStandardizationVisitor();

            VariableStandardizationVisitor vsv1 = new VariableStandardizationVisitor();
            VariableStandardizationVisitor vsv2 = new VariableStandardizationVisitor();

            VariableDatatypeVisitor vdv1 = new VariableDatatypeVisitor();
            VariableDatatypeVisitor vdv2 = new VariableDatatypeVisitor();

            MethodVisitor mv1 = new MethodVisitor();
            MethodVisitor mv2 = new MethodVisitor();

            FieldDatatypeVisitor fdv1 = new FieldDatatypeVisitor();
            FieldDatatypeVisitor fdv2 = new FieldDatatypeVisitor();

            HashCodeVisitor hcv1 = new HashCodeVisitor();
            HashCodeVisitor hcv2 = new HashCodeVisitor();

            ConstructorVisitor cv1 = new ConstructorVisitor();
            ConstructorVisitor cv2 = new ConstructorVisitor();

            ObjectDatatypeVisitor odv1 = new ObjectDatatypeVisitor();
            ObjectDatatypeVisitor odv2 = new ObjectDatatypeVisitor();

            cu1.accept(crv1, null); //All the nodes in the AST generated from the first submission are visited and the nodes identified as comments are removed
            cu2.accept(crv2, null); //All the nodes in the AST generated from the second submission are visited and the nodes identified as comments are removed

            // orders Nodes in such a way that the variable declaration is done at the last in classes
            cu1.accept(vov1, null);
            cu2.accept(vov2, null);

            // order Nodes by Data type in each Bloack Statement
            cu1.accept(vomv1, null);
            cu2.accept(vomv2, null);

//            Standardizes variable naming
            cu1.accept(vsv1, null);
            cu2.accept(vsv2, null);

            //Standardizes all datatypes
            cu1.accept(vdv1, null);
            cu2.accept(vdv2, null);
            cu1.accept(mv1, null);
            cu2.accept(mv2, null);
            cu1.accept(fdv1, null);
            cu2.accept(fdv2, null);
            cu1.accept(cv1, null);
            cu2.accept(cv2, null);
            cu1.accept(odv1, null);
            cu2.accept(odv2, null);

            //Order nodes in on the basis of number of variables, number of parameters, and the return type
            cu1.accept(nov1, null);
            cu2.accept(nov2, null);

            //Standardizes function naming
            cu1.accept(fsv1, null);
            cu2.accept(fsv2, null);
            
            cu1.accept(hcv1, null); //All the nodes in the AST generated from the first submission are visited.
            cu2.accept(hcv2, null); //All the nodes in the AST generated from the second submission are visited.


            // save transformed files to be displayed
            try {
                FileUtils.writeStringToFile(
                        new File("src//main//webapp//app//app//upload//"+assignmentId+"_transformed.java"), cu1.toString());
                FileUtils.writeStringToFile(
                        new File("src//main//webapp//app//app//upload//"+otherAssignmentId+"_transformed.java"), cu2.toString());
            } catch (IOException e) {
                e.printStackTrace();
            }


	        /*
	        * SimilarityPercentGenerator will calculate the percentage based on the similarity of the two programs.
	        */
            SimilarityPercentGenerator spg = new SimilarityPercentGenerator();
            similarityPercent = String.format("%.2f", spg.getSimilarityPercent(hcv1, hcv2)); //Converted to string to display the percent rounded off to two decimal places

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return similarityPercent;
    }
}
