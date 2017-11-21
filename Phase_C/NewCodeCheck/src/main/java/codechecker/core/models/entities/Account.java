package codechecker.core.models.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Account class
 */
@Entity
public class Account {
    @Id @GeneratedValue
    private Long id;
    private String name;
    private String password;

    /**
     * get account id
     */
    public Long getId() {
        return id;
    }

    /**
     * set account id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * get account name
     */
    public String getName() {
        return name;
    }

    /**
     * set account name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * get account password
     */
    public String getPassword() {
        return password;
    }

    /**
     * set account password
     */
    public void setPassword(String password) {
        this.password = password;
    }
}