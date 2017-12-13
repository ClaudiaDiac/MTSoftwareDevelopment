package org.app.service.ejb.test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.util.List;

import javax.ejb.EJB;

import org.app.patterns.EntityRepository;
import org.app.service.ejb.InternshipService;
import org.app.service.ejb.InternshipServiceEJB;
import org.app.service.ejb.LocatiePromovareFeatureDataService;
import org.app.service.ejb.LocatiePromovareFeatureDataServiceEJB;
import org.app.service.entities.Locatie;
import org.app.service.entities.Promovare;
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
public class TestLocatiePromovareFeatureDataServiceEJBArq {
	private static Logger logger = Logger.getLogger(TestLocatiePromovareFeatureDataServiceEJBArq.class.getName());
	
	@Deployment
	public static Archive<?> createDeployment(){
		return ShrinkWrap
				.create(WebArchive.class, "scrum-test-ejb.war")
				.addPackage(EntityRepository.class.getPackage()).addPackage(Locatie.class.getPackage())
				.addClass(InternshipService.class).addClass(InternshipServiceEJB.class)
				.addClass(LocatiePromovareFeatureDataService.class).addClass(LocatiePromovareFeatureDataServiceEJB.class)
				.addAsResource("META-INF/persistence.xml")
				.addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
	}
	
	@EJB
	private static LocatiePromovareFeatureDataService service;

	@Test
	public void test4_GetLocatie(){
		logger.info("DEBUG: Junit TESTING: testGetLocatie 20 ...");
		Locatie locatie = service.getById(20);
		assertNotNull("Fail to Get Locatie 20!", locatie);
	}
	
	@Test
	public void test3_CreateNewLocatie(){
		Locatie locatie = service.createNewLocatie(20);
		assertNotNull("Fail to create new project in repository!", locatie);
		
		locatie.setNumeLocatie(locatie.getNumeLocatie() + " - changed by test client");
		List<Promovare> promovare = locatie.getPromovari();
		
		locatie = service.add(locatie);
		assertNotNull("Fail to save new locatie in repository!", locatie);
		logger.info("DEBUG createNewLocatie: locatie changed:" + locatie);
		
		locatie = service.getById(20);
		assertNotNull("Fail to find changed project in repository!", locatie);
		logger.info("DEBUG createNewLocatie: queried locatie" + locatie);
	}
	
	@Test
	public void test2_DeleteLocatie(){
		logger.info("DEBUG: Junit TESTING: testDeleteLocatie 20 ...");
		Locatie locatie = service.getById(20);
		if(locatie != null)
			service.remove(locatie);
		locatie = service.getById(20);
		assertNull("Fail to delete Locatie 20!", locatie);
	}
}
