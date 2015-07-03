package br.edu.ifpb.qmanager.entidade;

import java.util.Date;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class TipoEdital {

	private int idTipoEdital;
	private String nomeTipoEdital;
	private Date registro;

	public TipoEdital() {
	}

	public TipoEdital(int idTipoEdital, String nomeTipoEdital) {
		this.idTipoEdital = idTipoEdital;
		this.nomeTipoEdital = nomeTipoEdital;
	}

	@XmlElement
	public int getIdTipoEdital() {
		return idTipoEdital;
	}

	public void setIdTipoEdital(int idTipoEdital) {
		this.idTipoEdital = idTipoEdital;
	}

	@XmlElement
	public String getNomeTipoEdital() {
		return nomeTipoEdital;
	}

	public void setNomeTipoEdital(String nomeTipoEdital) {
		this.nomeTipoEdital = nomeTipoEdital;
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
		return "TipoEdital [idTipoEdital=" + idTipoEdital + ", nomeTipoEdital="
				+ nomeTipoEdital + ", registro=" + registro + "]";
	}

}
