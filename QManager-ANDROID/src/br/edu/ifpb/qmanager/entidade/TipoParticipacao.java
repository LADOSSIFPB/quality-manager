package br.edu.ifpb.qmanager.entidade;

public class TipoParticipacao {

	private int idTipoParticipacao;
	private String nomeTipoParticipacao;

	public static final int TIPO_ORIENTADOR = 1;
	public static final int TIPO_COORIENTADOR = 2;
	public static final int TIPO_COLABORADOR = 3;
	public static final int TIPO_ORIENTANDO = 4;

	public TipoParticipacao() {
	}

	public TipoParticipacao(int idTipoParticipacao) {
		setIdTipoParticipacao(idTipoParticipacao);
	}

	public int getIdTipoParticipacao() {
		return idTipoParticipacao;
	}

	public void setIdTipoParticipacao(int idTipoParticipacao) {
		this.idTipoParticipacao = idTipoParticipacao;
	}

	public String getNomeTipoParticipacao() {
		return nomeTipoParticipacao;
	}

	public void setNomeTipoParticipacao(String nomeTipoParticipacao) {
		this.nomeTipoParticipacao = nomeTipoParticipacao;
	}

}
