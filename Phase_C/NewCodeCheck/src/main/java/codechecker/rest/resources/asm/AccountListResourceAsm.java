package codechecker.rest.resources.asm;

import org.springframework.hateoas.mvc.ResourceAssemblerSupport;
import codechecker.core.services.util.AccountList;
import codechecker.rest.mvc.AccountController;
import codechecker.rest.resources.AccountListResource;
import codechecker.rest.resources.AccountResource;

import java.util.List;

/**
 * Account List Resource Asm class
 * extends Resource Assembler Support
 */
public class AccountListResourceAsm extends ResourceAssemblerSupport<AccountList, AccountListResource> {

    /**
     * Account List Resource Asm constructor
     */
    public AccountListResourceAsm() {
        super(AccountController.class, AccountListResource.class);
    }

    /**
     * Function to get all accounts and add to final results list
     */
    @Override
    public AccountListResource toResource(AccountList accountList) {
        List<AccountResource> resList = new AccountResourceAsm().toResources(accountList.getAccounts());
        AccountListResource finalRes = new AccountListResource();
        finalRes.setAccounts(resList);
        return finalRes;
    }
}
