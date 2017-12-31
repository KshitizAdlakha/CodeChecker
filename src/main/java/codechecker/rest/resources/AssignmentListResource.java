package codechecker.rest.resources;

import org.springframework.hateoas.ResourceSupport;

import java.util.ArrayList;
import java.util.List;

/**
 * Assignment List Resource class
 * extends Resource Support
 */
public class AssignmentListResource extends ResourceSupport {
    private List<AssignmentResource> assignments = new ArrayList<AssignmentResource>();

    /**
     * Function to get assignments
     */
    public List<AssignmentResource> getAssignments() {
        return assignments;
    }

    /**
     * Function to set assignments
     */
    public void setAssignments(List<AssignmentResource> assignments) {
        this.assignments = assignments;
    }
}
