package de.wombatsoftware.TweetCamp.logging;

import java.util.logging.Logger;

import javax.enterprise.event.Observes;
import javax.inject.Inject;

import de.wombatsoftware.TweetCamp.model.User;
import de.wombatsoftware.TweetCamp.qualifier.LoggedIn;

public class EventLogger {
    @Inject
    private Logger logger;

    public void logLoginEvent(@Observes @LoggedIn User user) {
	logger.info("User: " + user.getUsername() + " successfully logged in!");
    }
    
    public void logUserEvent(@Observes User user) {
	logger.info("User event was catched!");
    }
}