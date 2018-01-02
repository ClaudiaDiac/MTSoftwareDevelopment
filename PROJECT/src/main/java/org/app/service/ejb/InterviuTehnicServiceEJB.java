package org.app.service.ejb;

import java.util.Collection;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.app.service.entities.InterviuTehnic;
import org.jboss.logging.Logger;

@Stateless @LocalBean
public class InterviuTehnicServiceEJB implements InterviuTehnicService {
	public static Logger logger = Logger.getLogger(InterviuTehnicService.class);

	@PersistenceContext(unitName = "MSD")
	private EntityManager em;
	
	public InterviuTehnicServiceEJB(){
}
	@PostConstruct
	public void init(){
		logger.info("POSTCONSTRUCT-INIT : " + this.em);
	}
	@Override
	public InterviuTehnic addInterviuTehnic(InterviuTehnic itToAdd){
		em.persist(itToAdd);
//		em.flush();
//		em.refresh(itToAdd);
		return itToAdd;
	}
	@Override
	public InterviuTehnic getInterviuTehnicByIDAplicant(Integer IDAplicant){
		return em.find(InterviuTehnic.class, IDAplicant);
		}
	public Collection<InterviuTehnic> getInterviuriTehnice(){
		List<InterviuTehnic> interviuritehnice = em.createQuery("SELECT ic FROM InterviuTehnic ic", InterviuTehnic.class)
			.getResultList();
	return interviuritehnice;
	}
	
	public String removeInterviuTehnic(InterviuTehnic itToDelete){
		itToDelete = em.merge(itToDelete);
		em.remove(itToDelete);
		em.flush();
		return "True";
	}
	
	
	public String getMessage(){
		return "InterviuTehnic Service is on...";
	}


}
