package br.edu.ifpb.qmanager.entidade;

import java.util.Date;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "projeto")
public class Projeto {

	private int idProjeto;

	private String nomeProjeto;

	private String resumoProjeto;

	private Date inicioProjeto;

	private Date fimProjeto;

	private String processo;

	private double orcamento;

	private Edital edital;

	private Campus campus;

	private GrandeArea grandeArea;

	private Area area;

	private Servidor cadastrador;

	private List<Discente> discentes;

	private Servidor orientador;

	private Servidor coorientador;

	private Servidor colaborador;

	private Date registro;

	public Projeto() {}

	@XmlElement
	public List<Discente> getDiscentes() {
		return discentes;
	}

	public void setDiscentes(List<Discente> discentes) {
		this.discentes = discentes;
	}

	@XmlElement
	public Servidor getOrientador() {
		return orientador;
	}

	public void setOrientador(Servidor orientador) {
		this.orientador = orientador;
	}

	@XmlElement
	public Servidor getCoorientador() {
		return coorientador;
	}

	public void setCoorientador(Servidor coorientador) {
		this.coorientador = coorientador;
	}

	@XmlElement
	public Servidor getColaborador() {
		return colaborador;
	}

	public void setColaborador(Servidor colaborador) {
		this.colaborador = colaborador;
	}

	@XmlElement
	public int getIdProjeto() {
		return idProjeto;
	}

	public void setIdProjeto(int idProjeto) {
		this.idProjeto = idProjeto;
	}

	@XmlElement
	public String getNomeProjeto() {
		return nomeProjeto;
	}

	public void setNomeProjeto(String nomeProjeto) {
		this.nomeProjeto = nomeProjeto;
	}

	@XmlElement
	public Date getInicioProjeto() {
		return inicioProjeto;
	}

	public void setInicioProjeto(Date inicioProjeto) {
		this.inicioProjeto = inicioProjeto;
	}

	@XmlElement
	public Date getFimProjeto() {
		return fimProjeto;
	}

	public void setFimProjeto(Date fimProjeto) {
		this.fimProjeto = fimProjeto;
	}

	@XmlElement
	public String getProcesso() {
		return processo;
	}

	public void setProcesso(String processo) {
		this.processo = processo;
	}

	@XmlElement
	public double getOrcamento() {
		return orcamento;
	}

	public void setOrcamento(double orcamento) {
		this.orcamento = orcamento;
	}

	@XmlElement
	public Edital getEdital() {
		return edital;
	}

	public void setEdital(Edital edital) {
		this.edital = edital;
	}

	@XmlElement
	public Date getRegistro() {
		return registro;
	}

	public void setRegistro(Date registro) {
		this.registro = registro;
	}

	@XmlElement
	public Campus getCampus() {
		return campus;
	}

	public void setCampus(Campus campus) {
		this.campus = campus;
	}

	@XmlElement
	public GrandeArea getGrandeArea() {
		return grandeArea;
	}

	public void setGrandeArea(GrandeArea grandeArea) {
		this.grandeArea = grandeArea;
	}

	@XmlElement
	public Area getArea() {
		return area;
	}

	public void setArea(Area area) {
		this.area = area;
	}

	public Servidor getCadastrador() {
		return cadastrador;
	}

	public void setCadastrador(Servidor cadastrador) {
		this.cadastrador = cadastrador;
	}

	@XmlElement
	public String getResumoProjeto() {
		return resumoProjeto;
	}

	public void setResumoProjeto(String resumoProjeto) {
		this.resumoProjeto = resumoProjeto;
	}

	@Override
	public String toString() {
		return "Projeto [idProjeto=" + idProjeto + ", nomeProjeto="
				+ nomeProjeto + ", edital=" + edital + ", campus=" + campus
				+ ", grandeArea=" + grandeArea + ", area=" + area
				+ ", cadastrador=" + cadastrador + "]";
	}

}
