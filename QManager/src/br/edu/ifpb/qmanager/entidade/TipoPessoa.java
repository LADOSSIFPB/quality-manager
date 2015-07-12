package br.edu.ifpb.qmanager.entidade;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "tipoPessoa")
public class TipoPessoa {

	private int idTipoPessoa;
	
	private String nomeTipoPessoa;

	public static final int TIPO_SERVIDOR = 1;
	
	public static final int TIPO_DISCENTE = 2;

	public TipoPessoa() {}

	public TipoPessoa(int idTipoPessoa, String nomeTipo) {
		setIdTipoPessoa(idTipoPessoa);
		setNomeTipoPessoa(nomeTipo);
	}

	@XmlElement
	public int getIdTipoPessoa() {
		return idTipoPessoa;
	}

	public void setIdTipoPessoa(int idTipoPessoa) {
		this.idTipoPessoa = idTipoPessoa;
	}

	@XmlElement
	public String getNomeTipoPessoa() {
		return nomeTipoPessoa;
	}

	public void setNomeTipoPessoa(String nomeTipoPessoa) {
		this.nomeTipoPessoa = nomeTipoPessoa;
	}
}
