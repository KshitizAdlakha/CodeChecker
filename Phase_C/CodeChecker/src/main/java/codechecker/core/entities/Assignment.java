package codechecker.core.entities;

public class Assignment implements IAssignment {
    private String assignmentName;
    private Long assignmentId;
    private String assignmentTopicName;
    private Long userId;

    @Override
    public Long getUserId() {
        return userId;
    }
    @Override
    public void setUserId(Long userId) {
        this.userId = userId;
    }

    @Override
    public String getAssignmentTopicName() {
        return assignmentTopicName;
    }

    @Override
    public void setAssignmentTopicName(String assignmentTopicName) {
        this.assignmentTopicName = assignmentTopicName;
    }

    @Override
    public Long getAssignmentId() {
        return assignmentId;
    }

    @Override
    public void setAssignmentId(Long assignmentTopicId) {
        this.assignmentId = assignmentTopicId;
    }

    @Override
    public String textualRepresentation(IAssignment iAssignment) {
        Assignment a = (Assignment) iAssignment;
        return "[Assignment ID: "+a.assignmentId+" Topic: "+a.getAssignmentTopicName()+" Name: "+a.getAssignmentName()+"]";
    }

    @Override
    public String getAssignmentName() {
        return assignmentName;
    }

    @Override
    public void setAssignmentName(String assignmentName) {
        this.assignmentName = assignmentName;
    }
}