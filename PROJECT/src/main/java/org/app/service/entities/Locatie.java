package org.app.service.entities;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

import static javax.persistence.CascadeType.ALL;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement(name="locatie")
@XmlAccessorType(XmlAccessType.NONE)
@Entity
public class Locatie implements Serializable{
	@Id
//	@GeneratedValue(strategy = AUTO)
	private Integer IDLocatie;
	private String NumeLocatie;
	
	@OneToMany(mappedBy="locatii", cascade = ALL, fetch = FetchType.EAGER, orphanRemoval = false)
	private List<Promovare> promovari = new ArrayList<>();
	
	public Locatie(Integer iDLocatie, String numeLocatie, List<Promovare> promovari) {
		super();
		IDLocatie = iDLocatie;
		NumeLocatie = numeLocatie;
		this.promovari = promovari;
	}

	public Locatie() {
		super();
	}

	@XmlElement
	public Integer getIDLocatie() {
		return IDLocatie;
	}

	public void setIDLocatie(Integer iDLocatie) {
		IDLocatie = iDLocatie;
	}

	@XmlElement
	public String getNumeLocatie() {
		return NumeLocatie;
	}

	public void setNumeLocatie(String numeLocatie) {
		NumeLocatie = numeLocatie;
	}

	@XmlElementWrapper(name = "promovari") @XmlElement(name = "promovare")
	public List<Promovare> getPromovari() {
		return promovari;
	}

	public void setPromovari(List<Promovare> promovari) {
		this.promovari = promovari;
	}
	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((IDLocatie == null) ? 0 : IDLocatie.hashCode());
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
		Locatie other = (Locatie) obj;
		if (IDLocatie == null) {
			if (other.IDLocatie != null)
				return false;
		} else if (!IDLocatie.equals(other.IDLocatie))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Locatie [IDLocatie=" + IDLocatie + ", NumeLocatie=" + NumeLocatie + "]";
	}

	public static String BASE_URL = "http://localhost:8080/SCRUM/data/locatie/";
	@XmlElement(name = "link")
	public AtomLink getLink() throws Exception {
		String restUrl = BASE_URL + this.getNumeLocatie();
		return new AtomLink(null, restUrl, "get-locatie");
	}

	public static Locatie toDTOAggregate(Locatie locatii) {
		return new Locatie(locatii.IDLocatie, locatii.NumeLocatie, null);
	}
	
}
