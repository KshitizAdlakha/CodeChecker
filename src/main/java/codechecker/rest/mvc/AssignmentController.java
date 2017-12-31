package codechecker.rest.mvc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import codechecker.core.models.entities.Assignment;
import codechecker.core.models.entities.AssignmentSubmission;
import codechecker.core.services.util.AssignmentSubmissionList;
import codechecker.core.services.AssignmentService;
import codechecker.core.services.exceptions.AssignmentNotFoundException;
import codechecker.core.services.util.AssignmentList;
import codechecker.rest.exceptions.NotFoundException;
import codechecker.rest.resources.AssignmentSubmissionListResource;
import codechecker.rest.resources.AssignmentSubmissionResource;
import codechecker.rest.resources.AssignmentListResource;
import codechecker.rest.resources.AssignmentResource;
import codechecker.rest.resources.asm.AssignmentSubmissionListResourceAsm;
import codechecker.rest.resources.asm.AssignmentSubmissionResourceAsm;
import codechecker.rest.resources.asm.AssignmentListResourceAsm;
import codechecker.rest.resources.asm.AssignmentResourceAsm;

import java.net.URI;


/**
 * Assignment Controller class
 */
@Controller
@RequestMapping("/rest/assignments")
public class AssignmentController {
    private AssignmentService assignmentService;

    /**
     * Assignment Controller constructor
     */
    @Autowired
    public AssignmentController(AssignmentService assignmentService) {
        this.assignmentService = assignmentService;
    }

    /**
     * Function to find all assignments for user
     */
    @RequestMapping(method = RequestMethod.GET)
    @PreAuthorize("permitAll")
    public ResponseEntity<AssignmentListResource> findAllAssignments() {
        AssignmentList assignmentList = assignmentService.findAllAssignments();
        AssignmentListResource assignmentListResource = new AssignmentListResourceAsm().toResource(assignmentList);
        return new ResponseEntity<AssignmentListResource>(assignmentListResource, HttpStatus.OK);
    }

    /**
     * Function to find all assignments for assignment id
     */
    @RequestMapping(value="/{assignmentId}",
        method = RequestMethod.GET)
    public ResponseEntity<AssignmentResource> getAssignment(@PathVariable Long assignmentId) {
        Assignment assignment = assignmentService.findAssignment(assignmentId);
        if(assignment != null) {
            AssignmentResource res = new AssignmentResourceAsm().toResource(assignment);
            return new ResponseEntity<AssignmentResource>(res, HttpStatus.OK);
        } else {
            return new ResponseEntity<AssignmentResource>(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Function to find assignments by name
     */
    @RequestMapping(value="/name/{assignmentName}",
            method = RequestMethod.GET)
    @PreAuthorize("permitAll")
    public ResponseEntity<AssignmentResource> findAssignmentByName(@PathVariable String assignmentName) {
        Assignment assignment = assignmentService.findAssignmentByAssignmentName(assignmentName);
        if(assignment != null) {
            AssignmentResource res = new AssignmentResourceAsm().toResource(assignment);
            return new ResponseEntity<AssignmentResource>(res, HttpStatus.OK);
        } else {
            return new ResponseEntity<AssignmentResource>(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Function to create assignment submissions
     */
    @RequestMapping(value="/{assignmentId}/assignment-submissions",
            method = RequestMethod.POST)
    @PreAuthorize("permitAll")
    public ResponseEntity<AssignmentSubmissionResource> createAssignmentSubmission(
            @PathVariable Long assignmentId,
            @RequestBody AssignmentSubmissionResource sentAssignmentSubmission) {
        AssignmentSubmission createdAssignmentSubmission = null;
        try {
            createdAssignmentSubmission =
                    assignmentService.createAssignmentSubmission(assignmentId, sentAssignmentSubmission.toAssignmentSubmission());
            AssignmentSubmissionResource createdResource =
                    new AssignmentSubmissionResourceAsm().toResource(createdAssignmentSubmission);
            HttpHeaders headers = new HttpHeaders();
            headers.setLocation(URI.create(createdResource.getLink("self").getHref()));
            return new ResponseEntity<AssignmentSubmissionResource>(createdResource, headers, HttpStatus.CREATED);
        } catch (AssignmentNotFoundException e) {
            throw new NotFoundException(e);
        }
    }

    /**
     * Function to find all assignment submissions
     */
    @RequestMapping(value="/{assignmentId}/assignment-submissions")
    public ResponseEntity<AssignmentSubmissionListResource> findAllAssignmentSubmissions(
            @PathVariable Long assignmentId) {
        try {
            AssignmentSubmissionList list = assignmentService.findAllAssignmentSubmissions(assignmentId);
            AssignmentSubmissionListResource res = new AssignmentSubmissionListResourceAsm().toResource(list);
            return new ResponseEntity<AssignmentSubmissionListResource>(res, HttpStatus.OK);
        } catch(AssignmentNotFoundException exception)
        {
            throw new NotFoundException(exception);
        }
    }

}
