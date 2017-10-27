/**
 * An interface describing the functionality of instructors in the
 * plagiarism detection software. Instructors are the type of user
 * that will analyze the output of the plagiarism detection software.
 */
public interface IInstructor extends IUser {
	/**
	 * Uploads an assignment submission to the database to be considered 
	 * for plagiarism.
	 * @param _submission - the assignment to be uploaded
	 */
	void uploadAssignmentSubmission(IAssignmentSubmission _submission);
	/**
	 * Sets the assignment topic to be analyzed for plagiarism.
	 * @param _at_id - the unique identifier of the assignment topic to be analyzed.
	 */
	void analyzeAssignmentTopic(String _at_id);
	/**
	 * Sets the assignment submission to be reviewed from within the current assignment topic.
	 * @param _submission- the unique identifier of the assignment submission to be analyzed.
	 */
	void reviewCode(IAssignmentSubmission _as_id);
	/**
	 * Toggle's the instructor's view of the current assignment submission between original
	 * and processed.
	 */
	void toggleCode();
}
