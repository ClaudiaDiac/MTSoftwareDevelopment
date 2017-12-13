package org.app.service.entities;

import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.AUTO;

@Entity
public class Angajati {
	@Id
	@GeneratedValue(strategy = AUTO)
	private Integer IDAngajat;
	private String NumeAngajat;
	private Integer IDPost;
	private String NumePost;
	private Integer Salar;
	private String TipContract;
	private Date DataAngajare;
	
	@ManyToOne
	static private Propuneri propunere;

	public Angajati(Integer iDAngajat, String numeAngajat, Integer iDPost, String numePost, Integer salar,
			String tipContract, Date dataAngajare, Propuneri propunere) {
		super();
		IDAngajat = iDAngajat;
		NumeAngajat = numeAngajat;
		IDPost = iDPost;
		NumePost = numePost;
		Salar = salar;
		TipContract = tipContract;
		DataAngajare = dataAngajare;
		this.propunere = propunere;
	}

	public Angajati() {
		super();
	}

	public Integer getIDAngajat() {
		return IDAngajat;
	}

	public void setIDAngajat(Integer iDAngajat) {
		IDAngajat = iDAngajat;
	}

	public String getNumeAngajat() {
		return NumeAngajat;
	}

	public void setNumeAngajat(String numeAngajat) {
		NumeAngajat = numeAngajat;
	}

	public Integer getIDPost() {
		return IDPost;
	}

	public void setIDPost(Integer iDPost) {
		IDPost = iDPost;
	}

	public String getNumePost() {
		return NumePost;
	}

	public void setNumePost(String numePost) {
		NumePost = numePost;
	}

	public Integer getSalar() {
		return Salar;
	}

	public void setSalar(Integer salar) {
		Salar = salar;
	}

	public String getTipContract() {
		return TipContract;
	}

	public void setTipContract(String tipContract) {
		TipContract = tipContract;
	}

	public Date getDataAngajare() {
		return DataAngajare;
	}

	public void setDataAngajare(Date dataAngajare) {
		DataAngajare = dataAngajare;
	}

	static public Propuneri getPropunere() {
		return propunere;
	}

	public void setPropunere(Propuneri propunere) {
		this.propunere = propunere;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((IDAngajat == null) ? 0 : IDAngajat.hashCode());
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
		Angajati other = (Angajati) obj;
		if (IDAngajat == null) {
			if (other.IDAngajat != null)
				return false;
		} else if (!IDAngajat.equals(other.IDAngajat))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Angajati [IDAngajat=" + IDAngajat + ", NumeAngajat=" + NumeAngajat + ", IDPost=" + IDPost
				+ ", NumePost=" + NumePost + ", Salar=" + Salar + ", TipContract=" + TipContract + ", DataAngajare="
				+ DataAngajare + "]";
	}
	
	

}
