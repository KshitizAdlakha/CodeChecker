package codechecker.core.repositories.jpa;

import org.springframework.stereotype.Repository;
import codechecker.core.models.entities.AssignmentSubmission;
import codechecker.core.repositories.AssignmentSubmissionRepo;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

/**
 * Jpa Assignment Submission Repo class
 * extends Assignment Submission Repo
 */
@Repository
public class JpaAssignmentSubmissionRepo implements AssignmentSubmissionRepo {
    @PersistenceContext
    private EntityManager em;

    /**
     * method to find assignment submission with given id from database
     */
    @Override
    public AssignmentSubmission findAssignmentSubmission(Long id) {
        return em.find(AssignmentSubmission.class, id);
    }

    /**
     * method to delete given assignment submission
     */
    @Override
    public AssignmentSubmission deleteAssignmentSubmission(Long id) {
        AssignmentSubmission entry = em.find(AssignmentSubmission.class, id);
        em.remove(entry);
        return entry;
    }

    /**
     * method to update assignment submission with given data to database
     */
    @Override
    public AssignmentSubmission updateAssignmentSubmission(Long id, AssignmentSubmission data) {
        AssignmentSubmission entry = em.find(AssignmentSubmission.class, id);
        entry.setTitle(data.getTitle());
        entry.setContent(data.getContent());
        return entry;
    }

    /**
     * method to create new assignment submission in database
     */
    @Override
    public AssignmentSubmission createAssignmentSubmission(AssignmentSubmission data) {
        em.persist(data);
        return data;
    }

    /**
     * method to find all assignment submission by given assignment id in database
     */
    @Override
    public List<AssignmentSubmission> findByAssignmentId(Long assignmentId) {
        Query query = em.createQuery("SELECT b FROM AssignmentSubmission b WHERE b.assignment.id=?1");
        query.setParameter(1, assignmentId);
        return query.getResultList();
    }
}
