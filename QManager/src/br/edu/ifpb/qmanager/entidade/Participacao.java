package br.edu.ifpb.qmanager.entidade;

import java.util.Date;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "participacao")
public class Participacao {

	private int idParticipacao;
	private Projeto projeto;
	private Pessoa pessoa;
	private Date inicioParticipacao;
	private Date fimParticipacao;
	private double valorBolsa;
	private TipoParticipacao tipoParticipacao;
	private Date registro;
	private boolean bolsista;

	public Participacao() {
		tipoParticipacao = new TipoParticipacao();
		projeto = new Projeto();
		pessoa = new Pessoa();
	}

	public Participacao(Projeto projeto, Pessoa pessoa,
			java.util.Date inicioParticipacao, java.util.Date fimParticipacao,
			double valorBolsa, TipoParticipacao tipoParticipacao,
			boolean bolsista) {
		setProjeto(projeto);
		setPessoa(pessoa);
		setInicioParticipacao(inicioParticipacao);
		setFimParticipacao(fimParticipacao);
		setValorBolsa(valorBolsa);
		setTipoParticipacao(tipoParticipacao);
		setBolsista(bolsista);
	}

	@XmlElement
	public int getIdParticipacao() {
		return idParticipacao;
	}

	public void setIdParticipacao(int idParticipacao) {
		this.idParticipacao = idParticipacao;
	}

	@XmlElement
	public Projeto getProjeto() {
		return projeto;
	}

	public void setProjeto(Projeto projeto) {
		this.projeto = projeto;
	}

	@XmlElement
	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}

	@XmlElement
	public Date getInicioParticipacao() {
		return inicioParticipacao;
	}

	public void setInicioParticipacao(Date inicioParticipacao) {
		this.inicioParticipacao = inicioParticipacao;
	}

	@XmlElement
	public Date getFimParticipacao() {
		return fimParticipacao;
	}

	public void setFimParticipacao(Date fimParticipacao) {
		this.fimParticipacao = fimParticipacao;
	}

	@XmlElement
	public double getValorBolsa() {
		return valorBolsa;
	}

	public void setValorBolsa(double valorBolsa) {
		this.valorBolsa = valorBolsa;
	}

	@XmlElement
	public TipoParticipacao getTipoParticipacao() {
		return tipoParticipacao;
	}

	public void setTipoParticipacao(TipoParticipacao tipoParticipacao) {
		this.tipoParticipacao = tipoParticipacao;
	}

	@XmlElement
	public Date getRegistro() {
		return registro;
	}

	public void setRegistro(Date registro) {
		this.registro = registro;
	}

	public boolean isBolsista() {
		return bolsista;
	}

	public void setBolsista(boolean bolsista) {
		this.bolsista = bolsista;
	}

}
