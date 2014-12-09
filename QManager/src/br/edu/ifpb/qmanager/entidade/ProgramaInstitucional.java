package br.edu.ifpb.qmanager.entidade;

import java.util.Date;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "programaInstitucional")
public class ProgramaInstitucional {

	private int idProgramaInstitucional;
	private String nomeProgramaInstitucional;
	private String sigla;
	private double orcamento;
	private InstituicaoFinanciadora instituicaoFinanciadora;
	private Gestor gestor;
	private Date registro;

	public ProgramaInstitucional() {
		instituicaoFinanciadora = new InstituicaoFinanciadora();
		// gestor = new Gestor();
	}

	public ProgramaInstitucional(String nomeProgramaInstitucional,
			String sigla, double orcamento,
			InstituicaoFinanciadora instituicao, Gestor gestor) {
		setNomeProgramaInstitucional(nomeProgramaInstitucional);
		setSigla(sigla);
		setOrcamento(orcamento);
		setInstituicaoFinanciadora(instituicao);
		setGestor(gestor);
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
	public double getOrcamento() {
		return orcamento;
	}

	public void setOrcamento(double orcamento) {
		this.orcamento = orcamento;
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
	public Gestor getGestor() {
		return gestor;
	}

	public void setGestor(Gestor gestor) {
		this.gestor = gestor;
	}

	@Override
	public String toString() {
		return "ProgramaInstitucional [idProgramaInstitucional="
				+ idProgramaInstitucional + ", nomeProgramaInstitucional="
				+ nomeProgramaInstitucional + ", sigla=" + sigla
				+ ", orcamento=" + orcamento + ", instituicaoFinanciadora="
				+ instituicaoFinanciadora + ", gestor=" + gestor
				+ ", registro=" + registro + "]";
	}

}
