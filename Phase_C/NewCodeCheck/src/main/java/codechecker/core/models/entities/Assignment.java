package codechecker.core.models.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

/**
 * Assignment class
 */
@Entity
public class Assignment {
    @Id
    @GeneratedValue
    private Long id;

    private String title;

    @OneToOne
    private Account owner;

    /**
     * get assignment id
     */
    public Long getId() {
        return id;
    }

    /**
     * set assignment id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * get assignment title
     */
    public String getTitle() {
        return title;
    }

    /**
     * set assignment title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * get account owner
     */
    public Account getOwner() {
        return owner;
    }

    /**
     * set account owner
     */
    public void setOwner(Account owner) {
        this.owner = owner;
    }
}
