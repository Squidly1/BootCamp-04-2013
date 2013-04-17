package de.wombatsoftware.TweetCamp.services.db;

import java.util.List;
import java.util.logging.Logger;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.enterprise.event.Observes;
import javax.inject.Inject;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;

import de.wombatsoftware.TweetCamp.model.User;
import de.wombatsoftware.TweetCamp.services.api.UserService;

@Stateless
@LocalBean
public class DBUserService extends AbstractService implements UserService {
    @Inject
    private Logger logger;

    @Override
    public List<User> findAllUsers() {
	return em.createQuery("FROM Users", User.class).getResultList();
    }

    @Override
    public User findUserByUsername(String username) {
	try {
	    return em.createQuery("FROM User u WHERE u.username = :username", User.class).setParameter("username", username).getSingleResult();
	} catch (NoResultException nre) {
	    return null;
	} catch (NonUniqueResultException nre) {
	    logger.severe("Multiple users with sanme username. FIX IT!");
	    return null;
	}
	
    }

    public void logUserEvent(@Observes User user) {
	logger.info("User event was catched by DBUserService!");
    }

    @Override
    public void save(User user) {
	saveEntity(user);
    }
}