package codechecker.core.entities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Date;
import com.github.javaparser.JavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;

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
        FileInputStream in;
		try {
			in = new FileInputStream(associatedFile);
	        // parse the file
	        CompilationUnit cu = JavaParser.parse(in);

	        // prints the resulting compilation unit to default system output
	        return cu.toString();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "";
		}
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
