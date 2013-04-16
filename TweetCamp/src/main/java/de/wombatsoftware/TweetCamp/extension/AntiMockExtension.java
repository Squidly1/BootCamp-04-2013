package de.wombatsoftware.TweetCamp.extension;

import java.util.logging.Logger;

import javax.enterprise.event.Observes;
import javax.enterprise.inject.spi.Extension;
import javax.enterprise.inject.spi.ProcessAnnotatedType;

import de.wombatsoftware.TweetCamp.stereotype.Mock;

public class AntiMockExtension implements Extension {
    private Logger logger = Logger.getLogger(this.getClass().getName());
    
    <T> void processAnnotatedType(@Observes ProcessAnnotatedType<T> pat) {
	if(pat.getAnnotatedType().isAnnotationPresent(Mock.class)) {
	    pat.veto();

	    logger.info(pat.getAnnotatedType().getJavaClass().getName() + " got deactivated because it Mocks");
	}
    }
}