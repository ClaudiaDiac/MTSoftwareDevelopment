package org.app.service.ejb.test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.util.List;

import javax.ejb.EJB;

import org.app.patterns.EntityRepository;
import org.app.service.ejb.InternshipPromovariLocatieDataServie;
import org.app.service.ejb.InternshipPromovariLocatieDataServieEJB;
import org.app.service.ejb.LocatieService;
import org.app.service.ejb.LocatieServiceEJB;
import org.app.service.entities.Internship;
import org.app.service.entities.Promovare;
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
public class TestInternshipPromovariLocatieDataServieEJBArq {
	private static Logger logger = Logger.getLogger(TestInternshipPromovariLocatieDataServieEJBArq.class.getName());
	
	@Deployment
	public static Archive<?> createDeployment(){
		return ShrinkWrap
				.create(WebArchive.class, "scrum-test-ejb.war")
				.addPackage(EntityRepository.class.getPackage()).addPackage(Internship.class.getPackage())
				.addClass(LocatieService.class).addClass(LocatieServiceEJB.class)
				.addClass(InternshipPromovariLocatieDataServie.class).addClass(InternshipPromovariLocatieDataServieEJB.class)
				.addAsResource("META-INF/persistence.xml")
				.addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
	}
	
	@EJB
	private static InternshipPromovariLocatieDataServie service;
	
	@Test
	public void test4_GetInternship(){
		logger.info("DEBUG: Jnuit TESTING: testGetInternship 1600 ...");
		Internship inter = service.getById(1600);
		assertNotNull("Fail to get Internship 1600!", inter);
	}
	
	@Test
	public void test3_CreateNewInternship(){
		Internship inter = service.createNewInternship(1600);
		assertNotNull("Fail to create new Internship in repository!", inter);
		
		inter.setDomeniuInternship(inter.getDomeniuInternship() + " - changed by test client");
		List<Promovare> promovari  = inter.getPromovari();
		for( Promovare p: promovari)
			p.setModPromovare(p.getModPromovare() + " - changed by test client");
		inter = service.add(inter);
		assertNotNull("Fail to save new internship in repository!", inter);
		logger.info("DEBUG createNewInternship: internship changed: " + inter);
		
		inter = service.getById(1600);
		assertNotNull("Fail to find changed internship in repository!", inter);
		logger.info("DEBUG createNewInternship: queried internship" + inter);
	}
	
	@Test
	public void test2_DeleteInternship(){
		logger.info("DEBUG: Jnuit TESTING: testDEleteInternship 1600 ...");
		Internship inter = service.getById(1600);
		if(inter != null)
			service.remove(inter);
		inter = service.getById(1600);
		assertNull("Fail to delete Internship 1600!", inter);
	}
}
