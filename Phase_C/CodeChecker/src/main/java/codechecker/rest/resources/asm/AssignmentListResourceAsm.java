package codechecker.rest.resources.asm;

import codechecker.core.services.util.AssignmentList;
import codechecker.rest.controllers.AssignmentController;
import codechecker.rest.resources.AssignmentListResource;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;

public class AssignmentListResourceAsm extends ResourceAssemblerSupport<AssignmentList, AssignmentListResource> {
    public AssignmentListResourceAsm() {
        super(AssignmentController.class, AssignmentListResource.class);
    }

    @Override
    public AssignmentListResource toResource(AssignmentList assignmentList) {
        AssignmentListResource res = new AssignmentListResource();
        res.setAssignments(new AssignmentResourceAsm().toResources(res.getAssignments()));
        return res;
    }
}
