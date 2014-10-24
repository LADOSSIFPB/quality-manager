package br.edu.ifpb.qmanager.entidade;


public class InstituicaoFinanciadora {

	private int idInstituicaoFinanciadora;
	private String cnpj;
	private String nomeInstituicaoFinanciadora;
	private String sigla;
	private double orcamento;
	private Gestor gestor;
	private String registro;

	public InstituicaoFinanciadora() {
		gestor = new Gestor();
	}

	public InstituicaoFinanciadora(String cnpj,
			String nomeInstituicaoFinanciadora, String sigla, double orcamento,
			Gestor gestor) {
		setNomeInstituicaoFinanciadora(nomeInstituicaoFinanciadora);
		setCnpj(cnpj);
		setSigla(sigla);
		setOrcamento(orcamento);
		setGestor(gestor);
	}

	public int getIdInstituicaoFinanciadora() {
		return idInstituicaoFinanciadora;
	}

	public void setIdInstituicaoFinanciadora(int idInstituicaoFinanciadora) {
		this.idInstituicaoFinanciadora = idInstituicaoFinanciadora;
	}

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		cnpj = cnpj.replace(".", "");
		cnpj = cnpj.replace("/", "");
		cnpj = cnpj.replace("-", "");
		this.cnpj = cnpj;
	}

	public String getNomeInstituicaoFinanciadora() {
		return nomeInstituicaoFinanciadora;
	}

	public void setNomeInstituicaoFinanciadora(
			String nomeInstituicaoFinanciadora) {
		this.nomeInstituicaoFinanciadora = nomeInstituicaoFinanciadora;
	}

	public String getSigla() {
		return sigla;
	}

	public void setSigla(String sigla) {
		this.sigla = sigla;
	}

	public double getOrcamento() {
		return orcamento;
	}

	public void setOrcamento(double orcamento) {
		this.orcamento = orcamento;
	}

	public Gestor getGestor() {
		return gestor;
	}

	public void setGestor(Gestor gestor) {
		this.gestor = gestor;
	}

	public String getRegistro() {
		return registro;
	}

	public void setRegistro(String registro) {
		this.registro = registro;
	}

	@Override
	public String toString() {
		return "InstituicaoFinanciadora [idInstituicaoFinanciadora="
				+ idInstituicaoFinanciadora + ", cnpj=" + cnpj
				+ ", nomeInstituicaoFinanciadora="
				+ nomeInstituicaoFinanciadora + ", sigla=" + sigla
				+ ", orcamento=" + orcamento + ", gestor=" + gestor
				+ ", registro=" + registro + "]";
	}

}
