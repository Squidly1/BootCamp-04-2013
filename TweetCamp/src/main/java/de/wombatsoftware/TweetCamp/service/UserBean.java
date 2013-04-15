package de.wombatsoftware.TweetCamp.service;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import de.wombatsoftware.TweetCamp.model.User;

@SessionScoped
@Named
public class UserBean implements Serializable {
    private static final long serialVersionUID = 4831057330322449512L;
    
    private User currentUser = null;
    private List<User> users;
    
    @PostConstruct
    public void init() {
	users = new ArrayList<User>();
	users.add(new User("Boot", "Camp"));
	users.add(new User("Wombat", "Software"));
    }
    
    public boolean login(String username, String passwort) {
	for(User u : users) {
	    if(u.getUsername().equals(username) && u.getPassword().equals(passwort)) {
		currentUser = u;

		return true;
	    }
	}

	return false;
    }
    
    public void logout() {
	currentUser = null;
    }

    public User getCurrentUser() {
	return currentUser;
    }

    public void setCurrentUser(User currentUser) {
	this.currentUser = currentUser;
    }

    public boolean register(String username, String password) {
	for(User u : users) {
	    if(u.getUsername().equals(username)) {
		return false;
	    }
	}

	users.add(new User(username, password));

	return true;
    }
}