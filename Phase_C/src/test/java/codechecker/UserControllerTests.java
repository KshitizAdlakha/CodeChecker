package codechecker;

import codechecker.mvc.controllers.UserController;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class UserControllerTests {
    @InjectMocks
    private UserController userController;

    private MockMvc mockMvc;

    @Before
    public void setup(){
        MockitoAnnotations.initMocks(this);
        mockMvc= MockMvcBuilders.standaloneSetup(userController).build();

    }

    @Test
    public void testUrlResultUsingRequestMapping() throws Exception {
        mockMvc.perform(get("/test")).andDo(print());
    }

    @Test
    public void testUrlResultUsingResponseBody() throws Exception {
        mockMvc.perform(get("/test2")).andDo(print());
    }

    @Test
    public void testPostRequest() throws Exception {
        mockMvc.perform(post("/postTest")
                .content("{\"name\":\"test\", \"username\":\"test\", \"password\":\"test\"}")
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print());
    }

    @Test
    public void testPostRequestResultUsingJsonPath() throws Exception {
        mockMvc.perform(post("/postTest")
                .content("{\"name\":\"test\", \"username\":\"test\", \"password\":\"test\"}")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.name", is("test")))
                .andDo(print());
    }
}
