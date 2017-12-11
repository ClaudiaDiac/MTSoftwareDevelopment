package org.app.service.ejb;

import java.util.Collection;

import javax.ejb.Remote;

import org.app.service.entities.Proiecte;

@Remote
public interface ProiecteService {
Proiecte addProiecte(Proiecte proiecteToAdd);
	
	String removeProiecte(Proiecte proiecteToDelete);
	
	Proiecte getProiecteByIDProiect(Integer IDProiect);
	Collection<Proiecte> getProiect();
	
	Proiecte getProiecteByNumeProiect(String NumeProiect);
	
	String getMessage();


}
