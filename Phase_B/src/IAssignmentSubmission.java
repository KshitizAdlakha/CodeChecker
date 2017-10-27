
public interface IAssignmentSubmission {

    /**
     * Get the name of the student
     */
    public String getStudentName();
    /**
     * Set the name of the student
     */
    public void setStudentName();
    /**
     * Get the name of the assignment topic related to the submission
     */
    public String getAssignmentTopic();
    /**
     * Set the name of the assignment topic related to the submission
     */
    public void setAssignmentTopic();
    /**
     * Get the date of the assignment submission
     */
    public String getDateSubmitted();
    /**
     * Set the date of the assignment submission
     */
    public void setDateSubmitted();
    /**
     *Textual representation of the Course object
     */
    String textualRepresentation();
    
    /**
     * Get the id of the assignment topic
     */
    public String getAssignmentSubmissionId();
    /**
     * Set id name of the assignment topic
     */
    public void SetAssignmentSubmissionId();
}
