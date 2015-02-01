package br.edu.ifpb.qmanager.entidade;

public class CargoServidor {

	private int idCargoServidor;
	private String nomeCargoServidor;
	private String registro;

	public static final int GESTOR = 1;
	public static final int COORDENADOR = 2;
	public static final int PROFESSOR = 3;
	public static final int TECNICO_ADMINISTRATIVO = 4;

	public int getIdCargoServidor() {
		return idCargoServidor;
	}

	public void setIdCargoServidor(int idCargoServidor) {
		this.idCargoServidor = idCargoServidor;
	}

	public String getNomeCargoServidor() {
		return nomeCargoServidor;
	}

	public void setNomeCargoServidor(String nomeCargoServidor) {
		this.nomeCargoServidor = nomeCargoServidor;
	}

	public String getRegistro() {
		return registro;
	}

	public void setRegistro(String registro) {
		this.registro = registro;
	}

	@Override
	public String toString() {
		return "CargoServidor [idCargoServidor=" + idCargoServidor
				+ ", cargoServidor=" + nomeCargoServidor + "]";
	}

}
