package codechecker.core.services.util;

import codechecker.core.models.entities.AssignmentSubmission;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by on 6/28/14.
 */
public class AssignmentSubmissionList {
    private List<AssignmentSubmission> entries = new ArrayList<AssignmentSubmission>();
    private Long assignmentSubmissionId;

    public AssignmentSubmissionList(Long assignmentSubmissionId, List<AssignmentSubmission> entries) {
        this.assignmentSubmissionId = assignmentSubmissionId;
        this.entries = entries;
    }

    public List<AssignmentSubmission> getEntries() {
        return entries;
    }

    public void setEntries(List<AssignmentSubmission> entries) {
        this.entries = entries;
    }

    public Long getAssignmentSubmissionId() {
        return assignmentSubmissionId;
    }

    public void setAssignmentSubmissionId(Long assignmentSubmissionId) {
        this.assignmentSubmissionId = assignmentSubmissionId;
    }
}
