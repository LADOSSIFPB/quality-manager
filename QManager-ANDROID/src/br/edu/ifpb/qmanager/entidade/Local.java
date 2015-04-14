package br.edu.ifpb.qmanager.entidade;

public class Local {

	private int idLocal;
	private String nomeLocal;
	private String registro;

	public Local() {
	}

	public Local(int idLocal, String nomeLocal) {
	}

	public int getIdLocal() {
		return idLocal;
	}

	public void setIdLocal(int idLocal) {
		this.idLocal = idLocal;
	}

	public String getNomeLocal() {
		return nomeLocal;
	}

	public void setNomeLocal(String nomeLocal) {
		this.nomeLocal = nomeLocal;
	}

	public String getRegistro() {
		return registro;
	}

	public void setRegistro(String registro) {
		this.registro = registro;
	}

	@Override
	public String toString() {
		return "Local [idLocal=" + idLocal + ", nomeLocal=" + nomeLocal + "]";
	}

}
