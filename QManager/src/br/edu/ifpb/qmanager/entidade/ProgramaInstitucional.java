package br.edu.ifpb.qmanager.entidade;

import java.util.Date;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "programaInstitucional")
public class ProgramaInstitucional {

	private int idProgramaInstitucional;
	private String nomeProgramaInstitucional;
	private String sigla;
	private TipoProgramaInstitucional tipoProgramaInstitucional;
	private InstituicaoFinanciadora instituicaoFinanciadora;
	private Servidor gestor;
	private List<RecursoProgramaInstitucional> recursosProgramaInstitucional;
	private Date registro;

	public ProgramaInstitucional() {
		instituicaoFinanciadora = new InstituicaoFinanciadora();
		gestor = new Servidor();
		tipoProgramaInstitucional = new TipoProgramaInstitucional();
	}

	@XmlElement
	public int getIdProgramaInstitucional() {
		return idProgramaInstitucional;
	}

	public void setIdProgramaInstitucional(int idProgramaInstitucional) {
		this.idProgramaInstitucional = idProgramaInstitucional;
	}

	@XmlElement
	public String getNomeProgramaInstitucional() {
		return nomeProgramaInstitucional;
	}

	public void setNomeProgramaInstitucional(String nomeProgramaInstitucional) {
		this.nomeProgramaInstitucional = nomeProgramaInstitucional;
	}

	@XmlElement
	public String getSigla() {
		return sigla;
	}

	public void setSigla(String sigla) {
		this.sigla = sigla;
	}

	@XmlElement
	public TipoProgramaInstitucional getTipoProgramaInstitucional() {
		return tipoProgramaInstitucional;
	}

	public void setTipoProgramaInstitucional(
			TipoProgramaInstitucional tipoProgramaInstitucional) {
		this.tipoProgramaInstitucional = tipoProgramaInstitucional;
	}

	@XmlElement
	public InstituicaoFinanciadora getInstituicaoFinanciadora() {
		return instituicaoFinanciadora;
	}

	public void setInstituicaoFinanciadora(
			InstituicaoFinanciadora instituicaoFinanciadora) {
		this.instituicaoFinanciadora = instituicaoFinanciadora;
	}

	@XmlElement
	public Date getRegistro() {
		return registro;
	}

	public void setRegistro(Date registro) {
		this.registro = registro;
	}

	@XmlElement
	public Servidor getGestor() {
		return gestor;
	}

	public void setGestor(Servidor gestor) {
		this.gestor = gestor;
	}

	@XmlElement
	public List<RecursoProgramaInstitucional> getRecursosProgramaInstitucional() {
		return recursosProgramaInstitucional;
	}

	public void setRecursosProgramaInstitucional(
			List<RecursoProgramaInstitucional> recursosProgramaInstitucional) {
		this.recursosProgramaInstitucional = recursosProgramaInstitucional;
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
