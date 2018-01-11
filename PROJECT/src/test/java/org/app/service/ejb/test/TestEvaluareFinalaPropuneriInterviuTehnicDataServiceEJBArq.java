package org.app.service.ejb.test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.util.List;

import javax.ejb.EJB;

import org.app.patterns.EntityRepository;
import org.app.service.ejb.EvaluareFinalaPropuneriInterviuTehnicDataService;
import org.app.service.ejb.EvaluareFinalaPropuneriInterviuTehnicDataServiceEJB;
import org.app.service.ejb.InterviuTehnicService;
import org.app.service.ejb.InterviuTehnicServiceEJB;
import org.app.service.entities.EvaluareFinala;
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
public class TestEvaluareFinalaPropuneriInterviuTehnicDataServiceEJBArq {
	private static Logger logger = Logger.getLogger(TestEvaluareFinalaPropuneriInterviuTehnicDataServiceEJBArq.class.getName());
	
	@Deployment
	public static Archive<?> createDeployment(){
		return ShrinkWrap
				.create(WebArchive.class, "scrum-test-ejb.war")
				.addPackage(EntityRepository.class.getPackage()).addPackage(EvaluareFinala.class.getPackage())
				.addClass(InterviuTehnicService.class).addClass(InterviuTehnicServiceEJB.class)
				.addClass(EvaluareFinalaPropuneriInterviuTehnicDataService.class).addClass(EvaluareFinalaPropuneriInterviuTehnicDataServiceEJB.class)
				.addAsResource("META-INF/persistence.xml")
				.addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
	}
	
	@EJB
	private static EvaluareFinalaPropuneriInterviuTehnicDataService service;
	
	@Test
	public void test4_GetEvaluareFinala(){
		logger.info("DEBUG: Junit TESTING: testGetEvaluareFinala 106 ...");
		EvaluareFinala evf = service.getById(106);
		assertNotNull("Fail to get Evaluare Finala 106!", evf);
	}
	
	@Test
	public void test3_CreateNewEvaluareFinala(){
		EvaluareFinala evf = service.createNewEvaluareFinala(106);
		assertNotNull("Fail to create new evaluare finala in repository!", evf);
		
		evf.setTipFeedback(evf.getTipFeedback() + " - changed by test client");
		List<Propuneri> propunere = evf.getPropunere();
		for(Propuneri pp: propunere)
			pp.setOferta(pp.getOferta() + " - changed by test client");
		evf = service.add(evf);
		assertNotNull("Fail to save new evaluare finala in repository!", evf);
		logger.info("DEBUG createNewEvaluareFinala: EvaluareFinala changed: " + evf);
		
		evf = service.getById(106);
		assertNotNull("Fail to find changed evaluare finala in repository!", evf);
		logger.info("DEBUG createNewEvaluareFinala: queried evaluare" + evf);
	}
	
	@Test
	public void test2_DeleteEvaluareFinala(){
		logger.info("DEBUG: Jnuit TESTING: testDeleteEvaluare 106 ...");
		EvaluareFinala evf = service.getById(106);
		if(evf != null)
			service.remove(evf);
		evf = service.getById(106);
		assertNull("Fail to delete evaluare finala 106!", evf);
	}
}
