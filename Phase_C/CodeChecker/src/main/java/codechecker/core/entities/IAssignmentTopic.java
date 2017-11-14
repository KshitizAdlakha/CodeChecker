package codechecker.core.entities;
public interface IAssignmentTopic {

    /**
     * Get the name of the assignment topic
     */
    public String getAssignmentTopicName();
    /**
     * Set the name of the assignment topic
     */
    public void setAssignmentTopicName();
    /**
     * Get the id of the assignment topic
     */
    public String getAssignmentTopicId();
    /**
     * Set id name of the assignment topic
     */
    public void SetAssignmentTopicId();
    /**
     * Get the deadline date of the assignment topic
     */
    public String getAssignmentDeadline();
    /**
     * Set the deadline date of the assignment topic
     */
    public void setAssignmentDeadline();
    /**
     * Get the course name associated with the assignment topic
     */
    public String getCourseName();
    /**
     * Set the course name associated with the assignment topic
     */
    public void setCourseName();
    /**
     * Get the threshold of the assignment topic
     */
    public String getThreshold();
    /**
     * Set the threshold of the assignment topic
     */
    public void setThreshold();
    /**
     *Textual representation of the AssignmentTopic object
     */
    String textualRepresentation();
}
