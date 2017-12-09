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
public class Internship {
	@Id
	@GeneratedValue(strategy = AUTO)
	private Integer IDInternship;
	private String DomeniuInternship;
	private String PerioadaInternship;
	private Date DataIncepere;
	private Date DataSfarsire;
	private Integer IDTest;
	
	@OneToMany(mappedBy="Internsip", cascade = ALL, fetch = FetchType.EAGER)
	private List<Promovare> promovari = new ArrayList<>();
	
	@OneToMany(mappedBy="internsip", cascade = ALL, fetch=FetchType.EAGER)
	private List<Aplicanti> aplicant = new ArrayList<>();

	public Internship(Integer iDInternship, String domeniuInternship, String perioadaInternship, Date dataIncepere,
			Date dataSfarsire, Integer iDTest, List<Promovare> promovari, List<Aplicanti> aplicant) {
		super();
		IDInternship = iDInternship;
		DomeniuInternship = domeniuInternship;
		PerioadaInternship = perioadaInternship;
		DataIncepere = dataIncepere;
		DataSfarsire = dataSfarsire;
		IDTest = iDTest;
		this.promovari = promovari;
		this.aplicant = aplicant;
	}

	public Internship() {
		super();
	}

	public Integer getIDInternship() {
		return IDInternship;
	}

	public void setIDInternship(Integer iDInternship) {
		IDInternship = iDInternship;
	}

	public String getDomeniuInternship() {
		return DomeniuInternship;
	}

	public void setDomeniuInternship(String domeniuInternship) {
		DomeniuInternship = domeniuInternship;
	}

	public String getPerioadaInternship() {
		return PerioadaInternship;
	}

	public void setPerioadaInternship(String perioadaInternship) {
		PerioadaInternship = perioadaInternship;
	}

	public Date getDataIncepere() {
		return DataIncepere;
	}

	public void setDataIncepere(Date dataIncepere) {
		DataIncepere = dataIncepere;
	}

	public Date getDataSfarsire() {
		return DataSfarsire;
	}

	public void setDataSfarsire(Date dataSfarsire) {
		DataSfarsire = dataSfarsire;
	}

	public Integer getIDTest() {
		return IDTest;
	}

	public void setIDTest(Integer iDTest) {
		IDTest = iDTest;
	}

	public List<Promovare> getPromovari() {
		return promovari;
	}

	public void setPromovari(List<Promovare> promovari) {
		this.promovari = promovari;
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
		result = prime * result + ((IDInternship == null) ? 0 : IDInternship.hashCode());
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
		Internship other = (Internship) obj;
		if (IDInternship == null) {
			if (other.IDInternship != null)
				return false;
		} else if (!IDInternship.equals(other.IDInternship))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Internship [IDInternship=" + IDInternship + ", DomeniuInternship=" + DomeniuInternship
				+ ", PerioadaInternship=" + PerioadaInternship + ", DataIncepere=" + DataIncepere + ", DataSfarsire="
				+ DataSfarsire + ", IDTest=" + IDTest + ", aplicant=" + aplicant + "]";
	}

	
}
