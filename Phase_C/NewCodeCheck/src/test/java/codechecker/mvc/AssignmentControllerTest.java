package codechecker.mvc;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import codechecker.core.models.entities.Account;
import codechecker.core.models.entities.Assignment;
import codechecker.core.models.entities.AssignmentSubmission;
import codechecker.core.services.util.AssignmentSubmissionList;
import codechecker.core.services.AssignmentService;
import codechecker.core.services.exceptions.AssignmentNotFoundException;
import codechecker.core.services.util.AssignmentList;
import codechecker.rest.mvc.AssignmentController;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class AssignmentControllerTest {
    @InjectMocks
    private AssignmentController controller;

    @Mock
    private AssignmentService assignmentService;

    private MockMvc mockMvc;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);

        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    public void findAllAssignments() throws Exception {
        List<Assignment> list = new ArrayList<Assignment>();

        Assignment assignmentA = new Assignment();
        assignmentA.setId(1L);
        assignmentA.setTitle("Title A");
        list.add(assignmentA);

        Assignment assignmentB = new Assignment();
        assignmentB.setId(2L);
        assignmentB.setTitle("Title B");
        list.add(assignmentB);

        AssignmentList allAssignments = new AssignmentList(list);

        when(assignmentService.findAllAssignments()).thenReturn(allAssignments);

        mockMvc.perform(get("/rest/assignments"))
                .andExpect(jsonPath("$.assignments[*].title",
                        hasItems(endsWith("Title A"), endsWith("Title B"))))
                .andExpect(status().isOk());
    }

    @Test
    public void getAssignment() throws Exception {
        Assignment assignment = new Assignment();
        assignment.setTitle("Test Title");
        assignment.setId(1L);

        Account account = new Account();
        account.setId(1L);
        assignment.setOwner(account);

        when(assignmentService.findAssignment(1L)).thenReturn(assignment);

        mockMvc.perform(get("/rest/assignments/1"))
                .andExpect(jsonPath("$.links[*].href",
                        hasItem(endsWith("/assignments/1"))))
                .andExpect(jsonPath("$.links[*].href",
                        hasItem(endsWith("/assignments/1/assignment-submissions"))))
                .andExpect(jsonPath("$.links[*].href",
                        hasItem(endsWith("/accounts/1"))))
                .andExpect(jsonPath("$.links[*].rel",
                        hasItems(is("self"), is("owner"), is("entries"))))
                .andExpect(jsonPath("$.title", is("Test Title")))
                .andExpect(status().isOk());
    }

    @Test
    public void getNonExistingAssignment() throws Exception {
        when(assignmentService.findAssignment(1L)).thenReturn(null);

        mockMvc.perform(get("/rest/assignments/1"))
                .andExpect(status().isNotFound());
    }

    @Test
    public void createAssignmentEntryExistingAssignment() throws Exception {
        Assignment assignment = new Assignment();
        assignment.setId(1L);

        AssignmentSubmission entry = new AssignmentSubmission();
        entry.setTitle("Test Title");
        entry.setId(1L);

        when(assignmentService.createAssignmentSubmission(eq(1L), any(AssignmentSubmission.class))).thenReturn(entry);

        mockMvc.perform(post("/rest/assignments/1/assignment-submissions")
                .content("{\"title\":\"Generic Title\"}")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.title", is(entry.getTitle())))
                .andExpect(jsonPath("$.links[*].href", hasItem(endsWith("rest/assignment-submissions/1"))))
                .andExpect(header().string("Location", endsWith("rest/assignment-submissions/1")))
                .andExpect(status().isCreated());
    }


    @Test
    public void createAssignmentEntryNonExistingAssignment() throws Exception {
        when(assignmentService.createAssignmentSubmission(eq(1L), any(AssignmentSubmission.class)))
                .thenThrow(new AssignmentNotFoundException());

        mockMvc.perform(post("/rest/assignments/1/assignments-submissions")
                .content("{\"title\":\"Generic Title\"}")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    public void listAssignmentSubmissionsForExistingAssignment() throws Exception {

        AssignmentSubmission entryA = new AssignmentSubmission();
        entryA.setId(1L);
        entryA.setTitle("Test Title");

        AssignmentSubmission entryB = new AssignmentSubmission();
        entryB.setId(2L);
        entryB.setTitle("Test Title");

        List<AssignmentSubmission> assignmentListings = new ArrayList();
        assignmentListings.add(entryA);
        assignmentListings.add(entryB);

        AssignmentSubmissionList list = new AssignmentSubmissionList(1L, assignmentListings);

        when(assignmentService.findAllAssignmentSubmissions(1L)).thenReturn(list);

        mockMvc.perform(get("/rest/assignments/1/assignment-submissions"))
                .andDo(print())
                .andExpect(jsonPath("$.links[*].href", hasItem(endsWith("/assignments/1/assignment-submissions"))))
                .andExpect(jsonPath("$.entries[*].title", hasItem(is("Test Title"))))
                .andExpect(status().isOk());
    }

    @Test
    public void listAssignmentSubmissionsForNonExistingAssignment() throws Exception {
        when(assignmentService.findAllAssignmentSubmissions(1L)).thenThrow(new AssignmentNotFoundException());

        mockMvc.perform(get("/rest/assignments/1/assignment-submissions"))
                .andDo(print())
                .andExpect(status().isNotFound());
    }
}
