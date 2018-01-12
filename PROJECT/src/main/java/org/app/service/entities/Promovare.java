package org.app.service.entities;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.AUTO;
import javax.persistence.ManyToOne;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "promovari")
@XmlAccessorType(XmlAccessType.NONE)
@Entity
public class Promovare implements Serializable {
	@Id
//	@GeneratedValue(strategy = AUTO)
	
	private Integer IDPromovare;
	private String DataPromovare;
	private Integer IDLocatie;
	private String ModPromovare;
	private Integer IDPromoter;
	private String NumePromotor;
	private String DomeniuInternship;

	@ManyToOne
	 private Locatie locatii;

	@ManyToOne
	 private Internship Internsip;

	public Promovare(Integer iDPromovare, String dataPromovare, Integer iDLocatie, String modPromovare,
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
	@XmlElement
	public Integer getIDPromovare() {
		return IDPromovare;
	}

	public void setIDPromovare(Integer iDPromovare) {
		IDPromovare = iDPromovare;
	}

	@XmlElement
	public String getDataPromovare() {
		return DataPromovare;
	}

	public void setDataPromovare(String dataPromovare) {
		DataPromovare = dataPromovare;
	}

	@XmlElement
	public Integer getIDLocatie() {
		return IDLocatie;
	}

	public void setIDLocatie(Integer iDLocatie) {
		IDLocatie = iDLocatie;
	}

	@XmlElement
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

	@XmlElement
	public String getNumePromotor() {
		return NumePromotor;
	}

	public void setNumePromotor(String numePromotor) {
		NumePromotor = numePromotor;
	}

	@XmlElement
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
	
	public static String BASE_URL = Locatie.BASE_URL;
	@XmlElement(name = "link")
	public AtomLink getLink() throws Exception {
		String restUrl = BASE_URL + this.getLocatii().getNumeLocatie() + "/promovare/" + this.getModPromovare();
		return new AtomLink(null, restUrl, "get-promovare");
	}
	
}
