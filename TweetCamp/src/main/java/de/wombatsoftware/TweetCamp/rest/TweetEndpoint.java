package de.wombatsoftware.TweetCamp.rest;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.UriBuilder;

import de.wombatsoftware.TweetCamp.model.Tweet;

@Stateless
@Path("/tweets")
public class TweetEndpoint {
    @PersistenceContext
    private EntityManager em;

    @POST
    @Consumes("application/json")
    public Response create(Tweet entity) {
	em.persist(entity);
	return Response.created(UriBuilder.fromResource(TweetEndpoint.class).path(String.valueOf(entity.getId())).build()).build();
    }

    @DELETE
    @Path("/{id:[0-9][0-9]*}")
    public Response deleteById(@PathParam("id") Long id) {
	Tweet entity = em.find(Tweet.class, id);
	if (entity == null) {
	    return Response.status(Status.NOT_FOUND).build();
	}
	em.remove(entity);
	return Response.noContent().build();
    }

    @GET
    @Path("/{id:[0-9][0-9]*}")
    @Produces("application/json")
    public Response findById(@PathParam("id") Long id) {
	Tweet entity = em.find(Tweet.class, id);
	if (entity == null) {
	    return Response.status(Status.NOT_FOUND).build();
	}
	return Response.ok(entity).build();
    }

    @GET
    @Produces("application/json")
    public Response listAll(@QueryParam("token") String token) {
	if(!"BootCamp".equals(token)) {
	    return Response.status(Status.FORBIDDEN).build();
	}

	final List<Tweet> results = em.createQuery("FROM Tweet", Tweet.class).getResultList();
	return Response.ok(results).build();
    }

    @PUT
    @Path("/{id:[0-9][0-9]*}")
    @Consumes("application/json")
    public Response update(@PathParam("id") Long id, Tweet entity) {
	entity.setId(id);
	entity = em.merge(entity);
	return Response.noContent().build();
    }
}