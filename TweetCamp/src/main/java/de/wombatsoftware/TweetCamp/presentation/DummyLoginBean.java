package de.wombatsoftware.TweetCamp.presentation;

import javax.enterprise.context.RequestScoped;
import de.wombatsoftware.TweetCamp.qualifier.Dummy;

@Dummy
@RequestScoped
public class DummyLoginBean implements UserHandler {

    @Override
    public String getUsername() {
	// TODO Auto-generated method stub
	return null;
    }

    @Override
    public String getPassword() {
	// TODO Auto-generated method stub
	return null;
    }

    @Override
    public void setUsername(String username) {
	// TODO Auto-generated method stub

    }

    @Override
    public void setPassword(String password) {
	// TODO Auto-generated method stub

    }

    @Override
    public void preLogin() {
	// TODO Auto-generated method stub

    }

    @Override
    public String login() {
	// TODO Auto-generated method stub
	return null;
    }

}
