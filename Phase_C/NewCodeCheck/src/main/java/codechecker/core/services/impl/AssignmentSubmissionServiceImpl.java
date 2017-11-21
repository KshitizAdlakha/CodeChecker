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

@Service
@Transactional
public class AssignmentSubmissionServiceImpl implements AssignmentSubmissionService {

    @Autowired
    private AssignmentSubmissionRepo entryRepo;

    @Override
    public AssignmentSubmission findAssignmentSubmission(Long id) {
        return entryRepo.findAssignmentSubmission(id);
    }

    @Override
    public AssignmentSubmission deleteAssignmentSubmission(Long id) {
        return entryRepo.deleteAssignmentSubmission(id);
    }

    @Override
    public AssignmentSubmission updateAssignmentSubmission(Long id, AssignmentSubmission data) {
        return entryRepo.updateAssignmentSubmission(id, data);
    }

    @Override
    public double compareAssignmentSubmissions(Long assignmentId, Long otherAssignmentId) {
        //This needs to be further implemented.
        FileInputStream in1;
        FileInputStream in2;
        //Assignment firstSubmission = findAssignment((long) 0, assignmentId);
        //Assignment secondSubmission  = findAssignment((long) 0, otherAssignmentId);

        try {
			/*
			 * Below is sample code until the following code is functional:
			 *  in1 = new FileInputStream(firstSubmission.getAssociatedFile());
			 *  in2 = new FileInputStream(secondSubmission.getAssociatedFile());
			 */
            in1 = new FileInputStream(new File("src//test//java//codechecker//Test1.java"));
            in2 = new FileInputStream(new File("src//test//java//codechecker//Test2.java"));
            // parse the file
            CompilationUnit cu1 = JavaParser.parse(in1);
            CompilationUnit cu2 = JavaParser.parse(in2);
	        /*
	         * This is where all the visitors will go.
	         */

            /*
             * HashCodeVisitor works on the assumption that the comments are already removed from the submissions.
             */
            HashCodeVisitor hcv1 = new HashCodeVisitor();
            HashCodeVisitor hcv2 = new HashCodeVisitor();
            cu1.accept(hcv1, null); //All the nodes in the AST generated from the first submission are visited. 
            cu2.accept(hcv2, null); //All the nodes in the AST generated from the second submission are visited. 

	    /*
	     * SimilarityPercentGenerator will calculate the percentage based on the similarity of the two programs.
	     */
            SimilarityPercentGenerator spg = new SimilarityPercentGenerator();
            String similarityPercent = String.format("%.2f", spg.getSimilarityPercent(hcv1, hcv2)); //Converted to string to display the percent rounded off to two decimal places 

        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return 0;
    }
}
