package codechecker.core.entities;

import java.util.Date;

public interface IAssignmentSubmission {

    /**
     * Get the associated student
     */
    public User getStudent();
    /**
     * Set the id of the student
     */
    public void setStudent(Long _studentId);
    /**
     * Get the name of the assignment topic related to the submission
     */
    public IAssignmentTopic getAssignmentTopic();
    /**
     * Set assignment topic(by id) related to the submission
     */
    public void setAssignmentTopic(Long _topic);
    /**
     * Get the date of the assignment submission
     */
    public Date getDateSubmitted();
    /**
     * Set the date of the assignment submission
     */
    public void setDateSubmitted(Date _submittedDate);
    /**
     *Textual representation of the Course object
     */
    String textualRepresentation();
}
