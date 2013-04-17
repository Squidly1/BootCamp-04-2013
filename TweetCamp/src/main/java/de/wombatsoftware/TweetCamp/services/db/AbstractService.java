package de.wombatsoftware.TweetCamp.services.db;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import de.wombatsoftware.TweetCamp.model.AbstractEntity;

public abstract class AbstractService {
    @PersistenceContext
    protected EntityManager em;

    public void saveEntity(AbstractEntity ae) {
	if(ae.isTransient()) {
	    em.persist(ae);
	} else {
	    em.merge(ae);
	}
    }
}