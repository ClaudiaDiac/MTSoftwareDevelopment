package org.app.service.entities;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.AUTO;

import java.io.Serializable;

@XmlRootElement(name = "intern")
@XmlAccessorType(XmlAccessType.NONE)
@Entity
public class Intern implements Serializable {
	@Id
//	@GeneratedValue(strategy = AUTO)
	private Integer IDIntern;
	private String NumeIntern;
	private Integer IDMentor;
	private String NumeMentor;
	private Integer IDProiect;
	
	@ManyToOne
	private InterviuTehnic inttehnic;
	
	@ManyToOne
	private Proiecte proiect;
	
	@ManyToOne
	private EvaluareFinala efinal;

	public Intern(Integer iDIntern, String numeIntern, Integer iDMentor, String numeMentor, Integer iDProiect,
			InterviuTehnic inttehnic, Proiecte proiect, EvaluareFinala efinal) {
		super();
		IDIntern = iDIntern;
		NumeIntern = numeIntern;
		IDMentor = iDMentor;
		NumeMentor = numeMentor;
		IDProiect = iDProiect;
		this.inttehnic = inttehnic;
		this.proiect = proiect;
		this.efinal = efinal;
	}

	public Intern() {
		super();
	}

	@XmlElement
	public Integer getIDIntern() {
		return IDIntern;
	}

	public void setIDIntern(Integer iDIntern) {
		IDIntern = iDIntern;
	}

	@XmlElement
	public String getNumeIntern() {
		return NumeIntern;
	}

	public void setNumeIntern(String numeIntern) {
		NumeIntern = numeIntern;
	}

	@XmlElement
	public Integer getIDMentor() {
		return IDMentor;
	}

	public void setIDMentor(Integer iDMentor) {
		IDMentor = iDMentor;
	}

	@XmlElement
	public String getNumeMentor() {
		return NumeMentor;
	}

	public void setNumeMentor(String numeMentor) {
		NumeMentor = numeMentor;
	}

	@XmlElement
	public Integer getIDProiect() {
		return IDProiect;
	}

	public void setIDProiect(Integer iDProiect) {
		IDProiect = iDProiect;
	}

//	@XmlElement
	public InterviuTehnic getInttehnic() {
		return inttehnic;
	}

	public void setInttehnic(InterviuTehnic inttehnic) {
		this.inttehnic = inttehnic;
	}

//	@XmlElement
	public Proiecte getProiect() {
		return proiect;
	}

	public void setProiect(Proiecte proiect) {
		this.proiect = proiect;
	}

//	@XmlElement
	public EvaluareFinala getEfinal() {
		return efinal;
	}

	public void setEfinal(EvaluareFinala efinal) {
		this.efinal = efinal;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((IDIntern == null) ? 0 : IDIntern.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Intern other = (Intern) obj;
		if (IDIntern == null) {
			if (other.IDIntern != null)
				return false;
		} else if (!IDIntern.equals(other.IDIntern))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Intern [IDIntern=" + IDIntern + ", NumeIntern=" + NumeIntern + ", IDMentor=" + IDMentor
				+ ", NumeMentor=" + NumeMentor + ", IDProiect=" + IDProiect + ", efinal=" + efinal + "]";
	}

	public static String BASE_URL = Proiecte.BASE_URL;
	@XmlElement(name = "link")
	public AtomLink getLink() throws Exception {
		String restUrl = BASE_URL + this.getProiect().getNumeProiect() + "/interni/" + this.getNumeIntern();
		return new AtomLink(null, restUrl, "get-intern");
	}
	
}
