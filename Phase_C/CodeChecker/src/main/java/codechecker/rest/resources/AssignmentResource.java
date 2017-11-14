package codechecker.rest.resources;

import codechecker.core.entities.Assignment;
import org.springframework.hateoas.ResourceSupport;

public class AssignmentResource extends ResourceSupport {
    private String assignmentName;
    private Long assignmentId;
    private Long userId;
    private String assignmentTopicName;

    public String getAssignmentTopicName() {
        return assignmentTopicName;
    }

    public void setAssignmentTopicName(String assignmentTopicName) {
        this.assignmentTopicName = assignmentTopicName;
    }

    public Long getAssignmentId() {
        return assignmentId;
    }

    public void setAssignmentId(Long assignmentTopicId) {
        this.assignmentId = assignmentTopicId;
    }

    public String getAssignmentName() {
        return assignmentName;
    }

    public void setAssignmentName(String assignmentName) {
        this.assignmentName = assignmentName;
    }

    public Long getUserId() {
        return userId;
    }
    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Assignment toAssignment() {
        Assignment a = new Assignment();
        a.setAssignmentName(assignmentName);
        a.setAssignmentTopicName(assignmentTopicName);
        a.setAssignmentId(assignmentId);
        a.setUserId(userId);
        return a;
    }
}
