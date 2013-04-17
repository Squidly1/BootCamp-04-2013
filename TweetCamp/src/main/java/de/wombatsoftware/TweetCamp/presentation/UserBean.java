package de.wombatsoftware.TweetCamp.presentation;

import java.io.Serializable;

import javax.enterprise.context.Dependent;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.inject.Named;

import de.wombatsoftware.TweetCamp.model.User;
import de.wombatsoftware.TweetCamp.qualifier.Dummy;
import de.wombatsoftware.TweetCamp.services.api.UserService;
import de.wombatsoftware.TweetCamp.stereotype.SessionModel;

@SessionModel
public class UserBean implements Serializable, UserBeanInterface {
    private static final long serialVersionUID = 4831057330322449512L;
    
    public static final String CURRENT_USER = "currentUser";
    
    @Inject
    private UserService userService;
    
    private User currentUser = new User();
    
    @Override
    public boolean login(String username, String passwort) {
	User user = userService.findUserByUsername(username);
	
	if(user != null && user.getPassword().equals(passwort)) {
	    currentUser = user;

	    return true;
	}

	return false;
    }

    @Override
    public void logout() {
	currentUser = null;
    }

    @Override
    @Produces
    @Named(CURRENT_USER)
    public User getCurrentUser() {
	return currentUser;
    }

    @Override
    public void setCurrentUser(User currentUser) {
	this.currentUser = currentUser;
    }

    @Override
    public boolean register(String username, String password) {
	userService.save(new User(username, password));

	return true;
    }
}