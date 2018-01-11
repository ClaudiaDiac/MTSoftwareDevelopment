package org.app.service.ejb;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import org.app.patterns.EntityRepository;
import org.app.patterns.EntityRepositoryBase;
import org.app.service.entities.EvaluareFinala;
import org.app.service.entities.Propuneri;
import org.jboss.logging.Logger;

@Stateless @LocalBean
public class EvaluareFinalaPropuneriInterviuTehnicDataServiceEJB extends EntityRepositoryBase<EvaluareFinala> implements EvaluareFinalaPropuneriInterviuTehnicDataService, Serializable{

	private static Logger logger = Logger.getLogger(EvaluareFinalaPropuneriInterviuTehnicDataServiceEJB.class.getName());
	
	@EJB
	private InterviuTehnicService itService;
	
	private EntityRepository<Propuneri> propunereRepository;
	
	@PostConstruct
	public void init(){
		
		propunereRepository = new EntityRepositoryBase<Propuneri>(this.em, Propuneri.class);
		logger.info("POSTCONSTRUCT-INIT propunereRepository: " + this.propunereRepository);
		logger.info("POSTCONSTRUCT-INIT itService: " + this.itService);
	}
	
	public EvaluareFinala createNewEvaluareFinala(Integer IDIntern){
		EvaluareFinala evfinal = new EvaluareFinala(IDIntern, "Miru Paul", 3003, "Pozitiv", "Acceptat", null, null);
		List<Propuneri> propunereEvaluareFinala = new ArrayList<>();
		
		propunereEvaluareFinala.add(new Propuneri(IDIntern, "Miru Paul", 8, "Data Base Modeler", "Oferta pentru Data Base Modeler", evfinal, null));
		evfinal.setPropunere(propunereEvaluareFinala);
		this.add(evfinal);
		return evfinal;
	}
	
	public Propuneri getPropuneriById(Integer IDIntern){
		return propunereRepository.getById(IDIntern);
	}
	
	public String getMessage(){
		return "EvaluareFinalaSprint DataService is working...";
	}
}
