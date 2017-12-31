package codechecker.rest.resources;

import org.springframework.hateoas.ResourceSupport;

import java.util.List;

/**
 * Assignment Submission List Resource class
 * extends Resource Support
 */
public class AssignmentSubmissionListResource extends ResourceSupport {
    private List<AssignmentSubmissionResource> entries;

    /**
     * Function to get assignment submissions
     */
    public List<AssignmentSubmissionResource> getEntries() {
        return entries;
    }

    /**
     * Function to set assignment submisison entries
     */
    public void setEntries(List<AssignmentSubmissionResource> entries) {
        this.entries = entries;
    }
}
