package codechecker.core.repositories;

import codechecker.core.models.entities.Account;

import java.util.List;


public interface AccountRepo {
//    find all all the user accounts in the system, future user for Admin user
    List<Account> findAllAccounts();

//    find an account by id, return null if account does not exist
    Account findAccount(Long id);

//    find account by username, return null if account does not exist
    Account findAccountByName(String name);

//    create an account
    Account createAccount(Account data);
}