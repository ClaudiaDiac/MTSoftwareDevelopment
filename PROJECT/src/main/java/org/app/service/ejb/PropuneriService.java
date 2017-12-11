package org.app.service.ejb;

import java.util.Collection;

import javax.ejb.Remote;

import org.app.service.entities.Propuneri;

@Remote
public interface PropuneriService {
Propuneri addPropuneri(Propuneri propuneriToAdd);
	
	String removePropuneri(Propuneri PropuneriToDelete);

	Propuneri getPropuneriByIDIntern(Integer IDIntern);
	Collection<Propuneri> getPropunere();
	
	Propuneri getPropuneriByNumePost(String NumePost);
	
	String getMessage();


}
