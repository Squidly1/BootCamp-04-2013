package de.wombatsoftware.TweetCamp.services.mock;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.Stateful;
import javax.enterprise.context.SessionScoped;
import javax.enterprise.inject.Specializes;
import javax.inject.Inject;

import de.wombatsoftware.TweetCamp.model.Tweet;
import de.wombatsoftware.TweetCamp.model.User;
import de.wombatsoftware.TweetCamp.services.api.UserService;
import de.wombatsoftware.TweetCamp.services.db.DBTweetService;
import de.wombatsoftware.TweetCamp.stereotype.Mock;

@Stateful
@Specializes
@Mock
public class MockTweetService extends DBTweetService implements Serializable {
    private static final long serialVersionUID = -896539864819320625L;

    @Inject
    private UserService userService;

    private List<Tweet> tweets;

    @PostConstruct
    private void init() {
	tweets = new ArrayList<Tweet>();

	User u = userService.findUserByUsername("Daniel");
	save(new Tweet(u, "Hello its JSF DAY", new Date()));
	save(new Tweet(u, "Hello its CDI DAY", new Date()));
	save(new Tweet(u, "Hello its EJB / JPA DAY", new Date()));
	save(new Tweet(u, "Hello its REST / Arquillian DAY", new Date()));
	save(new Tweet(u, "Hello its Errai DAY", new Date()));
    }

    @Override
    public List<Tweet> findAll() {
	return tweets;
    }

    @Override
    public void save(Tweet t) {
	tweets.add(t);
    }
}