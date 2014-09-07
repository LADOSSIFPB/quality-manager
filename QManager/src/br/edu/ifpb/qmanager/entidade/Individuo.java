package br.edu.ifpb.qmanager.entidade;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="individuo")
public class Individuo<I> {

	private I individuo;
	private Usuario usuario;
	private DadosBancarios dadosBancarios;
	
	public Individuo() {
	}
	
	public Individuo(I individuo, Usuario usuario, DadosBancarios dadosBancarios) {
		setIndividuo(individuo);
		setUsuario(usuario);
		setDadosBancarios(dadosBancarios);
	}
	
	@XmlElement
	public I getIndividuo() {
		return individuo;
	}

	public void setIndividuo(I individuo) {
		this.individuo = individuo;
	}

	@XmlElement
	public Usuario getUsuario() {
		return usuario;
	}
	
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	@XmlElement
	public DadosBancarios getDadosBancarios() {
		return dadosBancarios;
	}

	public void setDadosBancarios(DadosBancarios dadosBancarios) {
		this.dadosBancarios = dadosBancarios;
	}

	@Override
	public String toString() {
		return "Individuo [pessoa=" + individuo + ", usuario=" + usuario
				+ ", dadosBancarios=" + dadosBancarios + "]";
	}
	
}
