public interface IInstructor extends IUser {
	void uploadAssignmentSubmission(IAssignmentSubmission _submission);
	IAssignmentTopic analyzeAssignmentTopic();
	void reviewCode(IAssignmentSubmission _submission);
	void toggleCode();
}
