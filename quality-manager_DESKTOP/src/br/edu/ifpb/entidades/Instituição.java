package br.edu.ifpb.entidades;

// Campos da tabela
// ID_instituição, nome_instituição, sigla, orçamento

public class Instituição implements Entidade {
	
	private int IDInstituição;
	private String NomeInstituição;
	private String Sigla;
	private double Orçamento;
	
	/* Construtor utilizado para a criação de um objeto Instituição quando for inserir
	 * uma nova instituição ao sistema, de modo que a variável
	 * Id_instituição não precise ser setada (O próprio banco de dados define qual será o valor do
	 * Id da intituição).
	 */
	public Instituição(String nomeInstituição, String sigla, double orçamento) {
		
		setNomeInstituição(nomeInstituição);
		setSigla(sigla);
		setOrçamento(orçamento);
		
	}
	
	/* Para as demais consultas, quando for preciso montar uma instituição já existente,
	 * deve ser usado esse construtor, pois é necessário recuperar o ID da Instituição.
	 */
	public Instituição(int idInstituição, String nomeInstituição, String sigla, double orçamento) {
		
		setIDInstituição(idInstituição);
		setNomeInstituição(nomeInstituição);
		setSigla(sigla);
		setOrçamento(orçamento);
		
	}

	public int getIDInstituição() {
		return IDInstituição;
	}

	public void setIDInstituição(int idInstituição) {
		IDInstituição = idInstituição;
	}

	public String getNomeInstituição() {
		return NomeInstituição;
	}

	public void setNomeInstituição(String nomeInstituição) {
		NomeInstituição = nomeInstituição;
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
	
}
