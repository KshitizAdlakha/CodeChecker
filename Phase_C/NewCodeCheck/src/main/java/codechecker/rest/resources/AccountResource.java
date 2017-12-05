package codechecker.rest.resources;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.hateoas.ResourceSupport;
import codechecker.core.models.entities.Account;

/**
 * Account Resource class
 */
public class AccountResource extends ResourceSupport {
    private String name;

    private String password;

    private Long rid;

    /**
     * Function to get resource id
     */
    public Long getRid() {
        return rid;
    }

    /**
     * Function to set resource id
     */
    public void setRid(Long rid) {
        this.rid = rid;
    }

    /**
     * Function to get name of account
     */
    public String getName() {
        return name;
    }

    /**
     * Function to set name of account
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Function to get password
     */
    @JsonIgnore
    public String getPassword() {
        return password;
    }

    /**
     * Function to set password
     */
    @JsonProperty
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Function to set name and password of account
     */
    public Account toAccount() {
        Account account = new Account();
        account.setName(name);
        account.setPassword(password);
        return account;
    }
}
