package org.app.scrum.project;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.Singleton;

import org.app.service.entities.Locatie;
import org.app.service.entities.Promovare;

@Singleton
public class LocatieFactory {
	
	public Locatie buildLocatie(Integer IDLocatie, String NumeLocatie, Integer promovareCount){
		Locatie locatie = new Locatie(IDLocatie, NumeLocatie + "." + IDLocatie, Locatie.getListPromo());
		List<Promovare> promovariLocatie = new ArrayList<>();
		
		Date dataPromovare = new Date();
		Long interval = (long)(301 * 24 *60 * 60 * 1000);
		
		for (int i=0; i<=promovareCount-1; i++){
			promovariLocatie.add(new Promovare(2000, new Date(dataPromovare.getTime() + i * interval), 20, "Program Mentorat", 200, "Luca Ion", "Tester", locatie, Promovare.getInternsip()));
		}
		
		locatie.setPromovari(promovariLocatie);
		return locatie;
	}

}
