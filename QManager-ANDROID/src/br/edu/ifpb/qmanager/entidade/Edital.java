package br.edu.ifpb.qmanager.entidade;

public class Edital {

	private int idEdital;

	private String arquivo;

	private int numero;

	private int ano;

	private String inicioInscricoes;

	private String fimInscricoes;

	private String relatorioParcial;

	private String relatorioFinal;

	private int vagas;

	private double bolsaDiscente;

	private double bolsaDocente;

	private char tipoEdital;

	private ProgramaInstitucional programaInstitucional;

	private Servidor gestor;

	private String registro;

	private String numAno;

	private String nomeTipoEdital;

	public Edital() {
		programaInstitucional = new ProgramaInstitucional();
		gestor = new Servidor();
	}

	public Edital(String arquivo, int numero, int ano, String inicioInscricoes,
			String fimInscricoes, String relatorioParcial,
			String relatorioFinal, int vagas, double bolsaDiscente,
			double bolsaDocente, char tipoEdital,
			ProgramaInstitucional programaInstitucional, Servidor gestor) {
		setArquivo(arquivo);
		setNumero(numero);
		setAno(ano);
		setInicioInscricoes(inicioInscricoes);
		setFimInscricoes(fimInscricoes);
		setRelatorioParcial(relatorioParcial);
		setRelatorioFinal(relatorioFinal);
		setVagas(vagas);
		setBolsaDiscente(bolsaDiscente);
		setBolsaDocente(bolsaDocente);
		setTipoEdital(tipoEdital);
		setProgramaInstitucional(programaInstitucional);
		setGestor(gestor);
	}

	public int getIdEdital() {
		return idEdital;
	}

	public void setIdEdital(int idEdital) {
		this.idEdital = idEdital;
	}

	public String getArquivo() {
		return arquivo;
	}

	public void setArquivo(String arquivo) {
		this.arquivo = arquivo;
	}

	public int getNumero() {
		return numero;
	}

	public void setNumero(int numeroAno) {
		this.numero = numeroAno;
	}

	public int getAno() {
		return ano;
	}

	public void setAno(int ano) {
		this.ano = ano;
	}

	public String getInicioInscricoes() {
		return inicioInscricoes;
	}

	public void setInicioInscricoes(String inicioInscricoes) {
		this.inicioInscricoes = inicioInscricoes;
	}

	public String getFimInscricoes() {
		return fimInscricoes;
	}

	public void setFimInscricoes(String fimInscricoes) {
		this.fimInscricoes = fimInscricoes;
	}

	public String getRelatorioParcial() {
		return relatorioParcial;
	}

	public void setRelatorioParcial(String relatorioParcial) {
		this.relatorioParcial = relatorioParcial;
	}

	public String getRelatorioFinal() {
		return relatorioFinal;
	}

	public void setRelatorioFinal(String relatorioFinal) {
		this.relatorioFinal = relatorioFinal;
	}

	public int getVagas() {
		return vagas;
	}

	public void setVagas(int vagas) {
		this.vagas = vagas;
	}

	public double getBolsaDiscente() {
		return bolsaDiscente;
	}

	public void setBolsaDiscente(double bolsaDiscente) {
		this.bolsaDiscente = bolsaDiscente;
	}

	public double getBolsaDocente() {
		return bolsaDocente;
	}

	public void setBolsaDocente(double bolsaDocente) {
		this.bolsaDocente = bolsaDocente;
	}

	public char getTipoEdital() {
		return tipoEdital;
	}

	public void setTipoEdital(char tipoEdital) {
		this.tipoEdital = tipoEdital;
	}

	public ProgramaInstitucional getProgramaInstitucional() {
		return programaInstitucional;
	}

	public void setProgramaInstitucional(
			ProgramaInstitucional programaInstitucional) {
		this.programaInstitucional = programaInstitucional;
	}

	public Servidor getGestor() {
		return gestor;
	}

	public void setGestor(Servidor gestor) {
		this.gestor = gestor;
	}

	public String getRegistro() {
		return registro;
	}

	public String getNomeTipoEdital() {

		if (this.tipoEdital == 'P') {
			this.nomeTipoEdital = "Pesquisa";
		} else if (this.tipoEdital == 'E') {
			this.nomeTipoEdital = "Extensão";
		}

		return this.nomeTipoEdital;
	}

	public void setRegistro(String registro) {
		this.registro = registro;
	}

	public String getNumAno() {
		this.numAno = this.numero + "/" + this.ano;
		return this.numAno;
	}

	@Override
	public String toString() {
		return "Edital [idEdital=" + idEdital + ", arquivo=" + arquivo
				+ ", numero=" + numero + ", ano=" + ano + ", inicioInscricoes="
				+ inicioInscricoes + ", fimInscricoes=" + fimInscricoes
				+ ", relatorioParcial=" + relatorioParcial
				+ ", relatorioFinal=" + relatorioFinal + ", vagas=" + vagas
				+ ", bolsaDiscente=" + bolsaDiscente + ", bolsaDocente="
				+ bolsaDocente + ", tipoEdital=" + tipoEdital
				+ ", programaInstitucional=" + programaInstitucional
				+ ", gestor=" + gestor + ", registro=" + registro + "]";
	}
}
