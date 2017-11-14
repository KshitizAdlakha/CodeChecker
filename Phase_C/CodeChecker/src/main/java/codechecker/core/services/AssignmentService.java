package codechecker.core.services;

import codechecker.core.entities.Assignment;
import codechecker.core.services.util.AssignmentList;

public interface AssignmentService {
    public Assignment findAssignment(Long userId, Long assignmentId);
    public AssignmentList findAllAssignments(Long userId);
    public Assignment createAssignment(Assignment assignment);
}
