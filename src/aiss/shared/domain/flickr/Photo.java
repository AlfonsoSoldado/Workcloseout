
package aiss.shared.domain.flickr;

import java.io.Serializable;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown=true)
public class Photo implements Serializable{
	
	private static final long serialVersionUID = 4898430740106408046L;
	private Number farm;
   	private String id;
   	private String secret;
   	private String server;
   	private String title;

 	public Number getFarm(){
		return this.farm;
	}
	public void setFarm(Number farm){
		this.farm = farm;
	}
 	public String getId(){
		return this.id;
	}
	public void setId(String id){
		this.id = id;
	}
 	public String getSecret(){
		return this.secret;
	}
	public void setSecret(String secret){
		this.secret = secret;
	}
 	public String getServer(){
		return this.server;
	}
	public void setServer(String server){
		this.server = server;
	}
 	public String getTitle(){
		return this.title;
	}
	public void setTitle(String title){
		this.title = title;
	}
}
