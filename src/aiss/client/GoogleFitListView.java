package aiss.client;

import java.util.Map;

import aiss.shared.domain.googlefit.Sesion;
import aiss.shared.domain.googlefit.Session;

import com.google.api.gwt.oauth2.client.Auth;
import com.google.api.gwt.oauth2.client.AuthRequest;
import com.google.gwt.core.client.Callback;
import com.google.gwt.core.shared.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;

public class GoogleFitListView extends Composite{

	private static final Auth 	AUTH = Auth.get();
	private VerticalPanel 		mainPanel;
	private final TextBox 		labelAccessToken = new TextBox();
	private final TextBox		startTime = new TextBox();
	private final TextBox		endTime = new TextBox();
	private final FlexTable 	filesTable;
	
	private final MashupServiceAsync googleFitService = GWT.create(MashupService.class);
	
		public GoogleFitListView(Map<String, Object> params) {		
			
			mainPanel = new VerticalPanel();
			initWidget(mainPanel);
						
			final String GOOGLEAUTH_URL = "https://accounts.google.com/o/oauth2/auth";
			final String GOOGLECLIENT_ID = "585335148080-c6l319pt4ohvl6tiasauavcv5opfhk11.apps.googleusercontent.com";
			final String GOOGLEFIT_SCOPE = "https://www.googleapis.com/auth/fitness.activity.read";
			final String GOOGLEFIT_SCOPE2= "https://www.googleapis.com/auth/fitness.activity.write";
			final String GOOGLEFIT_SCOPE3 = "https://www.googleapis.com/auth/fitness.body.read";
			final String GOOGLEFIT_SCOPE4 = "https://www.googleapis.com/auth/fitness.body.write";
			final String GOOGLEFIT_SCOPE5 = "https://www.googleapis.com/auth/fitness.location.read";
			final String GOOGLEFIT_SCOPE6 = "https://www.googleapis.com/auth/fitness.location.write";
			
			Button buttonGD= new Button("Authenticate with Google Fit");
			Button buttonGDFiles= new Button("Get Google Fit Workout");
						
			final Label labelGD 		= new Label("");
			
			
			filesTable = new FlexTable();
			// TABLE AND HEADER 
			filesTable.setStylePrimaryName("filesTable");
			filesTable.getRowFormatter().setStylePrimaryName(0,"firstRow");
			filesTable.setWidget(0,0, new Label("Name"));
			filesTable.setWidget(0,1, new Label("ID"));
			filesTable.setWidget(0,2, new Label("Start Time"));
			filesTable.setWidget(0,3, new Label("End Time"));
			filesTable.setWidget(0,4, new Label("Activity Type"));
			
			
			buttonGD.addClickHandler(new ClickHandler() {
			      @Override
			      public void onClick(ClickEvent event) {
			    	  final AuthRequest  req = new AuthRequest(GOOGLEAUTH_URL, GOOGLECLIENT_ID).withScopes(GOOGLEFIT_SCOPE, GOOGLEFIT_SCOPE2,
			    			  GOOGLEFIT_SCOPE3, GOOGLEFIT_SCOPE4, GOOGLEFIT_SCOPE5, GOOGLEFIT_SCOPE6);
			    	  AUTH.login(req, new Callback<String, Throwable>(){

						@Override
						public void onFailure(Throwable caught) {
							
						}

						@Override
						public void onSuccess(String token) {
							labelAccessToken.setText(token);
						}
			    	  });
			      }
			    });
			buttonGDFiles.addClickHandler(new ClickHandler(){

				@Override
				public void onClick(ClickEvent event) {
					if (labelAccessToken.getText()=="")
			    		  Window.alert("Please, login before creating a file");
					else{
						googleFitService.getSesion(labelAccessToken.getText(),
								GOOGLECLIENT_ID,
								startTime.getText(), 
								endTime.getText(),	
								new AsyncCallback<Sesion>(){
								public void onSuccess(Sesion result) {
									showSesion(result);
								}

								public void onFailure(Throwable caught) {
									Window.alert("Algo ha fallado"+caught.getMessage());
								}
							});	
					}					
				}
				
			});
		    
			mainPanel.add(new Label("GOOGLE FIT"));
			mainPanel.add(buttonGD);
			mainPanel.add(labelGD);
//			mainPanel.add(labelAccessToken);
			
			mainPanel.add(new Label("Periodo inicial, la fecha debe tener el siguiente formato: '2016-05-22T00:00:00.00Z'"));
			mainPanel.add(startTime);
			mainPanel.add(new Label("Periodo final"));
			mainPanel.add(endTime);
			
			mainPanel.add(buttonGDFiles);
			mainPanel.add(filesTable);
			
			mainPanel.getElement().setId("googleFit");
			
		}
		
		void showSesion(final Sesion result){
			int i = 0;
			String s=null;
	        if (result != null) {
	        	for(final Session a : result.getSession()){
	        		filesTable.setWidget(i+1, 0, new Label(a.getName()));
	        		filesTable.setWidget(i+1, 1, new Label(a.getId()));
	        		filesTable.setWidget(i+1, 2, new Label(a.getStartTimeMillis()));
	        		filesTable.setWidget(i+1, 3, new Label(a.getEndTimeMillis()));
	        		switch(a.getActivityType()){
	        		case 0:
	        			s = "In Vehicle";
	        			break;
	        		case 1:
	        			s = "Biking";
	        			break;
	        		case 2:
	        			s = "On foot";
	        			break;
	        		case 3:
	        			s = "Still (not moving)";
	        			break;
	        		case 4:
	        			s = "Unknown (unable to detect activity)";
	        			break;
	        		case 5:
	        			s = "Tilting (sudden device gravity change)";
	        			break;
	        		case 7:
	        			s = "Walking";
	        			break;
	        		case 8:
	        			s = "Running";
	        			break;
	        		case 9:
	        			s = "Aerobics";
	        			break;
	        		case 10:
	        			s = "Badminton";
	        			break;
	        		case 11:
	        			s = "Baseball";
	        			break;
	        		case 12:
	        			s = "Basketball";
	        			break;
	        		case 13:
	        			s = "Biathlon";
	        			break;
	        		case 14:
	        			s = "Handbiking";
	        			break;
	        		case 15:
	        			s = "Mountain biking";
	        			break;
	        		case 16:
	        			s = "Road biking";
	        			break;
	        		case 17:
	        			s = "Spinning";
	        			break;
	        		case 18:
	        			s = "Stationary biking";
	        			break;
	        		case 19:
	        			s = "Utility biking";
	        			break;
	        		case 20:
	        			s = "Boxing";
	        			break;
	        		case 21:
	        			s = "Calisthenics";
	        			break;
	        		case 22:
	        			s = "Circuit training";
	        			break;
	        		case 23:
	        			s = "Cricket";
	        			break;
	        		case 24:
	        			s = "Dancing";
	        			break;
	        		case 25:
	        			s = "Elliptical";
	        			break;
	        		case 26:
	        			s = "Fencing";
	        			break;
	        		case 27:
	        			s = "Football (American)";
	        			break;
	        		case 28:
	        			s = "Football (Australian)";
	        			break;
	        		case 29:
	        			s = "Football (Soccer)";
	        			break;
	        		case 30:
	        			s = "Frisbee";
	        			break;
	        		case 31:
	        			s = "Gardening";
	        			break;
	        		case 32:
	        			s = "Golf";
	        			break;
	        		case 33:
	        			s = "Gymnastics";
	        			break;
	        		case 34:
	        			s = "Handball";
	        			break;
	        		case 35:
	        			s = "Hiking";
	        			break;
	        		case 36:
	        			s = "Hockey";
	        			break;
	        		case 37:
	        			s = "Horseback riding";
	        			break;
	        		case 38:
	        			s = "Housework";
	        			break;
	        		case 39:
	        			s = "Jumping rope";
	        			break;
	        		case 40:
	        			s = "Kayaking";
	        			break;
	        		case 41:
	        			s = "Kettlebell training";
	        			break;
	        		case 42:
	        			s = "Kickboxing";
	        			break;
	        		case 43:
	        			s = "Kitesurfing";
	        			break;
	        		case 44:
	        			s = "Martial arts";
	        			break;
	        		case 45:
	        			s = "Meditation";
	        			break;
	        		case 46:
	        			s = "Mixed martial arts";
	        			break;
	        		case 47:
	        			s = "P90X exercises";
	        			break;
	        		case 48:
	        			s = "Paragliding";
	        			break;
	        		case 49:
	        			s = "Pilates";
	        			break;
	        		case 50:
	        			s = "Polo";
	        			break;
	        		case 51:
	        			s = "Racquetball";
	        			break;
	        		case 52:
	        			s = "Rock climbing";
	        			break;
	        		case 53:
	        			s = "Rowing";
	        			break;
	        		case 54:
	        			s = "Rowing machine";
	        			break;
	        		case 55:
	        			s = "Rugby";
	        			break;
	        		case 56:
	        			s = "Jogging";
	        			break;
	        		case 57:
	        			s = "Running on sand";
	        			break;
	        		case 58:
	        			s = "Running (treadmill)";
	        			break;
	        		case 59:
	        			s = "Sailing";
	        			break;
	        		case 60:
	        			s = "Scuba diving";
	        			break;
	        		case 61:
	        			s = "Skateboarding";
	        			break;
	        		case 62:
	        			s = "Skating";
	        			break;
	        		case 63:
	        			s = "Cross skating";
	        			break;
	        		case 64:
	        			s = "Inline skating (rollerblading)";
	        			break;
	        		case 65:
	        			s = "Skiing";
	        			break;
	        		case 66:
	        			s = "Back-country skiing";
	        			break;
	        		case 67:
	        			s = "Cross-country skiing";
	        			break;
	        		case 68:
	        			s = "Downhill skiing";
	        			break;
	        		case 69:
	        			s = "Kite skiing";
	        			break;
	        		case 70:
	        			s = "Roller skiing";
	        			break;
	        		case 71:
	        			s = "Sledding";
	        			break;
	        		case 72:
	        			s = "Sleeping";
	        			break;
	        		case 73:
	        			s = "Snowboarding";
	        			break;
	        		case 74:
	        			s = "Snowmobile";
	        			break;
	        		case 75:
	        			s = "Snowshoeing";
	        			break;
	        		case 76:
	        			s = "Squash";
	        			break;
	        		case 77:
	        			s = "Stair climbing";
	        			break;
	        		case 78:
	        			s = "Stair-climbing machine";
	        			break;
	        		case 79:
	        			s = "Stand-up paddleboarding";
	        			break;
	        		case 80:
	        			s = "Strength training";
	        			break;
	        		case 81:
	        			s = "Surfing";
	        			break;
	        		case 82:
	        			s = "Swimming";
	        			break;
	        		case 83:
	        			s = "Swimming (swimming pool)";
	        			break;
	        		case 84:
	        			s = "Swimming (open water)";
	        			break;
	        		case 85:
	        			s = "Table tennis (ping pong)";
	        			break;
	        		case 86:
	        			s = "Team sports";
	        			break;
	        		case 87:
	        			s = "Tennis";
	        			break;
	        		case 88:
	        			s = "Treadmill (walking or running)";
	        			break;
	        		case 89:
	        			s = "Volleyball";
	        			break;
	        		case 90:
	        			s = "Volleyball (beach)";
	        			break;
	        		case 91:
	        			s = "Volleyball (indoor)";
	        			break;
	        		case 92:
	        			s = "Wakeboarding";
	        			break;
	        		case 93:
	        			s = "Walking (fitness)";
	        			break;
	        		case 94:
	        			s = "Nording walking";
	        			break;
	        		case 95:
	        			s = "Walking (treadmill)";
	        			break;
	        		case 96:
	        			s = "Waterpolo";
	        			break;
	        		case 97:
	        			s = "Weightlifting";
	        			break;
	        		case 98:
	        			s = "Wheelchair";
	        			break;
	        		case 99:
	        			s = "Windsurfing";
	        			break;
	        		case 100:
	        			s = "Yoga";
	        			break;
	        		case 101:
	        			s = "Zumba";
	        			break;
	        		case 102:
	        			s = "Diving";
	        			break;
	        		case 103:
	        			s = "Ergometer";
	        			break;
	        		case 104:
	        			s = "Ice skating";
	        			break;
	        		case 105:
	        			s = "Indoor skating";
	        			break;
	        		case 106:
	        			s = "Curling";
	        			break;
	        		case 108:
	        			s = "Other (unclassified fitness activity)";
	        			break;
	        		case 109:
	        			s = "Light sleep";
	        			break;
	        		case 110:
	        			s = "Deep sleep";
	        			break;
	        		case 111:
	        			s = "REM sleep";
	        			break;
	        		case 112:
	        			s = "Awake (during sleep cycle)";
	        			break; 
	        		default :
	        			s = "El ejercicio que quiere mostrar no se encuentra entre los de la aplicación.";
	        			break;
	        	}
	        		filesTable.setWidget(i+1, 4, new Label(s));
	        		i++;
			}
	      	        		
	       	}else{
	        	Window.alert("Resultado vacío.");
	        }
		}		
}
