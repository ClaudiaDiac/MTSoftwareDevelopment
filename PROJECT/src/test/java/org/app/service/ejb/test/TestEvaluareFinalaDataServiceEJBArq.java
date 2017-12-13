package org.app.service.ejb.test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.Collection;

import javax.ejb.EJB;

import org.app.service.ejb.EvaluareFinalaService;
import org.app.service.ejb.EvaluareFinalaServiceEJB;
import org.app.service.entities.EvaluareFinala;
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
public class TestEvaluareFinalaDataServiceEJBArq {
	private static  Logger logger = Logger.getLogger(TestEvaluareFinalaDataServiceEJBArq.class.getName());

	@EJB
	private static EvaluareFinalaService service;
	
	@Deployment
	public static Archive<?> createDeployment(){
		return ShrinkWrap
				.create(WebArchive.class, "SCRUM-S3-test.war")
				.addPackage(EvaluareFinala.class.getPackage())
				.addClass(EvaluareFinalaService.class)
				.addClass(EvaluareFinalaServiceEJB.class)
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
	public void test4_GetEvaluareFinala(){
		logger.info("DEBUG: Junit TESTING: testGetEvaluareFinala ...");
		
		Collection<EvaluareFinala> evfinal = service.getEvaluariFinale();
		assertTrue("Fail to read evaluare finala!", evfinal.size() > 0);
	}
	
	@Test
	public void test3_AddEvaluareFinala(){
		logger.info("DEBUG: Jnuit TESTING: testAddEvaluareFinala ...");
		
		Integer evfinalToAdd = 3;
		for( int i=1; i<= evfinalToAdd; i++){
			service.addEvaluareFinala(new EvaluareFinala(100, "Popa Dan", 3000, "Pozitiv", "Acceptat", EvaluareFinala.getListInterEval(), EvaluareFinala.getListPropEval()));
		}
		Collection<EvaluareFinala> evfinal = service.getEvaluariFinale();
		assertTrue("Fail to add evaluare finala!", evfinal.size() == evfinalToAdd);
	}
	
	@Test
	public void test2_DeleteEvaluareFinala(){
		logger.info("DEBUG: Junit TESTING: testDeleteEvaluareFinala");
		
		Collection<EvaluareFinala> evfinalAfterDelete = service.getEvaluariFinale();
		assertTrue("Fail to read evaluari finale!", evfinalAfterDelete.size() == 0);
	}
}
