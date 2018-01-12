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
import org.app.service.entities.EvaluareFinala;
import org.app.service.entities.Locatie;
import org.app.service.entities.Propuneri;
import org.jboss.logging.Logger;

@Path("evaluaref")
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
	
	@GET @Path("/{IDIntern}")
	@Produces ({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	public EvaluareFinala getById(@PathParam("IDIntern") Integer IDIntern){
		EvaluareFinala evf = super.getById(IDIntern);
		logger.info("**** DEBUG REST getById(" + IDIntern + ") =" + evf);
		return evf;
	}
	
	@GET
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	public Collection<EvaluareFinala> toCollection(){
		logger.info("**** DEBUG REST toCollection()");
		return super.toCollection();
	}
	
	@POST
	@Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	@Produces ({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	public Collection<EvaluareFinala> addIntoCollection(EvaluareFinala evf){
		super.add(evf);
		return super.toCollection();
	}
	
	@DELETE
	@Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	@Produces ({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public Collection<EvaluareFinala> removeFromCollection(EvaluareFinala evf){
		logger.info("DEBUG: called Remove - evaluaref: " + evf);
		super.remove(evf);
		return super.toCollection();
	}
	
	@DELETE @Path("/{IDIntern}")
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public void remove (@PathParam("IDIntern") Integer IDIntern){
		logger.info("DEBUG: called REMOVE - ById() for evaluaref >>>>> simplified ! + id");
		EvaluareFinala evf = super.getById(IDIntern);
		super.remove(evf);
	}
	
	@PUT @Path("/{IDIntern}")
	@Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	@Produces ({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	@Override
	public EvaluareFinala add(EvaluareFinala evf){
		evf = super.add(evf);
		return EvaluareFinala.toDTOAggregate(evf);
	}
}
