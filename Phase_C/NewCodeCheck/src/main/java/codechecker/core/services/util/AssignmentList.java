package codechecker.core.services.util;

import codechecker.core.models.entities.Assignment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by on 7/1/14.
 */
public class AssignmentList {

    private List<Assignment> assignments = new ArrayList<Assignment>();

    public AssignmentList(List resultList) {
        this.assignments = resultList;
    }

    public List<Assignment> getAssignments() {
        return assignments;
    }

    public void setAssignments(List<Assignment> assignments) {
        this.assignments = assignments;
    }
}
