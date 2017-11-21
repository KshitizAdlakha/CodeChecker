package codechecker.rest.resources.asm;

import org.springframework.hateoas.mvc.ResourceAssemblerSupport;
import codechecker.core.models.entities.Assignment;
import codechecker.rest.mvc.AccountController;
import codechecker.rest.mvc.AssignmentController;
import codechecker.rest.resources.AssignmentResource;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.*;

/**
 * Created by on 6/30/14.
 */
public class AssignmentResourceAsm extends ResourceAssemblerSupport<Assignment, AssignmentResource> {
    public AssignmentResourceAsm() {
        super(AssignmentController.class, AssignmentResource.class);
    }

    @Override
    public AssignmentResource toResource(Assignment assignment) {
        AssignmentResource resource = new AssignmentResource();
        resource.setTitle(assignment.getTitle());
        resource.add(linkTo(AssignmentController.class).slash(assignment.getId()).withSelfRel());
        resource.add(linkTo(AssignmentController.class).slash(assignment.getId()).slash("assignment-entries").withRel("entries"));
        resource.setRid(assignment.getId());
        if(assignment.getOwner() != null)
            resource.add(linkTo(AccountController.class).slash(assignment.getOwner().getId()).withRel("owner"));
        return resource;
    }
}
