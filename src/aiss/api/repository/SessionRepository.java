package aiss.api.repository;

import java.util.Collection;

import aiss.shared.domain.googlefit.Session;

public interface SessionRepository {
	//put modifica y post añade 

	public Collection<Session> getAll(); //devuelve todas las sesiones
	
	public Session get(String name); // devuelve una sesion dado su nombre
	
	public void put(Session s); // modifica la sesion s
	
	public void remove(Session s); // elimina la sesion s
}
