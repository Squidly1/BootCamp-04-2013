package de.wombatsoftware.TweetCamp.services.api;

import java.util.List;

import de.wombatsoftware.TweetCamp.model.User;


public interface UserService {
    public List<User> findAllUsers();
    public User findUserByUsername(final String username);
    public void save(User user);
}
