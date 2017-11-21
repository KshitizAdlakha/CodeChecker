package codechecker.core.services;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import com.github.javaparser.JavaParser;
import com.github.javaparser.ast.CompilationUnit;

import codechecker.core.models.entities.AssignmentSubmission;

public class AssignmentSubmissionService {
    public AssignmentSubmission findAssignmentSubmission(Long id) {
    	// Needs to be implemented
    	// Returns the AssignmentSubmission or null if it can't be found
    	return null;
    }; 
    public AssignmentSubmission deleteAssignmentSubmission(Long id) {
    	//Needs to be implemented
    	// Deletes the found AssignmentSubmission or returns null if it can't be found
    	return null;
    }

    /**
     * @param id the id of the AssignmentSubmission to updateAssignmentSubmission
     * @param data the AssignmentSubmission containing the data to be used for the updateAssignmentSubmission
     * @return the updated AssignmentSubmission or null if the AssignmentSubmission with the id cannot be found
     */
    public AssignmentSubmission updateAssignmentSubmission(Long id, AssignmentSubmission data) {
    	//Needs to be implemented
    	return null;
    }

    /* 
     * A method that compares the assignment submissions with the two specified
     * id values.
     * assignmentId - The id of the assignment submission to be compared against.
     * otherAssignmentId - The id of the assignment submission to be compared to the assignment
     * submission id with assignmentId.
     * 
     * Returns: a number from 0 to 1 that represents the percent match between the two
     * assignment submissions.
     * 
     */
    public double compareAssignmentSubmissions(Long assignmentId, Long otherAssignmentId) {
    	//This needs to be further implemented.
    	FileInputStream in1;
    	FileInputStream in2;
    	//Assignment firstSubmission = findAssignment((long) 0, assignmentId);
    	//Assignment secondSubmission  = findAssignment((long) 0, otherAssignmentId);
    	
		try {
			/*
			 * Below is sample code until the following code is functional:
			 * in1 = new FileInputStream(firstSubmission.getAssociatedFile());
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
