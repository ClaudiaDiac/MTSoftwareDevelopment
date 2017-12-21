package org.app.service.ejb;

import org.app.patterns.EntityRepository;
import org.app.service.entities.Intern;
import org.app.service.entities.Proiecte;

public interface ProiecteInternFeatureDataService {
	
	Proiecte createNewProiect(Integer IDProiect, Integer IDCoordonator);
	
	String getMessage();

}
