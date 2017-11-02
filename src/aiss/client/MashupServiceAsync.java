package aiss.client;

import java.util.List;

import aiss.shared.domain.flickr.Flickr;
import aiss.shared.domain.googlefit.Sesion;

import com.google.gwt.user.client.rpc.AsyncCallback;



public interface MashupServiceAsync {

	void getYoutubeVideos(String valorBuscador, AsyncCallback<List<String>> callback);
	void getFotosRelacionadas(String valorBuscador, AsyncCallback<Flickr> callback);
	void getSesion(String access_token, String id, String startTime, String endTime, AsyncCallback<Sesion> callback);

}
