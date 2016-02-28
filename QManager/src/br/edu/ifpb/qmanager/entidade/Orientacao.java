package br.edu.ifpb.qmanager.entidade;

import java.util.Date;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement(name = "orientacao")
public class Orientacao {
	
	private int idOrientacao;

	private Servidor servidor;

	private Discente discente;

	private Date dtInicio;

	private Date dtFim;

	private String tipoOrientacao;

	private Date dtRegistro;

	@XmlElement
	public int getIdOrientacao() {
		return idOrientacao;
	}

	public void setIdOrientacao(int idOrientacao) {
		this.idOrientacao = idOrientacao;
	}

	@XmlElement
	public Servidor getServidor() {
		return servidor;
	}

	public void setServidor(Servidor servidor) {
		this.servidor = servidor;
	}

	@XmlElement
	public Discente getDiscente() {
		return discente;
	}

	public void setDiscente(Discente discente) {
		this.discente = discente;
	}

	@XmlElement
	public Date getDtInicio() {
		return dtInicio;
	}

	public void setDtInicio(Date dtInicio) {
		this.dtInicio = dtInicio;
	}

	@XmlElement
	public Date getDtFim() {
		return dtFim;
	}

	public void setDtFim(Date dtFim) {
		this.dtFim = dtFim;
	}

	@XmlElement
	public String getTipoOrientacao() {
		return tipoOrientacao;
	}

	public void setTipoOrientacao(String tipoOrientacao) {
		this.tipoOrientacao = tipoOrientacao;
	}

	@XmlElement
	public Date getDtRegistro() {
		return dtRegistro;
	}

	public void setDtRegistro(Date dtRegistro) {
		this.dtRegistro = dtRegistro;
	}

}
	
	
	
	
	
	
	



