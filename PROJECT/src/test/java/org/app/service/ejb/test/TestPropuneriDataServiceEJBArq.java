package org.app.service.ejb.test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.Collection;

import javax.ejb.EJB;

import org.app.service.ejb.PropuneriService;
import org.app.service.ejb.PropuneriServiceEJB;
import org.app.service.entities.Propuneri;
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
public class TestPropuneriDataServiceEJBArq {
	private static Logger logger = Logger.getLogger(TestPropuneriDataServiceEJBArq.class.getName());
	
	@EJB
	private static PropuneriService service;
	
	@Deployment
	public static Archive<?> createDeployment(){
		return ShrinkWrap
				.create(WebArchive.class, "SCRUM-S3-test.war")
				.addPackage(Propuneri.class.getPackage())
				.addClass(PropuneriService.class)
				.addClass(PropuneriServiceEJB.class)
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
	public void test4_GetPropuneri(){
		logger.info("DEBUG: Junit TESTING: testGetPropuneri ...");
		
		Collection<Propuneri> propunere  = service.getPropunere();
		assertTrue("Fail to read propuneri!", propunere.size() > 0);
	}
	
	@Test
	public void test3_AddPropuneri(){
		logger.info("DEBUG: Junit TESTING: testAddPropuneri ...");
		
		Integer propunereToAdd = 3;
		for(int i=1; i <= propunereToAdd; i++){
			service.addPropuneri(new Propuneri(102, "Craciun Maria", 8, ".NET Developer", "Oferta3", null, null));
		}
		Collection<Propuneri> propunere = service.getPropunere();
		assertTrue("Fail to add propuneri!", propunere.size() == propunereToAdd);
	}
	
	@Test
	public void test2_DeletePropuneri(){
		logger.info("DEBUG: Junit TESTING: testDeletePropunere ...");
		
		Collection<Propuneri> propunere = service.getPropunere();
		for(Propuneri pp: propunere)
			service.removePropuneri(pp);
		Collection<Propuneri> propunereAfterDelete = service.getPropunere();
		assertTrue("Fail to read propuneri!", propunereAfterDelete.size() == 0);
	}
}
