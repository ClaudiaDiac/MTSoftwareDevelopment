package org.app.service.ejb;

import org.app.patterns.EntityRepository;
import org.app.service.entities.EvaluareFinala;
import org.app.service.entities.Propuneri;

public interface EvaluareFinalaPropuneriInterviuTehnicDataService extends EntityRepository<EvaluareFinala> {

	EvaluareFinala createNewEvaluareFinala(Integer IDIntern);
	
	Propuneri getPropuneriById(Integer IDIntern);
	
	String getMessage();
}
