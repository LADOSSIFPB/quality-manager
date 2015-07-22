package br.edu.ifpb.qmanager.relatorios;

import java.util.HashMap;
import java.util.Map;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "quadro")
public class Quadro {

	private String label;
	private Map<String, Integer> barras;

	public Quadro() {
		barras = new HashMap<String, Integer>();
	}

	@XmlElement
	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}
	
	public void addBarra(String chave, int valor) {
		barras.put(chave, valor);
	}

	@XmlElement
	public Map<String, Integer> getMap() {
		return barras;
	}

}
