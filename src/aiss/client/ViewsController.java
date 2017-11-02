package aiss.client;

import java.util.HashMap;
import java.util.Map;

import aiss.client.views.Resultados;
import aiss.client.views.Buscador;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.RootPanel;

public class ViewsController implements EntryPoint {

	static HorizontalPanel menu = new HorizontalPanel();
	
	public void onModuleLoad() {
		go("inicio", new HashMap<String, String>());
		goGoogleFit("inicio", new HashMap<String, Object>());
	}

	public static void go(String token) {
		ViewsController.go(token, new HashMap<String, String>());
	}

	public static void go(String token, Map<String, String> params) {
		Panel p = RootPanel.get();
		if (token == "inicio") {
			p.clear();
			p.add(new Buscador());
		} else if (token == "buscar") {
			p.clear();
			p.add(new Resultados(params.get("valorBuscador")));
		}
	}
	
	public static void goGoogleFit(String token) {
		ViewsController.goGoogleFit(token, new HashMap<String,Object>());
	}
	
	public static void goGoogleFit(String token, Map<String, Object> params) {
		RootPanel p = RootPanel.get();
		p.getElement().setId("root");
		if (token.equals("inicio")){
			p.clear();
			menu.clear();
			p.add(menu);
			menu.add(new GoogleFitListView(params));
		}else if(token.equals("list")){
		}
		menu.getElement().setId("googleFit");
	}
}