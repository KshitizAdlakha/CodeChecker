package codechecker.core.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import codechecker.core.models.entities.AssignmentSubmission;
import codechecker.core.repositories.AssignmentSubmissionRepo;
import codechecker.core.services.AssignmentSubmissionService;

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
}
