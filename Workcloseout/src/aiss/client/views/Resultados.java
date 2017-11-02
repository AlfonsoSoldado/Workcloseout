package aiss.client.views;

import java.util.List;

import aiss.client.MashupService;
import aiss.client.MashupServiceAsync;
import aiss.shared.domain.flickr.Flickr;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;

public class Resultados extends Composite {

	private TextBox campoTextoBuscador = new TextBox();
	private HorizontalPanel panelBase = new HorizontalPanel();
	
	private Integer NUM_VIDEOS_MOSTRAR = 3;
	private Integer NUM_FOTOS_FLICKR= 5;
	private Integer ALTURA_VIDEO_EN_PIXELES = 350;
	private Integer ANCHURA_VIDEO_EN_PIXELES = 350;
	private String ESPACIO_HTML = "&nbsp";
	
	private final MashupServiceAsync mashupService = GWT
			.create(MashupService.class);

	public Resultados(String valorBuscado) {
		initWidget(panelBase);
		
		campoTextoBuscador.setText(valorBuscado);

		RootPanel.get("youtube").clear();
		RootPanel.get("flickr").clear();
		
		buscarEnYouTube(valorBuscado);
		buscarEnFlickr(valorBuscado);
	}

	private void buscarEnYouTube(final String valorBuscador) {
		RootPanel.get("youtube").add(new HTML("Cargando Youtube..."));
		mashupService.getYoutubeVideos(valorBuscador, new AsyncCallback<List<String>>() {

			public void onSuccess(List<String> videos) {
				mostrarVideos(videos);
			}

			public void onFailure(Throwable caught) {
				RootPanel.get("youtube").clear();
				RootPanel.get("youtube").add(new HTML("Error inesperado, vuelva a intentarlo luego"));
			}
		});

	}
	
	private void buscarEnFlickr(final String valorBuscador) {
		
		mashupService.getFotosRelacionadas(valorBuscador, new AsyncCallback<Flickr>() {
					@Override
					public void onSuccess(Flickr flickr) {
						mostrarFotos(flickr);
					}

					@Override
					public void onFailure(Throwable caught) {
						RootPanel.get("flickir").clear();
						HTML html = new HTML("Error inesperado, vuelva a intentarlo luego");
						RootPanel.get("flickir").add(html);
					}
				});
	}
	
	private void mostrarVideos(List<String> videos) {
		Integer videosMostrados = 0;
		String videosIframe = "";
		Boolean hayVideos = videos != null && !videos.isEmpty();
		if (hayVideos) {
			for (String idVideoAMostrar : videos) {
				if (videosMostrados < NUM_VIDEOS_MOSTRAR) {
					videosIframe += "<iframe width=\""+ANCHURA_VIDEO_EN_PIXELES+"\" height=\""+ALTURA_VIDEO_EN_PIXELES+"\" "
							+ "src=\"https://www.youtube.com/embed/"
							+ idVideoAMostrar+ "\" frameborder=\"0\" allowfullscreen></iframe>"+ESPACIO_HTML+ESPACIO_HTML+ESPACIO_HTML;
					
					videosMostrados++;
				}
			}
		} else{
			videosIframe = "No hemos encontrado nada :(";
		}
		
		RootPanel.get("youtube").clear();
		RootPanel.get("youtube").add(new HTML(videosIframe));
	}
	
	private void mostrarFotos(Flickr flickr){
		String fotosEncontradas = "";		
		Integer fotosMostradas = calculaMaximasFotosAMostar(flickr);
		for (int i = 0; i < fotosMostradas; i++) {
			String id = flickr.getPhotos().getPhoto().get(i).getId();
			String server = flickr.getPhotos().getPhoto().get(i).getServer();
			String secret = flickr.getPhotos().getPhoto().get(i).getSecret();
			Number farm = flickr.getPhotos().getPhoto().get(i).getFarm();
			fotosEncontradas += "<img src=\"https://farm"+farm+".staticflickr.com/"+server+"/"+id+"_"+secret+".jpg\" />";
		}
	
		RootPanel.get("flickr").clear();
		RootPanel.get("flickr").add(new HTML(fotosEncontradas));
	}
	
	private Integer calculaMaximasFotosAMostar(Flickr flickr){
		if(NUM_FOTOS_FLICKR > flickr.getPhotos().getPhoto().size()){
			return flickr.getPhotos().getPhoto().size();
		}else{
			return NUM_FOTOS_FLICKR;
		}
	}
}
