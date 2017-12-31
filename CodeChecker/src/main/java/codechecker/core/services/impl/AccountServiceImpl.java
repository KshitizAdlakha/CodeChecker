package codechecker.core.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import codechecker.core.models.entities.Account;
import codechecker.core.models.entities.Assignment;
import codechecker.core.repositories.AccountRepo;
import codechecker.core.repositories.AssignmentRepo;
import codechecker.core.services.AccountService;
import codechecker.core.services.exceptions.AccountDoesNotExistException;
import codechecker.core.services.exceptions.AccountExistsException;
import codechecker.core.services.exceptions.AssignmentExistsException;
import codechecker.core.services.util.AccountList;
import codechecker.core.services.util.AssignmentList;

/**
 * Account Service Implementation class
 */
@Service
@Transactional
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountRepo accountRepo;

    @Autowired
    private AssignmentRepo assignmentRepo;

    @Override
    public Account findAccount(Long id) {
        return accountRepo.findAccount(id);
    }

    /**
     * @param data to create a new account
     * @return the created account with given account data
     */
    @Override
    public Account createAccount(Account data) {
        Account account = accountRepo.findAccountByName(data.getName());

        if(account != null) {
            throw new AccountExistsException();
        }
        return accountRepo.createAccount(data);
    }

    /**
     * @param accountId with which the assignment will be created
     * @param data which will be included in assignment
     * @return the created assignment with accountId and data
     */
    @Override
    public Assignment createAssignment(Long accountId, Assignment data) {
        Assignment assignmentSameTitle = assignmentRepo.findAssignmentByTitle(data.getTitle());

        if(assignmentSameTitle != null)
        {
            throw new AssignmentExistsException();
        }

        Account account = accountRepo.findAccount(accountId);
        if(account == null)
        {
            throw new AccountDoesNotExistException();
        }

        Assignment createdAssignment = assignmentRepo.createAssignment(data);

        createdAssignment.setOwner(account);

        return createdAssignment;
    }

    /**
     * @param accountId with which assignments of same accountId will be found
     * @return list of assignments with same accountId
     */
    @Override
    public AssignmentList findAssignmentsByAccount(Long accountId) {
        Account account = accountRepo.findAccount(accountId);
        if(account == null)
        {
            throw new AccountDoesNotExistException();
        }
        return new AssignmentList(assignmentRepo.findAssignmentsByAccount(accountId));
    }

    /**
     * find all accounts method
     */
    @Override
    public AccountList findAllAccounts() {
        return new AccountList(accountRepo.findAllAccounts());
    }

    /**
     * find account by given name method
     */
    @Override
    public Account findByAccountName(String name) {
        return accountRepo.findAccountByName(name);
    }
}
