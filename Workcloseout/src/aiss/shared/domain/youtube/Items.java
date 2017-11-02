package aiss.shared.domain.youtube;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown=true)
public class Items {
	
   	private Id id;

 	public Id getId(){
		return this.id;
	}
	public void setId(Id id){
		this.id = id;
	}
	
}
