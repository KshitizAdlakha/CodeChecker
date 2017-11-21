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
	         * This is where the diff function will go
	         * retur diff(cu1.toString(), cu2.toString());
	         */

        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return 0;
    }
}
