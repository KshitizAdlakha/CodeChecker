package codechecker.core.repositories;

import codechecker.core.models.entities.Assignment;

import java.util.List;

public interface AssignmentRepo {
//    create an assignment
    Assignment createAssignment(Assignment data);

//    find all the assignments related to all the accounts
    List<Assignment> findAllAssignments();

//    find a specific assignment by its id
    Assignment findAssignment(Long id);

//    find a specific assignment by its title
    Assignment findAssignmentByTitle(String title);

//    find a specific assignments related to a specific account
    List<Assignment> findAssignmentsByAccount(Long accountId);
}