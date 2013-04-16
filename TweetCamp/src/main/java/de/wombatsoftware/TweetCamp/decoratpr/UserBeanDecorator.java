package de.wombatsoftware.TweetCamp.decoratpr;

import java.io.Serializable;

import javax.decorator.Decorator;
import javax.decorator.Delegate;
import javax.enterprise.inject.Any;
import javax.inject.Inject;

import de.wombatsoftware.TweetCamp.model.User;
import de.wombatsoftware.TweetCamp.presentation.UserBeanInterface;
import de.wombatsoftware.TweetCamp.services.api.UserService;

@Decorator
public abstract class UserBeanDecorator implements UserBeanInterface, Serializable {
    private static final long serialVersionUID = -5294693093568272426L;

    @Inject
    @Delegate
    @Any
    private UserBeanInterface userBean;

    @Inject
    private UserService userService;

    @Override
    public boolean register(String username, String password) {
	User u = userService.findUserByUsername(username);

	if (u == null) {
	    return userBean.register(username, password);
	}

	return false;
    }
}