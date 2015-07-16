package br.edu.ifpb.qmanager.tipo;

public enum TipoArquivo {
	
	// Tipo Arquivo de Projeto
	ARQUIVO_PROJETO (1),
	ARQUIVO_EDITAL (2),
	ARQUIVO_INTEGRANTE (3);	

	private final int id;

	TipoArquivo(int id) {
		this.id = id;
	}

	public int getId() {
		return id;
	}
}