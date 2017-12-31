package codechecker.core.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import codechecker.core.models.entities.Account;
import codechecker.core.services.AccountService;

/**
 * User Detail Service Impl Class
 * implements User Details Service
 */
@Component
public class UserDetailServiceImpl implements UserDetailsService {

    @Autowired
    private AccountService service;

    /**
     * method to fetch the user by given username or throw an exception
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Account account = service.findByAccountName(username);
        if(account == null) {
            throw new UsernameNotFoundException("No user found with " + username);
        }
        return new AccountUserDetails(account);
    }
}
