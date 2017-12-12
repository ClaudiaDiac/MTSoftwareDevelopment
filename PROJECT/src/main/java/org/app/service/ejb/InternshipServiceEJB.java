package org.app.service.ejb;

import java.util.Collection;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.app.service.entities.Internship;
import org.jboss.logging.Logger;

@Stateless @LocalBean
public class InternshipServiceEJB implements InternshipService{
	private static Logger logger = Logger.getLogger(InternshipService.class);

	@PersistenceContext(unitName="MSD")
	private EntityManager em;
	
	public InternshipServiceEJB(){
}
	@PostConstruct
	public void init(){
		logger.info("POSTCONSTRUCT-INIT : " + this.em);
	}
	@Override
	public Internship addInternship(Internship internshipToAdd){
		em.persist(internshipToAdd);
		em.flush();
		em.refresh(internshipToAdd);
		return internshipToAdd;
	}
	@Override
	public Internship getInternshipByIDInternship(Integer IDInternship){
		return em.find(Internship.class, IDInternship);
	}
	public Collection<Internship> getInternships(){
		List<Internship> internships = em.createQuery("SELECT it FROM Internship it", Internship.class)
				.getResultList();
		return internships;
	}
	
	public String removeInternship(Internship internshipToDelete){
		internshipToDelete = em.merge(internshipToDelete);
		em.remove(internshipToDelete);
		em.flush();
		return "True";
	}
	
	@Override
	public Internship getInternshipByDomeniuInternship(String DomeniuInternship){
		return em.createQuery("SELECT it FROM Internship it WHERE it.DomeniuInternship = :domeniu", Internship.class)
				.setParameter("DomeniuInternship",DomeniuInternship)
				.getSingleResult();
	}
	
	public String getMessage(){
		return "Internship Service is on...";
	}


}
