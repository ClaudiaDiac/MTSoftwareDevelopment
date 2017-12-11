package org.app.service.ejb;

import java.util.Collection;

import javax.ejb.Remote;

import org.app.service.entities.InterviuTehnic;

@Remote
public interface InterviuTehnicService {
	InterviuTehnic addInterviuTehnic(InterviuTehnic itToAdd);

	String removeInterviuTehnic(InterviuTehnic itToDelete);
	
	InterviuTehnic getInterviuTehnicByIDAplicant(Integer IDAplicant);
	Collection<InterviuTehnic> getInterviuriTehnice();
	
	String getMessage();


}
