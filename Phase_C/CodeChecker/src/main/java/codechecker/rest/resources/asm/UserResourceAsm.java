package codechecker.rest.resources.asm;

import codechecker.core.entities.User;
import codechecker.rest.controllers.UserController;
import codechecker.rest.resources.UserResource;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

public class UserResourceAsm extends ResourceAssemblerSupport<User, UserResource> {

    public UserResourceAsm(){
        super(UserController.class, UserResource.class);
    }

    @Override
    public UserResource toResource(User user) {
        UserResource userResource = new UserResource();
        userResource.setName(user.getName());
        userResource.setPassword(user.getPassword());
        userResource.setUsername(user.getUsername());
        Link link = linkTo(methodOn(UserController.class).getUserById(user.getId())).withSelfRel();
        userResource.add(link.withSelfRel());
        return userResource;
    }
}
