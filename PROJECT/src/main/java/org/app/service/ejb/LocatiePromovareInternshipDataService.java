package org.app.service.ejb;

import javax.ejb.Remote;

import org.app.patterns.EntityRepository;
import org.app.service.entities.Locatie;
import org.app.service.entities.Promovare;

@Remote
public interface LocatiePromovareInternshipDataService extends EntityRepository<Locatie>{

	Locatie createNewLocatie(Integer IDLocatie);
	
	Promovare getPromovareById(Integer IDPromovare);
	
	String getMessage();
}
