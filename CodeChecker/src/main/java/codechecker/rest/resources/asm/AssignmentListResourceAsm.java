package codechecker.rest.resources.asm;

import org.springframework.hateoas.mvc.ResourceAssemblerSupport;
import codechecker.core.services.util.AssignmentList;
import codechecker.rest.mvc.AssignmentController;
import codechecker.rest.resources.AssignmentListResource;

/**
 * Assignment List Resource Asm class
 */
public class AssignmentListResourceAsm extends ResourceAssemblerSupport<AssignmentList, AssignmentListResource> {

    /**
     * Assignment List Resource Asm constructor
     */
    public AssignmentListResourceAsm()
    {
        super(AssignmentController.class, AssignmentListResource.class);
    }

    /**
     * Function to add all assignments resources to assignment list
     */
    @Override
    public AssignmentListResource toResource(AssignmentList assignmentList) {
        AssignmentListResource res = new AssignmentListResource();
        res.setAssignments(new AssignmentResourceAsm().toResources(assignmentList.getAssignments()));
        return res;
    }
}
