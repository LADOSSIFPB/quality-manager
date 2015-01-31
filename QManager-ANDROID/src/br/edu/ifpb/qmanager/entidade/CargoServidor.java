package br.edu.ifpb.qmanager.entidade;

public class CargoServidor {

	private int idCargoServidor;
	private String cargoServidor;

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

	public String getCargoServidor() {
		return cargoServidor;
	}

	public void setCargoServidor(String cargoServidor) {
		this.cargoServidor = cargoServidor;
	}

	@Override
	public String toString() {
		return "CargoServidor [idCargoServidor=" + idCargoServidor
				+ ", cargoServidor=" + cargoServidor + "]";
	}

}
