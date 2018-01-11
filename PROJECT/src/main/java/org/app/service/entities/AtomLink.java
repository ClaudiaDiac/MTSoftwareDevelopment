package org.app.service.entities;

import java.io.Serializable;
import java.net.URI;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;

@XmlAccessorType(value = XmlAccessType.NONE)
public class AtomLink implements Serializable{

	private URI href;
	private String rel;
	private String type;
	
	public AtomLink(URI href, String rel, String type) {
		super();
		this.href = href;
		this.rel = rel;
		this.type = type;
	}

	public AtomLink() {
		super();
	}

	@XmlAttribute(name = "href")
	public URI getHref() {
		return href;
	}

	@XmlAttribute(name = "rel")
	public String getRel() {
		return rel;
	}

	@XmlAttribute(name = "type")
	public String getType() {
		return type;
	}
	
	
}
