package org.app.service.ejb;

import org.app.patterns.EntityRepository;
import org.app.service.entities.Angajati;
import org.app.service.entities.Propuneri;

public interface PropuneriAngajatiProiecteDataService extends EntityRepository<Propuneri>{

	Propuneri createNewPropunere(Integer IDIntern);
	
	Angajati getAngajatiById(Integer IDAngajat);
	
	String getMessage();
}
