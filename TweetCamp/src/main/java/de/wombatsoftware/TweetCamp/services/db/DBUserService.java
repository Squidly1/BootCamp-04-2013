package de.wombatsoftware.TweetCamp.services.db;

import java.util.List;
import java.util.logging.Logger;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.event.Observes;
import javax.inject.Inject;

import de.wombatsoftware.TweetCamp.model.User;
import de.wombatsoftware.TweetCamp.services.api.UserService;

@RequestScoped
public class DBUserService implements UserService {
    @Inject
    private Logger logger;

    @Override
    public List<User> findAllUsers() {
	// TODO Auto-generated method stub
	return null;
    }

    @Override
    public User findUserByUsername(String username) {
	// TODO Auto-generated method stub
	return null;
    }

    @Override
    public void save(User user) {
//	em.merge(user);
    }
    
    public void logUserEvent(@Observes User user) {
	logger.info("User event was catched by DBUserService!");
    }
}