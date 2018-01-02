package org.app.scrum.project;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.Singleton;

import org.app.service.entities.Aplicanti;
import org.app.service.entities.InterviuTehnic;

@Singleton
public class InterviuTehnicFactory {
	
	public InterviuTehnic buildInterviuTehnic(Integer IDAplicant, String NumeAplicant, Date DataInterviu, String DomeniuInternship,
			Integer IDTest, Integer NotaTest, String Situatie, Integer aplicantiCount){
		InterviuTehnic itehnic = new InterviuTehnic(IDAplicant, NumeAplicant, DataInterviu, DomeniuInternship, IDTest, NotaTest, Situatie, null, null);
		List<Aplicanti> aplicantInterviuTehnic = new ArrayList<>();
		
//		Date dataAplicare = new Date();
//		Long interval = (long)(301 * 24 *60 * 60 * 1000);
		
		for (int i=0; i<=aplicantiCount-1; i++ ){
			aplicantInterviuTehnic.add(new Aplicanti(1001,"Diaconu Diana", "", 745156487, "dm@gmail.com", "FEAA", 3, "Software Development", "", "Da", null, itehnic));
		}
		itehnic.setAplicant(aplicantInterviuTehnic);
		return itehnic;
	}

}
