package br.edu.ifpb.qmanager.relatorios;

import java.util.HashMap;
import java.util.Map;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "pizza")
public class Pizza {

	private Map<String, Integer> fatias;

	public Pizza() {
		fatias = new HashMap<String, Integer>();
	}

	public void addFatia(String chave, int valor) {
		fatias.put(chave, valor);
	}

	@XmlElement
	public Map<String, Integer> getFatias() {
		return fatias;
	}

}
