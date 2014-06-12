package br.edu.ifpb.entidades;

// Campos da tabela
// ID_PIE, sigla_PIE, nome_PIE, instituição_ID

// PIE = Programa Institucional de Extensão

public class ProgramaInstitucionalExtensão implements Entidade {
	
	private int ID_PIE; 
	private String SiglaPIE;
	private String NomePIE;
	private int InstituiçãoID;
	
	/* Construtor utilizado para a criação de um objeto ProgramaInstitucionalExtensão quando for inserir
	 * um novo Programa Institucional de Extensão ao sistema, de modo que a variável
	 * Id_PIE não precise ser setada (O próprio banco de dados define qual será o valor do
	 * Id da ProgramaInstitucionalExtensão).
	 */
	public ProgramaInstitucionalExtensão(String siglaPIE, String nomePIE, int instituiçãoID) {

		setSiglaPIE(siglaPIE);
		setNomePIE(nomePIE);
		setInstituiçãoID(instituiçãoID);
		
	}
	
	/* Para as demais consultas, quando for preciso montar um ProgramaInstitucionalExtensão já existente,
	 * deve ser usado esse construtor, pois é necessário recuperar o ID da Instituição.
	 */
	public ProgramaInstitucionalExtensão(int id_PIE, String siglaPIE, String nomePIE, int instituiçãoID) {
		
		setID_PIE(id_PIE);
		setSiglaPIE(siglaPIE);
		setNomePIE(nomePIE);
		setInstituiçãoID(instituiçãoID);
		
	}

	public int getID_PIE() {
		return ID_PIE;
	}

	public void setID_PIE(int id_PIE) {
		ID_PIE = id_PIE;
	}

	public String getSiglaPIE() {
		return SiglaPIE;
	}

	public void setSiglaPIE(String siglaPIE) {
		SiglaPIE = siglaPIE;
	}

	public String getNomePIE() {
		return NomePIE;
	}

	public void setNomePIE(String nomePIE) {
		NomePIE = nomePIE;
	}

	public int getInstituiçãoID() {
		return InstituiçãoID;
	}

	public void setInstituiçãoID(int instituiçãoID) {
		InstituiçãoID = instituiçãoID;
	}

}
