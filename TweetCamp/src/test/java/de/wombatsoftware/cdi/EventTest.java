package de.wombatsoftware.cdi;

import javax.enterprise.event.Event;
import javax.enterprise.event.Observes;
import javax.inject.Inject;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import de.wombatsoftware.TweetCamp.model.AbstractEntity;
import de.wombatsoftware.TweetCamp.model.User;
import de.wombatsoftware.TweetCamp.qualifier.LoggedIn;

@RunWith(Arquillian.class)
public class EventTest {
    @Inject
    @LoggedIn
    private Event<User> userEvent;

    private static int numberOfCatchedEvents;
    
    int numberOfEventsToFire = 1000;

    @Deployment
    public static JavaArchive createDeployment() {
	return ShrinkWrap.create(JavaArchive.class, "test.jar")
	.addClass(User.class)
	.addClass(AbstractEntity.class)

	.addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
    }

    @Test
    public void testIsEventFiredAfterSuccessfulLogin() {
	long before = System.nanoTime();

	for(int i = 0; i<numberOfEventsToFire; i++) {
	    userEvent.fire(new User("FOO", "BAR"));
	}

	Assert.assertEquals(numberOfEventsToFire, numberOfCatchedEvents);
	long after = System.nanoTime();
	
	System.out.println("Time: " + (after - before) + "ns");
    }

    public void catchLoginEvent(@Observes User u) {
	numberOfCatchedEvents++;
    }
}