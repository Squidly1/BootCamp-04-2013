package de.wombatsoftware.TweetCamp.services.api;

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
import de.wombatsoftware.TweetCamp.producer.LoggerProducer;
import de.wombatsoftware.TweetCamp.services.db.AbstractService;
import de.wombatsoftware.TweetCamp.services.db.DBUserService;
import de.wombatsoftware.TweetCamp.services.mock.MockUserService;
import de.wombatsoftware.TweetCamp.stereotype.Mock;

@RunWith(Arquillian.class)
public class UserServiceTest {
    @Inject
    private UserService userservice;

    @Deployment
    public static JavaArchive createDeployment() {
	return ShrinkWrap.create(JavaArchive.class, "test.jar")
		.addClass(UserService.class)
		.addClass(AbstractService.class)
		.addClass(MockUserService.class)
		.addClass(DBUserService.class)
		
		.addClass(User.class)
		.addClass(AbstractEntity.class)
		
		.addClass(Mock.class)
		.addClass(LoggerProducer.class)
	.addAsResource("persistence.xml", "META-INF/persistence.xml")
	.addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
    }

    @Test
    public void testIsDeployed() {
	Assert.assertNotNull(userservice);
    }
}