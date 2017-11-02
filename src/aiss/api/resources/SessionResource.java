package aiss.api.resources;

import java.net.URI;
import java.util.Collection;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.core.Response.ResponseBuilder;

import org.jboss.resteasy.spi.BadRequestException;
import org.jboss.resteasy.spi.NotFoundException;

import aiss.api.repository.SessionRepository;
import aiss.repository.MapSessionRepository;
import aiss.shared.domain.googlefit.Session;

@Path("/workcloseout")
public class SessionResource {

	private static SessionResource _instance = null;
	
	SessionRepository repository;
	
	private SessionResource(){ 
		repository = new MapSessionRepository();
		initialize();
	}
	
	public static SessionResource getInstance(){
		if(_instance==null) _instance = new SessionResource();
		return _instance;
	}
	
	private void initialize(){
		Session s1 = new Session();
		s1.setActivityType(1);
		s1.setName("Session1");
		repository.put(s1);
		
		Session s2 = new Session();
		s2.setActivityType(4);
		s2.setName("Session2");
		repository.put(s2);
		
		Session s3 = new Session();
		s3.setActivityType(2);
		s3.setName("Session3");
		repository.put(s3);
		
		Session s4 = new Session();
		s4.setActivityType(0);
		s4.setName("Session4");
		repository.put(s4);
	}
	
	@GET
	@Produces("application/json")
	public Collection<Session> getAll()
	{
		return repository.getAll();
	}
	
	
	@GET
	@Path("/{name}")
	@Produces("application/json")
	public Session get(@PathParam("name") String name)
	{
		Session s = repository.get(name);
		
		if (s == null) {
			throw new NotFoundException("The session "+name+" was not found");			
		}
		
		return s;
	}
	
	@POST
	@Consumes("application/json")
	@Produces("application/json")
	public Response addSession(@Context UriInfo uriInfo, Session session) {
		if (session.getName() == null || "".equals(session.getName()))
			throw new BadRequestException("The name of the session must not be null");

		repository.put(session);

		UriBuilder ub = uriInfo.getAbsolutePathBuilder().path(this.getClass(), "get");
		URI uri = ub.build(session.getName());
		ResponseBuilder resp = Response.created(uri);
		resp.entity(session);			
		return resp.build();
	}

	
	@PUT
	@Path("/{sessionName}")
	@Consumes("application/json")
	public Response updateSession(@PathParam("sessionName") String name, Session session) {
		Session oldsession = repository.get(name);
		if (oldsession == null) {
			throw new NotFoundException("The session "+name+" was not found");			
		}
		
		if (!oldsession.getName().equals(session.getName())) {
			return Response.status(javax.ws.rs.core.Response.Status.CONFLICT).build();
		}
		
		repository.put(session);
		
		return Response.noContent().build();
	}
	
	@DELETE
	@Path("/{sessionName}")
	public void removeSession(@PathParam("sessionName") String name) {
		Session toberemoved=repository.get(name);
		if (toberemoved == null)
			throw new NotFoundException("The session "+name+" was not found");
		else
			repository.remove(toberemoved);
	}

	
	
}
