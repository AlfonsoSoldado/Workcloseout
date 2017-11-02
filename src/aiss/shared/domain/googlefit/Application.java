package aiss.shared.domain.googlefit;

import java.io.Serializable;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@SuppressWarnings("serial")
@JsonIgnoreProperties(ignoreUnknown=true)
public class Application implements Serializable{
	private String packageName;
   	private String version;

 	public String getPackageName(){
		return this.packageName;
	}
	public void setPackageName(String packageName){
		this.packageName = packageName;
	}
 	public String getVersion(){
		return this.version;
	}
	public void setVersion(String version){
		this.version = version;
	}
}
