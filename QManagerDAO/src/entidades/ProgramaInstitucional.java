package entidades;

/*
  TABLE `programa_institucional`
  `id_programa_institucional` INT NOT NULL AUTO_INCREMENT,
  `nm_programa_institucional` VARCHAR(45) NOT NULL,
  `nm_sigla` VARCHAR(10)
*/

public class ProgramaInstitucional implements EntidadeIF {
	
	private int idProgramaInstitucional;
	private String nomeProgramaInstitucional;
	private String sigla;
	private int instituicaoId;

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
	
}
