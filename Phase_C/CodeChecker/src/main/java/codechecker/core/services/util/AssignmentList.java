package codechecker.core.services.util;

import codechecker.core.entities.Assignment;

import java.util.ArrayList;
import java.util.List;

public class AssignmentList {
    private List<Assignment> assignments= new ArrayList<>();

    public List<Assignment> getAssignments(){
        return assignments;
    }

    public void setAssignments(List<Assignment> _assignments){
        this.assignments=_assignments;
    }
}
