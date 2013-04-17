package de.wombatsoftware.TweetCamp.services.db;

import java.util.List;
import java.util.logging.Logger;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.enterprise.event.Observes;
import javax.inject.Inject;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.omg.PortableInterceptor.USER_EXCEPTION;

import de.wombatsoftware.TweetCamp.model.User;
import de.wombatsoftware.TweetCamp.model.User_;
import de.wombatsoftware.TweetCamp.services.api.UserService;

@Stateless
@LocalBean
public class DBUserService extends AbstractService implements UserService {
    @Inject
    private Logger logger;

    @Override
    public List<User> findAllUsers() {
	return em.createNamedQuery(User.findAll, User.class).getResultList();
    }

    @Override
    public User findUserByUsername(String username) {
	CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
	CriteriaQuery<User> criteriaQuery = criteriaBuilder.createQuery(User.class);
	
	Root<User> u = criteriaQuery.from(User.class);
	criteriaQuery.where(
			criteriaBuilder.equal(u.get(User_.username), username)
			);
	
	try {
//	    return em.createNamedQuery(User.findUserByUsername, User.class).setParameter("username", username).getSingleResult();
	    return em.createQuery(criteriaQuery).getSingleResult();
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