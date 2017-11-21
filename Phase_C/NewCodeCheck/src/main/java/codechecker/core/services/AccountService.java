package codechecker.core.services;

import codechecker.core.models.entities.Account;
import codechecker.core.models.entities.Assignment;
import codechecker.core.services.util.AccountList;
import codechecker.core.services.util.AssignmentList;

 public interface AccountService {
    Account findAccount(Long id);
    Account createAccount(Account data);
    Assignment createAssignment(Long accountId, Assignment data);
    AssignmentList findAssignmentsByAccount(Long accountId);
    AccountList findAllAccounts();
    Account findByAccountName(String name);
}