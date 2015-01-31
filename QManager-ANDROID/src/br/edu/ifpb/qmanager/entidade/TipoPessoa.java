package br.edu.ifpb.qmanager.entidade;

public class TipoPessoa {

	private int idTipoPessoa;
	private String nomeTipoPessoa;

	public static final int TIPO_SERVIDOR = 1;
	public static final int TIPO_DISCENTE = 2;

	public TipoPessoa() {
	}

	public TipoPessoa(int idTipoPessoa, String nomeTipo) {
		setIdTipoPessoa(idTipoPessoa);
		setNomeTipoPessoa(nomeTipo);
	}

	public int getIdTipoPessoa() {
		return idTipoPessoa;
	}

	public void setIdTipoPessoa(int idTipoPessoa) {
		this.idTipoPessoa = idTipoPessoa;
	}

	public String getNomeTipoPessoa() {
		return nomeTipoPessoa;
	}

	public void setNomeTipoPessoa(String nomeTipoPessoa) {
		this.nomeTipoPessoa = nomeTipoPessoa;
	}

}
