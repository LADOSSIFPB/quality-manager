package br.edu.ifpb.entidades;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

// Campos da tabela
// ID_projeto, nome_projeto, data_início, data_término, ano_projeto, relatório_parcial, relatório_final, processo, ano_N

public class ProjetoPesquisa {
	
	private String ID_projeto;
	private String NomeProjeto;
	private Date DataInício;
	private Date DataTérmino;
	private String AnoProjeto;
	private String RelatórioParcial;
	private String RelatórioFinal;
	private String Processo;
	private String AnoN;
	
	
	/* Contrutor para a inserção do Projeto de Pesquisa no Banco de dados.
	 * Ou seja, inicialmente não é necessário ter informações como Data de ínicio, Data de termino,
	 * nem nenhum tipo de relatórios. Tais informações serão ditas no decorrer do projeto,
	 * ou caso seja aceito.
	 */
	public ProjetoPesquisa(String nomeProjeto, Date dataInício,	String anoProjeto,
						String processo, String anoN){
		
		setNomeProjeto(nomeProjeto);
		setAnoProjeto(anoProjeto);
		setProcesso(processo);
		setAnoN(anoN);
		
	}
	
	/* Para as demais consultas, quando for preciso montar um Projeto de pesquisa já existente,
	 * deve ser usado esse construtor, pois é necessário recuperar o todas as informações do
	 * projeto de pesquisa.
	 */
	public ProjetoPesquisa(String iD_projeto, String nomeProjeto, String dataInício, String dataTérmino,
						String anoProjeto, String relatórioParcial, String relatórioFinal,
						String processo, String anoN) throws ParseException {
		
		setID_projeto(iD_projeto);
		setNomeProjeto(nomeProjeto);
		setDataInício(recuperarDataJDBC(dataInício));
		setDataTérmino(recuperarDataJDBC(dataTérmino));
		setAnoProjeto(anoProjeto);
		setRelatórioParcial(relatórioParcial);
		setRelatórioFinal(relatórioFinal);
		setProcesso(processo);
		setAnoN(anoN);
		
	}
	
	//Após informar data no formato String, ele retornara um tipo java.sql.Date,
	//Pronto para ser inserido ao banco.
	public Date recuperarDataJDBC(String data) throws ParseException{
		SimpleDateFormat f = new SimpleDateFormat("dd/MM/yyyy");
		
		java.util.Date dataUtil = f.parse(data);
		
		java.sql.Date dataJDBC = new java.sql.Date(dataUtil.getTime());
		return dataJDBC;
	}

	public String getNomeProjeto() {
		return NomeProjeto;
	}

	public void setNomeProjeto(String nomeProjeto) {
		NomeProjeto = nomeProjeto;
	}

	public Date getDataInício() {
		return DataInício;
	}

	public void setDataInício(Date dataInício) {
		DataInício = dataInício;
	}

	public Date getDataTérmino() {
		return DataTérmino;
	}

	public void setDataTérmino(Date dataTérmino) {
		DataTérmino = dataTérmino;
	}

	public String getAnoProjeto() {
		return AnoProjeto;
	}

	public void setAnoProjeto(String anoProjeto) {
		AnoProjeto = anoProjeto;
	}

	public String getRelatórioParcial() {
		return RelatórioParcial;
	}

	public void setRelatórioParcial(String relatórioParcial) {
		RelatórioParcial = relatórioParcial;
	}

	public String getRelatórioFinal() {
		return RelatórioFinal;
	}

	public void setRelatórioFinal(String relatórioFinal) {
		RelatórioFinal = relatórioFinal;
	}

	public String getProcesso() {
		return Processo;
	}

	public void setProcesso(String processo) {
		Processo = processo;
	}

	public String getAnoN() {
		return AnoN;
	}

	public void setAnoN(String anoN) {
		AnoN = anoN;
	}

	public String getID_projeto() {
		return ID_projeto;
	}

	public void setID_projeto(String iD_projeto) {
		ID_projeto = iD_projeto;
	}
	
}
