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
public class LocatiePromovariInternshipDataServiceEJB extends EntityRepositoryBase<Locatie> implements LocatiePromovareInternshipDataService {
	private static Logger logger = Logger.getLogger(LocatiePromovariInternshipDataServiceEJB.class.getName());
	
	@EJB
	private InternshipService ittehnicService;
	
	private EntityRepository<Promovare> promovareRepository;
	
	@PostConstruct
	public void init(){
		promovareRepository = new EntityRepositoryBase<Promovare>(this.em, Promovare.class);
		logger.info("POSTCONSTRUCT-INIT promovareRepository: " + this.promovareRepository);
		logger.info("POSTCONSTRUCT-INIT ittehnicService: " + this.ittehnicService);
	}
	
	public Locatie createNewLocatie(Integer IDLocatie){
		Locatie locatii = new Locatie(IDLocatie, " Noua Locatie", null);
		List<Promovare> promovariLocatie = new ArrayList<>();
	//	Integer promovariCount = 3;
	//	Date dataPromovare = new Date();
	//	Long interval = (long) (301 * 24 *60 * 60 * 1000);
	//	for (int i=0; i<=promovariCount-1; i++){
			promovariLocatie.add(new Promovare(2005,  "", 25, "Mod Promovare", 205, "Nume Promoter", "Domeniu", locatii, null ));
	//	}
		locatii.setPromovari(promovariLocatie);
		this.add(locatii);
		return locatii;
	}
	 
	public Promovare getPromovareById(Integer IDPromovare){
		return promovareRepository.getById(IDPromovare);
	}
	
	public String getMessage(){
		return "LocatieSprint DataService is working...";
	}

}
