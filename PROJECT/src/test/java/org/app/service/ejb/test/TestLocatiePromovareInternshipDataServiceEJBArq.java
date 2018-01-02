package org.app.service.ejb.test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.util.List;

import javax.ejb.EJB;

import org.app.patterns.EntityRepository;
import org.app.service.ejb.InternshipService;
import org.app.service.ejb.InternshipServiceEJB;
import org.app.service.ejb.LocatiePromovareInternshipDataService;
import org.app.service.ejb.LocatiePromovariInternshipDataServiceEJB;
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
public class TestLocatiePromovareInternshipDataServiceEJBArq {
	private static Logger logger = Logger.getLogger(TestLocatiePromovareInternshipDataServiceEJBArq.class.getName());
	
	@Deployment
	public static Archive<?> createDeployment(){
		return ShrinkWrap
				.create(WebArchive.class, "scrum-test-ejb.war")
				.addPackage(EntityRepository.class.getPackage()).addPackage(Locatie.class.getPackage())
				.addClass(InternshipService.class).addClass(InternshipServiceEJB.class)
				.addClass(LocatiePromovareInternshipDataService.class).addClass(LocatiePromovariInternshipDataServiceEJB.class)
				.addAsResource("META-INF/persistence.xml")
				.addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
	}
	
	@EJB
	private static LocatiePromovareInternshipDataService service;
	
	@Test
	public void test4_GetLocatie(){
		logger.info("DEBUG: Jnuit TESTING: testGetLocatie 26 ...");
		Locatie locatii = service.getById(26);
		assertNotNull("Fail to get Locatie 26!", locatii);
	}
	
	@Test 
	public void test3_CreateNewLocatie(){
		Locatie locatii = service.createNewLocatie(26);
		assertNotNull("Fail to create new locatie in repository!", locatii);
		
		locatii.setNumeLocatie(locatii.getNumeLocatie() + " - changed by test client");
		List<Promovare> promovari = locatii.getPromovari();
		for(Promovare p: promovari)
			p.setModPromovare(p.getModPromovare() + " - changed by test client");	
		locatii = service.add(locatii);
		assertNotNull("Fail to save new locatie in repository!", locatii);
		logger.info("DEBUG createNewLocatie: locatie changed: " + locatii);
		
		locatii = service.getById(26);
				assertNotNull("Fail to find changed locatie in repository!", locatii);
		logger.info("DEBUG createNewLocatie: queried locatie" + locatii);
	}
	
	@Test 
	public void test2_DeleteLocatie(){
		logger.info("DEBUG: Junit TESTING: testDeleteLocatie 26 ...");
		Locatie locatii = service.getById(26);
		if(locatii != null)
			service.remove(locatii);
		locatii = service.getById(26);
		assertNull("Fail to delete Locatie 26", locatii);
	}
}
