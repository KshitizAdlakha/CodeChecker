package codechecker.mvc;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import codechecker.core.models.entities.Assignment;
import codechecker.core.models.entities.AssignmentSubmission;
import codechecker.core.services.AssignmentSubmissionService;
import codechecker.rest.mvc.AssignmentSubmissionController;

import static org.hamcrest.Matchers.*;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/*Assignment Submission Controller Test class
    */
public class AssignmentSubmissionControllerTest {
    @InjectMocks
    private AssignmentSubmissionController controller;

    @Mock
    private AssignmentSubmissionService service;

    private MockMvc mockMvc;

    /*Initial setup of mocks
        */
    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);

        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    /*Test to get existing assignment submission
    */
    @Test
    public void getExistingAssignmentSubmission() throws Exception {
        AssignmentSubmission entry = new AssignmentSubmission();
        entry.setId(1L);
        entry.setTitle("Test Title");

        Assignment assignment = new Assignment();
        assignment.setId(1L);

        entry.setAssignment(assignment);

        when(service.findAssignmentSubmission(1L)).thenReturn(entry);

        mockMvc.perform(get("/rest/assignment-submissions/1"))
                .andExpect(jsonPath("$.title", is(entry.getTitle())))
                .andExpect(jsonPath("$.links[*].href",
                        hasItems(endsWith("/assignments/1"), endsWith("/assignment-submissions/1"))))
                .andExpect(jsonPath("$.links[*].rel",
                        hasItems(is("self"), is("assignment"))))
                .andExpect(status().isOk());
    }

    /*Test to get non existing assignment submission
    */
    @Test
    public void getNonExistingAssignmentSubmission() throws Exception {
        when(service.findAssignmentSubmission(1L)).thenReturn(null);

        mockMvc.perform(get("/rest/assignment-submissions/1"))
           .andExpect(status().isNotFound());
    }

    /*Test to delete existing assignment submission
        */
    @Test
    public void deleteExistingAssignmentSubmission() throws Exception {
        AssignmentSubmission deletedAssignmentSubmission = new AssignmentSubmission();
        deletedAssignmentSubmission.setId(1L);
        deletedAssignmentSubmission.setTitle("Test Title");

        when(service.deleteAssignmentSubmission(1L)).thenReturn(deletedAssignmentSubmission);

        mockMvc.perform(delete("/rest/assignment-submissions/1"))
                .andExpect(jsonPath("$.title", is(deletedAssignmentSubmission.getTitle())))
                .andExpect(jsonPath("$.links[*].href", hasItem(endsWith("/assignment-submissions/1"))))
                .andExpect(status().isOk());
    }

    /*Test to delete non existing assignment submission
        */
    @Test
    public void deleteNonExistingAssignmentSubmission() throws Exception {
        when(service.deleteAssignmentSubmission(1L)).thenReturn(null);

        mockMvc.perform(delete("/rest/assignment-submissions/1"))
                .andExpect(status().isNotFound());
    }

    /*Test to update existing assignment submission
    */
    @Test
    public void updateExistingAssignmentSubmission() throws Exception {
        AssignmentSubmission updatedEntry = new AssignmentSubmission();
        updatedEntry.setId(1L);
        updatedEntry.setTitle("Test Title");

        when(service.updateAssignmentSubmission(eq(1L), any(AssignmentSubmission.class)))
                .thenReturn(updatedEntry);

        mockMvc.perform(put("/rest/assignment-submissions/1")
                .content("{\"title\":\"Test Title\"}")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.title", is(updatedEntry.getTitle())))
                .andExpect(jsonPath("$.links[*].href", hasItem(endsWith("/assignment-submissions/1"))))
                .andExpect(status().isOk());
    }

    /*Test to update non existing assignment submission
    */
    @Test
    public void updateNonExistingAssignmentSubmission() throws Exception {
        when(service.updateAssignmentSubmission(eq(1L), any(AssignmentSubmission.class)))
                .thenReturn(null);

        mockMvc.perform(put("/rest/assignment-submissions/1")
                .content("{\"title\":\"Test Title\"}")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    /*Test to compare existing assignment submission
    */
    @Test
    public void testCompareAssignmentSubmissions() throws Exception{

        mockMvc.perform(get("/rest/assignment-submissions/compare")
        		.param("assignmentId", "1")
        		.param("otherAssignmentId", "3"))
                .andExpect(status().isOk())
                .andDo(print());
    }
}
