package de.wombatsoftware.TweetCamp.presentation;

public interface UserHandler {
    public String getUsername();
    public String getPassword();

    public void setUsername(String username);
    public void setPassword(String password);
    
    public void preLogin();
    public String login();
}