package br.edu.ifpb.qmanager.entidade;

import java.util.Date;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "cargo_servidor")
public class CargoServidor {

	private int idCargoServidor;
	private String nomeCargoServidor;
	private Date registro;

	public static final int GESTOR = 1;
	public static final int COORDENADOR = 2;
	public static final int PROFESSOR = 3;
	public static final int TECNICO_ADMINISTRATIVO = 4;

	@XmlElement
	public int getIdCargoServidor() {
		return idCargoServidor;
	}

	public void setIdCargoServidor(int idCargoServidor) {
		this.idCargoServidor = idCargoServidor;
	}

	@XmlElement
	public String getNomeCargoServidor() {
		return nomeCargoServidor;
	}

	public void setNomeCargoServidor(String nomeCargoServidor) {
		this.nomeCargoServidor = nomeCargoServidor;
	}

	public Date getRegistro() {
		return registro;
	}

	public void setRegistro(Date registro) {
		this.registro = registro;
	}

	@Override
	public String toString() {
		return "CargoServidor [idCargoServidor=" + idCargoServidor
				+ ", cargoServidor=" + nomeCargoServidor + "]";
	}

}
