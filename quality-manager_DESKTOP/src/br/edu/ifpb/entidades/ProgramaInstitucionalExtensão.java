package br.edu.ifpb.entidades;

//	Campos da tabela
// Id_programa, SiglaPIP, NomePIP, Instituição_idInstituição

//PIP = Programa Institucional de Pesquisa

public class ProgramaInstitucionalExtensão {
	
	private int Id_PIE; 
	private String SiglaPIE;
	private String NomePIE;
	private int Instituição_id;
	
	/* Construtor utilizado para a criação de um objeto ProgramaInstitucionalPesquisa quando for inserir
	 * um novo Programa Institucional de Pesquisa ao sistema, de modo que a variável
	 * Id_PIP não precise ser setada (O próprio banco de dados define qual será o valor do
	 * Id da ProgramaInstitucionalPesquisa).
	 */
	public ProgramaInstitucionalExtensão(String siglaPIE, String nomePIE, int instituição_id) {

		setSiglaPIE(siglaPIE);
		setNomePIE(nomePIE);
		setInstituição_id(instituição_id);
		
	}
	
	/* Para as demais consultas, quando for preciso montar um ProgramaInstitucionalPesquisa já existente,
	 * deve ser usado esse construtor, pois é necessário recuperar o ID da Instituição.
	 */
	public ProgramaInstitucionalExtensão(int id_PIE, String siglaPIE, String nomePIE, int instituição_id) {
		
		setId_PIE(id_PIE);
		setSiglaPIE(siglaPIE);
		setNomePIE(nomePIE);
		setInstituição_id(instituição_id);
		
	}

	public int getId_PIE() {
		return Id_PIE;
	}

	public void setId_PIE(int id_PIE) {
		Id_PIE = id_PIE;
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

	public int getInstituição_id() {
		return Instituição_id;
	}

	public void setInstituição_id(int instituição_id) {
		Instituição_id = instituição_id;
	}

}
