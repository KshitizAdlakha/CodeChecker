package codechecker.rest.resources.asm;

import codechecker.core.entities.Assignment;
import codechecker.rest.controllers.AssignmentController;
import codechecker.rest.resources.AssignmentResource;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

public class AssignmentResourceAsm extends ResourceAssemblerSupport<Assignment, AssignmentResource> {
    public AssignmentResourceAsm() {
        super(AssignmentController.class, AssignmentResource.class);
    }

    @Override
    public AssignmentResource toResource(Assignment assignment) {
        AssignmentResource assignmentResource = new AssignmentResource();
        assignmentResource.setAssignmentName(assignment.getAssignmentName());
        assignmentResource.setAssignmentId(assignment.getAssignmentId());
        assignmentResource.setAssignmentTopicName(assignment.getAssignmentTopicName());
        Link link = linkTo(methodOn(AssignmentController.class)
                .findAssignment(assignment.getAssignmentId(), assignment.getUserId()))
                .withSelfRel();
        assignmentResource.add(link.withSelfRel());
        return assignmentResource;
    }
}
