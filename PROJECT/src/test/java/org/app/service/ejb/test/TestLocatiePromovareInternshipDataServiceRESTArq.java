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
import org.app.service.ejb.LocatiePromovareInternshipDataService;
import org.app.service.entities.Locatie;
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
public class TestLocatiePromovareInternshipDataServiceRESTArq {
	private static Logger logger = Logger.getLogger(TestLocatiePromovareInternshipDataServiceRESTArq.class.getName());
	
	private static String serviceURL = "http://localhost:8080/PROJECT/rest/locatie";
	
	@Deployment
	public static Archive<?> createDeployment(){
		return ShrinkWrap
				.create(WebArchive.class, "msd-s4-test.war")
				.addPackage(Locatie.class.getPackage())
				.addPackage(LocatiePromovareInternshipDataService.class.getPackage())
				.addPackage(EntityRepository.class.getPackage())
				.addPackage(ApplicationConfig.class.getPackage())
				.addAsResource("META-INF/persistence.xml")
				.addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml");
	}
	
	@Test
	public void test1_GetById(){
		String resourceURL = serviceURL + "/20";
		logger.info("DEBUG: Jnuit TESTING: test1_GetMessage ...");
		Locatie locatii = ClientBuilder.newClient().target(resourceURL).request().accept(MediaType.APPLICATION_JSON).get().readEntity(Locatie.class);
		assertNotNull("Data service failed!", locatii);
		logger.info("DEBUG: REST Response..." + locatii);
	}
	
	@Test
	public void test4_GetLocatie(){
		logger.info("DEBUG: Jnuit TESTING: test4_GetLocatie ...");
		Collection<Locatie> locatii = ClientBuilder.newClient()
		.target(serviceURL)
		.request().get()
		.readEntity(new GenericType<Collection<Locatie>>(){});
		assertTrue("Fail to read Proiecte!", locatii.size() > 0);
		locatii.stream().forEach(System.out::println);
	}
	
	@Test
	public void test3_AddLocatie(){
		logger.info("DEBUG: Junit TESTING: test3_AddLocatie ...");
		Client client = ClientBuilder.newClient();
		Collection<Locatie> locatiie;
		Locatie locatii;
		locatii = new Locatie(26, null, null);
		locatiie = client.target(serviceURL)
				.request().accept(MediaType.APPLICATION_JSON)
				.post(Entity.entity(locatii, MediaType.APPLICATION_JSON))
				.readEntity(new GenericType<Collection<Locatie>>(){});
		assertTrue("Fail to read Proiecte!", locatiie.size() > 0);
		locatiie = client.target(serviceURL)
				.request().get()
				.readEntity(new GenericType<Collection<Locatie>>(){});
		assertTrue("Fail to add Proiecte!", locatiie.size() >= 1);
		locatiie.stream().forEach(System.out::println);
	}
	
	@Test
	public void test2_DeleteProiecte(){
		String resourceURL = serviceURL + "/24";
		logger.info("DEBUG: Junit TESTING: test2_DeleteProiect ...");
		Client client = ClientBuilder.newClient();
		Collection<Locatie> locatii = client.target(serviceURL)
				.request().get()
				.readEntity(new GenericType<Collection<Locatie>>(){});
		for (Locatie l: locatii){
			client.target(resourceURL + l.getNumeLocatie()).request().delete();
		}
		
		Collection<Locatie> locatieAfterDelete = client.target(serviceURL)
				.request().get()
				.readEntity(new GenericType<Collection<Locatie>>(){});
		assertTrue("Fail to read Proiecte!", locatieAfterDelete.size() == 0);
	}
	
	@Test
	public void test5_UpdateLocatie(){
		String resourceURL = serviceURL + "/21";
		
		Client client = ClientBuilder.newClient();
		
		Locatie locatii = client.target(resourceURL)
				.request().accept(MediaType.APPLICATION_JSON)
				.get().readEntity(Locatie.class);
		
		assertNotNull("REST Data Service failed!", locatii);
		
		locatii.setNumeLocatie(locatii.getNumeLocatie() + "_UPDATED");
		locatii = client.target(resourceURL)
				.request().accept(MediaType.APPLICATION_JSON)
				.put(Entity.entity(locatii, MediaType.APPLICATION_JSON))
				.readEntity(Locatie.class);
		
		assertNotNull("Rest Data Service failed!", locatii);
	}
}
