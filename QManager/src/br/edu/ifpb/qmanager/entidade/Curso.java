package br.edu.ifpb.qmanager.entidade;

import java.util.Date;

/*
  TABLE `curso`
  `id_curso` INT NOT NULL,
  `nm_curso` VARCHAR(45) NOT NULL,
*/

public class Curso {
	
	private int idCurso;
	private String nomeCurso;
	private Date registro;
	
	public Curso(){}
	
	public Curso(String nomeCurso) {
		setNomeCurso(nomeCurso);
	}

	public int getIdCurso() {
		return idCurso;
	}

	public void setIdCurso(int idCurso) {
		this.idCurso = idCurso;
	}

	public String getNomeCurso() {
		return nomeCurso;
	}

	public void setNomeCurso(String nomeCurso) {
		this.nomeCurso = nomeCurso;
	}

	public Date getRegistro() {
		return registro;
	}

	public void setRegistro(Date registro) {
		this.registro = registro;
	}
	
	@Override
	public String toString() {
		return "-- Curso --\n\n Nome do Curso= " + nomeCurso + "\n\n--\n\n";
	}	
}
