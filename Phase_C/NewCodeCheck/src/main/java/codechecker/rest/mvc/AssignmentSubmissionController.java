package codechecker.rest.mvc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import codechecker.core.models.entities.AssignmentSubmission;
import codechecker.core.services.AssignmentSubmissionService;
import codechecker.rest.resources.AssignmentSubmissionResource;
import codechecker.rest.resources.asm.AssignmentSubmissionResourceAsm;

/**
 * Created by on 6/5/14.
 */
@Controller
@RequestMapping("/rest/assignment-submissions")
public class AssignmentSubmissionController {
    private AssignmentSubmissionService service;

    @Autowired
    public AssignmentSubmissionController(AssignmentSubmissionService service)
    {
        this.service = service;
    }

/*    @RequestMapping(value="/{assignmentSubmissionId}",
            method = RequestMethod.GET)
    public ResponseEntity<AssignmentSubmissionResource> getAssignmentSubmission (
            @PathVariable Long assignmentSubmissionId) {
        AssignmentSubmission entry = service.findAssignmentSubmission(assignmentSubmissionId);
        if(entry != null) {
            AssignmentSubmissionResource res = new AssignmentSubmissionResourceAsm().toResource(entry);
            return new ResponseEntity<AssignmentSubmissionResource>(res, HttpStatus.OK);
        } else {
            return new ResponseEntity<AssignmentSubmissionResource>(HttpStatus.NOT_FOUND);
        }
    }
*/
    @RequestMapping(value="/{assignmentSubmissionId}",
            method = RequestMethod.DELETE)
    public ResponseEntity<AssignmentSubmissionResource> deleteAssignmentSubmission(
            @PathVariable Long assignmentSubmissionId) {
        AssignmentSubmission entry = service.deleteAssignmentSubmission(assignmentSubmissionId);
        if(entry != null)
        {
            AssignmentSubmissionResource res = new AssignmentSubmissionResourceAsm().toResource(entry);
            return new ResponseEntity<AssignmentSubmissionResource>(res, HttpStatus.OK);
        } else {
            return new ResponseEntity<AssignmentSubmissionResource>(HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(value="/{assignmentSubmissionId}",
            method = RequestMethod.PUT)
    public ResponseEntity<AssignmentSubmissionResource> updateAssignmentSubmission(
            @PathVariable Long assignmentSubmissionId, @RequestBody AssignmentSubmissionResource sentAssignmentSubmission) {
        AssignmentSubmission updatedEntry =
                service.updateAssignmentSubmission(assignmentSubmissionId, sentAssignmentSubmission.toAssignmentSubmission());
        if(updatedEntry != null)
        {
            AssignmentSubmissionResource res = new AssignmentSubmissionResourceAsm().toResource(updatedEntry);
            return new ResponseEntity<AssignmentSubmissionResource>(res, HttpStatus.OK);
        } else {
            return new ResponseEntity<AssignmentSubmissionResource>(HttpStatus.NOT_FOUND);
        }
    }
    
    /* 
     * An API method that follows the pattern '/rest/assignment/{assignmentId}/compare'
     * {assignmentId} - The id of the assignment submission to be compared against.
     * otherAssignmentId - The id of the assignment submission to be compared to the assignment
     * submission id with assignmentId.
     * 
     * Returns: a number from 0 to 1 that represents the percent match between the two
     * assignment submissions.
     * 
     */
    @RequestMapping(value="/compare", method = RequestMethod.GET)
    public ResponseEntity<Double> compareAssignmentSubmissions(@RequestParam Long assignmentId, @RequestParam Long otherAssignmentId) {
        double percentMatch = service.compareAssignmentSubmissions(assignmentId, otherAssignmentId);
        return new ResponseEntity<Double>(percentMatch, HttpStatus.OK);
    }
}
