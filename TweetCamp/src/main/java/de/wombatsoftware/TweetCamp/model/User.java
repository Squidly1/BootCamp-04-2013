package de.wombatsoftware.TweetCamp.model;

import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

@Entity
@NamedQueries({
    @NamedQuery(name = User.findAll, query="SELECT u FROM User u"),
    @NamedQuery(name = User.findUserByUsername, query="SELECT u FROM User u WHERE u.username = :username")
})
public class User extends AbstractEntity {
    private static final long serialVersionUID = -7502393298119267496L;
    
    public static final String findAll = "User.findAll";
    public static final String findUserByUsername = "User.findUserByUsername";

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