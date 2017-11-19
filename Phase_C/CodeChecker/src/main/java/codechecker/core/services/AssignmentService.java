package codechecker.core.services;

import codechecker.core.entities.Assignment;
import codechecker.core.services.util.AssignmentList;

public class AssignmentService {
    public Assignment findAssignment(Long userId, Long assignmentId) {
    	//This needs to be implemented.
    	return new Assignment();
    }
    public AssignmentList findAllAssignments(Long userId) {
    	//This needs to be implemented.
    	return new AssignmentList();
    }
    public Assignment createAssignment(Assignment assignment) {
    	//This needs to be implemented.
    	return new Assignment();
    }
    public double compareAssignmentSubmissions(Long assignmentId, Long otherAssignmentId) {
    	//This needs to be implemented.
    	return 0;
    }
}
