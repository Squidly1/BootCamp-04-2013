package de.wombatsoftware.TweetCamp.presentation;

import java.io.Serializable;

import javax.enterprise.context.Dependent;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.inject.Named;

import de.wombatsoftware.TweetCamp.model.User;
import de.wombatsoftware.TweetCamp.services.api.UserService;
import de.wombatsoftware.TweetCamp.stereotype.SessionModel;

@SessionModel
public class UserBean implements Serializable {
    private static final long serialVersionUID = 4831057330322449512L;
    
    @Inject
    private UserService userService;
    
    private User currentUser;
    
    public boolean login(String username, String passwort) {
	User user = userService.findUserByUsername(username);
	
	if(user != null && user.getPassword().equals(passwort)) {
	    currentUser = user;

	    return true;
	}

	return false;
    }

    public void logout() {
	currentUser = null;
    }

    @Produces
    @Named("currentUser")
    @Dependent
    public User getCurrentUser() {
	return currentUser;
    }

    public void setCurrentUser(User currentUser) {
	this.currentUser = currentUser;
    }

//    DECORATOR : extract check for given username
    public boolean register(String username, String password) {
	userService.save(new User(username, password));

	return true;
    }
}