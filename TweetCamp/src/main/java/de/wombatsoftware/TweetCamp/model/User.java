package de.wombatsoftware.TweetCamp.model;

import javax.persistence.Entity;

@Entity
public class User extends AbstractEntity {
    private static final long serialVersionUID = -7502393298119267496L;

    private String password;
    private String username;

    public User() {
    }

    public User(String username, String password) {
	this.username = username;
	this.password = password;
    }

    public String getPassword() {
	return password;
    }

    public String getUsername() {
	return username;
    }

    public void setPassword(String password) {
	this.password = password;
    }

    public void setUsername(String username) {
	this.username = username;
    }
}