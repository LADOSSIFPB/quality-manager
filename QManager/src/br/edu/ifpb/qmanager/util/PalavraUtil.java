package br.edu.ifpb.qmanager.util;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "palavra_util")
public class PalavraUtil {

	private String palavra;
	
	@XmlElement
	public String getPalavra() {
		return palavra;
	}
	
	public void setPalavra(String palavra) {
		this.palavra = palavra;
	}
	
}
