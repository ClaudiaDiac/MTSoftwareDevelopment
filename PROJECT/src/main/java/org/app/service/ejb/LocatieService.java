package org.app.service.ejb;

import java.util.Collection;

import javax.ejb.Remote;

import org.app.service.entities.Locatie;

@Remote
public interface LocatieService {
	Locatie addLocatie(Locatie locatieToAdd);

	String removeLocatie(Locatie locatieToDelete);
	
	Locatie getLocatieByIDLocatie(Integer IDLocatie);
	Collection<Locatie> getLocatii();
	
	Locatie getLocatieByNumeLocatie(String NumeLocatie);
	
	String getMessage();


}
