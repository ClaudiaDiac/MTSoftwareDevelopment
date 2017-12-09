package org.app.service.entities;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.AUTO;
import static javax.persistence.CascadeType.ALL;

@Entity
public class InterviuTehnic {
	@Id
	@GeneratedValue(strategy = AUTO)
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

	public Integer getIDAplicant() {
		return IDAplicant;
	}

	public void setIDAplicant(Integer iDAplicant) {
		IDAplicant = iDAplicant;
	}

	public String getNumeAplicant() {
		return NumeAplicant;
	}

	public void setNumeAplicant(String numeAplicant) {
		NumeAplicant = numeAplicant;
	}

	public Date getDataInterviu() {
		return DataInterviu;
	}

	public void setDataInterviu(Date dataInterviu) {
		DataInterviu = dataInterviu;
	}

	public String getDomeniuInternship() {
		return DomeniuInternship;
	}

	public void setDomeniuInternship(String domeniuInternship) {
		DomeniuInternship = domeniuInternship;
	}

	public Integer getIDTest() {
		return IDTest;
	}

	public void setIDTest(Integer iDTest) {
		IDTest = iDTest;
	}

	public Integer getNotaTest() {
		return NotaTest;
	}

	public void setNotaTest(Integer notaTest) {
		NotaTest = notaTest;
	}

	public String getSituatie() {
		return Situatie;
	}

	public void setSituatie(String situatie) {
		Situatie = situatie;
	}

	public List<Intern> getInterni() {
		return interni;
	}

	public void setInterni(List<Intern> interni) {
		this.interni = interni;
	}

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

	
}
