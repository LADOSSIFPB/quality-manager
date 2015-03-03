package br.edu.ifpb.qmanager.entidade;

import java.util.Date;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "pessoa")
public class Pessoa {

	private int pessoaId;
	
	private String nomePessoa;
	
	private String cpf;
	
	private String matricula;
	
	private String endereco;
	
	private String cep;
	
	private String telefone;
	
	private String email;
	
	private String senha;
	
	private TipoPessoa tipoPessoa;
	
	private Local local;
	
	private Date registro;

	private DadosBancarios dadosBancarios;
	
	private boolean isHabilitada;

	public Pessoa() {
		dadosBancarios = new DadosBancarios();
		tipoPessoa = new TipoPessoa();
	}

	public Pessoa(String nomePessoa, String cpf, String matricula,
			String endereco, String cep, String telefone, String email,
			String senha, TipoPessoa tipoPessoa, Local local,
			DadosBancarios dadosBancarios) {
		setNomePessoa(nomePessoa);
		setCpf(cpf);
		setMatricula(matricula);
		setEndereco(endereco);
		setCep(cep);
		setTelefone(telefone);
		setEmail(email);
		setSenha(senha);
		setTipoPessoa(tipoPessoa);
		setLocal(local);
		setDadosBancarios(dadosBancarios);
	}

	@XmlElement
	public int getPessoaId() {
		return pessoaId;
	}

	public void setPessoaId(int pessoaId) {
		this.pessoaId = pessoaId;
	}

	@XmlElement
	public String getNomePessoa() {
		return nomePessoa;
	}

	public void setNomePessoa(String nomePessoa) {
		this.nomePessoa = nomePessoa;
	}

	@XmlElement
	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		cpf = cpf.replace(".", "");
		cpf = cpf.replace("-", "");
		this.cpf = cpf;
	}

	@XmlElement
	public String getMatricula() {
		return matricula;
	}

	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}

	@XmlElement
	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	@XmlElement
	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		cep = cep.replace(".", "");
		cep = cep.replace("-", "");
		this.cep = cep;
	}

	@XmlElement
	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		telefone = telefone.replace("(", "");
		telefone = telefone.replace(")", "");
		telefone = telefone.replace("-", "");
		telefone = telefone.replace(" ", "");
		this.telefone = telefone;
	}

	@XmlElement
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@XmlElement
	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	@XmlElement
	public TipoPessoa getTipoPessoa() {
		return tipoPessoa;
	}

	public void setTipoPessoa(TipoPessoa tipoPessoa) {
		this.tipoPessoa = tipoPessoa;
	}

	@XmlElement
	public DadosBancarios getDadosBancarios() {
		return dadosBancarios;
	}

	public void setDadosBancarios(DadosBancarios dadosBancarios) {
		this.dadosBancarios = dadosBancarios;
	}

	@XmlElement
	public Local getLocal() {
		return local;
	}

	public void setLocal(Local local) {
		this.local = local;
	}

	@XmlElement
	public Date getRegistro() {
		return registro;
	}

	public void setRegistro(Date registro) {
		this.registro = registro;
	}

	public boolean isHabilitada() {
		return isHabilitada;
	}

	public void setHabilitada(boolean isHabilitada) {
		this.isHabilitada = isHabilitada;
	}
}
