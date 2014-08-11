package entidades;

/*
 TABLE `instituicao`
 `id_instituicao` INT NOT NULL AUTO_INCREMENT,
 `nm_instituicao` VARCHAR(45) NOT NULL,
 `nm_sigla` VARCHAR(10) NOT NULL,
 `vl_orcamento` DOUBLE NOT NULL,
 */

public class Instituicao implements EntidadeIF {

	private int idInstituicao;
	private String cnpj;
	private String nomeInstituicao;
	private String sigla;
	private double orcamento;
	
	// Construtor para readById
	public Instituicao(int idInstituicao) {
		this("--", "--", "--", 0.0);
		setIdInstituicao(idInstituicao);
	}
	
	// construtor para creat
	public Instituicao(String cnpj, String nomeInstituicao, String sigla, double orcamento) {
		setNomeInstituicao(nomeInstituicao);
		setCnpj(cnpj);
		setSigla(sigla);
		setOrcamento(orcamento);
	}

	public int getIdInstituicao() {
		return idInstituicao;
	}

	public void setIdInstituicao(int idInstituicao) {
		this.idInstituicao = idInstituicao;
	}
	
	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	public String getNomeInstituicao() {
		return nomeInstituicao;
	}

	public void setNomeInstituicao(String nomeInstituicao) {
		this.nomeInstituicao = nomeInstituicao;
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

	@Override
	public String toString() {
		return "-- Instituicao --\n\nCNPJ= " + cnpj + "\nNome da Instituicao= "
				+ nomeInstituicao + "\nSigla= " + sigla + "\nOrcamento= "
				+ orcamento + "\n\n--\n\n";
	}

}
