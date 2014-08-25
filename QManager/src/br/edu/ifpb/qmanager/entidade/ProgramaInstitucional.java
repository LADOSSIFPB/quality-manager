package br.edu.ifpb.qmanager.entidade;

/*
  TABLE `programa_institucional`
  `id_programa_institucional` INT NOT NULL AUTO_INCREMENT,
  `nm_programa_institucional` VARCHAR(45) NOT NULL,
  `nm_sigla` VARCHAR(10)
*/

public class ProgramaInstitucional {
	
	private int idProgramaInstitucional;
	private String nomeProgramaInstitucional;
	private String sigla;
	private int instituicaoId;
	
	public ProgramaInstitucional(int idProgramaInstitucional) {
		setIdProgramaInstitucional(idProgramaInstitucional);
	}
	
	public ProgramaInstitucional(String nomeProgramaInstitucional, String sigla, int instituicaoId) {
		setNomeProgramaInstitucional(nomeProgramaInstitucional);
		setSigla(sigla);
		setInstituicaoId(instituicaoId);
	}

	public int getIdProgramaInstitucional() {
		return idProgramaInstitucional;
	}

	public void setIdProgramaInstitucional(int idProgramaInstitucional) {
		this.idProgramaInstitucional = idProgramaInstitucional;
	}

	public String getNomeProgramaInstitucional() {
		return nomeProgramaInstitucional;
	}

	public void setNomeProgramaInstitucional(String nomeProgramaInstitucional) {
		this.nomeProgramaInstitucional = nomeProgramaInstitucional;
	}

	public String getSigla() {
		return sigla;
	}

	public void setSigla(String sigla) {
		this.sigla = sigla;
	}
	
	public int getInstituicaoId() {
		return instituicaoId;
	}

	public void setInstituicaoId(int instituicaoId) {
		this.instituicaoId = instituicaoId;
	}

	@Override
	public String toString() {
		return "-- ProgramaInstitucional --\n\n" + "Nome do Programa Institucional= "
				+ nomeProgramaInstitucional + "\nSigla= " + sigla + "\n\n--\n\n";
	}
	
}
