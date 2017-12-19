package org.app.service.ejb;

import org.app.patterns.EntityRepository;
import org.app.service.entities.Intern;
import org.app.service.entities.Proiecte;

public interface ProiecteInternFeatureDataService extends EntityRepository<Proiecte>{
	
	Proiecte createNewProiect(Integer IDProiect, Integer IDCoordonator);
	
	Intern getInternById(Integer IDIntern);
	
	String getMessage();

}
