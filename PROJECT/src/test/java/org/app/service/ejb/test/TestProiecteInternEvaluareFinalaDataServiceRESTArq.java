package org.app.service.ejb.test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.Collection;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;

import org.app.patterns.EntityRepository;
import org.app.service.ejb.ProiecteInternEvaluareFinalaDataService;
import org.app.service.entities.Proiecte;
import org.app.service.rest.ApplicationConfig;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.logging.Logger;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;

@RunWith(Arquillian.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestProiecteInternEvaluareFinalaDataServiceRESTArq {
	private static Logger logger = Logger.getLogger(TestProiecteInternEvaluareFinalaDataServiceRESTArq.class.getName());
	
	private static String serviceURL = "http://localhost:8080/PROJECT/rest/locatie";
	
	@Deployment
	public static Archive<?> createDeployment(){
		return ShrinkWrap
				.create(WebArchive.class, "msd-s4-test.war")
				.addPackage(Proiecte.class.getPackage())
				.addPackage(ProiecteInternEvaluareFinalaDataService.class.getPackage())
				.addPackage(EntityRepository.class.getPackage())
				.addPackage(ApplicationConfig.class.getPackage())
				.addAsResource("META-INF/pesistence.xml")
				.addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml");
	}
	
	@Test
	public void test1_GetById(){
		String resourceURL = serviceURL + "/3001";
		logger.info("DEBUG: Jnuit TESTING: test1_GetMessage ...");
		Proiecte proiect = ClientBuilder.newClient().target(resourceURL).request().accept(MediaType.APPLICATION_JSON).get().readEntity(Proiecte.class);
		assertNotNull("Data service failed!", proiect);
		logger.info("DEBUG: REST Response..." + proiect);
	}
	
	@Test
	public void test4_GetProiect(){
		logger.info("DEBUG: Jnuit TESTING: test4_GetProiect ...");
		Collection<Proiecte> proiect = ClientBuilder.newClient()
		.target(serviceURL)
		.request().get()
		.readEntity(new GenericType<Collection<Proiecte>>(){});
		assertTrue("Fail to read Proiecte!", proiect.size() > 0);
		proiect.stream().forEach(System.out::println);
	}
	
	@Test
	public void test3_AddProiect(){
		logger.info("DEBUG: Junit TESTING: test3_AddLocatie ...");
		Client client = ClientBuilder.newClient();
		Collection<Proiecte> proiecte;
		Proiecte proiect;
		proiect = new Proiecte(3006, "Nou Proiect modelare", 36, "Cojocaru Diana", null);
		proiecte = client.target(serviceURL)
				.request().accept(MediaType.APPLICATION_JSON)
				.post(Entity.entity(proiect, MediaType.APPLICATION_JSON))
				.readEntity(new GenericType<Collection<Proiecte>>(){});
		assertTrue("Fail to read Proiecte!", proiecte.size() > 0);
		proiecte = client.target(serviceURL)
				.request().get()
				.readEntity(new GenericType<Collection<Proiecte>>(){});
		assertTrue("Fail to add Proiecte!", proiecte.size() >= 1);
		proiecte.stream().forEach(System.out::println);
	}
	
	@Test
	public void test2_DeleteProiect(){
		String resourceURL = serviceURL + "/3005";
		logger.info("DEBUG: Junit TESTING: test2_DeleteProiect ...");
		Client client = ClientBuilder.newClient();
		Collection<Proiecte> proiect = client.target(serviceURL)
				.request().get()
				.readEntity(new GenericType<Collection<Proiecte>>(){});
		for (Proiecte p: proiect){
			client.target(resourceURL + p.getNumeProiect()).request().delete();
		}
		
		Collection<Proiecte> proiectAfterDelete = client.target(serviceURL)
				.request().get()
				.readEntity(new GenericType<Collection<Proiecte>>(){});
		assertTrue("Fail to read Proiecte!", proiectAfterDelete.size() == 0);
	}
	
	@Test
	public void test5_UpdateLocatie(){
		String resourceURL = serviceURL + "/3000";
		
		Client client = ClientBuilder.newClient();
		
		Proiecte proiect = client.target(resourceURL)
				.request().accept(MediaType.APPLICATION_JSON)
				.get().readEntity(Proiecte.class);
		
		assertNotNull("REST Data Service failed!", proiect);
		
		proiect.setNumeProiect(proiect.getNumeProiect() + "_UPDATED");
		proiect = client.target(resourceURL)
				.request().accept(MediaType.APPLICATION_JSON)
				.put(Entity.entity(proiect, MediaType.APPLICATION_JSON))
				.readEntity(Proiecte.class);
		
		assertNotNull("Rest Data Service failed!", proiect);
	}
	
}
