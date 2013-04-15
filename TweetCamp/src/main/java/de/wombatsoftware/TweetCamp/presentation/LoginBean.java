package de.wombatsoftware.TweetCamp.presentation;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import de.wombatsoftware.TweetCamp.service.UserBean;

@RequestScoped
@Named
public class LoginBean implements UserHandler {
    @Inject
    private UserBean userBean;

    private String username;
    private String password;
    
    public void preLogin() {
	System.out.println("Prelogin");
    }

    public String login() {
	System.out.println("Login");
	boolean success = userBean.login(getUsername(), getPassword());

	if (success) {
	    return "home";
	}

	FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Credentials wrong!"));
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