package codechecker.rest.resources;

import org.springframework.hateoas.ResourceSupport;
import codechecker.core.models.entities.AssignmentSubmission;

/**
 * Assignment Submission Resource class
 */
public class AssignmentSubmissionResource extends ResourceSupport {
    private String title;


    private Long rid;

    /**
     * Function to get resource id for assignment submission
     */
    public Long getRid() {
        return rid;
    }

    /**
     * Function to set resource id for assignment submission
     */
    public void setRid(Long rid) {
        this.rid = rid;
    }

    /**
     * Function to get title for assignment submission
     */
    public String getTitle() {
        return title;
    }

    /**
     * Function to set title for assignment submission
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Function to set title for newly created assignment submission
     */
    public AssignmentSubmission toAssignmentSubmission() {
        AssignmentSubmission entry = new AssignmentSubmission();
        entry.setTitle(title);
        return entry;
    }
}
