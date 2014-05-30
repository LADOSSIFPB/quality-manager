package br.edu.ifpb.entidades;

//		Campos da tabela
//NomeInstituição, Sigla, Orçamento


public class Instituição {
	
	private int IdInstituição;
	private String Nome_Instituição;
	private String Sigla;
	private double Orçamento;
	
	/* Construtor utilizado para a criação de um objeto Instituição quando for inserir
	 * uma nova instituição ao sistema, de modo que a variável
	 * Id_instituição não precise ser setada (O próprio banco de dados define qual será o valor do
	 * Id da intituição).
	 */
	public Instituição(String nome_Instituição, String sigla, double orçamento) {
		
		setNomeInstituicao(nome_Instituição);
		setSigla(sigla);
		setOrçamento(orçamento);
		
	}
	
	/* Para as demais consultas, quando for preciso montar uma instituição já existente,
	 * deve ser usado esse construtor, pois é necessário recuperar o ID da Instituição.
	 */
	public Instituição(int idInstituição, String nome_Instituição, String sigla, double orçamento) {
		
		setIdInstituição(idInstituição);
		setNomeInstituicao(nome_Instituição);
		setSigla(sigla);
		setOrçamento(orçamento);
		
	}

	public String getNomeInstituicao() {
		return this.Nome_Instituição;
	}

	public void setNomeInstituicao(String nome_Instituição) {
		this.Nome_Instituição = nome_Instituição;
	}

	public String getSigla() {
		return Sigla;
	}

	public void setSigla(String sigla) {
		Sigla = sigla;
	}

	public double getOrçamento() {
		return Orçamento;
	}

	public void setOrçamento(double orçamento) {
		Orçamento = orçamento;
	}

	public int getIdInstituição() {
		return IdInstituição;
	}

	public void setIdInstituição(int idInstituição) {
		this.IdInstituição = idInstituição;
	}

}
