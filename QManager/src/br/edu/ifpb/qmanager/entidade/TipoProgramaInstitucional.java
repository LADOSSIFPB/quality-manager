package br.edu.ifpb.qmanager.entidade;

import java.util.Date;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class TipoProgramaInstitucional {
	
	public static final int PESQUISA = 1;
	public static final int EXTENSAO = 2;

	private int idTipoProgramaInstitucional;
	private String nomeTipoProgramaInstitucional;
	private Date registro;

	public TipoProgramaInstitucional() {
	}

	public TipoProgramaInstitucional(int idTipoEdital, String nomeTipoEdital) {
		this.idTipoProgramaInstitucional = idTipoEdital;
		this.nomeTipoProgramaInstitucional = nomeTipoEdital;
	}

	@XmlElement
	public int getIdTipoProgramaInstitucional() {
		return idTipoProgramaInstitucional;
	}

	public void setIdTipoProgramaInstitucional(int idTipoProgramaInstitucional) {
		this.idTipoProgramaInstitucional = idTipoProgramaInstitucional;
	}

	@XmlElement
	public String getNomeTipoProgramaInstitucional() {
		return nomeTipoProgramaInstitucional;
	}

	public void setNomeTipoProgramaInstitucional(String nomeTipoProgramaInstitucional) {
		this.nomeTipoProgramaInstitucional = nomeTipoProgramaInstitucional;
	}

	@XmlElement
	public Date getRegistro() {
		return registro;
	}

	public void setRegistro(Date registro) {
		this.registro = registro;
	}

	@Override
	public String toString() {
		return "TipoEdital [idTipoProgramaInstitucional=" + idTipoProgramaInstitucional + ", nomeTipoProgramaInstitucional="
				+ nomeTipoProgramaInstitucional + ", registro=" + registro + "]";
	}

}
