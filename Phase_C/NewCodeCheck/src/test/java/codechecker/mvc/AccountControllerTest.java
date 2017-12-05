package codechecker.mvc;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import codechecker.core.models.entities.Account;
import codechecker.core.models.entities.Assignment;
import codechecker.core.services.AccountService;
import codechecker.core.services.exceptions.AccountDoesNotExistException;
import codechecker.core.services.exceptions.AccountExistsException;
import codechecker.core.services.exceptions.AssignmentExistsException;
import codechecker.core.services.util.AccountList;
import codechecker.core.services.util.AssignmentList;
import codechecker.rest.mvc.AccountController;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/*Account Controller Test class
    */
public class AccountControllerTest {
    @InjectMocks
    private AccountController controller;

    @Mock
    private AccountService service;

    private MockMvc mockMvc;

    private ArgumentCaptor<Account> accountCaptor;

    /*Initial setup of mocks and accounts
    */
    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);

        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();

        accountCaptor = ArgumentCaptor.forClass(Account.class);
    }

    /*Test to find all assignments for account
    */
    @Test
    public void findAllAssignmentsForAccount() throws Exception {
        List<Assignment> list = new ArrayList<Assignment>();

        Assignment assignmentA = new Assignment();
        assignmentA.setId(1L);
        assignmentA.setTitle("Title A");
        list.add(assignmentA);

        Assignment assignmentB = new Assignment();
        assignmentB.setId(2L);
        assignmentB.setTitle("Title B");
        list.add(assignmentB);

        AssignmentList assignmentList = new AssignmentList(list);

        when(service.findAssignmentsByAccount(1L)).thenReturn(assignmentList);

        mockMvc.perform(get("/rest/accounts/1/assignments"))
                .andExpect(jsonPath("$.assignments[*].title",
                        hasItems(endsWith("Title A"), endsWith("Title B"))))
                .andExpect(status().isOk());
    }

    /*Test to find all assignments for non-existing account
    */
    @Test
    public void findAllAssignmentsForNonExistingAccount() throws Exception {
        List<Assignment> list = new ArrayList<Assignment>();

        Assignment assignmentA = new Assignment();
        assignmentA.setId(1L);
        assignmentA.setTitle("Title A");
        list.add(assignmentA);

        Assignment assignmentB = new Assignment();
        assignmentB.setId(2L);
        assignmentB.setTitle("Title B");
        list.add(assignmentB);

        AssignmentList assignmentList = new AssignmentList(list);

        when(service.findAssignmentsByAccount(1L)).thenThrow(new AccountDoesNotExistException());

        mockMvc.perform(get("/rest/accounts/1/assignments"))
                .andExpect(status().isNotFound());
    }

    /*Test to create account for non existing username
    */
    @Test
    public void createAccountNonExistingUsername() throws Exception {
        Account createdAccount = new Account();
        createdAccount.setId(1L);
        createdAccount.setPassword("test");
        createdAccount.setName("test");

        when(service.createAccount(any(Account.class))).thenReturn(createdAccount);

        mockMvc.perform(post("/rest/accounts")
                .content("{\"name\":\"test\",\"password\":\"test\"}")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(header().string("Location", endsWith("/rest/accounts/1")))
                .andExpect(jsonPath("$.name", is(createdAccount.getName())))
                .andExpect(status().isCreated());

        verify(service).createAccount(accountCaptor.capture());

        String password = accountCaptor.getValue().getPassword();
        assertEquals("test", password);
    }

    /*Test to create account for existing username
    */
    @Test
    public void createAccountExistingUsername() throws Exception {
        Account createdAccount = new Account();
        createdAccount.setId(1L);
        createdAccount.setPassword("test");
        createdAccount.setName("test");

        when(service.createAccount(any(Account.class))).thenThrow(new AccountExistsException());

        mockMvc.perform(post("/rest/accounts")
                .content("{\"name\":\"test\",\"password\":\"test\"}")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isConflict());
    }

    /*Test to get account for given username
    */
    @Test
    public void getExistingAccount() throws Exception {
        Account foundAccount = new Account();
        foundAccount.setId(1L);
        foundAccount.setPassword("test");
        foundAccount.setName("test");

        when(service.findAccount(1L)).thenReturn(foundAccount);

        mockMvc.perform(get("/rest/accounts/1"))
                .andDo(print())
                .andExpect(jsonPath("$.password", is(nullValue())))
                .andExpect(jsonPath("$.name", is(foundAccount.getName())))
                .andExpect(jsonPath("$.links[*].rel",
                        hasItems(endsWith("self"), endsWith("assignments"))))
                        .andExpect(status().isOk());
    }

    /*Test to get non-existing account for username
    */
    @Test
    public void getNonExistingAccount() throws Exception {
        when(service.findAccount(1L)).thenReturn(null);

        mockMvc.perform(get("/rest/accounts/1"))
                .andExpect(status().isNotFound());
    }

    /*Test to find all accounts
    */
    @Test
    public void findAllAccounts() throws Exception {
        List<Account> accounts = new ArrayList<Account>();

        Account accountA = new Account();
        accountA.setId(1L);
        accountA.setPassword("accountA");
        accountA.setName("accountA");
        accounts.add(accountA);

        Account accountB = new Account();
        accountB.setId(1L);
        accountB.setPassword("accountB");
        accountB.setName("accountB");
        accounts.add(accountB);

        AccountList accountList = new AccountList(accounts);

        when(service.findAllAccounts()).thenReturn(accountList);

        mockMvc.perform(get("/rest/accounts"))
                .andExpect(jsonPath("$.accounts[*].name",
                        hasItems(endsWith("accountA"), endsWith("accountB"))))
                .andExpect(status().isOk());
    }

    /*Test to find all accounts by name
        */
    @Test
    public void findAccountsByName() throws Exception {
        List<Account> accounts = new ArrayList<Account>();

        Account accountA = new Account();
        accountA.setId(1L);
        accountA.setPassword("accountA");
        accountA.setName("accountA");
        accounts.add(accountA);

        Account accountB = new Account();
        accountB.setId(1L);
        accountB.setPassword("accountB");
        accountB.setName("accountB");
        accounts.add(accountB);

        AccountList accountList = new AccountList(accounts);

        when(service.findAllAccounts()).thenReturn(accountList);

        mockMvc.perform(get("/rest/accounts").param("name", "accountA"))
                .andExpect(jsonPath("$.accounts[*].name",
                        everyItem(endsWith("accountA"))))
                .andExpect(status().isOk());
    }
}
