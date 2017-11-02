
package aiss.shared.domain.flickr;

import java.io.Serializable;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown=true)
public class Flickr implements Serializable{
	
	private static final long serialVersionUID = -1705599656826772024L;
	private Photos photos;
   	private String stat;

 	public Photos getPhotos(){
		return this.photos;
	}
	public void setPhotos(Photos photos){
		this.photos = photos;
	}
 	public String getStat(){
		return this.stat;
	}
	public void setStat(String stat){
		this.stat = stat;
	}
}
