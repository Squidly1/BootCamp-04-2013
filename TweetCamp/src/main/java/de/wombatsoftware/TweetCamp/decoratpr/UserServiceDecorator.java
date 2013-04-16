package de.wombatsoftware.TweetCamp.decoratpr;

import java.util.logging.Logger;

import javax.decorator.Decorator;
import javax.decorator.Delegate;
import javax.enterprise.inject.Any;
import javax.inject.Inject;

import de.wombatsoftware.TweetCamp.model.User;
import de.wombatsoftware.TweetCamp.services.api.UserService;

@Decorator
public abstract class UserServiceDecorator implements UserService {

    @Inject
    @Delegate
    @Any
    private UserService userService;
    
    @Inject
    private Logger logger;

    @Override
    public void save(User user) {
	User u = userService.findUserByUsername(user.getUsername());
	logger.info("Password before saving: " + (u != null ? u.getPassword() : null));
	
	userService.save(user);

	logger.info("Password after saving: " + user.getPassword());
    }
}