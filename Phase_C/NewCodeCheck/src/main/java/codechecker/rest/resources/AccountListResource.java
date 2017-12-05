package codechecker.rest.resources;

import org.springframework.hateoas.ResourceSupport;

import java.util.ArrayList;
import java.util.List;

/**
 * Assignment List Resource class
 * extends Resource Support
 */
public class AccountListResource extends ResourceSupport {
    private List<AccountResource> accounts = new ArrayList<AccountResource>();

    /**
     * Function to get all user accounts
     */
    public List<AccountResource> getAccounts() {
        return accounts;
    }

    /**
     * Function to set all user accounts
     */
    public void setAccounts(List<AccountResource> accounts) {
        this.accounts = accounts;
    }
}
