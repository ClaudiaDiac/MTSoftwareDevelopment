package org.app.service.ejb;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
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
import org.app.service.entities.Intern;
import org.app.service.entities.Proiecte;
import org.jboss.logging.Logger;

@Path("proiect")
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
	
	@POST @Path("/new/{IDProiect}")
	@Produces ({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	
	public Proiecte createNewProiect( @PathParam("IDProiect")Integer IDProiect){
		Proiecte proiect = new Proiecte(IDProiect, "Proiect Nou", 35, "Craciun Laurentiu", null);
		List<Intern> internProiecte = new ArrayList<>();
		 internProiecte.add(new Intern(105, "Secleanu Alexandru", 15, "Parlu Codrin", 3005, null, proiect, null));
		 
		 proiect.setInterni(internProiecte);
		 this.add(proiect);
		 return proiect;
	}
	
	public Intern getInternById (Integer IDIntern){
		return internRepository.getById(IDIntern);
	}
	
	public String getMessage(){
		return "ProjectSprint Data Service is working...";
	}
	
	@GET @Path("/{IDProiect}")
	@Produces ({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	public Proiecte getById(@PathParam("IDProiect") Integer IDProiect){
		Proiecte proiect = super.getById(IDProiect);
		logger.info("**** DEBUG REST getById(" + IDProiect + ") =" + proiect);
		return proiect;
	}

	@GET
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	public Collection<Proiecte> toCollection(){
		logger.info("**** DEBUG REST toCollection()");
		return super.toCollection();
	}

	@POST
	@Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	@Produces ({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	public Collection<Proiecte> addIntoCollection(Proiecte proiect){
		super.add(proiect);
		return super.toCollection();
	}
	
	@DELETE
	@Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	@Produces ({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public Collection<Proiecte> removeFromCollection(Proiecte proiect){
		logger.info("DEBUG: called Remove - proiect: " + proiect);
		super.remove(proiect);
		return super.toCollection();
	}
	
	@DELETE @Path("/{IDProiect}")
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public void remove (@PathParam("IDProiect") Integer IDProiect){
		logger.info("DEBUG: called REMOVE - ById() for proiecte >>>>> simplified ! + id");
		Proiecte proiect = super.getById(IDProiect);
		super.remove(proiect);
	}
	
	@PUT @Path("/{IDProiect}")
	@Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	@Produces ({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	@Override
	public Proiecte add(Proiecte proiect){
		proiect = super.add(proiect);
		return Proiecte.toDOAggregate(proiect);
		//return proiect;
	}
}

