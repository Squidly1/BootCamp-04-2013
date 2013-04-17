package de.wombatsoftware.TweetCamp.presentation;

import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.enterprise.event.Event;
import javax.enterprise.inject.Model;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import de.wombatsoftware.TweetCamp.model.User;
import de.wombatsoftware.TweetCamp.qualifier.LoggedIn;
import de.wombatsoftware.TweetCamp.services.interceptor.binding.Performance;

@Model
@Named
@Performance
public class LoginBean implements UserHandler {
    @Inject
    private UserBeanInterface userBean;
    
    @Inject
    private Logger logger;
    
    @Inject
    @LoggedIn
    private Event<User> loginEvent;
    
    public LoginBean() {}
    
    @PostConstruct
    public void init() {
	logger.info("PostConstrut method");
    }
    
//    Constructor Injection
//    @Inject
//    public LoginBean(UserBeanInterface userBean) {
//	this.userBean = userBean;
//	
//	System.out.println("Constructor Injection");
//    }
    
//    Inject with several arguments
//    @Inject
//    public void injectAll(UserBeanInterface userBean, RegisterBean registerBean) {
//	this.userBean = userBean;
//	
//	if (registerBean != null) {
//	    System.out.println("Register not null");
//	}
//	
//	System.out.println("InjectAll method");
//    }
    
//    @Inject
//    public void injectAll2(UserBean userBean, RegisterBean registerBean) {
//	this.userBean = userBean;
//	
//	if (registerBean != null) {
//	    System.out.println("Register2 not null");
//	}
//	
//	System.out.println("InjectAll2 method");
//    }

    private String username;
    private String password;
    
    public void preLogin() {
	logger.info("Prelogin");
    }

    public String login() {
	boolean success = userBean.login(getUsername(), getPassword());

	if (success) {
	    loginEvent.fire(userBean.getCurrentUser());

	    return "home";
	}

	FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Credentials wrong!"));
	return null;
    }

//    Method Injection
//    @Inject
//    public void setUserBean(UserBeanInterface userBean) {
//        this.userBean = userBean;
//
//        logger.info("Method Injection");
//    }

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