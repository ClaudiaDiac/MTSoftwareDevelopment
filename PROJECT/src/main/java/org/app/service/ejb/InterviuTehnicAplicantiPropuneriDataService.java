package org.app.service.ejb;

import org.app.patterns.EntityRepository;
import org.app.service.entities.Aplicanti;
import org.app.service.entities.InterviuTehnic;

public interface InterviuTehnicAplicantiPropuneriDataService extends EntityRepository<InterviuTehnic>{

	InterviuTehnic createNewInterviuT(Integer IDIAplicant);
	
	Aplicanti getAplicantiById(Integer IDAplicant);
	
	String getMessage();
}
