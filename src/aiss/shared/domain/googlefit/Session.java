package aiss.shared.domain.googlefit;

import java.io.Serializable;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown=true)
public class Session implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer activityType;
   	private Application application;
   	private String endTimeMillis;
   	private String id;
   	private String name;
   	private String startTimeMillis;

 	public Integer getActivityType(){
		return this.activityType;
	}
	public void setActivityType(Integer activityType){
		this.activityType = activityType;
	}
 	public Application getApplication(){
		return this.application;
	}
	public void setApplication(Application application){
		this.application = application;
	}
 	public String getEndTimeMillis(){
		return this.endTimeMillis;
	}
	public void setEndTimeMillis(String endTimeMillis){
		this.endTimeMillis = endTimeMillis;
	}
 	public String getId(){
		return this.id;
	}
	public void setId(String id){
		this.id = id;
	}
 	public String getName(){
		return this.name;
	}
	public void setName(String name){
		this.name = name;
	}
 	public String getStartTimeMillis(){
		return this.startTimeMillis;
	}
	public void setStartTimeMillis(String startTimeMillis){
		this.startTimeMillis = startTimeMillis;
	}
}
