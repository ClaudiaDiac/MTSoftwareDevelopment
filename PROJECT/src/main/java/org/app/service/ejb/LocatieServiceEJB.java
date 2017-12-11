package org.app.service.ejb;

import java.util.Collection;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.app.service.entities.Locatie;
import org.jboss.logging.Logger;

@Stateless @LocalBean
public class LocatieServiceEJB implements LocatieService{
private static Logger logger = Logger.getLogger(LocatieService.class);
	
	@PersistenceContext(unitName="MSD")
	private EntityManager em;
	
	public LocatieServiceEJB(){
}
	@PostConstruct
	public void init(){
		logger.info("POSTCONSTRUCT-INIT : " + this.em);
	}
	@Override
	public Locatie addLocatie(Locatie locatieToAdd){
		em.persist(locatieToAdd);
		em.flush();
		em.refresh(locatieToAdd);
		return locatieToAdd;
	}
	@Override
	public Locatie getLocatieByIDLocatie(Integer IDLocatie){
		return em.find(Locatie.class, IDLocatie);
	}
	public Collection<Locatie> getLocatii(){
		List<Locatie> locatii = em.createQuery("SELECT l FROM Locatie l", Locatie.class)
				.getResultList();
		return locatii;
	}
	public String removeLocatie(Locatie locatieToDelete){
		locatieToDelete = em.merge(locatieToDelete);
		em.remove(locatieToDelete);
		em.flush();
		return "True";
	}
	@Override
	public Locatie getLocatieByNumeLocatie(String NumeLocatie){
		return em.createQuery("SELECT l FROM Locatie l WHERE l.NumeLocatie= :name", Locatie.class)
				.setParameter("NumeLocatie", NumeLocatie)
				.getSingleResult();
	}
	
	public String getMessage(){
		return "Locatie Service is on...";
	}


}
