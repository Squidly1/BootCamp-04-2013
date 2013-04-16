package de.wombatsoftware.TweetCamp.model;

import java.io.Serializable;
import java.util.Date;

public class Tweet implements Serializable {
    private static final long serialVersionUID = -7502393298119267496L;
    
    public Tweet(){}

    public Tweet(User user, String message, Date creationTimestamp) {
	super();
	this.user = user;
	this.message = message;
	this.creationTimestamp = creationTimestamp;
    }

    private User user;
    private String message;
    private Date creationTimestamp;

    public User getUser() {
	return user;
    }

    public void setUser(User user) {
	this.user = user;
    }

    public String getMessage() {
	return message;
    }

    public void setMessage(String message) {
	this.message = message;
    }

    public Date getCreationTimestamp() {
	return creationTimestamp;
    }

    public void setCreationTimestamp(Date creationTimestamp) {
	this.creationTimestamp = creationTimestamp;
    }
}