package br.edu.ifpb.entidades;

//		Campos da tabela
//NomeInstituição, Sigla, Orçamento


public class Instituição {
	
	private int ID_Instituição;
	private String NomeInstituição;
	private String Sigla;
	private double Orçamento;
	
	/* Construtor utilizado para a criação de um objeto Instituição quando for inserir
	 * uma nova instituição ao sistema, de modo que a variável
	 * Id_instituição não precise ser setada (O próprio banco de dados define qual será o valor do
	 * Id da intituição).
	 */
	public Instituição(String nomeinstituição, String sigla, double orçamento) {
		
		setNomeInstituição(nomeinstituição);
		setSigla(sigla);
		setOrçamento(orçamento);
		
	}
	
	/* Para as demais consultas, quando for preciso montar uma instituição já existente,
	 * deve ser usado esse construtor, pois é necessário recuperar o ID da Instituição.
	 */
	public Instituição(int idinstituição, String nomeinstituição, String sigla, double orçamento) {
		
		setID_Instituição(idinstituição);
		setNomeInstituição(nomeinstituição);
		setSigla(sigla);
		setOrçamento(orçamento);
		
	}

	public int getID_Instituição() {
		return ID_Instituição;
	}

	public void setID_Instituição(int iD_Instituição) {
		ID_Instituição = iD_Instituição;
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
