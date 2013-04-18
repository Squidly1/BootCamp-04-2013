package de.wombatsoftware.TweetCamp.rest;

import java.util.Date;
import java.util.List;

import javax.ws.rs.core.MediaType;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.container.test.api.RunAsClient;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.Filters;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.asset.StringAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.reflect.TypeToken;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.ClientResponse.Status;
import com.sun.jersey.api.client.WebResource;

import de.wombatsoftware.TweetCamp.LocaleHandler;
import de.wombatsoftware.TweetCamp.model.User;

@RunWith(Arquillian.class)
public class UserEndpointTest {
    @Deployment
    public static WebArchive createDeployment() {
	return ShrinkWrap.create(WebArchive.class, "test.war")
		.addPackages(true, Filters.exclude(LocaleHandler.class), "de/wombatsoftware/TweetCamp")
		.addClass(TypeToken.class)
		.addClass(JsonDeserializer.class)
		.addClass(JsonDeserializationContext.class)

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
    
    @Test
    @RunAsClient
    public void testUserInDatabaseViaRest() {
	Client client = Client.create();
	GsonBuilder gsonb = new GsonBuilder();
	gsonb.registerTypeHierarchyAdapter(Date.class, new DateDeserializer());
	Gson gson = gsonb.create();
	
	WebResource webResource = client.resource("http://TweetCamp:8080/rest/users/");
	webResource.accept(MediaType.APPLICATION_JSON);
	
	ClientResponse result = webResource.get(ClientResponse.class);
	
	if(Status.OK.getStatusCode() == result.getStatus()) {
	    String s = result.getEntity(String.class);
	    System.out.println("Result: " + s);

	    List<User> users = gson.fromJson(s, new TypeToken<List<User>>(){}.getType());

	    Assert.assertEquals(1, users.size());
	} else {
	    System.out.println("Status: " + result.getStatus());
	    Assert.fail();
	}
    }
}