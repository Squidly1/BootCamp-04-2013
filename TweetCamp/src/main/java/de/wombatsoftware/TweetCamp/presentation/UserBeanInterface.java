package de.wombatsoftware.TweetCamp.presentation;

import javax.enterprise.context.Dependent;
import javax.enterprise.inject.Produces;
import javax.inject.Named;

import de.wombatsoftware.TweetCamp.model.User;

public interface UserBeanInterface {

    public abstract boolean login(String username, String passwort);

    public abstract void logout();

    @Produces
    @Named("currentUser")
    @Dependent
    public abstract User getCurrentUser();

    public abstract void setCurrentUser(User currentUser);

    //    DECORATOR : extract check for given username
    public abstract boolean register(String username, String password);

}