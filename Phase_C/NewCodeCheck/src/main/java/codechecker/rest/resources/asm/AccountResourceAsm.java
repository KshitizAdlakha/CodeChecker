package codechecker.rest.resources.asm;

import org.springframework.hateoas.mvc.ResourceAssemblerSupport;
import codechecker.core.models.entities.Account;
import codechecker.rest.mvc.AccountController;
import codechecker.rest.resources.AccountResource;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.*;

/**
 * Account Resource Asm class
 * extends Resource Assembler Support
 */
public class AccountResourceAsm extends ResourceAssemblerSupport<Account, AccountResource> {
    /**
     * Account Resource Asm constructor
     */
    public AccountResourceAsm() {
        super(AccountController.class, AccountResource.class);
    }

    /**
     * Function to get all details of valid user accounts
     */
    @Override
    public AccountResource toResource(Account account) {
        AccountResource res = new AccountResource();
        res.setName(account.getName());
        res.setPassword(account.getPassword());
        res.setRid(account.getId());
        res.add(linkTo(methodOn(AccountController.class).getAccount(account.getId())).withSelfRel());
        res.add(linkTo(methodOn(AccountController.class).findAllAssignments(account.getId())).withRel("assignments"));
        return res;
    }
}
