package codechecker.core.services.util;

import codechecker.core.models.entities.Account;

import java.util.ArrayList;
import java.util.List;

/**
 * Account List class
 */
public class AccountList {

    private List<Account> accounts = new ArrayList<Account>();

    /**
     * Account list constructor
     */
    public AccountList(List<Account> list) {
        this.accounts = list;
    }

    /**
     * get accounts method
     */
    public List<Account> getAccounts() {
        return accounts;
    }

    /**
     * set accounts method
     */
    public void setAccounts(List<Account> accounts) {
        this.accounts = accounts;
    }
}
