package org.app.service.ejb.test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.Collection;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;

import org.app.patterns.EntityRepository;
import org.app.service.ejb.EvaluareFinalaPropuneriInterviuTehnicDataService;
import org.app.service.entities.EvaluareFinala;
import org.app.service.rest.ApplicationConfig;
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
public class TestEvaluareFinalaPropuneriInterviuTehnicDataServiceRESTArq {
		private static Logger logger = Logger.getLogger(TestEvaluareFinalaPropuneriInterviuTehnicDataServiceRESTArq.class.getName());
		
		private static String serviceURL = "http://localhost:8080/PROJECT/rest/evaluaref";
		
		@Deployment
		public static Archive<?> createDeployment(){
			return ShrinkWrap
					.create(WebArchive.class, "msd-s4-test.war")
					.addPackage(EvaluareFinala.class.getPackage())
					.addPackage(EvaluareFinalaPropuneriInterviuTehnicDataService.class.getPackage())
					.addPackage(EntityRepository.class.getPackage())
					.addPackage(ApplicationConfig.class.getPackage())
					.addAsResource("META-INF/pesistence.xml")
					.addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml");
		}
		
		@Test
		public void test1_GetById(){
			String resourceURL = serviceURL + "/3001";
			logger.info("DEBUG: Jnuit TESTING: test1_GetMessage ...");
			EvaluareFinala evaluaref = ClientBuilder.newClient().target(resourceURL).request().accept(MediaType.APPLICATION_JSON).get().readEntity(EvaluareFinala.class);
			assertNotNull("Data service failed!", evaluaref);
			logger.info("DEBUG: REST Response..." + evaluaref);
		}
		
		@Test
		public void test4_GetEvaluareFinala(){
			logger.info("DEBUG: Jnuit TESTING: test4_GetProiect ...");
			Collection<EvaluareFinala> evaluaref = ClientBuilder.newClient()
			.target(serviceURL)
			.request().get()
			.readEntity(new GenericType<Collection<EvaluareFinala>>(){});
			assertTrue("Fail to read Proiecte!", evaluaref.size() > 0);
			evaluaref.stream().forEach(System.out::println);
		}
		
		@Test
		public void test3_AddEvaluareF(){
			logger.info("DEBUG: Junit TESTING: test3_AddLocatie ...");
			Client client = ClientBuilder.newClient();
			Collection<EvaluareFinala> evaluarefi;
			EvaluareFinala evaluaref;
			evaluaref = new EvaluareFinala(108, "Cojoc Adelina", 3004, "Pozitiv", "Acceptat", null, null);
			evaluarefi = client.target(serviceURL)
					.request().accept(MediaType.APPLICATION_JSON)
					.post(Entity.entity(evaluaref, MediaType.APPLICATION_JSON))
					.readEntity(new GenericType<Collection<EvaluareFinala>>(){});
			assertTrue("Fail to read Proiecte!", evaluarefi.size() > 0);
			evaluarefi = client.target(serviceURL)
					.request().get()
					.readEntity(new GenericType<Collection<EvaluareFinala>>(){});
			assertTrue("Fail to add Proiecte!", evaluarefi.size() >= 1);
			evaluarefi.stream().forEach(System.out::println);
		}
		
		@Test
		public void test2_DeleteEvaluareF(){
			String resourceURL = serviceURL + "/";
			logger.info("DEBUG: Junit TESTING: test2_DeleteProiect ...");
			Client client = ClientBuilder.newClient();
			Collection<EvaluareFinala> evaluaref = client.target(serviceURL)
					.request().get()
					.readEntity(new GenericType<Collection<EvaluareFinala>>(){});
			for (EvaluareFinala ef: evaluaref){
				client.target(resourceURL + ef.getNumeIntern()).request().delete();
			}
			
			Collection<EvaluareFinala> proiectAfterDelete = client.target(serviceURL)
					.request().get()
					.readEntity(new GenericType<Collection<EvaluareFinala>>(){});
			assertTrue("Fail to read Proiecte!", proiectAfterDelete.size() == 0);
		}
		
		@Test
		public void test5_UpdateEvaluareF(){
			String resourceURL = serviceURL + "/3000";
			
			Client client = ClientBuilder.newClient();
			
			EvaluareFinala evaluaref = client.target(resourceURL)
					.request().accept(MediaType.APPLICATION_JSON)
					.get().readEntity(EvaluareFinala.class);
			
			assertNotNull("REST Data Service failed!", evaluaref);
			
			evaluaref.setNumeIntern(evaluaref.getNumeIntern() + "_UPDATED");
			evaluaref = client.target(resourceURL)
					.request().accept(MediaType.APPLICATION_JSON)
					.put(Entity.entity(evaluaref, MediaType.APPLICATION_JSON))
					.readEntity(EvaluareFinala.class);
			
			assertNotNull("Rest Data Service failed!", evaluaref);
		}
		
		
}
