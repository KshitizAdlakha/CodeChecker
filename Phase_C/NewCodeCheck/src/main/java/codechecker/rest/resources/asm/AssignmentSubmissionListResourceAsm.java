package codechecker.rest.resources.asm;

import org.springframework.hateoas.mvc.ResourceAssemblerSupport;
import codechecker.core.services.util.AssignmentSubmissionList;
import codechecker.rest.mvc.AssignmentController;
import codechecker.rest.resources.AssignmentSubmissionListResource;
import codechecker.rest.resources.AssignmentSubmissionResource;
import java.util.List;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.*;

public class AssignmentSubmissionListResourceAsm extends
        ResourceAssemblerSupport<AssignmentSubmissionList, AssignmentSubmissionListResource> {
    public AssignmentSubmissionListResourceAsm() {
        super(AssignmentController.class, AssignmentSubmissionListResource.class);
    }

    @Override
    public AssignmentSubmissionListResource toResource(AssignmentSubmissionList list) {
        List<AssignmentSubmissionResource> resources = new AssignmentSubmissionResourceAsm().toResources(list.getEntries());
        AssignmentSubmissionListResource listResource = new AssignmentSubmissionListResource();
        listResource.setEntries(resources);
        listResource.add(linkTo(methodOn(AssignmentController.class)
                .findAllAssignmentSubmissions(list.getAssignmentSubmissionId())).withSelfRel());
        return listResource;
    }
}
