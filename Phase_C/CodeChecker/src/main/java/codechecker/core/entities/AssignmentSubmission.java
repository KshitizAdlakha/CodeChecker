package codechecker.core.entities;

import java.util.Date;

public class AssignmentSubmission implements IAssignmentSubmission {
    private Long studentId;
    private Long assignmentTopicId;
    private Date dateSubmitted;
    private File associatedFile;


	@Override
	public IAssignmentTopic getAssignmentTopic() {
		return null;
	}

	@Override
	public void setAssignmentTopic(Long _topicID) {
		assignmentTopicId = _topicID;

	}

	@Override
	public Date getDateSubmitted() {
		return dateSubmitted;
	}

	@Override
	public void setDateSubmitted(Date _submittedDate) {
		dateSubmitted = _submittedDate;
		
	}
	
	@Override
	public String textualRepresentation() {
		return null;
	}

    /**
     * Get the associated student
     */
	@Override
    public User getStudent() {
		return null;
	}
    /**
     * Set the id of the student
     */
	@Override
    public void setStudent(Long _studentId) {
		studentId = _studentId;
	}

}
