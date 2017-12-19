package org.app.service.entities;

import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.AUTO;
import javax.persistence.ManyToOne;

@Entity
public class Promovare {
	@Id
	@GeneratedValue(strategy = AUTO)
	private Integer IDPromovare;
	private Date DataPromovare;
	private Integer IDLocatie;
	private String ModPromovare;
	private Integer IDPromoter;
	private String NumePromotor;
	private String DomeniuInternship;

	@ManyToOne
	 private Locatie locatii;

	@ManyToOne
	 private Internship Internsip;

	public Promovare(Integer iDPromovare, Date dataPromovare, Integer iDLocatie, String modPromovare,
			Integer iDPromoter, String numePromotor, String domeniuInternship, Locatie locatii, Internship internsip) {
		super();
		IDPromovare = iDPromovare;
		DataPromovare = dataPromovare;
		IDLocatie = iDLocatie;
		ModPromovare = modPromovare;
		IDPromoter = iDPromoter;
		NumePromotor = numePromotor;
		DomeniuInternship = domeniuInternship;
		this.locatii = locatii;
		Internsip = internsip;
	}

	public Promovare() {
		super();
	}

	public Integer getIDPromovare() {
		return IDPromovare;
	}

	public void setIDPromovare(Integer iDPromovare) {
		IDPromovare = iDPromovare;
	}

	public Date getDataPromovare() {
		return DataPromovare;
	}

	public void setDataPromovare(Date dataPromovare) {
		DataPromovare = dataPromovare;
	}

	public Integer getIDLocatie() {
		return IDLocatie;
	}

	public void setIDLocatie(Integer iDLocatie) {
		IDLocatie = iDLocatie;
	}

	public String getModPromovare() {
		return ModPromovare;
	}

	public void setModPromovare(String modPromovare) {
		ModPromovare = modPromovare;
	}

	public Integer getIDPromoter() {
		return IDPromoter;
	}

	public void setIDPromoter(Integer iDPromoter) {
		IDPromoter = iDPromoter;
	}

	public String getNumePromotor() {
		return NumePromotor;
	}

	public void setNumePromotor(String numePromotor) {
		NumePromotor = numePromotor;
	}

	public String getDomeniuInternship() {
		return DomeniuInternship;
	}

	public void setDomeniuInternship(String domeniuInternship) {
		DomeniuInternship = domeniuInternship;
	}

	public Locatie getLocatii() {
		return locatii;
	}

	public void setLocatii(Locatie locatii) {
		this.locatii = locatii;
	}

	public Internship getInternsip() {
		return Internsip;
	}

	public void setInternsip(Internship internsip) {
		Internsip = internsip;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((IDPromovare == null) ? 0 : IDPromovare.hashCode());
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
		Promovare other = (Promovare) obj;
		if (IDPromovare == null) {
			if (other.IDPromovare != null)
				return false;
		} else if (!IDPromovare.equals(other.IDPromovare))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Promovare [IDPromovare=" + IDPromovare + ", DataPromovare=" + DataPromovare + ", IDLocatie=" + IDLocatie
				+ ", ModPromovare=" + ModPromovare + ", IDPromoter=" + IDPromoter + ", NumePromotor=" + NumePromotor
				+ ", DomeniuInternship=" + DomeniuInternship + ", locatii=" + locatii + ", Internsip=" + Internsip
				+ "]";
	}
	
	
}
