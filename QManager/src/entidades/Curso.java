package entidades;

/*
  TABLE `curso`
  `id_curso` INT NOT NULL,
  `nm_curso` VARCHAR(45) NOT NULL,
*/

public class Curso implements EntidadeIF {
	
	private int idCurso;
	private String nomeCurso;
	
	public Curso(int idCurso) {
		this("Nome Curso");
		setIdCurso(idCurso);
	}
	
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

	@Override
	public String toString() {
		return "-- Curso --\n\n Nome do Curso= " + nomeCurso + "\n\n--\n\n";
	}
	
}
