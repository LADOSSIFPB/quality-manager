package br.edu.ifpb.entidades;

//	Campos da tabela
// Id_programa, SiglaPIP, NomePIP, Instituição_idInstituição

//PIP = Programa Institucional de Pesquisa

public class ProgramaInstitucionalPesquisa {
	
	private int Id_PIP; 
	private String SiglaPIP;
	private String NomePIP;
	private int Instituição_id;
	
	/* Construtor utilizado para a criação de um objeto ProgramaInstitucionalPesquisa quando for inserir
	 * um novo Programa Institucional de Pesquisa ao sistema, de modo que a variável
	 * Id_PIP não precise ser setada (O próprio banco de dados define qual será o valor do
	 * Id da ProgramaInstitucionalPesquisa).
	 */
	public ProgramaInstitucionalPesquisa(String siglaPIP, String nomePIP, int instituição_id) {

		setSiglaPIP(siglaPIP);
		setNomePIP(nomePIP);
		setInstituição_id(instituição_id);
		
	}
	
	/* Para as demais consultas, quando for preciso montar um ProgramaInstitucionalPesquisa já existente,
	 * deve ser usado esse construtor, pois é necessário recuperar o ID da Instituição.
	 */
	public ProgramaInstitucionalPesquisa(int id_PIP, String siglaPIP, String nomePIP, int instituição_id) {
		
		setId_PIP(id_PIP);
		setSiglaPIP(siglaPIP);
		setNomePIP(nomePIP);
		setInstituição_id(instituição_id);
		
	}

	public int getId_PIP() {
		return Id_PIP;
	}

	public void setId_PIP(int id_PIP) {
		Id_PIP = id_PIP;
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

	public int getInstituição_id() {
		return Instituição_id;
	}

	public void setInstituição_id(int instituição_id) {
		Instituição_id = instituição_id;
	}

}
