package codechecker.core.services;

import codechecker.core.entities.User;

public interface UserService {
    public User findUserByUsername(String username);
    public User findUserById(Long id);
    public User findUserByUsernameAndPassword(String username, String password);
    public void createUser(User user);
}
