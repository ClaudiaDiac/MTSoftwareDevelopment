package org.app.scrum.project;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Singleton;

import org.app.service.entities.Intern;
import org.app.service.entities.Proiecte;

@Singleton
public class ProiecteFactory {
	
	public Proiecte buildProiecte(Integer IDProiect, String NumeProiect, Integer IDCoordonator, String NumeCoordonator, Integer interniCount){
		Proiecte proiecte = new Proiecte(IDProiect, NumeProiect + " : ", IDCoordonator, NumeCoordonator + " : ", null);
		List<Intern> interniProiecte = new ArrayList<>();
		
		for (int i=0; i<=interniCount-1; i++){
			interniProiecte.add(new Intern(100, "Popa Dan", 10, "Apostol Andrei", 3000, null, proiecte, null));
		}
		proiecte.setInterni(interniProiecte);
		return proiecte;
	}

}
