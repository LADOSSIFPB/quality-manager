package br.edu.ifpb.qmanager.entidade;

public class Participacao {

	private int idParticipacao;
	private Projeto projeto;
	private Pessoa pessoa;
	private String inicioParticipacao;
	private String fimParticipacao;
	private double valorBolsa;
	private TipoParticipacao tipoParticipacao;
	private String registro;
	private boolean bolsista;

	public Participacao() {
		tipoParticipacao = new TipoParticipacao();
		projeto = new Projeto();
		pessoa = new Pessoa();
	}

	public Participacao(Projeto projeto, Pessoa pessoa,
			String inicioParticipacao, String fimParticipacao,
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

	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
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

	public TipoParticipacao getTipoParticipacao() {
		return tipoParticipacao;
	}

	public void setTipoParticipacao(TipoParticipacao tipoParticipacao) {
		this.tipoParticipacao = tipoParticipacao;
	}

	public String getRegistro() {
		return registro;
	}

	public void setRegistro(String registro) {
		this.registro = registro;
	}

	public boolean isBolsista() {
		return bolsista;
	}

	public void setBolsista(boolean bolsista) {
		this.bolsista = bolsista;
	}

}
