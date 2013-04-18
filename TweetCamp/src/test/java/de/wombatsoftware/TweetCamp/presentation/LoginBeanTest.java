package de.wombatsoftware.TweetCamp.presentation;

import javax.enterprise.event.Event;
import javax.enterprise.event.Observes;
import javax.inject.Inject;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.Filters;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.asset.StringAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import de.wombatsoftware.TweetCamp.LocaleHandler;
import de.wombatsoftware.TweetCamp.model.User;
import de.wombatsoftware.TweetCamp.qualifier.LoggedIn;

@RunWith(Arquillian.class)
public class LoginBeanTest {
    @Inject
    private LoginBean loginbean;
    
    private static int numberOfCatchedEvents;

    @Deployment
    public static WebArchive createDeployment() {
	return ShrinkWrap.create(WebArchive.class, "test.war")
		.addPackages(true, Filters.exclude(LocaleHandler.class), "de/wombatsoftware/TweetCamp")

//		persistence.xml drops and recreates the database after each deployment
		.addAsResource("CreateDropPersistence.xml", "META-INF/persistence.xml")
		
//		import.sql is a meta file that is imported during each deployment
		.addAsResource("ImportUser.sql", "import.sql")
		
//		activates the AntiMockExtension and disables all @Mock classes
		.addAsResource("javax.enterprise.inject.spi.Extension", "META-INF/services/javax.enterprise.inject.spi.Extension")
		
//		adds an empty beans.xml to activate CDI
		.addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml")
		
//		adds an empty faces-config.xml to activate JSF 2
		.addAsWebInfResource(new StringAsset("<faces-config version=\"2.0\"/>"),"faces-config.xml");
    }
    
    @Before
    public void setupDatabaseState() {
//	import.sql...
    }

    @Test
    public void testIsDeployed() {
	Assert.assertNotNull(loginbean);
    }
    
    @Inject
    @LoggedIn
    private Event<User> userEvent;

    @Test
    public void testIsEventFiredAfterSuccessfulLogin() {	
	loginbean.setUsername("Daniel");
	loginbean.setPassword("Sachse");

	loginbean.login();

	Assert.assertEquals(1, numberOfCatchedEvents);
    }

    public void catchLoginEvent(@Observes @LoggedIn User u) {
	numberOfCatchedEvents++;
    }
}