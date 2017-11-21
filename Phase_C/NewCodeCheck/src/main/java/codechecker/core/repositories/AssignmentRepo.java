package codechecker.core.repositories;

import codechecker.core.models.entities.Assignment;

import java.util.List;

public interface AssignmentRepo {
    public Assignment createAssignment(Assignment data);
    public List<Assignment> findAllAssignments();
    public Assignment findAssignment(Long id);
    public Assignment findAssignmentByTitle(String title);
    public List<Assignment> findAssignmentsByAccount(Long accountId);
}