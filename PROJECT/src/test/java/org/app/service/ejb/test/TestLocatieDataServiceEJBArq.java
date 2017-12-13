package org.app.service.ejb.test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.Collection;

import javax.ejb.EJB;

import org.app.service.ejb.LocatieService;
import org.app.service.ejb.LocatieServiceEJB;
import org.app.service.entities.Locatie;
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
public class TestLocatieDataServiceEJBArq {
	private static Logger logger = Logger.getLogger(TestLocatieDataServiceEJBArq.class.getName());

	@EJB
	private static LocatieService service;
	
	@Deployment
	public static Archive<?> createDeployment(){
		return ShrinkWrap
				.create(WebArchive.class, "SCRUM-S3-test.war")
				.addPackage(Locatie.class.getPackage())
				.addClass(LocatieService.class)
				.addClass(LocatieServiceEJB.class)
				.addAsResource("META-INF/persistence.xml")
				.addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
		
	}
	
	@Test
	public void test1_GetMessage(){
		logger.info("DEBUG: Junit TESTING: getMessage ...");
		String response = service.getMessage();
		assertNotNull("Data Service failed!", response);
		logger.info("DEBUG: EJB Response ..." + response);
	}
	
	@Test
	public void tes4_GetLocatii(){
		logger.info("DEBUG : Junit TESTIN: testGetLocatii ...");
		
		Collection<Locatie> locatii = service.getLocatii();
		assertTrue("Fail to read locatii!", locatii.size() > 0);
	}
	
	@Test
	public void test3_AddLocatie(){
		logger.info("DEBUG: Junit TESTING: testAddLocatie ...");
		
		Integer locatiiToAdd = 3;
		for(int i=1; i<=locatiiToAdd; i++){
			service.addLocatie(new Locatie(20, "FEAA", Locatie.getListPromo()));
		}
		Collection<Locatie> locatii = service.getLocatii();
		assertTrue("Fail to add locatii!", locatii.size() == locatiiToAdd);
	}
	
	@Test
	public void test2_DeleteLocatie(){
		logger.info("DEBUG: Junit TESTIN: testDeleteLocatie ...");
		
		Collection<Locatie> locatii = service.getLocatii();
		for(Locatie l:locatii)
			service.removeLocatie(l);
		Collection<Locatie> locatiiAfterDelete = service.getLocatii();
		assertTrue("Fail to read locatii!", locatiiAfterDelete.size() == 0);
	}
	
}
