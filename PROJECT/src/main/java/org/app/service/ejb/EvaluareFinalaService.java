package org.app.service.ejb;

import java.util.Collection;

import javax.ejb.Remote;

import org.app.service.entities.EvaluareFinala;

@Remote
public interface EvaluareFinalaService {
	EvaluareFinala addEvaluareFinala(EvaluareFinala evfToAdd);

	String removeEvaluareFinala(EvaluareFinala evfToDelete);
	
	EvaluareFinala getEvaluareFinalaByIDIntern(Integer IDIntern);
	Collection<EvaluareFinala> getEvaluariFinale();
	
	EvaluareFinala getEvaluareFinalaByNumeIntern(String NumeIntern);
	
	String getMessage();


}
