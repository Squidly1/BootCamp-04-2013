package de.wombatsoftware.TweetCamp.services.db;

import java.util.List;

import javax.ejb.Stateless;

import de.wombatsoftware.TweetCamp.model.Tweet;
import de.wombatsoftware.TweetCamp.services.api.TweetService;

@Stateless
public class DBTweetService extends AbstractService implements TweetService {

    @Override
    public List<Tweet> findAll() {
	return em.createQuery("FROM Tweet", Tweet.class).getResultList();
    }

    @Override
    public void save(Tweet t) {
	saveEntity(t);
    }
}