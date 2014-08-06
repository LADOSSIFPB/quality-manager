package entidades;

/*
  TABLE `curso`
  `id_curso` INT NOT NULL,
  `nm_curso` VARCHAR(45) NOT NULL,
*/

public class Curso implements EntidadeIF {
	
	private int idCurso;
	private String nomeCurso;
	
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
	
}
