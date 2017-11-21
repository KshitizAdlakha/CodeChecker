package codechecker.core.services.util;

import codechecker.core.models.entities.Assignment;

import java.util.ArrayList;
import java.util.List;

/**
 * Assignment List class
 */
public class AssignmentList {

    private List<Assignment> assignments = new ArrayList<Assignment>();

    /**
     * Assignment list constructor
     */
    public AssignmentList(List resultList) {
        this.assignments = resultList;
    }

    /**
     * get assignments method
     */
    public List<Assignment> getAssignments() {
        return assignments;
    }

    /**
     * set assignments method
     */
    public void setAssignments(List<Assignment> assignments) {
        this.assignments = assignments;
    }
}
