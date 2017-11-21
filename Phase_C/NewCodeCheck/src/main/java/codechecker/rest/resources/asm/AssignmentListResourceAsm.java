package codechecker.rest.resources.asm;

import org.springframework.hateoas.mvc.ResourceAssemblerSupport;
import codechecker.core.services.util.AssignmentList;
import codechecker.rest.mvc.AssignmentController;
import codechecker.rest.resources.AssignmentListResource;

public class AssignmentListResourceAsm extends ResourceAssemblerSupport<AssignmentList, AssignmentListResource> {

    public AssignmentListResourceAsm()
    {
        super(AssignmentController.class, AssignmentListResource.class);
    }

    @Override
    public AssignmentListResource toResource(AssignmentList assignmentList) {
        AssignmentListResource res = new AssignmentListResource();
        res.setAssignments(new AssignmentResourceAsm().toResources(assignmentList.getAssignments()));
        return res;
    }
}
