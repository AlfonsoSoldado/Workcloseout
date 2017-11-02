package aiss.client;

import java.util.List;

import aiss.shared.domain.flickr.Flickr;
import aiss.shared.domain.googlefit.Sesion;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;


/**
 * The client side stub for the RPC service.
 */
@RemoteServiceRelativePath("mashup")
public interface MashupService extends RemoteService {
	
	List<String> getYoutubeVideos(String valorBuscador);
	Flickr getFotosRelacionadas(String valorBuscador);
	Sesion getSesion(String access_token, String id, String startTime, String endTime);
}
	