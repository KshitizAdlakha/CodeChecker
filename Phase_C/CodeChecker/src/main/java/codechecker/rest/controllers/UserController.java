package codechecker.rest.controllers;

import codechecker.core.entities.User;
import codechecker.core.services.UserService;
import codechecker.rest.resources.UserResource;
import codechecker.rest.resources.asm.UserResourceAsm;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.net.URI;

@Controller
public class UserController {
    private UserService service;

    public UserController(UserService service){
        this.service=service;
    }

//    GET Requests

    // Using ResponseEntity<> to return response
    @RequestMapping(value= "/rest/user/{userId}", method = RequestMethod.GET)
    public ResponseEntity<UserResource> getUserById(@PathVariable Long userId){
        User user = service.findUserById(userId);
        if(user==null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            UserResource userResource = new UserResourceAsm().toResource(user);
            user.setName(user.getName());
            user.setUsername(user.getUsername());
            user.setPassword(user.getPassword());
            return new ResponseEntity<>(userResource, HttpStatus.OK);
        }
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<UserResource> createAccount(
            @RequestBody UserResource sentUser
    ) {
        try {
            User createdUser = UserService.createUser(sentUser.toUser());
            UserResource res = new UserResourceAsm().toResource(createdUser);
            HttpHeaders headers = new HttpHeaders();
            headers.setLocation(URI.create(res.getLink("self").getHref()));
            return new ResponseEntity<UserResource>(res, headers, HttpStatus.CREATED);
        } catch(AccountExistsException exception) {
            throw new ConflictException(exception);
        }
    }
}
