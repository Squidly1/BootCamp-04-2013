package de.wombatsoftware.TweetCamp.presentation;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import de.wombatsoftware.TweetCamp.service.UserBean;

@RequestScoped
@Named
public class RegisterBean {
    @Inject
    private UserBean userBean;

    private String username;
    private String password;
    
    public String register() {
	boolean success = userBean.register(getUsername(), getPassword());

	if (success) {
	    return "home";
	}

	return null;
    }

    public String getUsername() {
	return username;
    }

    public void setUsername(String username) {
	this.username = username;
    }

    public String getPassword() {
	return password;
    }

    public void setPassword(String password) {
	this.password = password;
    }
}