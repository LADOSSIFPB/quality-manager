package br.edu.ifpb.entidades;

//	Campos da tabela
// Id_programa, SiglaPIP, NomePIP, Instituição_idInstituição

//PIP = Programa Institucional de Pesquisa

public class ProgramaInstitucionalExtensão {
	
	private int ID_PIE; 
	private String SiglaPIE;
	private String NomePIE;
	private int Instituição_ID;
	
	/* Construtor utilizado para a criação de um objeto ProgramaInstitucionalExtensão quando for inserir
	 * um novo Programa Institucional de Extensão ao sistema, de modo que a variável
	 * Id_PIE não precise ser setada (O próprio banco de dados define qual será o valor do
	 * Id da ProgramaInstitucionalExtensão).
	 */
	public ProgramaInstitucionalExtensão(String siglaPIE, String nomePIE, int instituição_ID) {

		setSiglaPIE(siglaPIE);
		setNomePIE(nomePIE);
		setInstituição_ID(instituição_ID);
		
	}
	
	/* Para as demais consultas, quando for preciso montar um ProgramaInstitucionalExtensão já existente,
	 * deve ser usado esse construtor, pois é necessário recuperar o ID da Instituição.
	 */
	public ProgramaInstitucionalExtensão(int id_PIE, String siglaPIE, String nomePIE, int instituição_ID) {
		
		setId_PIE(id_PIE);
		setSiglaPIE(siglaPIE);
		setNomePIE(nomePIE);
		setInstituição_ID(instituição_ID);
		
	}

	public int getID_PIE() {
		return ID_PIE;
	}

	public void setId_PIE(int id_PIE) {
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

	public int getInstituição_ID() {
		return Instituição_ID;
	}

	public void setInstituição_ID(int instituição_id) {
		Instituição_ID = instituição_id;
	}

}
