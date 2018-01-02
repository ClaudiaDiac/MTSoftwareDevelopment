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
import org.app.service.entities.Intern;
import org.app.service.entities.Proiecte;
import org.jboss.logging.Logger;

@Stateless @LocalBean
public class ProiecteInterniEvaluareFinalaDataServiceEJB extends EntityRepositoryBase<Proiecte> implements ProiecteInternEvaluareFinalaDataService, Serializable{

	private static Logger logger = Logger.getLogger(ProiecteInterniEvaluareFinalaDataServiceEJB.class.getName());

	@EJB
	private EvaluareFinalaService efDataService;
	
	private EntityRepository<Intern> internRepository;
	
	@PostConstruct
	public void init(){
		internRepository = new EntityRepositoryBase<Intern>(this.em, Intern.class);
		logger.info("POSTCONSTRUCT-INIT internRepository: " + this.internRepository);
		logger.info("POSTCONSTRUCT-INIT efDataService: " + this.efDataService);
	}
	
	public Proiecte createNewProiect(Integer IDProiect){
		Proiecte proiect = new Proiecte(IDProiect, "Proiect Nou", 35, "Craciun Laurentiu", null);
		List<Intern> internProiecte = new ArrayList<>();
		 internProiecte.add(new Intern(105, "Secleanu Alexandru", 15, "Parlu Codrin", 3005, null, proiect, null));
		 proiect.setInterni(internProiecte);
		 this.add(proiect);
		 return proiect;
	}
	
	public Intern getInternById(Integer IDIntern){
		return internRepository.getById(IDIntern);
	}
	
	public String getMessage(){
		return "ProjectSprint Data Service isworking...";
	}

	
}

