package aiss.server;

import java.util.ArrayList;
import java.util.List;

import org.restlet.resource.ClientResource;

import aiss.client.MashupService;
import aiss.server.resources.GoogleFitResource;
import aiss.shared.domain.flickr.Flickr;
import aiss.shared.domain.googlefit.Sesion;
import aiss.shared.domain.youtube.Items;
import aiss.shared.domain.youtube.Videos;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;


@SuppressWarnings("serial")
public class MashupServiceImpl extends RemoteServiceServlet implements MashupService {
		
	public List<String> getYoutubeVideos(String valorBuscador) {
		String personalizado = "%20entrenamiento";
		ClientResource cr =  new ClientResource("https://www.googleapis.com/youtube/v3/search?q="+valorBuscador+personalizado+"&part=snippet&key=AIzaSyD-KbBFVaIMDoI82mDKIyPW5BeeIptM4EU");
		Videos videos = cr.get(Videos.class);

		List<String> listaVideos = obtenerVideosIds(videos);
		return listaVideos;
    }
	
	@Override
	public Flickr getFotosRelacionadas(String valorBuscador) {
		String personalizado = "%20entrenamiento";
		ClientResource cr = new ClientResource("https://api.flickr.com/services/rest/?method=flickr.photos.search&api_key=357f1608ccb0a2ea7b9ec60e3686732d&text=\""+ valorBuscador+ personalizado +"\"&format=json&nojsoncallback=1");
		Flickr flickr = cr.get(Flickr.class);
		return flickr;
	}
	
	private List<String> obtenerVideosIds(Videos videos){
		List<String> listaVideos = new ArrayList<String>();
		for(Items item: videos.getItems()){
			if(item.getId().getVideoId() != null){
				listaVideos.add(item.getId().getVideoId());			
			}
		}
		return listaVideos;
	}

	@Override
	public Sesion getSesion(String access_token, String id, String startTime,
			String endTime) {
		GoogleFitResource gfr = new GoogleFitResource(access_token, id, startTime, endTime);
		return gfr.getUser();
	}
}
