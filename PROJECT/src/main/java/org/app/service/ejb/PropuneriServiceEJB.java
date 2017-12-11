package org.app.service.ejb;

import java.util.Collection;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.app.service.entities.Propuneri;
import org.jboss.logging.Logger;

@Stateless @LocalBean
public class PropuneriServiceEJB implements PropuneriService {
private static Logger logger = Logger.getLogger(PropuneriService.class);
	
	@PersistenceContext(unitName="MSD")
	private EntityManager em;
	
	public PropuneriServiceEJB(){
		
	}
	
	@PostConstruct
	public void init(){
		logger.info("POSTCONSTRUCT-INIT : " + this.em);
	}
	@Override
	public Propuneri addPropuneri(Propuneri propuneriToAdd){
		em.persist(propuneriToAdd);
		em.flush();
		em.refresh(propuneriToAdd);
		return propuneriToAdd;
	}
	@Override 
	public Propuneri getPropuneriByIDIntern(Integer IDIntern){
		return em.find(Propuneri.class, IDIntern);
	}
	public Collection<Propuneri> getPropunere(){
		List<Propuneri> propunere = em.createQuery("SELECT pp FROM Propuneri pp", Propuneri.class)
				.getResultList();
		return propunere;
	}
	
	public String removePropuneri(Propuneri propuneriToDelete){
		propuneriToDelete = em.merge(propuneriToDelete);
		em.remove(propuneriToDelete);
		em.flush();
		return "True";
	}
	@Override
	public Propuneri getPropuneriByNumePost(String NumePost){
		return em.createQuery("SELECT pp FROM Propuneri pp WHERE pp.NumePost = :name", Propuneri.class)
				.setParameter("NumePost", NumePost)
				.getSingleResult();
	}
	
	public String getMessage(){
		return "Propuneri Service is on...";
	}


}
