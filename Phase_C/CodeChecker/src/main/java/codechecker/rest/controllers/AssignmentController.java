package codechecker.rest.controllers;

import codechecker.core.entities.Assignment;
import codechecker.core.services.AssignmentService;
import codechecker.core.services.util.AssignmentList;
import codechecker.rest.resources.AssignmentListResource;
import codechecker.rest.resources.AssignmentResource;
import codechecker.rest.resources.asm.AssignmentListResourceAsm;
import codechecker.rest.resources.asm.AssignmentResourceAsm;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AssignmentController {
    private AssignmentService assignmentService;

    public AssignmentController(AssignmentService _assignmentService){
        this.assignmentService=_assignmentService;
    }

    @RequestMapping(value="/rest/user/{userId}/assignment/", method = RequestMethod.GET)
    public ResponseEntity<AssignmentListResource> findAllAssignments(@PathVariable Long userId) {
        AssignmentList assignmentList = assignmentService.findAllAssignments(userId);
        AssignmentListResource assignmentListResource = new AssignmentListResourceAsm().toResource(assignmentList);
        return new ResponseEntity<>(assignmentListResource, HttpStatus.OK);
    }

    @RequestMapping(value="/rest/user/{userId}/assignment/{assignmentId}", method = RequestMethod.GET)
    public ResponseEntity<AssignmentResource> findAssignment(@PathVariable Long userId, @PathVariable Long assignmentId) {
        Assignment assignment = assignmentService.findAssignment(userId, assignmentId);
        AssignmentResource assignmentResource = new AssignmentResourceAsm().toResource(assignment);
        return new ResponseEntity<>(assignmentResource, HttpStatus.OK);
    }
    
    @RequestMapping(value="/rest/{assignmentId}/compare", method = RequestMethod.GET)
    public ResponseEntity<Double> compareAssignmentSubmissions(@PathVariable Long assignmentId, @RequestParam Long otherAssignmentId) {
        double percentMatch = assignmentService.compareAssignmentSubmissions(assignmentId, otherAssignmentId);
        return new ResponseEntity<>(percentMatch, HttpStatus.OK);
    }
}
