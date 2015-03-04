package br.edu.ifpb.qmanager.entidade;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "servidor")
public class Servidor extends Pessoa {

	private Titulacao titulacao;
	
	private CargoServidor cargoServidor;
	
	private Departamento departamento;
	
	private Campus campus;

	@XmlElement
	public CargoServidor getCargoServidor() {
		return cargoServidor;
	}

	public void setCargoServidor(CargoServidor cargoServidor) {
		this.cargoServidor = cargoServidor;
	}

	@XmlElement
	public Titulacao getTitulacao() {
		return titulacao;
	}

	public void setTitulacao(Titulacao titulacao) {
		this.titulacao = titulacao;
	}

	@XmlElement
	public Departamento getDepartamento() {
		return departamento;
	}

	public void setDepartamento(Departamento departamento) {
		this.departamento = departamento;
	}

	@XmlElement
	public Campus getCampus() {
		return campus;
	}

	public void setCampus(Campus campus) {
		this.campus = campus;
	}
}
