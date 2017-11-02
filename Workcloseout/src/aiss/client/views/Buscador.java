package aiss.client.views;

import java.util.HashMap;
import java.util.Map;

import aiss.client.ViewsController;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;

public class Buscador extends Composite {

	private final HorizontalPanel panelBase;
	private TextBox campoTextoBuscador = new TextBox();

	public Buscador() {

		panelBase = new HorizontalPanel();
		initWidget(panelBase);
		
		RootPanel.get("youtube").clear();
		RootPanel.get("flickr").clear();

		Button botonDeBusqueda = new Button("Busque su ejercicio");
		botonDeBusqueda.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				String valorBuscador = campoTextoBuscador.getValue();
				valorBuscador = valorBuscador == "" ? "Biceps" : valorBuscador;
				Map<String, String> param = new HashMap<String, String>();
				param.put("valorBuscador", valorBuscador);
				ViewsController.go("buscar", param);
			}
		});
		
		RootPanel.get("buscador").clear();
		//Añadimos el input html
		RootPanel.get("buscador").add(campoTextoBuscador);
		//Añadimos el boton de buscar
		RootPanel.get("buscador").add(botonDeBusqueda);
	}

}