package org.app.scrum.project;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.Singleton;

import org.app.service.entities.Angajati;
import org.app.service.entities.EvaluareFinala;
import org.app.service.entities.Propuneri;

@Singleton
public class PropuneriFactory {
	
	public Propuneri buildPropuneri(Integer IDIntern, String NumeIntern, Integer IDPost, String NumePost, String Oferta, EvaluareFinala efinala, Integer angajatiCount ){
		Propuneri propuneri = new Propuneri(IDIntern, NumeIntern + " : ", IDPost, NumePost + " : ", Oferta, efinala, Propuneri.getListProp());
		List<Angajati> angajatPropuneri = new ArrayList<>();
		
		Date dataPromovare = new Date();
		Long interval = (long)(301 * 24 *60 * 60 * 1000);
		
		for (int i=0; i<=angajatiCount-1; i++){
			angajatPropuneri.add(new Angajati(1, "Andrei Stefan", 7, "Analist", 2000, "Determinat", new Date(dataPromovare.getTime() + i * interval), Angajati.getPropunere()));
			
		}
		propuneri.setAngajat(angajatPropuneri);
		return propuneri;
	}

}
