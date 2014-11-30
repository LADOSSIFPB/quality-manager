package br.edu.ifpb.qmanager.entidade;

import java.io.Serializable;

public class Participacao implements Serializable {

	private static final long serialVersionUID = 1L;

	private int idParticipacao;
	private Projeto projeto;
	private MembroProjeto membroProjeto;
	private String inicioParticipacao;
	private String fimParticipacao;
	private double valorBolsa;
	private String registro;

	public Participacao() {
	}

	public Participacao(Projeto projeto, MembroProjeto membroProjeto,
			String inicioParticipacao, String fimParticipacao, double valorBolsa) {
		setProjeto(projeto);
		setMembroProjeto(membroProjeto);
		setInicioParticipacao(inicioParticipacao);
		setFimParticipacao(fimParticipacao);
		setValorBolsa(valorBolsa);
	}

	public int getIdParticipacao() {
		return idParticipacao;
	}

	public void setIdParticipacao(int idParticipacao) {
		this.idParticipacao = idParticipacao;
	}

	public Projeto getProjeto() {
		return projeto;
	}

	public void setProjeto(Projeto projeto) {
		this.projeto = projeto;
	}

	public MembroProjeto getMembroProjeto() {
		return membroProjeto;
	}

	public void setMembroProjeto(MembroProjeto membroProjeto) {
		this.membroProjeto = membroProjeto;
	}

	public String getInicioParticipacao() {
		return inicioParticipacao;
	}

	public void setInicioParticipacao(String inicioParticipacao) {
		this.inicioParticipacao = inicioParticipacao;
	}

	public String getFimParticipacao() {
		return fimParticipacao;
	}

	public void setFimParticipacao(String fimParticipacao) {
		this.fimParticipacao = fimParticipacao;
	}

	public double getValorBolsa() {
		return valorBolsa;
	}

	public void setValorBolsa(double valorBolsa) {
		this.valorBolsa = valorBolsa;
	}

	public String getRegistro() {
		return registro;
	}

	public void setRegistro(String registro) {
		this.registro = registro;
	}

	@Override
	public String toString() {
		return "Partipacao [idParticipacao=" + idParticipacao + ", projeto="
				+ projeto + ", membroProjeto=" + membroProjeto
				+ ", inicioParticipacao=" + inicioParticipacao
				+ ", fimParticipacao=" + fimParticipacao + ", valorBolsa="
				+ valorBolsa + ", Registro=" + registro + "]";
	}

}
