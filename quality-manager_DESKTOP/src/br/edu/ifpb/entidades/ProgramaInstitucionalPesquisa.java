package br.edu.ifpb.entidades;

// Campos da tabela
// ID_PIP, sigla_PIP, nome_PIP, instituição_ID

// PIP = Programa Institucional de Pesquisa

public class ProgramaInstitucionalPesquisa {
	
	private int ID_PIP;
	private String SiglaPIP;
	private String NomePIP;
	private int InstituiçãoID;
	
	/* Construtor utilizado para a criação de um objeto ProgramaInstitucionalPesquisa quando for inserir
	 * um novo Programa Institucional de Pesquisa ao sistema, de modo que a variável
	 * Id_PIP não precise ser setada (O próprio banco de dados define qual será o valor do
	 * Id da ProgramaInstitucionalPesquisa).
	 */
	public ProgramaInstitucionalPesquisa(String siglaPIP, String nomePIP, int instituiçãoID) {

		setSiglaPIP(siglaPIP);
		setNomePIP(nomePIP);
		setInstituiçãoID(instituiçãoID);
		
	}
	
	/* Para as demais consultas, quando for preciso montar um ProgramaInstitucionalPesquisa já existente,
	 * deve ser usado esse construtor, pois é necessário recuperar o ID da Instituição.
	 */
	public ProgramaInstitucionalPesquisa(int id_PIP, String siglaPIP, String nomePIP, int instituiçãoID) {
		
		setID_PIP(id_PIP);
		setSiglaPIP(siglaPIP);
		setNomePIP(nomePIP);
		setInstituiçãoID(instituiçãoID);
		
	}

	public int getID_PIP() {
		return ID_PIP;
	}

	public void setID_PIP(int id_PIP) {
		ID_PIP = id_PIP;
	}

	public String getSiglaPIP() {
		return SiglaPIP;
	}

	public void setSiglaPIP(String siglaPIP) {
		SiglaPIP = siglaPIP;
	}

	public String getNomePIP() {
		return NomePIP;
	}

	public void setNomePIP(String nomePIP) {
		NomePIP = nomePIP;
	}

	public int getInstituiçãoID() {
		return InstituiçãoID;
	}

	public void setInstituiçãoID(int instituiçãoID) {
		InstituiçãoID = instituiçãoID;
	}

}
