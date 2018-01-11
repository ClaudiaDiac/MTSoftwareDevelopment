package org.app.service.ejb.test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.util.List;

import javax.ejb.EJB;

import org.app.patterns.EntityRepository;
import org.app.service.ejb.EvaluareFinalaService;
import org.app.service.ejb.EvaluareFinalaServiceEJB;
import org.app.service.ejb.ProiecteInternEvaluareFinalaDataService;
import org.app.service.ejb.ProiecteInterniEvaluareFinalaDataServiceEJB;
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
public class TestProiecteInterniEvaluareFinalaDataServiceEJBArq {
	private static Logger logger = Logger.getLogger(TestProiecteInterniEvaluareFinalaDataServiceEJBArq.class.getName());

	@Deployment
	public static Archive<?> createDeployment(){
		return ShrinkWrap
				.create(WebArchive.class, "scrum-test-ejb.war")
				.addPackage(EntityRepository.class.getPackage()).addPackage(Proiecte.class.getPackage())
				.addClass(EvaluareFinalaService.class).addClass(EvaluareFinalaServiceEJB.class)
				.addClass(ProiecteInternEvaluareFinalaDataService.class).addClass(ProiecteInterniEvaluareFinalaDataServiceEJB.class)
				.addAsResource("META-INF/persistence.xml")
				.addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
	}
	
	@EJB
	private static ProiecteInternEvaluareFinalaDataService service;
	
	@Test
	public void test4_getProject(){
		logger.info("DEBUG: Junit TESTING: testGetProiect 3005 ...");
		Proiecte proiect = service.getById(3005);
		assertNotNull("Fail to Get Proiect 3005!", proiect);
	}
	
	@Test
	public void test3_CreateNewProiect(){
		Proiecte proiect = service.createNewProiect(3005);
		assertNotNull("Fail to create new project in repository!", proiect);
		
		proiect.setNumeProiect(proiect.getNumeProiect() + " - changed by test client");
		List<Intern> interni = proiect.getInterni();
		for(Intern i: interni)
			i.setNumeIntern(i.getNumeIntern() + " - changed by test client");
		proiect = service.add(proiect);
		assertNotNull("Fail to save new project in repository!", proiect);
		logger.info("DEBUG createNewProiect: proiect changed: " + proiect);
		
		proiect = service.getById(3005);
		assertNotNull("Fail to find changed proiect in repository!", proiect);
		logger.info("DEBUG createNewProiect: queried proiect" + proiect);
	}
	
	@Test
	public void test2_DeleteProiect(){
		logger.info("DEBUG: Jnuit TESTING: testDeleteProiect 3005...");
		Proiecte proiect = service.getById(3005);
		if(proiect != null)
			service.remove(proiect);
			proiect = service.getById(3005);
		assertNull("Fail to delete Proiecte 3005!", proiect);
	}
}
