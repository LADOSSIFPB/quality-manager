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
	private int cursoId;
	
	public Turma(int ano, String turno, int cursoId) {
		setIdTurma(idTurma);
		setAno(ano);
		setTurno(turno);
		setCursoId(cursoId);
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

	public int getCursoId() {
		return cursoId;
	}

	public void setCursoId(int cursoId) {
		this.cursoId = cursoId;
	}
	
}
