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
	private String instituicao;
	private String sigla;
	private double orcamento;

	public Instituicao(String cnpj, String instituicao, String sigla, double orcamento) {
		setInstituicao(instituicao);
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

	public String getInstituicao() {
		return instituicao;
	}

	public void setInstituicao(String instituicao) {
		this.instituicao = instituicao;
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

}
