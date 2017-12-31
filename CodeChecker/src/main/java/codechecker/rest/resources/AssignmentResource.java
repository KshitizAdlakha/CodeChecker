package codechecker.rest.resources;

import codechecker.core.models.entities.Account;
import org.springframework.hateoas.ResourceSupport;
import codechecker.core.models.entities.Assignment;

/**
 * Assignment Resource class
 * extends Resource Support
 */
public class AssignmentResource extends ResourceSupport {

    private String title;

    private Long rid;


    private Account owner;

    /**
     * Function to get owner
     */
    public Account getOwner() {
        return owner;
    }

    /**
     * Function to set owner
     */
    public void setOwner(Account account) {
        this.owner = account;
    }

    /**
     * Function to get resource id
     */
    public Long getRid() {
        return rid;
    }

    /**
     * Function to set resource id
     */
    public void setRid(Long rid) {
        this.rid = rid;
    }

    /**
     * Function to get title of assignment
     */
    public String getTitle() {
        return title;
    }

    /**
     * Function to set title of assignment
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Function to set title to newly created assignment
     */
    public Assignment toAssignment() {
        Assignment assignment = new Assignment();
        assignment.setTitle(title);
        return assignment;
    }
}
