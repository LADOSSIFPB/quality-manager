package br.edu.ifpb.qmanager.entidade;

import java.sql.Date;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import br.edu.ifpb.qmanager.excecao.QManagerSQLException;
import br.edu.ifpb.qmanager.util.Metodos;

@XmlRootElement(name="projeto")
public class Projeto {

	private int idProjeto;
	private String nomeProjeto;
	private Date inicioProjeto;
	private Date fimProjeto;
	private String relatorioSubmetido;
	private String relatorioParcial;
	private String relatorioFinal;
	private String processo;
	private char tipoProjeto;
	private double orcamento;
	private Date registro;
	private Edital edital;

	// construtor para readById
	public Projeto() {
	}

	// construtor para creat
	public Projeto(String nomeProjeto, String inicioProjeto, String fimProjeto,
			String relatorioSubmetido, String relatorioParcial, String relatorioFinal, String processo,
			char tipoProjeto, double orcamento, Edital edital) {
		setNomeProjeto(nomeProjeto);
		setInicioProjeto(inicioProjeto);
		setFimProjeto(fimProjeto);
		setRelatorioSubmetido(relatorioSubmetido);
		setRelatorioParcial(relatorioParcial);
		setRelatorioFinal(relatorioFinal);
		setProcesso(processo);
		setTipoProjeto(tipoProjeto);
		setOrcamento(orcamento);
		setEdital(edital);
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

	public void setInicioProjetoSQL(Date inicioProjeto) {
		this.inicioProjeto = inicioProjeto;
	}
	
	public void setInicioProjeto(String inicioProjeto) {
		try {
			this.inicioProjeto = Metodos.converterStringEmDataSQL(inicioProjeto);
		} catch (QManagerSQLException qme) {
			// TODO Auto-generated catch block
			System.err.println(qme.getMessage());
		}
	}

	@XmlElement
	public Date getFimProjeto() {
		return fimProjeto;
	}

	public void setFimProjetoSQL(Date fimProjeto) {
		this.fimProjeto = fimProjeto;
	}
	
	public void setFimProjeto(String fimProjeto) {
		try {
			this.fimProjeto = Metodos.converterStringEmDataSQL(fimProjeto);
		} catch (QManagerSQLException qme) {
			// TODO Auto-generated catch block
			System.err.println(qme.getMessage());
		}
	}

	@XmlElement
	public String getRelatorioSubmetido() {
		return relatorioSubmetido;
	}

	public void setRelatorioSubmetido(String relatorioSubmetido) {
		this.relatorioSubmetido = relatorioSubmetido;
	}

	@XmlElement
	public String getRelatorioParcial() {
		return relatorioParcial;
	}

	public void setRelatorioParcial(String relatorioParcial) {
		this.relatorioParcial = relatorioParcial;
	}

	@XmlElement
	public String getRelatorioFinal() {
		return relatorioFinal;
	}

	public void setRelatorioFinal(String relatorioFinal) {
		this.relatorioFinal = relatorioFinal;
	}

	@XmlElement
	public String getProcesso() {
		return processo;
	}

	public void setProcesso(String processo) {
		this.processo = processo;
	}

	@XmlElement
	public char getTipoProjeto() {
		return tipoProjeto;
	}

	public void setTipoProjeto(char tipoProjeto) {
		this.tipoProjeto = tipoProjeto;
	}

	@XmlElement
	public double getOrcamento() {
		return orcamento;
	}

	public void setOrcamento(double orcamento) {
		this.orcamento = orcamento;
	}

	@XmlElement
	public Date getRegistro() {
		return registro;
	}

	public void setRegistro(Date registro) {
		this.registro = registro;
	}

	@XmlElement
	public Edital getEdital() {
		return edital;
	}

	public void setEdital(Edital edital) {
		this.edital = edital;
	}

	@Override
	public String toString() {
		return "Projeto [idProjeto=" + idProjeto + ", nomeProjeto="
				+ nomeProjeto + ", inicioProjeto=" + inicioProjeto
				+ ", fimProjeto=" + fimProjeto + ", relatorioSubmetido="
				+ relatorioSubmetido + ", relatorioParcial=" + relatorioParcial
				+ ", relatorioFinal=" + relatorioFinal + ", processo="
				+ processo + ", tipoProjeto=" + tipoProjeto + ", orcamento="
				+ orcamento + ", registro=" + registro + ", edital=" + edital
				+ "]";
	}

}
