package br.edu.ifpb.entidades;

import java.sql.Date;

//	 							Campos da tabela
//Nome_do_projeto, Data_de_inicio, Data_de_termino, Ano_do_projeto, Relatorio_parcial,
//Relatorio_final, Processo, EditalPesquisa_N_ANO

public class ProjetoPesquisa {
	
	private String NomeProjeto;
	private Date DataÍnicio;
	private Date DataTérmino;
	private String AnoProjeto;
	private String RelatorioParcial;
	private String RelatorioFinal;
	private String Processo;
	private String EditalPesquisa_N_Ano;
	
	
	/*Contrutor para a inserção do Projeto de Pesquisa no Banco de dados.
	 * Ou seja, inicialmente não é necessário ter informações como Data de ínicio, Data de termino,
	 * nem nenhum tipo de relatórios. Tais informações serão ditas no decorrer do projeto,
	 * ou caso seja aceito.
	 */
	public ProjetoPesquisa(String nomeprojeto, Date dataínicio,	String anoprojeto,
						String processo, String editalpesquisa_N_ano){
		
		setNomeProjeto(nomeprojeto);
		setAnoProjeto(anoprojeto);
		setProcesso(processo);
		setEditalPesquisa_N_Ano(editalpesquisa_N_ano);
		
	}
	
	/* Para as demais consultas, quando for preciso montar um Projeto de pesquisa já existente,
	 * deve ser usado esse construtor, pois é necessário recuperar o todas as informações do
	 * projeto de pesquisa.
	 */
	public ProjetoPesquisa(String nomeprojeto, Date dataínicio, Date datatérmino,
						String anoprojeto, String relatórioparcial, String relatóriofinal,
						String processo, String editalpesquisa_N_ano) {
		
		setNomeProjeto(nomeprojeto);
		setDataÍnicio(dataínicio);
		setDataTérmino(datatérmino);
		setAnoProjeto(anoprojeto);
		setRelatorioParcial(relatórioparcial);
		setRelatorioFinal(relatóriofinal);
		setProcesso(processo);
		setEditalPesquisa_N_Ano(editalpesquisa_N_ano);
		
		
	}

	public String getNomeProjeto() {
		return NomeProjeto;
	}

	public void setNomeProjeto(String nomeProjeto) {
		NomeProjeto = nomeProjeto;
	}

	public Date getDataÍnicio() {
		return DataÍnicio;
	}

	public void setDataÍnicio(Date dataÍnicio) {
		DataÍnicio = dataÍnicio;
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

	public String getRelatorioParcial() {
		return RelatorioParcial;
	}

	public void setRelatorioParcial(String relatorioParcial) {
		RelatorioParcial = relatorioParcial;
	}

	public String getRelatorioFinal() {
		return RelatorioFinal;
	}

	public void setRelatorioFinal(String relatorioFinal) {
		RelatorioFinal = relatorioFinal;
	}

	public String getProcesso() {
		return Processo;
	}

	public void setProcesso(String processo) {
		Processo = processo;
	}

	public String getEditalPesquisa_N_Ano() {
		return EditalPesquisa_N_Ano;
	}

	public void setEditalPesquisa_N_Ano(String editalPesquisa_N_ano) {
		EditalPesquisa_N_Ano = editalPesquisa_N_ano;
	}


	

}
