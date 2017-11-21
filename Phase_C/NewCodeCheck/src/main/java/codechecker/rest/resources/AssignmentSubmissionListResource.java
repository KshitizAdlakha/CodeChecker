package codechecker.rest.resources;

import org.springframework.hateoas.ResourceSupport;

import java.util.List;

/**
 * Created by on 6/28/14.
 */
public class AssignmentSubmissionListResource extends ResourceSupport {
    private List<AssignmentSubmissionResource> entries;

    public List<AssignmentSubmissionResource> getEntries() {
        return entries;
    }

    public void setEntries(List<AssignmentSubmissionResource> entries) {
        this.entries = entries;
    }
}
