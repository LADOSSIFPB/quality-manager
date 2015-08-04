package br.edu.ifpb.qmanager.entidade;

import java.util.Date;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "programaInstitucional")
public class ProgramaInstitucional {

	private int idProgramaInstitucional;
	
	private String nomeProgramaInstitucional;
	
	private String sigla;
	
	private TipoProgramaInstitucional tipoProgramaInstitucional;
	
	private InstituicaoFinanciadora instituicaoFinanciadora;
	
	private Servidor cadastrador;
	
	private Date registro;

	public ProgramaInstitucional() {}

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
	public Servidor getCadastrador() {
		return cadastrador;
	}

	public void setCadastrador(Servidor cadastrador) {
		this.cadastrador = cadastrador;
	}
}
