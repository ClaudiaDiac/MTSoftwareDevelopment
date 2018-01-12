package org.app.service.ejb;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.app.patterns.EntityRepository;
import org.app.patterns.EntityRepositoryBase;
import org.app.service.entities.Aplicanti;
import org.app.service.entities.EvaluareFinala;
import org.app.service.entities.InterviuTehnic;
import org.jboss.logging.Logger;

@Path("interviut")
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

	@GET @Path("/{IDAplicant}")
	@Produces ({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	public InterviuTehnic getById(@PathParam("IDAplicant") Integer IDAplicant){
		InterviuTehnic intt = super.getById(IDAplicant);
		logger.info("**** DEBUG REST getById(" + IDAplicant + ") =" + intt);
		return intt;
	}
	
	@GET
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	public Collection<InterviuTehnic> toCollection(){
		logger.info("**** DEBUG REST toCollection()");
		return super.toCollection();
	}
	
	@POST
	@Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	@Produces ({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	public Collection<InterviuTehnic> addIntoCollection(InterviuTehnic intt){
		super.add(intt);
		return super.toCollection();
	}
	
	@DELETE
	@Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	@Produces ({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public Collection<InterviuTehnic> removeFromCollection(InterviuTehnic intt){
		logger.info("DEBUG: called Remove - interviutehnic: " + intt);
		super.remove(intt);
		return super.toCollection();
	}
	
	@DELETE @Path("/{IDAplicant}")
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public void remove (@PathParam("IDAplicant") Integer IDAplicant){
		logger.info("DEBUG: called REMOVE - ById() for interviutehnic >>>>> simplified ! + id");
		InterviuTehnic intt = super.getById(IDAplicant);
		super.remove(intt);
	}
	
	@PUT @Path("/{IDAplicant}")
	@Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	@Produces ({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	@Override
	public InterviuTehnic add(InterviuTehnic intt){
		intt = super.add(intt);
		return InterviuTehnic.toDTOAggregate(intt);
	}
}
