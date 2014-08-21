package entidades;

import java.io.Serializable;


public class Docente extends Pessoa implements Serializable {

    private String titulacao;
    private String cargo;
    
    public Docente(int pessoaId) {
            super(pessoaId);
    }
    
    public Docente() {
		super();
	}
   
    public Docente(String nomePessoa, String cpf, String matricula,
                    String endereco, String cep, String telefone, String email, String senha,
                    String titulacao, String cargo) 
    {
            super(nomePessoa, cpf, matricula, endereco, cep, telefone, email, senha);
            setTitulacao(titulacao);
            setCargo(cargo);
    }

    public String getTitulacao() {
            return titulacao;
    }

    public void setTitulacao(String titulacao) {
            this.titulacao = titulacao;
    }

    public String getCargo() {
            return cargo;
    }

    public void setCargo(String cargo) {
            this.cargo = cargo;
    }

    @Override
    public String toString() {
            return super.toString() + "-- Docente --\n\n Titulação= " + titulacao + "\nCargo= " + cargo + "\n\n--\n\n";
    }

}

