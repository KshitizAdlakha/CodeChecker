package codechecker.core.repositories.jpa;

import org.springframework.stereotype.Repository;
import codechecker.core.models.entities.Account;
import codechecker.core.repositories.AccountRepo;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

/**
 * Jpa Account Repo class
 */
@Repository
public class JpaAccountRepo implements AccountRepo {

    @PersistenceContext
    private EntityManager em;


    /**
     * method to get all the user created accounts from database
     */
    @Override
    public List<Account> findAllAccounts() {
        Query query = em.createQuery("SELECT a FROM Account a");
        return query.getResultList();
    }

    /**
     * method to find user account with given id from database
     */
    @Override
    public Account findAccount(Long id) {
        return em.find(Account.class, id);
    }

    /**
     * method to get user account by given name from database
     */
    @Override
    public Account findAccountByName(String name) {
        Query query = em.createQuery("SELECT a FROM Account a WHERE a.name=?1");
        query.setParameter(1, name);
        List<Account> accounts = query.getResultList();
        if(accounts.size() == 0) {
            return null;
        } else {
            return accounts.get(0);
        }
    }
    /**
     * method to insert new account into database
     */
    @Override
    public Account createAccount(Account data) {
        em.persist(data);
        return data;
    }
}
