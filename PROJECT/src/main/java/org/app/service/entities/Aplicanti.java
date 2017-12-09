package org.app.service.entities;

import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.AUTO;

@Entity
public class Aplicanti {
	@Id
	@GeneratedValue(strategy = AUTO)
	private Integer IDAplicant;
	private String NumeAplicant;
	private Date DataAplicare;
	private Integer Telefon;
	private String Email;
	private String Facultate;
	private Integer AnStudii;
	private String DomeniuInternship;
	private Date DataSelectie;
	private Boolean Selectat;
	
	@ManyToOne
	private Internship internsip;
	
	@ManyToOne
	private InterviuTehnic inttehnic;

	public Aplicanti(Integer iDAplicant, String numeAplicant, Date dataAplicare, Integer telefon, String email,
			String facultate, Integer anStudii, String domeniuInternship, Date dataSelectie, Boolean selectat,
			Internship internsip, InterviuTehnic inttehnic) {
		super();
		IDAplicant = iDAplicant;
		NumeAplicant = numeAplicant;
		DataAplicare = dataAplicare;
		Telefon = telefon;
		Email = email;
		Facultate = facultate;
		AnStudii = anStudii;
		DomeniuInternship = domeniuInternship;
		DataSelectie = dataSelectie;
		Selectat = selectat;
		this.internsip = internsip;
		this.inttehnic = inttehnic;
	}

	public Aplicanti() {
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

	public Date getDataAplicare() {
		return DataAplicare;
	}

	public void setDataAplicare(Date dataAplicare) {
		DataAplicare = dataAplicare;
	}

	public Integer getTelefon() {
		return Telefon;
	}

	public void setTelefon(Integer telefon) {
		Telefon = telefon;
	}

	public String getEmail() {
		return Email;
	}

	public void setEmail(String email) {
		Email = email;
	}

	public String getFacultate() {
		return Facultate;
	}

	public void setFacultate(String facultate) {
		Facultate = facultate;
	}

	public Integer getAnStudii() {
		return AnStudii;
	}

	public void setAnStudii(Integer anStudii) {
		AnStudii = anStudii;
	}

	public String getDomeniuInternship() {
		return DomeniuInternship;
	}

	public void setDomeniuInternship(String domeniuInternship) {
		DomeniuInternship = domeniuInternship;
	}

	public Date getDataSelectie() {
		return DataSelectie;
	}

	public void setDataSelectie(Date dataSelectie) {
		DataSelectie = dataSelectie;
	}

	public Boolean getSelectat() {
		return Selectat;
	}

	public void setSelectat(Boolean selectat) {
		Selectat = selectat;
	}

	public Internship getInternsip() {
		return internsip;
	}

	public void setInternsip(Internship internsip) {
		this.internsip = internsip;
	}

	public InterviuTehnic getInttehnic() {
		return inttehnic;
	}

	public void setInttehnic(InterviuTehnic inttehnic) {
		this.inttehnic = inttehnic;
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
		Aplicanti other = (Aplicanti) obj;
		if (IDAplicant == null) {
			if (other.IDAplicant != null)
				return false;
		} else if (!IDAplicant.equals(other.IDAplicant))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Aplicanti [IDAplicant=" + IDAplicant + ", NumeAplicant=" + NumeAplicant + ", DataAplicare="
				+ DataAplicare + ", Telefon=" + Telefon + ", Email=" + Email + ", Facultate=" + Facultate
				+ ", AnStudii=" + AnStudii + ", DomeniuInternship=" + DomeniuInternship + ", DataSelectie="
				+ DataSelectie + ", Selectat=" + Selectat + "]";
	}

	
}
