package br.edu.ifpb.qmanager.entidade;

import java.util.Date;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "arquivoProjeto")
public class ArquivoProjeto {

	private int idArquivoProjeto;
	
	private Projeto projeto;
	
	private String nomeRealArquivo;
	
	private String nomeSistemaArquivo;
	
	private String extensaoArquivo;
	
	private Pessoa pessoaUploader;
	
	//TODO: Tipo arquivo: (1) Projeto inicial, (2) Projeto corrigido, (3) Relatório parcial e (4) Relatório final.
	
	private Date registro;
	
	private byte[] file;

	@XmlElement
	public int getIdArquivoProjeto() {
		return idArquivoProjeto;
	}

	public void setIdArquivoProjeto(int idArquivoProjeto) {
		this.idArquivoProjeto = idArquivoProjeto;
	}

	@XmlElement
	public Projeto getProjeto() {
		return projeto;
	}

	public void setProjeto(Projeto projeto) {
		this.projeto = projeto;
	}

	@XmlElement
	public String getNomeRealArquivo() {
		return nomeRealArquivo;
	}

	public void setNomeRealArquivo(String nomeRealArquivo) {
		this.nomeRealArquivo = nomeRealArquivo;
	}

	@XmlElement
	public String getNomeSistemaArquivo() {
		return nomeSistemaArquivo;
	}

	public void setNomeSistemaArquivo(String nomeSistemaArquivo) {
		this.nomeSistemaArquivo = nomeSistemaArquivo;
	}

	@XmlElement
	public String getExtensaoArquivo() {
		return extensaoArquivo;
	}

	public void setExtensaoArquivo(String extensaoArquivo) {
		this.extensaoArquivo = extensaoArquivo;
	}

	@XmlElement
	public Pessoa getPessoaUploader() {
		return pessoaUploader;
	}

	public void setPessoaUploader(Pessoa pessoaUploader) {
		this.pessoaUploader = pessoaUploader;
	}

	@XmlElement
	public Date getRegistro() {
		return registro;
	}

	public void setRegistro(Date registro) {
		this.registro = registro;
	}

	@XmlElement
	public byte[] getFile() {
		return file;
	}

	public void setFile(byte[] file) {
		this.file = file;
	}
}
