package org.app.service.ejb.test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.util.List;

import javax.ejb.EJB;

import org.app.patterns.EntityRepository;
import org.app.service.ejb.ProiecteService;
import org.app.service.ejb.ProiecteServiceEJB;
import org.app.service.ejb.PropuneriAngajatiProiecteDataService;
import org.app.service.ejb.PropuneriAngajatiProiecteDataServiceEJB;
import org.app.service.entities.Angajati;
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
public class TestPropuneriAngajatiProiecteDataServiceEJBArq {
	private static Logger logger = Logger.getLogger(TestPropuneriAngajatiProiecteDataServiceEJBArq.class.getName());
	
	@Deployment
	public static Archive<?> createDeployment(){
		return ShrinkWrap
				.create(WebArchive.class, "scrum-test-ejb.war")
				.addPackage(EntityRepository.class.getPackage()).addPackage(Propuneri.class.getPackage())
				.addClass(ProiecteService.class).addClass(ProiecteServiceEJB.class)
				.addClass(PropuneriAngajatiProiecteDataService.class).addClass(PropuneriAngajatiProiecteDataServiceEJB.class)
				.addAsResource("META-INF/persistence.xml")
				.addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
	}
	
	@EJB
	private static PropuneriAngajatiProiecteDataService service;
	
	@Test
	public void test4_GetPropuneri(){
		logger.info("DEBUG: Junit TESTING: testGetPropuneri 105 ...");
		Propuneri propunere = service.getById(105);
		assertNotNull("Fail to get Propunere 105!", propunere);
	}
	
	@Test
	public void test3_CreateNewPropunere(){
		Propuneri propunere = service.createNewPropunere(105);
		assertNotNull("Fail to create new propunere in repository!", propunere);
		
		propunere.setNumePost(propunere.getNumePost() + " - changed by test client");
		List<Angajati> angajat = propunere.getAngajat();
		for(Angajati a: angajat)
			a.setNumeAngajat(a.getNumeAngajat() + " - changed by test client");
		propunere = service.add(propunere);
		assertNotNull("Fail to save new propunere in repository!", propunere);
		logger.info("DEBUG createNewPropunere: propunere changed: " + propunere);
		
		propunere = service.getById(105);
		assertNotNull("Fail to find changed propunere in repository!", propunere);
		logger.info("DEBUG createNewPropunere: queried propunere" + propunere);
	}
	
	@Test
	public void test2_DeletePropunere(){
		logger.info("DEBUG: Junit TESTING: testDeletePropunere 105 ...");
		Propuneri propunere = service.getById(105);
		if(propunere != null)
			service.refresh(propunere);
		propunere = service.getById(105);
		assertNull("Fail to delete Propunere 105!", propunere);
	}
}
