package br.edu.ifpb.qmanager.entidade;

public class ProgramaInstitucional {

	private int idProgramaInstitucional;
	private String nomeProgramaInstitucional;
	private String sigla;
	private InstituicaoFinanciadora instituicaoFinanciadora;
	private Servidor gestor;
	private String registro;

	public ProgramaInstitucional() {
		instituicaoFinanciadora = new InstituicaoFinanciadora();
		gestor = new Servidor();
	}

	public ProgramaInstitucional(String nomeProgramaInstitucional,
			String sigla, InstituicaoFinanciadora instituicao, Servidor gestor) {
		setNomeProgramaInstitucional(nomeProgramaInstitucional);
		setSigla(sigla);
		setInstituicaoFinanciadora(instituicao);
		setGestor(gestor);
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

	public InstituicaoFinanciadora getInstituicaoFinanciadora() {
		return instituicaoFinanciadora;
	}

	public void setInstituicaoFinanciadora(
			InstituicaoFinanciadora instituicaoFinanciadora) {
		this.instituicaoFinanciadora = instituicaoFinanciadora;
	}

	public String getRegistro() {
		return registro;
	}

	public void setRegistro(String registro) {
		this.registro = registro;
	}

	public Servidor getGestor() {
		return gestor;
	}

	public void setGestor(Servidor gestor) {
		this.gestor = gestor;
	}

	@Override
	public String toString() {
		return "ProgramaInstitucional [idProgramaInstitucional="
				+ idProgramaInstitucional + ", nomeProgramaInstitucional="
				+ nomeProgramaInstitucional + ", sigla=" + sigla
				+ ", instituicaoFinanciadora=" + instituicaoFinanciadora
				+ ", gestor=" + gestor + ", registro=" + registro + "]";
	}

}
