package entidades;

/*
 TABLE `turma`
 `id_turma` INT NOT NULL AUTO_INCREMENT,
 `nr_ano` INT NOT NULL,
 `nm_turno` CHAR NOT NULL,
 `curso_id` INT NOT NULL
 */

public class Turma implements EntidadeIF {

	private int idTurma;
	private int ano;
	private String turno;
	private Curso curso;

	public Turma() {
	}

	public Turma(int ano, String turno, Curso curso) {
		this.ano = ano;
		this.turno = turno;
		this.curso = curso;
	}
	
	@Override
	public String toString() {
		return "-- Turma --\n\nIdentificador da Turma= " + idTurma + "\nAno= "
				+ ano + "\nTurno= " + turno + "\nCurso Identificador= "
				+ curso + "\n\n--\n\n";
	}

	public int getIdTurma() {
		return idTurma;
	}

	public void setIdTurma(int idTurma) {
		this.idTurma = idTurma;
	}

	public int getAno() {
		return ano;
	}

	public void setAno(int ano) {
		this.ano = ano;
	}

	public String getTurno() {
		return turno;
	}

	public void setTurno(String turno) {
		this.turno = turno;
	}

	public Curso getCurso() {
		return curso;
	}

	public void setCurso(Curso curso) {
		this.curso = curso;
	}

}
