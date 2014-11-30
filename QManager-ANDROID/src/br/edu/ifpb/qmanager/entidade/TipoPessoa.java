package br.edu.ifpb.qmanager.entidade;

import java.io.Serializable;

public class TipoPessoa implements Serializable {

	private static final long serialVersionUID = 1L;

	private int idTipoPessoa;
	private String nomeTipo;

	public static final int TIPO_COORDENADOR = 1;
	public static final int TIPO_ORIENTADOR = 2;
	public static final int TIPO_DISCENTE = 3;
	public static final int TIPO_GESTOR = 4;

	public TipoPessoa() {
	}

	public TipoPessoa(int idTipoPessoa, String nomeTipo) {
		setIdTipoPessoa(idTipoPessoa);
		setNomeTipo(nomeTipo);
	}

	public int getIdTipoPessoa() {
		return idTipoPessoa;
	}

	public void setIdTipoPessoa(int idTipoPessoa) {
		this.idTipoPessoa = idTipoPessoa;
	}

	public String getNomeTipo() {
		return nomeTipo;
	}

	public void setNomeTipo(String nomeTipo) {
		this.nomeTipo = nomeTipo;
	}

}
