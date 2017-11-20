package codechecker;

import codechecker.core.entities.User;
import codechecker.core.services.AssignmentService;
import codechecker.core.services.UserService;
import codechecker.rest.controllers.AssignmentController;
import codechecker.rest.controllers.UserController;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.hamcrest.Matchers.endsWith;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class AssignmentSubmissionControllerTests {
    @InjectMocks
    private AssignmentController assignmentSubmissionController;

    @Mock
    private AssignmentService service;

    private MockMvc mockMvc;

    @Before
    public void setup(){
        MockitoAnnotations.initMocks(this);
        mockMvc= MockMvcBuilders.standaloneSetup(assignmentSubmissionController).build();

    }

    @Test
    public void testToGetExistingUser() throws Exception{
        User user= new User();
        user.setId(1L);
        user.setName("Bob");
        user.setUsername("bob");
        user.setPassword("bob");

        //when(service.findUserById(1L)).thenReturn(user);

        mockMvc.perform(get("/rest/assignment/1/compare").param("otherAssignmentId", "3"))
                .andExpect(status().isOk())
                .andDo(print());
    }


}
