package codechecker.rest.resources.asm;

import org.springframework.hateoas.mvc.ResourceAssemblerSupport;
import codechecker.core.models.entities.Assignment;
import codechecker.rest.mvc.AccountController;
import codechecker.rest.mvc.AssignmentController;
import codechecker.rest.resources.AssignmentResource;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.*;

/**
 * Assignment Resource Asm class
 */
public class AssignmentResourceAsm extends ResourceAssemblerSupport<Assignment, AssignmentResource> {
    /**
     * Assignment Resource Asm constructor
     */
    public AssignmentResourceAsm() {
        super(AssignmentController.class, AssignmentResource.class);
    }

    /**
     * Function to get assignment resources of the given assignment
     */
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
