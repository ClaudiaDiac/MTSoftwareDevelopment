package org.app.scrum.project;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.Singleton;

import org.app.service.entities.Aplicanti;
import org.app.service.entities.Internship;
import org.app.service.entities.Promovare;


@Singleton
public class InternshipFactory {
	
	public Internship buildInternship(Integer IDInternship, String DomeniuInternship, String PerioadaInternship, Date DataIncepere,
			Date DataSfarsire, Integer IDTest, List<Promovare> promovari, List<Aplicanti> aplicant, Integer promovareCount){
		Internship internship = new Internship(IDInternship, DomeniuInternship, PerioadaInternship, DataIncepere, DataSfarsire, IDTest, Internship.getListPromoInter(), Internship.getListAplInter());
		List<Promovare> promovariInternship = new ArrayList<>();
		
		Date dataPromovare = new Date();
		Long interval = (long)(301 * 24 *60 * 60 * 1000);
		
		for(int i=0; i<=promovareCount-1; i++){
			promovariInternship.add(new Promovare(2001, new Date(dataPromovare.getTime() + i * interval), 21, "Program Mentorat 2", 201, "Laur Alexandra", "Analist", Promovare.getLocatii(), Promovare.getInternsip()));
		}
		internship.setPromovari(promovariInternship);
		return internship;
	}
	

}

