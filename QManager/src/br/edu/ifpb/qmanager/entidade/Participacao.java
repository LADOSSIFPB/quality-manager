package br.edu.ifpb.qmanager.entidade;

import java.sql.Date;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import br.edu.ifpb.qmanager.excecao.QManagerSQLException;
import br.edu.ifpb.qmanager.util.StringUtil;

@XmlRootElement(name="participacao")
public class Participacao<P> {

	private int idParticipacao;
	private P pessoa;
	private Projeto projeto;
	private Date dataInicio;
	private Date dataFim;
	private double bolsista;
	private Date registro;

	public Participacao() {
	}

	public Participacao(P pessoa, Projeto projeto, String dataInicio,
			String dataFim, double bolsista) {
		setPessoa(pessoa);
		setProjeto(projeto);
		setDataInicio(dataInicio);
		setDataFim(dataFim);
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
	public P getPessoa() {
		return pessoa;
	}

	public void setPessoa(P pessoa) {
		this.pessoa = pessoa;
	}

	@XmlElement
	public Projeto getProjeto() {
		return projeto;
	}

	public void setProjeto(Projeto projeto) {
		this.projeto = projeto;
	}

	@XmlElement
	public Date getDataInicio() {
		return dataInicio;
	}

	public void setDataInicioSQL(Date dataInicio) {
		this.dataInicio = dataInicio;
	}
	
	public void setDataInicio(String dataInicio) {
		try {
			this.dataInicio = StringUtil.converterStringEmDataSQL(dataInicio);
		} catch (QManagerSQLException qme) {
			// TODO Auto-generated catch block
			System.err.println(qme.getMessage());
		}
	}

	@XmlElement
	public Date getDataFim() {
		return dataFim;
	}

	public void setDataFimSQL(Date dataFim) {
		this.dataFim = dataFim;
	}
	
	public void setDataFim(String dataFim) {
		try {
			this.dataFim = StringUtil.converterStringEmDataSQL(dataFim);
		} catch (QManagerSQLException qme) {
			// TODO Auto-generated catch block
			System.err.println(qme.getMessage());
		}
	}

	@XmlElement
	public double getBolsista() {
		return bolsista;
	}

	public void setBolsista(double bolsista) {
		this.bolsista = bolsista;
	}

	@XmlElement
	public Date getRegistro() {
		return registro;
	}

	public void setRegistro(Date registro) {
		this.registro = registro;
	}

	@Override
	public String toString() {
		return "Participacao [idParticipacao=" + idParticipacao + ", pessoa="
				+ pessoa + ", projeto=" + projeto + ", dataInicio="
				+ dataInicio + ", dataFim=" + dataFim + ", bolsista="
				+ bolsista + ", registro=" + registro + "]";
	}

}
