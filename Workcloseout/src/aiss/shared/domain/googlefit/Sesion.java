package aiss.shared.domain.googlefit;

import java.io.Serializable;
import java.util.List;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@SuppressWarnings("serial")
@JsonIgnoreProperties(ignoreUnknown=true)
public class Sesion implements Serializable{
	private List<Session> session;

 	public List<Session> getSession(){
		return this.session;
	}
	public void setSession(List<Session> session){
		this.session = session;
	}
}
