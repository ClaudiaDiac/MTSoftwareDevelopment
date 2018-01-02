package org.app.service.ejb;

import org.app.patterns.EntityRepository;
import org.app.service.entities.Internship;
import org.app.service.entities.Promovare;

public interface InternshipPromovariLocatieDataServie extends EntityRepository<Internship>{

	Internship createNewInternship(Integer IDInternship);
	
	Promovare getPromovariById(Integer IDPromovare);
	
	String getMessage();
}
