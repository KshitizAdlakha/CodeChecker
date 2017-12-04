package codechecker.rest.resources;

import org.springframework.hateoas.ResourceSupport;
import codechecker.core.models.entities.AssignmentSubmission;

public class AssignmentSubmissionResource extends ResourceSupport {
    private String title;


    private Long rid;

    public Long getRid() {
        return rid;
    }

    public void setRid(Long rid) {
        this.rid = rid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }


    public AssignmentSubmission toAssignmentSubmission() {
        AssignmentSubmission entry = new AssignmentSubmission();
        entry.setTitle(title);
        return entry;
    }
}
