package codechecker.core.services;

import codechecker.core.models.entities.Account;
import codechecker.core.models.entities.Assignment;
import codechecker.core.services.util.AccountList;
import codechecker.core.services.util.AssignmentList;

/**
 * Account Service interface
 */
 public interface AccountService {

   /**
    * @param id of the account to be found
    * @return the found account with the given id
    */
    Account findAccount(Long id);

   /**
    * @param data of the account to be created
    * @return the created account with the given data
    */
    Account createAccount(Account data);

   /**
    * @param accountId with which the assignment will be created
    * @param assignment data which will be included in assignment
    * @return the created assignment with accountId and data
    */
    Assignment createAssignment(Long accountId, Assignment data);

   /**
    * @param accountId for which assignment is to be found
    * @return the found assignment with given accountId
    */
    AssignmentList findAssignmentsByAccount(Long accountId);

   /**
    * find all accounts method
    */
    AccountList findAllAccounts();

   /**
    * @param account name to be found
    * @return the account found by given name
    */
    Account findByAccountName(String name);
}