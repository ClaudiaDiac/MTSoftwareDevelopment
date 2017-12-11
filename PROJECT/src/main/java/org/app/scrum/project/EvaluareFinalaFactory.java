package org.app.scrum.project;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Singleton;

import org.app.service.entities.EvaluareFinala;
import org.app.service.entities.Propuneri;

@Singleton
public class EvaluareFinalaFactory {
	
	public EvaluareFinala builEvaluareFinala(Integer IDIntern, String NumeIntern, Integer propuneriCount){
		EvaluareFinala evfinala = new EvaluareFinala(IDIntern, NumeIntern, propuneriCount, NumeIntern, NumeIntern, null, null);
		List<Propuneri> propunereEvaluareFinala = new ArrayList<>();
		
		for (int i=0; i<=propuneriCount-1; i++){
			propunereEvaluareFinala.add(new Propuneri(100, "Luca Ion", 6, "Tester", "Oferta1", evfinala, null));
			
		}
		evfinala.setPropunere(propunereEvaluareFinala);
		return evfinala;
		
	}

}
