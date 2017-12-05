package codechecker.core.repositories;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import codechecker.core.models.entities.Account;
import codechecker.core.models.entities.Assignment;
import codechecker.core.models.entities.AssignmentSubmission;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring/business-config.xml")
public class AccountRepoTest {

    @Autowired
    private AccountRepo repo;
    private AssignmentRepo assignmentRepo;
    private AssignmentSubmissionRepo assignmentSubmissionRepo;

    private Account account;
    private Assignment assignment;
    private AssignmentSubmission assignmentSubmission;

    /*Intial setup by creating a new account
    */
    @Before
    @Transactional
    @Rollback(false)
    public void setup() {
        account = new Account();
        assignment = new Assignment();
        assignmentSubmission = new AssignmentSubmission();

        account.setName("name");
        account.setPassword("password");
        repo.createAccount(account);

    }

    /*Checks to see if the account with a given id can be found
    */
    @Test
    @Transactional
    public void testFindAcount() {
        Account account = repo.findAccount(this.account.getId());

        assertNotNull(account);
        assertEquals(account.getName(), "name");
        assertEquals(account.getPassword(), "password");
    }
}