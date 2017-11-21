package codechecker.rest.resources;

import org.springframework.hateoas.ResourceSupport;
import codechecker.core.models.entities.Assignment;

public class AssignmentResource extends ResourceSupport {

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

    public Assignment toAssignment() {
        Assignment assignment = new Assignment();
        assignment.setTitle(title);
        return assignment;
    }
}
