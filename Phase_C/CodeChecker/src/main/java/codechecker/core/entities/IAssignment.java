package codechecker.core.entities;

public interface IAssignment {
    /**
     * Get the name of the assignment topic
     */
    public String getAssignmentTopicName();
    /**
     * Set the name of the assignment topic
     */
    public void setAssignmentTopicName(String assignmentTopicName);
    /**
     * Get the id of the assignment topic
     */
    /**
     * Get the name of the assignment topic
     */
    public String getAssignmentName();
    /**
     * Set the name of the assignment topic
     */
    public void setAssignmentName(String assignmentName);
    /**
     * Get the id of the assignment topic
     */
    public Long getAssignmentId();
    /**
     * Set id name of the assignment topic
     */
    public void setAssignmentId(Long assignmentTopicId);
    /**
     *Textual representation of the Assignment object
     */
    String textualRepresentation(IAssignment iAssignment);
    /**
     * Get id of the user to whom the assignment belongs
     */
    public Long getUserId();
    /**
     * Set id of the user to whom the assignment belongs
     */
    public void setUserId(Long userId);
}
