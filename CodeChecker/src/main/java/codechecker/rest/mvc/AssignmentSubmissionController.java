package codechecker.rest.mvc;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import codechecker.core.models.entities.AssignmentSubmission;
import codechecker.core.services.AssignmentSubmissionService;
import codechecker.rest.resources.AssignmentSubmissionResource;
import codechecker.rest.resources.asm.AssignmentSubmissionResourceAsm;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

/**
 * Assignment Submission Controller
 */
@Controller
@RequestMapping("/rest/assignment-submissions")
public class AssignmentSubmissionController {
    private AssignmentSubmissionService service;

    /**
     * Assignment Submission Controller constructor
     */
    @Autowired
    public AssignmentSubmissionController(AssignmentSubmissionService service)
    {
        this.service = service;
    }

    /**
     * Function to find all assignment submissions
     */
    @RequestMapping(value="/{assignmentSubmissionId}",
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

    /**
     * Function to upload the submitted java file
     */
    @RequestMapping(value = "/{assignmentSubmissionId}/upload", method = RequestMethod.POST)
    @PreAuthorize("permitAll")
    public ResponseEntity<AssignmentSubmissionResource> upload(
            @PathVariable Long assignmentSubmissionId,
            @RequestParam("file") MultipartFile file) throws IOException {

        byte[] bytes;

        if (!file.isEmpty()) {
            bytes = file.getBytes();
            FileUtils.writeByteArrayToFile(new File("src/main/webapp/app/app/upload/"+assignmentSubmissionId+".java"), bytes);
        }

        return new ResponseEntity<AssignmentSubmissionResource>(HttpStatus.OK);
    }

    /**
     * Function to delete an assignment submission by assignment submission id
     */
    @RequestMapping(value="/{assignmentSubmissionId}",
            method = RequestMethod.DELETE)
    public ResponseEntity<AssignmentSubmissionResource> deleteAssignmentSubmission(
            @PathVariable Long assignmentSubmissionId) {
        AssignmentSubmission entry = service.deleteAssignmentSubmission(assignmentSubmissionId);
        if(entry != null) {
            AssignmentSubmissionResource res = new AssignmentSubmissionResourceAsm().toResource(entry);
            return new ResponseEntity<AssignmentSubmissionResource>(res, HttpStatus.OK);
        } else {
            return new ResponseEntity<AssignmentSubmissionResource>(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Function to update assignment submission by given assignment submission id
     */
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
    @PreAuthorize("permitAll")
    public ResponseEntity<String> compareAssignmentSubmissions(@RequestParam Long assignmentId, @RequestParam Long otherAssignmentId) {
        String percentMatch = service.compareAssignmentSubmissions(assignmentId, otherAssignmentId);
        return new ResponseEntity<String>(percentMatch, HttpStatus.OK);
    }
}
