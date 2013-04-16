package de.wombatsoftware.TweetCamp.presentation;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.enterprise.inject.Instance;
import javax.inject.Inject;
import javax.inject.Named;

import de.wombatsoftware.TweetCamp.model.User;

@Named
@RequestScoped
public class Playground implements Serializable {
    private static final long serialVersionUID = 2037661450937617810L;

    @Inject
    private UserHandler userHandler;
    
    @Inject
    private Instance<UserHandler> userHandlers;
    
    @Inject
    @Named("currentUser")
    private User user;

    public UserHandler getUserHandler() {
	return userHandler;
    }

    public void setUserHandler(UserHandler userHandler) {
	this.userHandler = userHandler;
    }

    public List<UserHandler> getUserHandlers() {
	List<UserHandler> handlers = new ArrayList<UserHandler>();
	Iterator<UserHandler> i = userHandlers.iterator();
	while(i.hasNext()) {
	    handlers.add(i.next());
	}

	return handlers;
    }

    public void setUserHandlers(Instance<UserHandler> userHandlers) {
	this.userHandlers = userHandlers;
    }

    public User getUser() {
	return user;
    }

    public void setUser(User user) {
	this.user = user;
    }
}