package entidades;

import java.util.Date;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * 
 * @author XYZ
 *
 *TABLE `instituicao`
 	 `id_instituicao` INT NOT NULL AUTO_INCREMENT,
	 `nm_instituicao` VARCHAR(45) NOT NULL,
	 `nm_sigla` VARCHAR(10) NOT NULL,
	 `vl_orcamento` DOUBLE NOT NULL,
 */
@XmlRootElement
public class Instituicao {

	private int idInstituicao;
	private String cnpj;
	private String nomeInstituicao;
	private String sigla;
	private double orcamento;
	private Date registro;
	
	// Construtor para readById
	public Instituicao(){}
	
	// construtor para creat
	public Instituicao(String cnpj, String nomeInstituicao, String sigla, double orcamento) {
		setNomeInstituicao(nomeInstituicao);
		setCnpj(cnpj);
		setSigla(sigla);
		setOrcamento(orcamento);
	}

	@XmlElement
	public int getIdInstituicao() {
		return idInstituicao;
	}

	public void setIdInstituicao(int idInstituicao) {
		this.idInstituicao = idInstituicao;
	}
	
	@XmlElement
	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	@XmlElement
	public String getNomeInstituicao() {
		return nomeInstituicao;
	}

	public void setNomeInstituicao(String nomeInstituicao) {
		this.nomeInstituicao = nomeInstituicao;
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

	public Date getRegistro() {
		return registro;
	}

	public void setRegistro(Date registro) {
		this.registro = registro;
	}
	
	@Override
	public String toString() {
		return "-- Instituicao --\n\nCNPJ= " + cnpj + "\nNome da Instituicao= "
				+ nomeInstituicao + "\nSigla= " + sigla + "\nOrcamento= "
				+ orcamento + "\n\n--\n\n";
	}
}
