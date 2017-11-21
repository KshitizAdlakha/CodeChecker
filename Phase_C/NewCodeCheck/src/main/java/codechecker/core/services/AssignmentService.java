package codechecker.core.services;

import codechecker.core.models.entities.Assignment;
import codechecker.core.models.entities.AssignmentSubmission;
import codechecker.core.services.exceptions.AssignmentNotFoundException;
import codechecker.core.services.util.AssignmentSubmissionList;
import codechecker.core.services.util.AssignmentList;

public interface AssignmentService {
    /**
     * @param assignmentId the id of the assignment to add this AssignmentSubmission to
     * @param data the AssignmentSubmission containing the data to be used for creating the new entity
     * @return the created AssignmentSubmission with a generated ID
     * @throws AssignmentNotFoundException if the assignment to add to cannot be found
     */
    AssignmentSubmission createAssignmentSubmission(Long assignmentId, AssignmentSubmission data);

    AssignmentList findAllAssignments();

    AssignmentSubmissionList findAllAssignmentSubmissions(Long assignmentId); // findAssignment all associated assignment entries

    Assignment findAssignment(Long id);
}