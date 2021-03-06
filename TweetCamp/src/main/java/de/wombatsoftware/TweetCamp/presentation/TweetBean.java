package de.wombatsoftware.TweetCamp.presentation;

import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.enterprise.inject.Model;
import javax.inject.Inject;
import javax.inject.Named;

import de.wombatsoftware.TweetCamp.model.Tweet;
import de.wombatsoftware.TweetCamp.model.User;
import de.wombatsoftware.TweetCamp.services.api.TweetService;

@Model
public class TweetBean {
    @Inject
    private TweetService tweetService;
    
    @Inject
    private Logger logger;

    @Inject
    @Named(UserBean.CURRENT_USER)
    private User currentUser;

    private List<Tweet> tweets;

    private String message;

    public String getMessage() {
	return message;
    }

    public void setMessage(String message) {
	this.message = message;
    }

    @PostConstruct
    public void init() {
	tweets = tweetService.findAll();
    }
    
    @PreDestroy
    public void destroy() {
	logger.info("PreDestroy im TweetBean");
    }

    public String tweet() {
	tweetService.save(new Tweet(currentUser, message, new Date()));

	init();
	message = null;

	return null;
    }

    public List<Tweet> getTweets() {
	return tweets;
    }
}