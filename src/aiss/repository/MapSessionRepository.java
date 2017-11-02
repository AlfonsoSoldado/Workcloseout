package aiss.repository;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import aiss.api.repository.SessionRepository;
import aiss.shared.domain.googlefit.Session;

public class MapSessionRepository implements SessionRepository {

	Map<String, Session> sessionMap;
	
	public MapSessionRepository(){
		this.sessionMap = new HashMap<>();
	}

	public Collection<Session> getAll() {
		return sessionMap.values();
	}

	public Session get(String name) {
		return sessionMap.get(name);
	}

	public void put(Session s) {
		sessionMap.put(s.getName(), s);		
	}

	public void remove(Session s) {
		sessionMap.remove(s.getName());
	}
	
	
	
}
