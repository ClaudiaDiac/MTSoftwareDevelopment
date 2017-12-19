package org.app.service.ejb.test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.Collection;
import java.util.Date;

import javax.ejb.EJB;

import org.app.service.ejb.InternshipService;
import org.app.service.ejb.InternshipServiceEJB;
import org.app.service.entities.Internship;
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
public class TestInternshipDataServiceEJBArq {
	private static Logger logger = Logger.getLogger(TestInternshipDataServiceEJBArq.class.getName());
	
	@EJB
	private static InternshipService service;

	@Deployment
	public static Archive<?> createDeployment(){
		return ShrinkWrap
				.create(WebArchive.class, "SCRUM-S3-test.war")
				.addPackage(Internship.class.getPackage())
				.addClass(InternshipService.class)
				.addClass(InternshipServiceEJB.class)
				.addAsResource("META-INF/persistence.xml")
				.addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
	}
	
	@Test
	public void test1_GetMessage(){
		logger.info("DEBUG: Junit TESTING: getMessage ...");
		String response = service.getMessage();
		assertNotNull("DataService failed!", response);
		logger.info("DEBUG: EJB Response ..." + response);
	}
	
	@Test
	public void test4_GetInternship(){
		logger.info("DEBUG: Junit TESTING: testetInternship ...");
		
		Collection<Internship> internsip = service.getInternships();
		assertTrue("Fail to read internships!", internsip.size() > 0);
	}
	
	@Test
	public void test3_AddInternship(){
		logger.info("DEBUG: Junit TESTING: testAddInternships ...");
		
		Integer internsipToAdd = 3;
		for(int i=1; i <= internsipToAdd; i++){
			service.addInternship(new Internship(1100, "Testare", "Trei luni", new Date(), new Date(), 1110, null, null));
		}
		Collection<Internship> internsip = service.getInternships();
		assertTrue("Fail to add internship!", internsip.size() == internsipToAdd);
	}
	
	@Test
	public void test2_DeleteInternship(){
		logger.info("DEBUG: Junit TESTING: testDeleteInternship ...");
		
		Collection<Internship> internsipAfterDelete = service.getInternships();
		assertTrue("Fail to read internship!", internsipAfterDelete.size() == 0);
	}
}
