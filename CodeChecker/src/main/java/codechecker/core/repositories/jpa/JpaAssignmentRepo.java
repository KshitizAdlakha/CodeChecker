package codechecker.core.repositories.jpa;

import org.springframework.stereotype.Repository;
import codechecker.core.models.entities.Assignment;
import codechecker.core.repositories.AssignmentRepo;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

/**
 * Jpa Assignment Repo class
 * extends Assignment Repo
 */
@Repository
public class JpaAssignmentRepo implements AssignmentRepo {
    @PersistenceContext
    private EntityManager em;

    /**
     * method to create new assignment with given data
     * from database
     */
    @Override
    public Assignment createAssignment(Assignment data) {
        em.persist(data);
        return data;
    }

    /**
     * method to find all assignments from database
     */
    @Override
    public List<Assignment> findAllAssignments() {
        Query query = em.createQuery("SELECT b from Assignment b");
        return query.getResultList();
    }

    /**
     * method to find assignments in database
     */
    @Override
    public Assignment findAssignment(Long id) {
        return em.find(Assignment.class, id);
    }

    /**
     * method to find assignments by title in database
     */
    @Override
    public Assignment findAssignmentByTitle(String title) {
        Query query = em.createQuery("SELECT b from Assignment b where b.title=?1");
        query.setParameter(1, title);
        List<Assignment> assignments = query.getResultList();
        if(assignments.isEmpty()) {
            return null;
        } else {
            return assignments.get(0);
        }
    }

    /**
     * method to find assignments by account from database
     */
    @Override
    public List<Assignment> findAssignmentsByAccount(Long accountId) {
        Query query = em.createQuery("SELECT b from Assignment b where b.owner.id=?1");
        query.setParameter(1, accountId);
        return query.getResultList();
    }
}
