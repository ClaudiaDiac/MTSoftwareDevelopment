package org.app.service.ejb.test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.util.List;

import javax.ejb.EJB;

import org.app.patterns.EntityRepository;
import org.app.service.ejb.InterviuTehnicAplicantiPropuneriDataService;
import org.app.service.ejb.InterviuTehnicAplicantiPropuneriDataServiceEJB;
import org.app.service.ejb.PropuneriService;
import org.app.service.ejb.PropuneriServiceEJB;
import org.app.service.entities.Aplicanti;
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
public class TestInterviuTehnicAplicantiPropuneriDataServiceEJBArq {
	private static Logger logger = Logger.getLogger(TestInterviuTehnicAplicantiPropuneriDataServiceEJBArq.class.getName());
	
	@Deployment
	public static Archive<?> createDeployment(){
		return ShrinkWrap
				.create(WebArchive.class, "scrum-test-ejb.war")
				.addPackage(EntityRepository.class.getPackage()).addPackage(InterviuTehnic.class.getPackage())
				.addClass(PropuneriService.class).addClass(PropuneriServiceEJB.class)
				.addClass(InterviuTehnicAplicantiPropuneriDataService.class).addClass(InterviuTehnicAplicantiPropuneriDataServiceEJB.class)
				.addAsResource("META-INF/persistence.xml")
				.addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
	}
	
	@EJB
	private static InterviuTehnicAplicantiPropuneriDataService service;
	
	@Test
	public void test4_GetInterviuTehnic(){
		logger.info("DEBUG: Junit TESTING: testGetInterviuTehnic 1005...");
		InterviuTehnic inteh = service.getById(1005);
		assertNotNull("Fail to get Interviu tehnic 1005!", inteh);
	}
	
	@Test
	public void test3_CreateNewInterviuTehnic(){
		InterviuTehnic inteh = service.createNewInterviuT(1005);
		assertNotNull("fail to create new interviu tehnic in repository!", inteh);
		
		inteh.setDomeniuInternship(inteh.getDomeniuInternship() + " - changed by test client");
		List<Aplicanti> aplicant = inteh.getAplicant();
		for(Aplicanti a: aplicant)
			a.setSelectat(a.getSelectat() + " - changed by test client");
		inteh = service.add(inteh);
		assertNotNull("Fail to save new interviu in repository!", inteh);
		logger.info("DEBUG: createNewInterviuTehnic: interviu tehnic changed: " + inteh);
		
		inteh = service.getById(1005);
		assertNotNull("Failt to find changed interviu tehnic in repository!", inteh);
		logger.info("DEBUG createNewProject: queried interviu" + inteh);
	}
	
	@Test
	public void test2_DeleteInterviuTehnic(){
		logger.info("DEBUG: Jnuit TESTING: testDeleteInterviuTehnic 1005 ...");
		InterviuTehnic inteh = service.getById(1005);
		if(inteh != null)
			service.remove(inteh);
		inteh = service.getById(1005);
		assertNull("Fail to delete Interviu Tehnic 1005!", inteh);
	}
}
