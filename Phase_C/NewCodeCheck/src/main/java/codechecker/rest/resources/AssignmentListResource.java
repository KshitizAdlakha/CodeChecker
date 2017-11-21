package codechecker.rest.resources;

import org.springframework.hateoas.ResourceSupport;

import java.util.ArrayList;
import java.util.List;

public class AssignmentListResource extends ResourceSupport {
    private List<AssignmentResource> assignments = new ArrayList<AssignmentResource>();

    public List<AssignmentResource> getAssignments() {
        return assignments;
    }

    public void setAssignments(List<AssignmentResource> assignments) {
        this.assignments = assignments;
    }
}
