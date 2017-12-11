package org.app.service.ejb;

import java.util.Collection;
import java.util.List;


import javax.annotation.PostConstruct;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.app.service.entities.EvaluareFinala;
import org.jboss.logging.Logger;

@Stateless @LocalBean
public class EvaluareFinalaServiceEJB implements EvaluareFinalaService {
	private static Logger logger = Logger.getLogger(EvaluareFinalaService.class);

	@PersistenceContext(unitName="MSD")
	private EntityManager em;
	
	public EvaluareFinalaServiceEJB(){
}
	@PostConstruct
	public void init(){
		logger.info("POSTCONSTRUCT-INIT : " + this.em);
	}
	@Override
	public EvaluareFinala addEvaluareFinala(EvaluareFinala evfToAdd){
		em.persist(evfToAdd);
		em.flush();
		em.refresh(evfToAdd);
		return evfToAdd;
	}
	@Override
	public EvaluareFinala getEvaluareFinalaByIDIntern(Integer IDIntern){
		return em.find(EvaluareFinala.class, IDIntern);
	}
	public Collection<EvaluareFinala> getEvaluariFinale(){
		List<EvaluareFinala> evaluarifinale = em.createQuery("SELECT ef FROM EvaluareFinala", EvaluareFinala.class)
				.getResultList();
		return evaluarifinale;
	}
	public String removeEvaluareFinala(EvaluareFinala evfToDelete){
		evfToDelete = em.merge(evfToDelete);
		em.remove(evfToDelete);
		em.flush();
		return "True";
	}
	
	@Override
	public EvaluareFinala getEvaluareFinalaByNumeIntern(String NumeIntern){
		return em.createQuery("SELECT ef FROM EvaluareFinala ef WHERE ef.NumeIntern = :name", EvaluareFinala.class)
				.setParameter("NumeIntern", NumeIntern)
				.getSingleResult();
	}
	
	public String getMessage(){
		return "EvaluareFinala Service is on...";
	}


}
