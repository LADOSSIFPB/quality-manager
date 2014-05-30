package br.edu.ifpb.entidades;

import java.util.Date;

//	 							Campos da tabela
//Nome_do_projeto, Data_de_inicio, Data_de_termino, Ano_do_projeto, Relatorio_final,
//Relatorio_parcial, Processo, Registro, EditalPesquisa_N_ANO

public class ProjetoExtensão {
	
	private String Nome_do_projeto;
	private Date Data_de_inicio;
	private Date Data_de_termino;
	private String Ano_do_projeto;
	private String Relatorio_final;
	private String Relatorio_parcial;
	private String Processo;
	private String Registro;
	private String EditalPesquisa_N_ANO;
	
	
	/*Contrutor para a inserção do Projeto de Pesquisa no Banco de dados.
	 * Ou seja, inicialmente não é necessário ter informações como Data de ínicio, Data de termino,
	 * nem nenhum tipo de relatórios. Tais informações serão ditas no decorrer do projeto,
	 * ou caso seja aceito.
	 */
	public ProjetoExtensão(String nome_do_projeto, Date data_de_inicio,	String ano_do_projeto,
						String processo, String editalPesquisa_N_ANO){
		
		setNome_do_projeto(nome_do_projeto);
		setAno_do_projeto(ano_do_projeto);
		setProcesso(processo);
		setEditalPesquisa_N_ANO(editalPesquisa_N_ANO);
		
	}
	
	/* Para as demais consultas, quando for preciso montar um Projeto de pesquisa já existente,
	 * deve ser usado esse construtor, pois é necessário recuperar o todas as informações do
	 * projeto de pesquisa.
	 */
	public ProjetoExtensão(String nome_do_projeto, Date data_de_inicio, Date data_de_termino,
						String ano_do_projeto, String relatorio_parcial, String relatorio_final,
						String processo, String registro, String editalPesquisa_N_ANO) {
		
		setNome_do_projeto(nome_do_projeto);
		setData_de_inicio(data_de_inicio);
		setData_de_termino(data_de_termino);
		setAno_do_projeto(ano_do_projeto);
		setRelatorio_parcial(relatorio_parcial);
		setRelatorio_final(relatorio_final);
		setProcesso(processo);
		setRegistro(registro);
		setEditalPesquisa_N_ANO(editalPesquisa_N_ANO);
		
		
	}

	public String getNome_do_projeto() {
		return Nome_do_projeto;
	}

	public void setNome_do_projeto(String nome_do_projeto) {
		Nome_do_projeto = nome_do_projeto;
	}

	public Date getData_de_inicio() {
		return Data_de_inicio;
	}

	public void setData_de_inicio(Date data_de_inicio) {
		Data_de_inicio = data_de_inicio;
	}

	public Date getData_de_termino() {
		return Data_de_termino;
	}

	public void setData_de_termino(Date data_de_termino) {
		Data_de_termino = data_de_termino;
	}

	public String getAno_do_projeto() {
		return Ano_do_projeto;
	}

	public void setAno_do_projeto(String ano_do_projeto) {
		Ano_do_projeto = ano_do_projeto;
	}

	public String getRelatorio_final() {
		return Relatorio_final;
	}

	public void setRelatorio_final(String relatorio_final) {
		Relatorio_final = relatorio_final;
	}

	public String getRelatorio_parcial() {
		return Relatorio_parcial;
	}

	public void setRelatorio_parcial(String relatorio_parcial) {
		Relatorio_parcial = relatorio_parcial;
	}

	public String getProcesso() {
		return Processo;
	}

	public void setProcesso(String processo) {
		Processo = processo;
	}

	public String getRegistro() {
		return Registro;
	}

	public void setRegistro(String registro) {
		Registro = registro;
	}

	public String getEditalPesquisa_N_ANO() {
		return EditalPesquisa_N_ANO;
	}

	public void setEditalPesquisa_N_ANO(String editalPesquisa_N_ANO) {
		EditalPesquisa_N_ANO = editalPesquisa_N_ANO;
	}
	
	
}