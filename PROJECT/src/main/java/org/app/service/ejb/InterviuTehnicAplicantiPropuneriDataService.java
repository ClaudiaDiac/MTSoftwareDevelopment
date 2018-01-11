package org.app.service.ejb;

import javax.ejb.Remote;

import org.app.patterns.EntityRepository;
import org.app.service.entities.Aplicanti;
import org.app.service.entities.InterviuTehnic;

@Remote
public interface InterviuTehnicAplicantiPropuneriDataService extends EntityRepository<InterviuTehnic>{

	InterviuTehnic createNewInterviuT(Integer IDIAplicant);
	
	Aplicanti getAplicantById(Integer IDAplicant);
	
	String getMessage();
}
