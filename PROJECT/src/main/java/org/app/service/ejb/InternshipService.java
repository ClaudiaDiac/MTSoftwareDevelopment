package org.app.service.ejb;

import java.util.Collection;

import javax.ejb.Remote;

import org.app.service.entities.Internship;

@Remote
public interface InternshipService {
		Internship addInternship(Internship internshipToAdd);

		String removeInternship(Internship internshipToDelete);
		
		Internship getInternshipByIDInternship(Integer IDInternship);
		Collection<Internship> getInternships();
		
		Internship getInternshipByDomeniuInternship(String DomeniuInternship);
		
		String getMessage();


}
