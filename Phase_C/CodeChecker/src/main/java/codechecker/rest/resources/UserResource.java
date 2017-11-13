package codechecker.rest.resources;

import codechecker.core.entities.User;
import codechecker.core.services.UserService;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.hateoas.ResourceSupport;

public class UserResource extends ResourceSupport {
    private String name;
    private String username;
    private String password;

    @JsonIgnore
    public String getPassword() {
        return password;
    }

    @JsonProperty
    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Boolean login(String username, String password){
        return username == password;
    }

    public User toUser(){
        User u= new User();
        u.setName(name);
        u.setPassword(password);
        u.setUsername(username);
        return u;
    }
}