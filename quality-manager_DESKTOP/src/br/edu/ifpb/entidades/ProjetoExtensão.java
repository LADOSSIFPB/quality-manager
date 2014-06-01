package br.edu.ifpb.entidades;

import java.sql.Date;

//	 							Campos da tabela
//Nome_do_projeto, Data_de_inicio, Data_de_termino, Ano_do_projeto, Relatorio_final,
//Relatorio_parcial, Processo, Registro, EditalPesquisa_N_ANO

public class ProjetoExtensão {
	
	private String NomeProjeto;
	private Date DataÍnicio;
	private Date DataTermino;
	private String AnoProjeto;
	private String RelatorioFinal;
	private String RelatorioParcial;
	private String Processo;
	private String Registro;
	private String EditalPesquisa_N_ANO;
	
	
	/*Contrutor para a inserção do Projeto de Pesquisa no Banco de dados.
	 * Ou seja, inicialmente não é necessário ter informações como Data de ínicio, Data de termino,
	 * nem nenhum tipo de relatórios. Tais informações serão ditas no decorrer do projeto,
	 * ou caso seja aceito.
	 */
	public ProjetoExtensão(String nomeprojeto,	String anoprojeto,
						String processo, String editalpesquisa_N_ano){
		
		setNomeProjeto(nomeprojeto);
		setAnoProjeto(anoprojeto);
		setProcesso(processo);
		setEditalPesquisa_N_ANO(editalpesquisa_N_ano);
		
	}
	
	/* Para as demais consultas, quando for preciso montar um Projeto de pesquisa já existente,
	 * deve ser usado esse construtor, pois é necessário recuperar o todas as informações do
	 * projeto de pesquisa.
	 */
	public ProjetoExtensão(String nomeprojeto, Date dataínicio, Date datatérmino, String anoprojeto,
			String relatórioparcial, String relatóriofinal, String processo,
			String registro, String editalpesquisa_N_ano) {
		
		setNomeProjeto(nomeprojeto);
		setDataÍnicio(dataínicio);
		setDataTermino(datatérmino);
		setAnoProjeto(anoprojeto);
		setRelatorioParcial(relatórioparcial);
		setRelatorioFinal(relatóriofinal);
		setProcesso(processo);
		setRegistro(registro);
		setEditalPesquisa_N_ANO(editalpesquisa_N_ano);
		
		
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

	public Date getDataTermino() {
		return DataTermino;
	}

	public void setDataTermino(Date dataTermino) {
		DataTermino = dataTermino;
	}

	public String getAnoProjeto() {
		return AnoProjeto;
	}

	public void setAnoProjeto(String anoProjeto) {
		AnoProjeto = anoProjeto;
	}

	public String getRelatorioFinal() {
		return RelatorioFinal;
	}

	public void setRelatorioFinal(String relatorioFinal) {
		RelatorioFinal = relatorioFinal;
	}

	public String getRelatorioParcial() {
		return RelatorioParcial;
	}

	public void setRelatorioParcial(String relatorioParcial) {
		RelatorioParcial = relatorioParcial;
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