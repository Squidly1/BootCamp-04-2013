package de.wombatsoftware.TweetCamp.services.mock;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.enterprise.event.Observes;
import javax.enterprise.inject.Specializes;
import javax.inject.Inject;

import de.wombatsoftware.TweetCamp.model.User;
import de.wombatsoftware.TweetCamp.services.db.DBUserService;
import de.wombatsoftware.TweetCamp.stereotype.Mock;

@SessionScoped
@Specializes
@Mock
public class MockUserService extends DBUserService implements Serializable {
    private static final long serialVersionUID = 2012817739551043919L;

    @Inject
    private Logger logger;
    
    private List<User> users;

    @PostConstruct
    public void init() {
	users = new ArrayList<User>();
	users.add(new User("Daniel", "Sachse"));
	users.add(new User("Wombat", "Software"));
    }

    @Override
    public List<User> findAllUsers() {
	return users;
    }

    @Override
    public User findUserByUsername(String username) {
	for (User u : users) {
	    if (u.getUsername().equals(username)) {
		return u;
	    }
	}

	return null;
    }

    @Override
    public void save(User user) {
	users.add(user);
    }
    
    public void logUserEvent(@Observes User user) {
	logger.info("User event was catched by MockUserService!");
    }
}