package org.app.service.ejb.test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.Collection;
import java.util.Date;

import javax.ejb.EJB;

import org.app.service.ejb.InterviuTehnicService;
import org.app.service.ejb.InterviuTehnicServiceEJB;
import org.app.service.entities.InterviuTehnic;
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
public class TestInterviuTehnicDataServiceEJBArq {
	private static Logger logger = Logger.getLogger(TestInterviuTehnicDataServiceEJBArq.class.getName());
	
	@EJB
	private static InterviuTehnicService service;

	@Deployment
	public static Archive<?> createDeployment(){
		return ShrinkWrap
				.create(WebArchive.class, "SCRUM-S3-test.war")
				.addPackage(InterviuTehnic.class.getPackage())
				.addClass(InterviuTehnicService.class)
				.addClass(InterviuTehnicServiceEJB.class)
				.addAsResource("META-INF/persistence.xml")
				.addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
	}
	
	@Test
	public void test1_GetMessage(){
		logger.info("DEBUG: Junit TESTING: testGetInterviuTehnic ...");
		String response = service.getMessage();
		assertNotNull("Data Service failed!", response);
		logger.info("DEBUG: EJB Response ..." + response);
	}
	
	@Test
	public void test4_GetInterviuTehnic(){
		logger.info("DEBUG: Junit TESTING: testGetInterviuTehnic ...");
		
		Collection<InterviuTehnic> inttehnic = service.getInterviuriTehnice();
		assertTrue("Fail to read interviu tehnice!", inttehnic.size() > 0);
	}
	
	@Test
	public void test3_AddInterviuTehnic(){
		logger.info("DEBUG: Junit TESTING: testAddInterviuTehnic ...");
		
		Integer inttehnicToAdd = 3;
		for(int i=1; i <= inttehnicToAdd; i++){
			service.addInterviuTehnic(new InterviuTehnic(1002, "Zmau Cornelia", new Date(), "Modelare", 1220, 8, "Acceptat", null, null));
		}
		Collection<InterviuTehnic> inttehnic = service.getInterviuriTehnice();
		assertTrue("Fail to add Interviuri!", inttehnic.size() == inttehnicToAdd);
	}
	
	@Test
	public void test2_DeleteInterviuTehnic(){
		logger.info("DEBUG: Junit TESTING: testDeleteInterviuTehnic ...");
		
		Collection<InterviuTehnic> inttehnic = service.getInterviuriTehnice();
		for(InterviuTehnic it: inttehnic)
			service.removeInterviuTehnic(it);
		Collection<InterviuTehnic> inttehnicAfterDelete = service.getInterviuriTehnice();
		assertTrue("Fail to read inttehnic!", inttehnicAfterDelete.size() == 0);
	}
}
