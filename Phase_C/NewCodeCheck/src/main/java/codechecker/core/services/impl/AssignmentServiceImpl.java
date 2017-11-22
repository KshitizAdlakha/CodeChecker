package codechecker.core.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import codechecker.core.models.entities.Assignment;
import codechecker.core.models.entities.AssignmentSubmission;
import codechecker.core.repositories.AssignmentSubmissionRepo;
import codechecker.core.repositories.AssignmentRepo;
import codechecker.core.services.AssignmentService;
import codechecker.core.services.exceptions.AssignmentNotFoundException;
import codechecker.core.services.util.AssignmentSubmissionList;
import codechecker.core.services.util.AssignmentList;

/**
 * Assignment Service Implementation class
 */
@Service
@Transactional
public class AssignmentServiceImpl implements AssignmentService {

    @Autowired
    private AssignmentRepo assignmentRepo;

    @Autowired
    private AssignmentSubmissionRepo entryRepo;

    /**
     * @param assignmentId with which the assignment submission will be created
     * @param data data which will be included in assignment submission
     * @return the created assignmentSubmission with assignmentId and assignmentSubmission data
     */
    @Override
    public AssignmentSubmission createAssignmentSubmission(Long assignmentId, AssignmentSubmission data) {
        Assignment assignment = assignmentRepo.findAssignment(assignmentId);
        if(assignment == null)
        {
            throw new AssignmentNotFoundException();
        }
        AssignmentSubmission entry = entryRepo.createAssignmentSubmission(data);
        entry.setAssignment(assignment);
        return entry;
    }

    /**
     * find all assignments method
     */
    @Override
    public AssignmentList findAllAssignments() {
        return new AssignmentList(assignmentRepo.findAllAssignments());
    }

    /**
     * @param assignmentId by which similar assignment lists will be found
     * @return list of all found assignment submission lists with given assignmentId
     */
    @Override
    public AssignmentSubmissionList findAllAssignmentSubmissions(Long assignmentId) {
        Assignment assignment = assignmentRepo.findAssignment(assignmentId);
        if(assignment == null)
        {
            throw new AssignmentNotFoundException();
        }
        return new AssignmentSubmissionList(assignmentId, entryRepo.findByAssignmentId(assignmentId));
    }

    /**
     * @param id with which the assignment will be found
     * @return the created assignment with id
     */
    @Override
    public Assignment findAssignment(Long id) {
        return assignmentRepo.findAssignment(id);
    }
}
