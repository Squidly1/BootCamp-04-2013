package de.wombatsoftware.TweetCamp.services.api;

import java.util.List;

import de.wombatsoftware.TweetCamp.model.Tweet;

public interface TweetService {
    public List<Tweet> findAll();
    public void save(Tweet t);
}
