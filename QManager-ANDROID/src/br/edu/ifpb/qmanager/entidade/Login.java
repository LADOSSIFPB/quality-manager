package br.edu.ifpb.qmanager.entidade;


public class Login {

	private String identificador;
	private String senha;

	public Login() {
	}

	public Login(String identificador, String senha) {
		setIdentificador(identificador);
		setSenha(senha);
	}
	
	
	public String getIdentificador() {
		return identificador;
	}

	public void setIdentificador(String identificador) {
		this.identificador = identificador;
	}

	
	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	@Override
	public String toString() {
		return "Login [login=" + identificador + ", senha=" + senha + "]";
	}	
}
