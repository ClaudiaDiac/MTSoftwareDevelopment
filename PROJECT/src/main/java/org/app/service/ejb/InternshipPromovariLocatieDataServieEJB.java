package org.app.service.ejb;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import org.app.patterns.EntityRepository;
import org.app.patterns.EntityRepositoryBase;
import org.app.service.entities.Internship;
import org.app.service.entities.Promovare;
import org.jboss.logging.Logger;


@Stateless @LocalBean
public class InternshipPromovariLocatieDataServieEJB extends EntityRepositoryBase<Internship> implements InternshipPromovariLocatieDataServie, Serializable {

	private static Logger logger = Logger.getLogger(InternshipPromovariLocatieDataServieEJB.class.getName());
	
	@EJB
	private LocatieService locatiiService;
	
	private EntityRepository<Promovare> promovariRepository;
	
	@PostConstruct
	public void init(){
		promovariRepository = new EntityRepositoryBase<Promovare>(this.em, Promovare.class);
		logger.info("POSTCONSTRUCT-INIT promovariRepository: " + this.promovariRepository);
		logger.info("POSTCONSTRUCT-INIT locatiiService: " + this.locatiiService);
	}
	
	public Internship createNewInternship(Integer IDInternship){
		Date its = new Date();
		Long interval = (long) (301 * 24 * 60 *60 * 1000);
		
		Internship intersip = new Internship(IDInternship, "Java Programmer", "3 luni", new Date(its.getTime() +  68 * interval), new Date(its.getTime() + 101 * interval), 1660, null, null );
		List<Promovare> promovariInternship = new ArrayList<>();
		
		promovariInternship.add(new Promovare(2006, "10-05-2018", 26, "Prezentare Curs", 206, "Olariu Matei", "Java Programming", null, intersip));
		
		intersip.setPromovari(promovariInternship);
		this.add(intersip);
		return intersip;
	}
	
	public Promovare getPromovariById(Integer IDPromovare){
		return promovariRepository.getById(IDPromovare);
	}
	
	public String getMessage(){
		return "InternshipSprint DataService is working...";
	}
	
}
