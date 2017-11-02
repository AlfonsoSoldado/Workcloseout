package aiss.server.resources;

import org.restlet.resource.ClientResource;
import org.restlet.resource.ResourceException;

import aiss.shared.domain.googlefit.Sesion;

public class GoogleFitResource {
	private String uri 	= "https://www.googleapis.com/fitness/v1/users/me/sessions";
	private String access_token;
	private String id;
	private String startTime;
	private String endTime;
	
	public GoogleFitResource(String access_token, String id, String startTime, String endTime) {
		this.id=id;
		this.startTime=startTime;
		this.endTime=endTime;
		this.access_token = access_token;
	}
	
	public Sesion getUser(){
		ClientResource cr = null;
		Sesion user = null;
		try{
			cr = new ClientResource(uri + "?includeDeleted=false&startTime=" + startTime + "&endTime=" + endTime + "&key=" + id +
		"&access_token=" + access_token);
			user = cr.get(Sesion.class);
		
		} catch(ResourceException re){
			System.out.println("Error when retrieving all files: " + cr.getResponse().getStatus());
		}
		return user;
	}
}
