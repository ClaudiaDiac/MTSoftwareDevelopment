package org.app.service.ejb;

import org.app.patterns.EntityRepository;
import org.app.service.entities.Intern;
import org.app.service.entities.Proiecte;

public interface ProiecteInternEvaluareFinalaDataService extends EntityRepository<Proiecte> {
	
	Proiecte createNewProiect(Integer IDProiect);
	
	Intern getInternById(Integer IDIntern);
	
	String getMessage();

}
