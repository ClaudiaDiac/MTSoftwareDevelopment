package org.app.service.ejb.test;

import static org.junit.Assert.assertNotNull;

import java.util.List;

import javax.ejb.EJB;

import org.app.patterns.EntityRepository;
import org.app.service.ejb.EvaluareFinalaService;
import org.app.service.ejb.EvaluareFinalaServiceEJB;
import org.app.service.ejb.ProiecteInternFeatureDataService;
import org.app.service.ejb.ProiecteInternFeatureDataServiceEJB;
import org.app.service.entities.Intern;
import org.app.service.entities.Proiecte;
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
public class TestProiecteIternEvaluareFinalaDataServiceEJBArq {
	private static Logger logger = Logger.getLogger(TestProiecteIternEvaluareFinalaDataServiceEJBArq.class.getName());
	 
	@Deployment
	public static Archive<?> createDeployment(){
		return ShrinkWrap
				.create(WebArchive.class, "scrum-test-ejb.war")
				.addPackage(EntityRepository.class.getPackage()).addPackage(Proiecte.class.getPackage())
				.addClass(EvaluareFinalaService.class).addClass(EvaluareFinalaServiceEJB.class)
				.addClass(ProiecteInternFeatureDataService.class).addClass(ProiecteInternFeatureDataServiceEJB.class)
				.addAsResource("META-INF/persistence.xml")
				.addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
	}
	
	@EJB
	private static ProiecteInternFeatureDataService service;
	
	@Test
	public void test4_GetProiect(){
		logger.info("DEBUG: Jnuit TESTING: testGetProiect 3002 ...");
		Proiecte proiect = service.getById(3002);
		assertNotNull("Fail to get Proiect 3002!", proiect);
	}
	
	@Test
	public void test3_CreateNewPeoiect(){
		Proiecte proiect = service.createNewProiect(3002, 32);
		assertNotNull("Fail to create new proiect in repository!", proiect);
		proiect.setNumeProiect(proiect.getNumeProiect() + " - changed by ...");
		List<Intern> interni = proiect.getInterni();
		for(Intern i: interni)
			i.setNumeIntern(i.getNumeMentor() + " - changed by test ...");
		proiect = service.add(proiect);
		assertNotNull("Fail to save new proiect in repository!", proiect);
		logger.info("DEBUG crateNewProiect: proiect changed: " + proiect);
		
		proiect = service.getById(3002);
		assertNotNull("Fail to find changed proiect in repository!" , proiect);
		logger.info("DEBUG createNewProiect: queried proiect" + proiect);
	}
	
	@Test
	public void test2_DeleteProiect(){
		logger.info("DEBUG: Juuit TESTING: testDeleteProiect 3002...");
		Proiecte proiect = service.getById(3002);
		if(proiect != null)
			service.remove(proiect);
		proiect = service.getById(3002);
		assertNotNull("Fail to delete Proiect 3002!", proiect);
	}

}
