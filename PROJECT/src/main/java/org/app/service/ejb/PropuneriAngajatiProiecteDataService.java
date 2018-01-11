package org.app.service.ejb;

import javax.ejb.Remote;

import org.app.patterns.EntityRepository;
import org.app.service.entities.Angajati;
import org.app.service.entities.Propuneri;

@Remote
public interface PropuneriAngajatiProiecteDataService extends EntityRepository<Propuneri>{

	Propuneri createNewPropunere(Integer IDIntern);
	
	Angajati getAngajatiById(Integer IDAngajat);
	
	String getMessage();
}
