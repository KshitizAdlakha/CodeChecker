package codechecker.core.repositories;

import codechecker.core.models.entities.AssignmentSubmission;

import java.util.List;

public interface AssignmentSubmissionRepo {
    // Returns the AssignmentSubmission or null if it can't be found
    AssignmentSubmission findAssignmentSubmission(Long id);

    /**
     * Deletes the found AssignmentSubmission or returns null if it can't be found
     * @param id the id of the AssignmentSubmission to be deleted
     * @return AssignmentSubmission that has been deleted, or returns null if no AssignmentSubmission found associated to the given id
     */
    AssignmentSubmission deleteAssignmentSubmission(Long id);

    /**
     * Updates an existing AssignmentSubmission
     * @param id the id of the AssignmentSubmission to updateAssignmentSubmission
     * @param data the AssignmentSubmission containing the data to be used for the updateAssignmentSubmission
     * @return the updated AssignmentSubmission or null if the AssignmentSubmission with the id cannot be found
     */
    AssignmentSubmission updateAssignmentSubmission(Long id, AssignmentSubmission data);

    /**
     * Creates a new Assignment Submission
     * @param data the AssignmentSubmission containing the data to be used for the new AssignmentSubmission
     * @return the created AssignmentSubmission
     */
    AssignmentSubmission createAssignmentSubmission(AssignmentSubmission data);

    /**
     * Finds a specific Assignment Submission by Id
     * @param id the id of the AssignmentSubmission to be searched
     * @return the AssignmentSubmission associated to the given id, returns null if no AssignmentSubmission found
     */
    List<AssignmentSubmission> findByAssignmentId(Long id);
}
