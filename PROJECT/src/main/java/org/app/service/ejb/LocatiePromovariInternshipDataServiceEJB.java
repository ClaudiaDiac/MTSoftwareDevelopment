package org.app.service.ejb;

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
import org.app.service.entities.Locatie;
import org.app.service.entities.Proiecte;
import org.app.service.entities.Promovare;
import org.jboss.logging.Logger;

@Path ("locatie")
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
			promovariLocatie.add(new Promovare(2005, "15-05-2018" , 25, "Mod Promovare", 205, "Nume Promoter", "Domeniu", locatii, null ));
	//	}
		locatii.setPromovari(promovariLocatie);
		this.add(locatii);
		return locatii;
	}
	 
	@GET @Path("/{IDPromovare}")
	@Produces ({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
		public Promovare getPromovareById(@PathParam("IDPromovare")Integer IDPromovare){
		return promovareRepository.getById(IDPromovare);
	}
	
	public String getMessage(){
		return "LocatieSprint DataService is working...";
	}

	@GET @Path("/{IDLocatie}")
	@Produces ({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	public Locatie getById(@PathParam("IDLocatie") Integer IDLocatie){
		Locatie locatii = super.getById(IDLocatie);
		logger.info("**** DEBUG REST getById(" + IDLocatie + ") =" + locatii);
		return locatii;
	}
	
	@GET
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	public Collection<Locatie> toCollection(){
		logger.info("**** DEBUG REST toCollection()");
		return super.toCollection();
	}
	
	@POST
	@Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	@Produces ({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	public Collection<Locatie> addIntoCollection(Locatie locatii){
		super.add(locatii);
		return super.toCollection();
	}
	
	@DELETE
	@Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	@Produces ({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public Collection<Locatie> removeFromCollection(Locatie locatii){
		logger.info("DEBUG: called Remove - locatie: " + locatii);
		super.remove(locatii);
		return super.toCollection();
	}
	
	@DELETE @Path("/{IDLocatie}")
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public void remove (@PathParam("IDLocatie") Integer IDLocatie){
		logger.info("DEBUG: called REMOVE - ById() for proiecte >>>>> simplified ! + id");
		Locatie locatii = super.getById(IDLocatie);
		super.remove(locatii);
	}
	
	@PUT @Path("/{IDLocatie}")
	@Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	@Produces ({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	@Override
	public Locatie add(Locatie locatii){
		locatii = super.add(locatii);
		return Locatie.toDTOAggregate(locatii);
	}
}
