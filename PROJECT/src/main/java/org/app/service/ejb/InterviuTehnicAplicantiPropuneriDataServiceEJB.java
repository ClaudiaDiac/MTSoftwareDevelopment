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
import org.app.service.entities.Aplicanti;
import org.app.service.entities.InterviuTehnic;
import org.jboss.logging.Logger;

@Stateless @LocalBean
public class InterviuTehnicAplicantiPropuneriDataServiceEJB extends EntityRepositoryBase<InterviuTehnic> implements InterviuTehnicAplicantiPropuneriDataService, Serializable{

	private static Logger logger = Logger.getLogger(InterviuTehnicAplicantiPropuneriDataServiceEJB.class.getName());
	
	@EJB
	private PropuneriService propunereService;
	
	private EntityRepository<Aplicanti> aplicantRepository;
	
	@PostConstruct
	public void init(){
		aplicantRepository = new EntityRepositoryBase<Aplicanti>(this.em, Aplicanti.class);
		logger.info("POSTCONSTRUCT-INIT aplicantRepository: " + this.aplicantRepository);
		logger.info("POSTCONSTRUCT-INIT propunereService: " + this.propunereService);
	}
	
	public InterviuTehnic createNewInterviuT(Integer IDAplicant){
		Date its = new Date();
		Long interval = (long) (301 * 24 * 60 *60 * 1000);
		InterviuTehnic ittehnic = new InterviuTehnic(IDAplicant, "Olaru Gabriela", new Date(its.getTime() + 65 * interval), "Software Development",  1330, 9, "Acceptat", null, null);
		List<Aplicanti> aplicantInterviuTehnic = new ArrayList<>();
		
		aplicantInterviuTehnic.add(new Aplicanti(IDAplicant, "Olaru Gabriela", "05-06-2018", 745265894, "olarug@gmail.com", "Facultatea de Informatica", 3, "Software Development", "15-06-2018", "Da", null, ittehnic ));
		
		ittehnic.setAplicant(aplicantInterviuTehnic);
		this.add(ittehnic);
		return ittehnic;
		
	}
	
	public Aplicanti getAplicantById(Integer IDAplicant){
		return aplicantRepository.getById(IDAplicant);
	}
	
	public String getMessage(){
		return "InterviuTehnicSprint DataService is working...";
	}

}
