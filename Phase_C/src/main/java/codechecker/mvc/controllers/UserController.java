package codechecker.mvc.controllers;

import codechecker.mvc.entities.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class UserController {

//    GET Requests

    // Using ResponseEntity<> to return response
    @RequestMapping("/test")
    public ResponseEntity<Object> testGetRequest(){
        User user = new User();
        user.setName("test");
        user.setUsername("test");
        user.setPassword("test");
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    // Using @ResponseBody to return response
    @RequestMapping("/test2")
    public @ResponseBody User testGetRequestByResponseBody(){
        User user = new User();
        user.setName("test");
        user.setUsername("test");
        user.setPassword("test");
        return user;
    }


//    POST Requests

    @RequestMapping(value = "/postTest", method = RequestMethod.POST)
    public @ResponseBody User testPostRequest(@RequestBody User user){
        return user;
    }
}
