package de.wombatsoftware.TweetCamp.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
public class Tweet extends AbstractEntity {
    private static final long serialVersionUID = -7502393298119267496L;

    private String message;
    
    @ManyToOne
    private User user;

    public Tweet() {
    }

    public Tweet(User user, String message, Date creationTimestamp) {
	this.user = user;
	this.message = message;
	setCreateTimestamp(creationTimestamp);
    }

    public String getMessage() {
	return message;
    }

    public User getUser() {
	return user;
    }

    public void setMessage(String message) {
	this.message = message;
    }

    public void setUser(User user) {
	this.user = user;
    }
}