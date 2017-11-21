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

@Service
@Transactional
public class AssignmentServiceImpl implements AssignmentService {

    @Autowired
    private AssignmentRepo assignmentRepo;

    @Autowired
    private AssignmentSubmissionRepo entryRepo;

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

    @Override
    public AssignmentList findAllAssignments() {
        return new AssignmentList(assignmentRepo.findAllAssignments());
    }

    @Override
    public AssignmentSubmissionList findAllAssignmentSubmissions(Long assignmentId) {
        Assignment assignment = assignmentRepo.findAssignment(assignmentId);
        if(assignment == null)
        {
            throw new AssignmentNotFoundException();
        }
        return new AssignmentSubmissionList(assignmentId, entryRepo.findByAssignmentId(assignmentId));
    }

    @Override
    public Assignment findAssignment(Long id) {
        return assignmentRepo.findAssignment(id);
    }
}
