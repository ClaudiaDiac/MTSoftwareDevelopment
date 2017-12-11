package org.app.scrum.project;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Singleton;

import org.app.service.entities.Angajati;
import org.app.service.entities.Propuneri;

@Singleton
public class PropuneriFactory {
	
	public Propuneri buildPropuneri(Integer IDIntern, String Oferta, Integer angajatiCount){
		Propuneri propuneri = new Propuneri(IDIntern, Oferta, angajatiCount, Oferta, Oferta, null, null);
		List<Angajati> angajatPropuneri = new ArrayList<>();
		
		for (int i=0; i<=angajatiCount-1; i++){
			angajatPropuneri.add(new Angajati(1, "Andrei Stefan", 7, "Analist", 2000, "Determinat", null, propuneri));
			
		}
		propuneri.setAngajat(angajatPropuneri);
		return propuneri;
	}

}
