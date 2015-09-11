package br.edu.ifpb.qmanager.entidade;

import java.io.Serializable;
import java.util.Date;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "participacao")
public class Participacao implements Serializable{

	private static final long serialVersionUID = -4212332069351630736L;
	
	private int idParticipacao;
	
	private Projeto projeto;
	
	private Pessoa pessoa;
	
	private Date inicioParticipacao;
	
	private Date fimParticipacao;
	
	private TipoParticipacao tipoParticipacao;
	
	private boolean bolsista;
	
	private Date registro;

	public Participacao() {
		tipoParticipacao = new TipoParticipacao();
		projeto = new Projeto();
		pessoa = new Pessoa();
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
