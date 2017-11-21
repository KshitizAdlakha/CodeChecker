package codechecker.core.services;


import codechecker.core.models.entities.AssignmentSubmission;

/**
 * Assignment Submission Service interface
 */
public interface AssignmentSubmissionService {
	// Returns the AssignmentSubmission or null if it can't be found
	AssignmentSubmission findAssignmentSubmission(Long id);

	// Deletes the found AssignmentSubmission or returns null if it can't be found
     AssignmentSubmission deleteAssignmentSubmission(Long id);

    /**
     * @param id the id of the AssignmentSubmission to updateAssignmentSubmission
     * @param data the AssignmentSubmission containing the data to be used for the updateAssignmentSubmission
     * @return the updated AssignmentSubmission or null if the AssignmentSubmission with the id cannot be found
     */
    AssignmentSubmission updateAssignmentSubmission(Long id, AssignmentSubmission data);

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
    double compareAssignmentSubmissions(Long assignmentId, Long otherAssignmentId);

}
