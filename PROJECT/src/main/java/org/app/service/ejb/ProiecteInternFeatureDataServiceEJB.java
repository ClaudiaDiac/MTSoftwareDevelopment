package org.app.service.ejb;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;

import org.app.patterns.EntityRepository;
import org.app.patterns.EntityRepositoryBase;
import org.app.service.entities.Intern;
import org.app.service.entities.Proiecte;
import org.jboss.logging.Logger;

public class ProiecteInternFeatureDataServiceEJB extends EntityRepositoryBase<Proiecte> implements ProiecteInternFeatureDataService{
	private static Logger logger = Logger.getLogger(ProiecteInternFeatureDataServiceEJB.class.getName());
	
	@EJB
	private EvaluareFinalaService evfinalService;
	
	private EntityRepository<Intern> internRepository;
	
	@PostConstruct
	public void init(){
		internRepository = new EntityRepositoryBase<Intern>(this.em, Intern.class);
		logger.info("POSTCONSTRUCT-INIT internRepository: " + this.internRepository);
		logger.info("POSTCONSTRUCT-INIT evfinalService: " + this.evfinalService);
	}
	
	public Proiecte createNewProiect(Integer IDProiect, Integer IDCoordonator){
		Proiecte proiect = new Proiecte(IDProiect, "Proiect Nou", IDCoordonator, "Nume Coordonator", null);
				List<Intern> interniProiect = new ArrayList<>();
				
				Integer interniCount = 3;
				for(int i=0; i<=interniCount-1; i++){
					interniProiect.add(new Intern(103, " ", 13, " ", IDProiect, null, proiect, null));
				}
				proiect.setInterni(interniProiect);
				this.add(proiect);
				return proiect;
	}
	
	public Intern getInternById(Integer IDIntern){
		return internRepository.getById(IDIntern);
	}
	
	public String getMessage(){
		return "ProiecteSprint DataService is working ...";
	}

}
