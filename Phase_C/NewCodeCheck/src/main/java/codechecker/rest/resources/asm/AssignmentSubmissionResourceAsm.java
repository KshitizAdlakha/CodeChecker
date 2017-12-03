package codechecker.rest.resources.asm;

import org.springframework.hateoas.Link;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;
import codechecker.core.models.entities.AssignmentSubmission;
import codechecker.rest.mvc.AssignmentController;
import codechecker.rest.mvc.AssignmentSubmissionController;
import codechecker.rest.resources.AssignmentSubmissionResource;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.*;

public class AssignmentSubmissionResourceAsm extends ResourceAssemblerSupport<AssignmentSubmission, AssignmentSubmissionResource> {

    public AssignmentSubmissionResourceAsm()
    {
        super(AssignmentSubmissionController.class, AssignmentSubmissionResource.class);
    }

    @Override
    public AssignmentSubmissionResource toResource(AssignmentSubmission assignmentSubmission) {
        AssignmentSubmissionResource res = new AssignmentSubmissionResource();
        res.setTitle(assignmentSubmission.getTitle());
//        res.setContent(assignmentSubmission.getContent());
        res.setRid(assignmentSubmission.getId());
        Link self = linkTo(AssignmentSubmissionController.class).slash(assignmentSubmission.getId()).withSelfRel();
        res.add(self);
        if(assignmentSubmission.getAssignment() != null)
        {
            res.add((linkTo(AssignmentController.class).slash(assignmentSubmission.getAssignment().getId()).withRel("assignment")));
        }
        return res;
    }
}
