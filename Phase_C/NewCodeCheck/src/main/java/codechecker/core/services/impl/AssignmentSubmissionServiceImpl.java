package codechecker.core.services.impl;

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
        //Assignment firstSubmission = findAssignment((long) 0, assignmentId);
        //Assignment secondSubmission  = findAssignment((long) 0, otherAssignmentId);

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
            VariableStandardizationVisitor vsv1 = new VariableStandardizationVisitor();
            VariableStandardizationVisitor vsv2 = new VariableStandardizationVisitor();
            FunctionStandardizationVisitor fsv1 = new FunctionStandardizationVisitor();
            FunctionStandardizationVisitor fsv2 = new FunctionStandardizationVisitor();
            HashCodeVisitor hcv1 = new HashCodeVisitor();
            HashCodeVisitor hcv2 = new HashCodeVisitor();

            cu1.accept(crv1, null); //All the nodes in the AST generated from the first submission are visited and the nodes identified as comments are removed
            cu2.accept(crv2, null); //All the nodes in the AST generated from the second submission are visited and the nodes identified as comments are removed
            
            //Standardizes variable naming
            cu1.accept(vsv1, null);
            cu2.accept(vsv2, null);
            
            //Standardizes function naming
            cu1.accept(fsv1, null);
            cu2.accept(fsv2, null);
            
            cu1.accept(hcv1, null); //All the nodes in the AST generated from the first submission are visited.
            cu2.accept(hcv2, null); //All the nodes in the AST generated from the second submission are visited.

	        /*
	        * SimilarityPercentGenerator will calculate the percentage based on the similarity of the two programs.
	        */
            SimilarityPercentGenerator spg = new SimilarityPercentGenerator();
            similarityPercent = String.format("%.2f", spg.getSimilarityPercent(hcv1, hcv2)); //Converted to string to display the percent rounded off to two decimal places

        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return similarityPercent;
    }
}
