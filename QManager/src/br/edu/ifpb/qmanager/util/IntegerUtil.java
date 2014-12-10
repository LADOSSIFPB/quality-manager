package br.edu.ifpb.qmanager.util;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "integer_util")
public class IntegerUtil {

	private int id;

	public IntegerUtil() {
	}

	public IntegerUtil(int id) {
		setId(id);
	}

	@XmlElement
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

}
