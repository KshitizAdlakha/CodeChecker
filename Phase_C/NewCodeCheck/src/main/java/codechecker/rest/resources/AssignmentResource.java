package codechecker.rest.resources;

import codechecker.core.models.entities.Account;
import org.springframework.hateoas.ResourceSupport;
import codechecker.core.models.entities.Assignment;

public class AssignmentResource extends ResourceSupport {

    private String title;

    private Long rid;


    private Account owner;

    public Account getOwner() {
        return owner;
    }

    public void setOwner(Account account) {
        this.owner = account;
    }

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
