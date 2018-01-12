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

@XmlRootElement(name = "interviut")
@XmlAccessorType(XmlAccessType.NONE)
@Entity
public class InterviuTehnic implements Serializable{
	@Id
//	@GeneratedValue(strategy = AUTO)
	private Integer IDAplicant;
	private String NumeAplicant;
	private Date DataInterviu;
	private String DomeniuInternship;
	private Integer IDTest;
	private Integer NotaTest;
	private String Situatie;
	
	@OneToMany(mappedBy="inttehnic", cascade=ALL, fetch=FetchType.EAGER)
	private List<Intern> interni = new ArrayList<>();
	
	@OneToMany(mappedBy="inttehnic", cascade=ALL, fetch=FetchType.EAGER)
	private List<Aplicanti> aplicant = new ArrayList<>();

	public InterviuTehnic(Integer iDAplicant, String numeAplicant, Date dataInterviu, String domeniuInternship,
			Integer iDTest, Integer notaTest, String situatie, List<Intern> interni, List<Aplicanti> aplicant) {
		super();
		IDAplicant = iDAplicant;
		NumeAplicant = numeAplicant;
		DataInterviu = dataInterviu;
		DomeniuInternship = domeniuInternship;
		IDTest = iDTest;
		NotaTest = notaTest;
		Situatie = situatie;
		this.interni = interni;
		this.aplicant = aplicant;
	}

	public InterviuTehnic() {
		super();
	}

	@XmlElement
	public Integer getIDAplicant() {
		return IDAplicant;
	}

	public void setIDAplicant(Integer iDAplicant) {
		IDAplicant = iDAplicant;
	}

	@XmlElement
	public String getNumeAplicant() {
		return NumeAplicant;
	}

	public void setNumeAplicant(String numeAplicant) {
		NumeAplicant = numeAplicant;
	}

	@XmlElement
	public Date getDataInterviu() {
		return DataInterviu;
	}

	public void setDataInterviu(Date dataInterviu) {
		DataInterviu = dataInterviu;
	}

	@XmlElement
	public String getDomeniuInternship() {
		return DomeniuInternship;
	}

	public void setDomeniuInternship(String domeniuInternship) {
		DomeniuInternship = domeniuInternship;
	}

	@XmlElement
	public Integer getIDTest() {
		return IDTest;
	}

	public void setIDTest(Integer iDTest) {
		IDTest = iDTest;
	}

	@XmlElement
	public Integer getNotaTest() {
		return NotaTest;
	}

	public void setNotaTest(Integer notaTest) {
		NotaTest = notaTest;
	}

	@XmlElement
	public String getSituatie() {
		return Situatie;
	}

	public void setSituatie(String situatie) {
		Situatie = situatie;
	}

	@XmlElementWrapper(name = "interni") @XmlElement(name = "intern")
	public List<Intern> getInterni() {
		return interni;
	}

	public void setInterni(List<Intern> interni) {
		this.interni = interni;
	}

	@XmlElementWrapper(name = "aplicant") @XmlElement(name = "aplicanti")
	public List<Aplicanti> getAplicant() {
		return aplicant;
	}

	public void setAplicant(List<Aplicanti> aplicant) {
		this.aplicant = aplicant;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((IDAplicant == null) ? 0 : IDAplicant.hashCode());
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
		InterviuTehnic other = (InterviuTehnic) obj;
		if (IDAplicant == null) {
			if (other.IDAplicant != null)
				return false;
		} else if (!IDAplicant.equals(other.IDAplicant))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "InterviuTehnic [IDAplicant=" + IDAplicant + ", NumeAplicant=" + NumeAplicant + ", DataInterviu="
				+ DataInterviu + ", DomeniuInternship=" + DomeniuInternship + ", IDTest=" + IDTest + ", NotaTest="
				+ NotaTest + ", Situatie=" + Situatie + ", interni=" + interni + ", aplicant=" + aplicant + "]";
	}

	public static String BASE_URL = "http://localhost:8080/SCRUM/data/interviut/";
	@XmlElement(name = "link")
	public AtomLink getLink() throws Exception {
		String restUrl = BASE_URL + this.getDomeniuInternship();
		return new AtomLink(null, restUrl, "get-domeniu");
	}

	public static InterviuTehnic toDTOAggregate(InterviuTehnic intt) {
		return new InterviuTehnic( intt.IDAplicant, intt.NumeAplicant, intt.DataInterviu, 
				intt.DomeniuInternship, intt.IDTest, intt.NotaTest, intt.Situatie, null, null);
	}
	
}
