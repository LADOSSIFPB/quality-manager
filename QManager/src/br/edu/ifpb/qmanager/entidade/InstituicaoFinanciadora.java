package br.edu.ifpb.qmanager.entidade;

import java.util.Date;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "instituicaoFinanciadora")
public class InstituicaoFinanciadora {

	private int idInstituicaoFinanciadora;
	private String cnpj;
	private String nomeInstituicaoFinanciadora;
	private String sigla;
	private double orcamento;
	private Coordenador coordenador;
	private Date registro;

	public InstituicaoFinanciadora() {
		coordenador = new Coordenador();
	}

	public InstituicaoFinanciadora(String cnpj,
			String nomeInstituicaoFinanciadora, String sigla, double orcamento,
			Coordenador coordenador) {
		setNomeInstituicaoFinanciadora(nomeInstituicaoFinanciadora);
		setCnpj(cnpj);
		setSigla(sigla);
		setOrcamento(orcamento);
		setCoordenador(coordenador);
	}

	@XmlElement
	public int getIdInstituicaoFinanciadora() {
		return idInstituicaoFinanciadora;
	}

	public void setIdInstituicaoFinanciadora(int idInstituicaoFinanciadora) {
		this.idInstituicaoFinanciadora = idInstituicaoFinanciadora;
	}

	@XmlElement
	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		cnpj = cnpj.replace(".", "");
		cnpj = cnpj.replace("/", "");
		cnpj = cnpj.replace("-", "");
		this.cnpj = cnpj;
	}

	@XmlElement
	public String getNomeInstituicaoFinanciadora() {
		return nomeInstituicaoFinanciadora;
	}

	public void setNomeInstituicaoFinanciadora(
			String nomeInstituicaoFinanciadora) {
		this.nomeInstituicaoFinanciadora = nomeInstituicaoFinanciadora;
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
	public Coordenador getCoordenador() {
		return coordenador;
	}

	public void setCoordenador(Coordenador coordenador) {
		this.coordenador = coordenador;
	}

	@XmlElement
	public Date getRegistro() {
		return registro;
	}

	public void setRegistro(Date registro) {
		this.registro = registro;
	}

	@Override
	public String toString() {
		return "InstituicaoFinanciadora [idInstituicaoFinanciadora="
				+ idInstituicaoFinanciadora + ", cnpj=" + cnpj
				+ ", nomeInstituicaoFinanciadora="
				+ nomeInstituicaoFinanciadora + ", sigla=" + sigla
				+ ", orcamento=" + orcamento + ", coordenador=" + coordenador
				+ ", registro=" + registro + "]";
	}

}
