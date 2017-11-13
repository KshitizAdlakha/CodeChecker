package codechecker;

import codechecker.core.entities.User;
import codechecker.core.services.UserService;
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

public class UserControllerTests {
    @InjectMocks
    private UserController userController;

    @Mock
    private UserService service;

    private MockMvc mockMvc;

    @Before
    public void setup(){
        MockitoAnnotations.initMocks(this);
        mockMvc= MockMvcBuilders.standaloneSetup(userController).build();

    }

    @Test
    public void testToGetExistingUser() throws Exception{
        User user= new User();
        user.setId(1L);
        user.setName("Bob");
        user.setUsername("bob");
        user.setPassword("bob");

        when(service.findUserById(1L)).thenReturn(user);

        mockMvc.perform(get("/rest/user/1"))
                .andExpect(jsonPath("$.name", is("Bob")))
                .andExpect(jsonPath("$.links[*].href", hasItem(endsWith("/rest/user/1"))))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    public void testToGetUserThatDoesNotExist() throws Exception{
        when(service.findUserById(2L)).thenReturn(null);

        mockMvc.perform(get("/rest/user/2"))
                .andExpect(status().isNotFound())
                .andDo(print());
    }

//    @Test
//    public void testUrlResultUsingRequestMapping() throws Exception {
//        mockMvc.perform(get("/test")).andDo(print());
//    }
//
//    @Test
//    public void testUrlResultUsingResponseBody() throws Exception {
//        mockMvc.perform(get("/test2")).andDo(print());
//    }
//
//    @Test
//    public void testPostRequest() throws Exception {
//        mockMvc.perform(post("/postTest")
//                .content("{\"name\":\"test\", \"username\":\"test\", \"password\":\"test\"}")
//                .contentType(MediaType.APPLICATION_JSON))
//                .andDo(print());
//    }
//
//    @Test
//    public void testPostRequestResultUsingJsonPath() throws Exception {
//        mockMvc.perform(post("/postTest")
//                .content("{\"name\":\"test\", \"username\":\"test\", \"password\":\"test\"}")
//                .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(jsonPath("$.name", is("test")))
//                .andDo(print());
//    }


}
