package org.app.service.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.AUTO;
import static javax.persistence.CascadeType.ALL;

@XmlRootElement(name = "propunere")
@XmlAccessorType(XmlAccessType.NONE)
@Entity
public class Propuneri implements Serializable {
	@Id
//	@GeneratedValue(strategy = AUTO)
	private Integer IDIntern;
	
	private String NumeIntern;
	private Integer IDPost;
	private String NumePost;
	private String Oferta;
	
	@ManyToOne
	private EvaluareFinala efinala;
	
	@OneToMany(mappedBy="propunere", cascade = ALL, fetch=FetchType.EAGER)
	private List<Angajati> angajat = new ArrayList<>();

	public Propuneri(Integer iDIntern, String numeIntern, Integer iDPost, String numePost, String oferta,
			EvaluareFinala efinala, List<Angajati> angajat) {
		super();
		IDIntern = iDIntern;
		NumeIntern = numeIntern;
		IDPost = iDPost;
		NumePost = numePost;
		Oferta = oferta;
		this.efinala = efinala;
		this.angajat = angajat;
	}

	public Propuneri() {
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
	public Integer getIDPost() {
		return IDPost;
	}

	public void setIDPost(Integer iDPost) {
		IDPost = iDPost;
	}

	@XmlElement
	public String getNumePost() {
		return NumePost;
	}

	public void setNumePost(String numePost) {
		NumePost = numePost;
	}

	@XmlElement
	public String getOferta() {
		return Oferta;
	}

	public void setOferta(String oferta) {
		Oferta = oferta;
	}

	@XmlElement
	public EvaluareFinala getEfinala() {
		return efinala;
	}

	public void setEfinala(EvaluareFinala efinala) {
		this.efinala = efinala;
	}

	@XmlElementWrapper(name = "angajat") @XmlElement(name = "angajati")
	public List<Angajati> getAngajat() {
		return angajat;
	}

	public void setAngajat(List<Angajati> angajat) {
		this.angajat = angajat;
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
		Propuneri other = (Propuneri) obj;
		if (IDIntern == null) {
			if (other.IDIntern != null)
				return false;
		} else if (!IDIntern.equals(other.IDIntern))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Propuneri [IDIntern=" + IDIntern + ", NumeIntern=" + NumeIntern + ", IDPost=" + IDPost + ", NumePost="
				+ NumePost + ", Oferta=" + Oferta + ", efinala=" + efinala + ", angajat=" + angajat + "]";
	}

	public static String BASE_URL = EvaluareFinala.BASE_URL;
	@XmlElement(name = "link")
	public AtomLink getLink() throws Exception {
		String restUrl = BASE_URL + this.getEfinala().getNumeIntern() + "/propunere/" + this.getOferta();
		return new AtomLink(null, restUrl, "get-oferta");
	}
	
}
