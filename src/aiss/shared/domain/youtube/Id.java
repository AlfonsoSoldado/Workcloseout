package aiss.shared.domain.youtube;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown=true)
public class Id {
	
	private String kind;
   	private String videoId;

 	public String getKind(){
		return this.kind;
	}
	public void setKind(String kind){
		this.kind = kind;
	}
 	public String getVideoId(){
		return this.videoId;
	}
	public void setVideoId(String videoId){
		this.videoId = videoId;
	}
}
