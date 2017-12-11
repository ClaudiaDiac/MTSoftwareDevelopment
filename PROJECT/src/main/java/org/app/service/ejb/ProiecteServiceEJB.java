package org.app.service.ejb;

import java.util.Collection;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.app.service.entities.Proiecte;
import org.jboss.logging.Logger;

@Stateless @LocalBean
public class ProiecteServiceEJB implements ProiecteService{
private static Logger logger = Logger.getLogger(ProiecteService.class);
	
	@PersistenceContext(unitName="MSD")
	private EntityManager em;
	public ProiecteServiceEJB(){
	}
	
	@PostConstruct
	public void init(){
		logger.info("POSTCONSTRUCT-INIT : " + this.em);
	}
	@Override
	public Proiecte addProiecte(Proiecte proiecteToAdd){
		em.persist(proiecteToAdd);
		em.flush();
		em.refresh(proiecteToAdd);
		return proiecteToAdd;
	}
	@Override
	public Proiecte getProiecteByIDProiect(Integer IDProiect){
		return em.find(Proiecte.class, IDProiect);
	}
	public Collection<Proiecte> getProiect(){
		List<Proiecte> proiect = em.createQuery("SELECT p FROM Proiecte p", Proiecte.class)
				.getResultList();
		return proiect;
	}
	public String removeProiecte(Proiecte proiecteToDelete){
		proiecteToDelete = em.merge(proiecteToDelete);
		em.remove(proiecteToDelete);
		em.flush();
		return "True";
	}
	@Override
	public Proiecte getProiecteByNumeProiect(String NumeProiect){
		return em.createQuery("SELECT p FROM Proiecte p WHERE p.NumeProiect = :name", Proiecte.class)
				.setParameter("NumeProiect", NumeProiect)
				.getSingleResult();
	}
	
	public String getMessage(){
		return "Proiecte Service is on...";
	}


}
