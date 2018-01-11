package org.app.service.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.AUTO;
import static javax.persistence.CascadeType.ALL;

@XmlRootElement(name = "evaluaref")
@XmlAccessorType(XmlAccessType.NONE)
@Entity
public class EvaluareFinala implements Serializable {
	@Id
//	@GeneratedValue(strategy = AUTO)
	private Integer IDIntern;
	private String NumeIntern;
	private Integer IDProiect;
	private String TipFeedback;
	private String DecizieFinala;
	
	@OneToMany(mappedBy="efinal", cascade = ALL, fetch = FetchType.EAGER)
	private List<Intern> interni = new ArrayList<>();
	
	@OneToMany(mappedBy="efinala", cascade = ALL, fetch = FetchType.EAGER)
	private List<Propuneri> propunere = new ArrayList<>();

	public EvaluareFinala(Integer iDIntern, String numeIntern, Integer iDProiect, String tipFeedback,
			String decizieFinala, List<Intern> interni, List<Propuneri> propunere) {
		super();
		IDIntern = iDIntern;
		NumeIntern = numeIntern;
		IDProiect = iDProiect;
		TipFeedback = tipFeedback;
		DecizieFinala = decizieFinala;
		this.interni = interni;
		this.propunere = propunere;
	}

	public EvaluareFinala() {
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
	public Integer getIDProiect() {
		return IDProiect;
	}

	public void setIDProiect(Integer iDProiect) {
		IDProiect = iDProiect;
	}

	@XmlElement
	public String getTipFeedback() {
		return TipFeedback;
	}

	public void setTipFeedback(String tipFeedback) {
		TipFeedback = tipFeedback;
	}

	@XmlElement
	public String getDecizieFinala() {
		return DecizieFinala;
	}

	public void setDecizieFinala(String decizieFinala) {
		DecizieFinala = decizieFinala;
	}

	@XmlElementWrapper(name = "interni") @XmlElement(name = "intern")
	public List<Intern> getInterni() {
		return interni;
	}

	public void setInterni(List<Intern> interni) {
		this.interni = interni;
	}

	@XmlElementWrapper(name = "propunere") @XmlElement(name = "propunere")
	public List<Propuneri> getPropunere() {
		return propunere;
	}

	public void setPropunere(List<Propuneri> propunere) {
		this.propunere = propunere;
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
		EvaluareFinala other = (EvaluareFinala) obj;
		if (IDIntern == null) {
			if (other.IDIntern != null)
				return false;
		} else if (!IDIntern.equals(other.IDIntern))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "EvaluareFinala [IDIntern=" + IDIntern + ", NumeIntern=" + NumeIntern + ", IDProiect=" + IDProiect
				+ ", TipFeedback=" + TipFeedback + ", DecizieFinala=" + DecizieFinala + "]";
	}

	public static String BASE_URL = "http://localhost:8080/SCRUM/data/evaluaref/";
	@XmlElement(name = "link")
	public AtomLink getLink() throws Exception {
		String restUrl = BASE_URL + this.getNumeIntern();
		return new AtomLink(null, restUrl, "get-intern");
	}
	
	
}
