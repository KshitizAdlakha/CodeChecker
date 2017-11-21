package codechecker.core.repositories;

import codechecker.core.models.entities.Account;

import java.util.List;


public interface AccountRepo {
    List<Account> findAllAccounts();
    Account findAccount(Long id);
    Account findAccountByName(String name);
    Account createAccount(Account data);
}