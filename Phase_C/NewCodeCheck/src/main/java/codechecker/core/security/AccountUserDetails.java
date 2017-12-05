package codechecker.core.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import codechecker.core.models.entities.Account;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Account User Details class
 * implements User Details
 */
public class AccountUserDetails implements UserDetails {
    private final Account account;

    /**
     * method to set User Details
     */
    public AccountUserDetails(Account account) {
        this.account = account;
    }

    /**
     * method to add authorities to list
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        GrantedAuthority authority = new GrantedAuthority() {
            @Override
            public String getAuthority() {
                return "USER";
            }
        };

        ArrayList<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
        authorities.add(authority);
        return authorities;
    }

    /**
     * method to get password from user account
     */
    @Override
    public String getPassword() {
        return account.getPassword();
    }

    /**
     * method to get username from user account
     */
    @Override
    public String getUsername() {
        return account.getName();
    }

    /**
     * method to check if account is not expired
     */
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    /**
     * method to check if account is not locked
     */
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    /**
     * method to check if credentials is not expired
     */
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    /**
     * method to check if account is enabled
     */
    @Override
    public boolean isEnabled() {
        return true;
    }
}
