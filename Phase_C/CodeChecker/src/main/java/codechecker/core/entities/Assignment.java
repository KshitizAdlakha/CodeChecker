package codechecker.core.entities;

public class Assignment implements IAssignment {
    private String assignmentTopicName;
    private Long assignmentTopicId;
    private String assignmentName;

    @Override
    public String getAssignmentTopicName() {
        return assignmentTopicName;
    }

    @Override
    public void setAssignmentTopicName(String assignmentTopicName) {
        this.assignmentTopicName = assignmentTopicName;
    }

    @Override
    public Long getAssignmentTopicId() {
        return assignmentTopicId;
    }

    @Override
    public void setAssignmentTopicId(Long assignmentTopicId) {
        this.assignmentTopicId = assignmentTopicId;
    }

    @Override
    public String textualRepresentation(IAssignment iAssignment) {
        Assignment a = (Assignment) iAssignment;
        return "[Assignment ID: "+a.assignmentTopicId+" Topic: "+a.getAssignmentTopicName()+" Name: "+a.getAssignmentName()+"]";
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
