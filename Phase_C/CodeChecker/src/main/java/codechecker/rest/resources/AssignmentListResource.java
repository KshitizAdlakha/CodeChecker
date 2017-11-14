package codechecker.rest.resources;

import codechecker.core.entities.Assignment;
import org.springframework.hateoas.ResourceSupport;

import java.util.ArrayList;
import java.util.List;

public class AssignmentListResource extends ResourceSupport {
    private List<Assignment> assignments= new ArrayList<>();

    public List<Assignment> getAssignments(){
        return assignments;
    }

    public void setAssignments(List<Assignment> _assignments){
        this.assignments=_assignments;
    }
}
