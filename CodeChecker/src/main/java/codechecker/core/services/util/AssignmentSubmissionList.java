package codechecker.core.services.util;

import codechecker.core.models.entities.AssignmentSubmission;

import java.util.ArrayList;
import java.util.List;

/**
 * Assignment Submission List
 */
public class AssignmentSubmissionList {
    private List<AssignmentSubmission> entries = new ArrayList<AssignmentSubmission>();
    private Long assignmentSubmissionId;

    /**
     * Assignment Submission List constructor
     */
    public AssignmentSubmissionList(Long assignmentSubmissionId, List<AssignmentSubmission> entries) {
        this.assignmentSubmissionId = assignmentSubmissionId;
        this.entries = entries;
    }

    /**
     * get entries method
     */
    public List<AssignmentSubmission> getEntries() {
        return entries;
    }

    /**
     * set entries method
     */
    public void setEntries(List<AssignmentSubmission> entries) {
        this.entries = entries;
    }

    /**
     * get assignment submission id method
     */
    public Long getAssignmentSubmissionId() {
        return assignmentSubmissionId;
    }

    /**
     * set assignment submission id method
     */
    public void setAssignmentSubmissionId(Long assignmentSubmissionId) {
        this.assignmentSubmissionId = assignmentSubmissionId;
    }
}
