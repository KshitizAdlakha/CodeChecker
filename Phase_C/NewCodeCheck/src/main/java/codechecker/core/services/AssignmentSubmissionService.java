package codechecker.core.services;

import codechecker.core.models.entities.AssignmentSubmission;

public interface AssignmentSubmissionService {
    AssignmentSubmission findAssignmentSubmission(Long id); // Returns the AssignmentSubmission or null if it can't be found
    AssignmentSubmission deleteAssignmentSubmission(Long id); // Deletes the found AssignmentSubmission or returns null if it can't be found

    /**
     * @param id the id of the AssignmentSubmission to updateAssignmentSubmission
     * @param data the AssignmentSubmission containing the data to be used for the updateAssignmentSubmission
     * @return the updated AssignmentSubmission or null if the AssignmentSubmission with the id cannot be found
     */
    AssignmentSubmission updateAssignmentSubmission(Long id, AssignmentSubmission data);

}
