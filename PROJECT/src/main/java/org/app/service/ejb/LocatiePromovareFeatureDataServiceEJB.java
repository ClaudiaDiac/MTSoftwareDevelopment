package org.app.service.ejb;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import org.app.patterns.EntityRepository;
import org.app.patterns.EntityRepositoryBase;
import org.app.service.entities.Locatie;
import org.app.service.entities.Promovare;
import org.jboss.logging.Logger;


@Stateless @LocalBean
public class LocatiePromovareFeatureDataServiceEJB extends EntityRepositoryBase<Locatie> implements LocatiePromovareFeatureDataService {
	private static Logger logger = Logger.getLogger(LocatiePromovareFeatureDataServiceEJB.class.getName());
	
	@EJB
	private InternshipService internshipService;
	
	private EntityRepository<Promovare> promovareRepository;
	
	@PostConstruct
	public void init(){
		promovareRepository = new EntityRepositoryBase<Promovare>(this.em, Promovare.class);
		logger.info("POSTCONSTRUCT-INIT promovareRepository: " + this.promovareRepository);
		logger.info("POSTCONSTRUCT-INIT internshipService: " + this.internshipService);;
	}
	
	public Locatie createNewLocatie(Integer IDLoc){
		Locatie locatie = new Locatie(IDLoc, "FEAA", Locatie.getListPromo());
		List<Promovare> promovariLocatie = new ArrayList<>();
		
		Date dataPromovare = new Date();
		Long interval = (long)(301 * 24 *60 * 60 * 1000);
		
		Integer promovareCount = 3;
		for (int i=0; i<=promovareCount-1; i++){
			promovariLocatie.add(new Promovare(2000, new Date(dataPromovare.getTime() + i * interval), 20, "Program Mentorat", 200, "Luca Ion", "Tester", locatie, Promovare.getInternsip()));
		}
		
		locatie.setPromovari(promovariLocatie);
		this.add(locatie);
		return locatie;
	}
	
	public Promovare getPromovariById(Integer IDPromo){
		return promovareRepository.getById(IDPromo);
	}
	
	public String getMessage(){
		return " LocatieSprint DataService is working...";
	}

}