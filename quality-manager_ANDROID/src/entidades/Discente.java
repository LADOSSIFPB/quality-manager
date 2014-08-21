package entidades;

import java.io.Serializable;


public class Discente extends Pessoa implements Serializable {
	
	private int turmaId;
	private String curso;

	public Discente() {
	
	}
	
    public Discente(int pessoaId) {
            super(pessoaId);
    }

    public Discente(String nomePessoa, String cpf, String matricula,
                    String endereco, String cep, String telefone, String email,
                    String senha, int turmaId, String curso) {
            super(nomePessoa, cpf, matricula, endereco, cep, telefone, email, senha);
            setTurmaId(turmaId);
            setCurso(curso);
    }

    public int getTurmaId() {
            return turmaId;
    }

    public void setTurmaId(int turmaId) {
            this.turmaId = turmaId;
    }
    
    public String getCurso() {
    	return curso;
    }
    
    public void setCurso(String curso) {
    	this.curso = curso;
    }

    @Override
    public String toString() {
            return super.toString() + "-- Discente --\n\nTurma Identificador= "
                            + turmaId + "\n\n--\n\n";
    }


}
