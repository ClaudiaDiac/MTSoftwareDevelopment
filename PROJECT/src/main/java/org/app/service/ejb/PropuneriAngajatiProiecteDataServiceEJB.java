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
import org.app.service.entities.Angajati;
import org.app.service.entities.Propuneri;
import org.jboss.logging.Logger;

@Stateless @LocalBean
public class PropuneriAngajatiProiecteDataServiceEJB  extends EntityRepositoryBase<Propuneri> implements PropuneriAngajatiProiecteDataService, Serializable{

	private static Logger logger = Logger.getLogger(PropuneriAngajatiProiecteDataServiceEJB.class.getName());
	
	@EJB
	private ProiecteService proiectService;
	
	private EntityRepository<Angajati> angajatRepository;
	
	@PostConstruct
	public void init(){
		angajatRepository = new EntityRepositoryBase<Angajati>(this.em, Angajati.class);
		logger.info("POSTCONSTRUCT-INIT angajatRepository: " + this.angajatRepository);
		logger.info("POSTCONSTRUCT-INIT proiectService: " + this.proiectService);
	}
	
	public Propuneri createNewPropunere(Integer IDIntern){
		Propuneri propunere = new Propuneri(IDIntern, "Mangalu Ionut", 99, "Cyber Secutiry", "Oferta pentru Cyber Security", null, null);
		List<Angajati> angajatPropunere = new ArrayList<>();
				angajatPropunere.add(new Angajati(1, "Creciu Georgiana", 6, ".NET Developer", 2000, "Determinat", "15-04-2016", propunere));
				
				propunere.setAngajat(angajatPropunere);
				this.add(propunere);
				return propunere;
	}
	
	public Angajati getAngajatiById(Integer IDAngajat){
		return angajatRepository.getById(IDAngajat);
	}
	
	public String getMessage(){
		return "PropuneriSprint DataService is working...";
	}

}
