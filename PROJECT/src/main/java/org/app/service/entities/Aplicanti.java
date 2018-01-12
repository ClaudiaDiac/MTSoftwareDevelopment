package org.app.service.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.AUTO;

@XmlRootElement(name = "aplicant")
@XmlAccessorType(XmlAccessType.NONE)
@Entity
public class Aplicanti implements Serializable {
	@Id
//	@GeneratedValue(strategy = AUTO)
	private Integer IDAplicant;
	private String NumeAplicant;
	private String DataAplicare;
	private Integer Telefon;
	private String Email;
	private String Facultate;
	private Integer AnStudii;
	private String DomeniuInternship;
	private String DataSelectie;
	private String Selectat;
	
	@ManyToOne
	private Internship internsip;
	
	@ManyToOne
	private InterviuTehnic inttehnic;

	public Aplicanti(Integer iDAplicant, String numeAplicant, String dataAplicare, Integer telefon, String email,
			String facultate, Integer anStudii, String domeniuInternship, String dataSelectie, String selectat,
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
	public String getDataAplicare() {
		return DataAplicare;
	}

	public void setDataAplicare(String dataAplicare) {
		DataAplicare = dataAplicare;
	}

	@XmlElement
	public Integer getTelefon() {
		return Telefon;
	}

	public void setTelefon(Integer telefon) {
		Telefon = telefon;
	}

	@XmlElement
	public String getEmail() {
		return Email;
	}

	public void setEmail(String email) {
		Email = email;
	}

	@XmlElement
	public String getFacultate() {
		return Facultate;
	}

	public void setFacultate(String facultate) {
		Facultate = facultate;
	}

	@XmlElement
	public Integer getAnStudii() {
		return AnStudii;
	}

	public void setAnStudii(Integer anStudii) {
		AnStudii = anStudii;
	}

	@XmlElement
	public String getDomeniuInternship() {
		return DomeniuInternship;
	}

	public void setDomeniuInternship(String domeniuInternship) {
		DomeniuInternship = domeniuInternship;
	}

	@XmlElement
	public String getDataSelectie() {
		return DataSelectie;
	}

	public void setDataSelectie(String dataSelectie) {
		DataSelectie = dataSelectie;
	}

	@XmlElement
	public String getSelectat() {
		return Selectat;
	}

	public void setSelectat(String selectat) {
		Selectat = selectat;
	}

//	@XmlElement
	public Internship getInternsip() {
		return internsip;
	}

	public void setInternsip(Internship internsip) {
		this.internsip = internsip;
	}

//	@XmlElement
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

	public static String BASE_URL = InterviuTehnic.BASE_URL;
	@XmlElement(name = "link")
	public AtomLink getLink() throws Exception {
		String restUrl = BASE_URL + this.getInttehnic().getNumeAplicant() + "/aplicant/" + this.getNumeAplicant();
		return new AtomLink(null, restUrl, "get-aplicant");
	}
	
	
}
