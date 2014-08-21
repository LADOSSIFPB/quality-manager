package entidades;

import java.io.Serializable;

public class Pessoa implements Serializable{
    
    private int pessoaId;
    private String nomePessoa;
    private String cpf;
    private String matricula;
    private String endereco;
    private String cep;
    private String telefone;
    private String email;
    private String senha;

    public Pessoa(int pessoaId) {
            setPessoaId(pessoaId);
    }

    public Pessoa() {
    	
	}
    
    public Pessoa(String nomePessoa, String cpf, String matricula,
                    String endereco, String cep, String telefone, String email, String senha) {
            setNomePessoa(nomePessoa);
            setCpf(cpf);
            setMatricula(matricula);
            setEndereco(endereco);
            setCep(cep);
            setTelefone(telefone);
            setEmail(email);
    }

    public int getPessoaId() {
            return pessoaId;
    }

    public void setPessoaId(int pessoaId) {
            this.pessoaId = pessoaId;
    }
   
    public String getNomePessoa() {
            return nomePessoa;
    }

    public void setNomePessoa(String nomePessoa) {
            this.nomePessoa = nomePessoa;
    }

    public String getCpf() {
            return cpf;
    }

    public void setCpf(String cpf) {
            this.cpf = cpf;
    }

    public String getMatricula() {
            return matricula;
    }

    public void setMatricula(String matricula) {
            this.matricula = matricula;
    }

    public String getEndereco() {
            return endereco;
    }

    public void setEndereco(String endereco) {
            this.endereco = endereco;
    }

    public String getCep() {
            return cep;
    }

    public void setCep(String cep) {
            this.cep = cep;
    }

    public String getTelefone() {
            return telefone;
    }

    public void setTelefone(String telefone) {
            this.telefone = telefone;
    }

    public String getEmail() {
            return email;
    }

    public void setEmail(String email) {
            this.email = email;
    }    
    
    public String getSenha() {
    	return senha;
    }
    
    public void setSenha(String senha) {
    	this.senha = senha;
    }

    @Override
    public String toString() {
            return "-- Pessoa --\n\nPessoa Identificador= " + pessoaId + "\nNome da Pessoa= " + nomePessoa
                            + "\nCPF= " + cpf + "\nMatrícula= " + matricula + "\nEndereço= "
                            + endereco + "\nCEP= " + cep + "\nTelefone= " + telefone
                            + "\nEmail= " + email;
    }
   
}

