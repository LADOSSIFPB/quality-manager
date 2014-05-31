package br.edu.ifpb.entidades;

import java.sql.Date;

//				Campos da tabela
//Nome, Descricao, Localidade, Ano, Inicio_Evento, Fim_Evento, Area_de_Atuacao

public class Evento {
	
	private int id_evento;
	private String Nome;
	private String Descricao;
	private String Localidade; 
	private String Ano;
	private Date Inicio_Evento;
	private Date Fim_Evento;
	private String Area_de_Atuacao;
	
	/* Construtor utilizado para a criação de um objeto Evento quando for inserir
	 * um novo Evento ao sistema, de modo que a variável
	 * id_evento não precise ser setada (O próprio banco de dados define qual será o valor do
	 * Id da evento).
	 */
	public Evento(String nome, String descricao, String localidade, String ano,
			Date inicio_Evento, Date fim_Evento, String area_de_Atuacao) {
		
		setNome(nome);
		setDescricao(descricao);
		setLocalidade(localidade);
		setAno(ano);
		setInicio_Evento(inicio_Evento);
		setFim_Evento(fim_Evento);
		setArea_de_Atuacao(area_de_Atuacao);
		
	}
	
	/* Para as demais consultas, quando for preciso montar um Evento já existente,
	 * deve ser usado esse construtor, pois é necessário recuperar o ID da Evento.
	 */	
	public Evento(int id_evento, String nome, String descricao, String localidade, String ano,
			Date inicio_Evento, Date fim_Evento, String area_de_Atuacao) {
		
		setId_evento(id_evento);
		setNome(nome);
		setDescricao(descricao);
		setLocalidade(localidade);
		setAno(ano);
		setInicio_Evento(inicio_Evento);
		setFim_Evento(fim_Evento);
		setArea_de_Atuacao(area_de_Atuacao);
		
	}

	public int getId_evento() {
		return id_evento;
	}

	public void setId_evento(int id_evento) {
		this.id_evento = id_evento;
	}

	public String getNome() {
		return Nome;
	}

	public void setNome(String nome) {
		Nome = nome;
	}

	public String getDescricao() {
		return Descricao;
	}

	public void setDescricao(String descricao) {
		Descricao = descricao;
	}

	public String getLocalidade() {
		return Localidade;
	}

	public void setLocalidade(String localidade) {
		Localidade = localidade;
	}

	public String getAno() {
		return Ano;
	}

	public void setAno(String ano) {
		Ano = ano;
	}

	public Date getInicio_Evento() {
		return Inicio_Evento;
	}

	public void setInicio_Evento(Date inicio_Evento) {
		Inicio_Evento = inicio_Evento;
	}

	public Date getFim_Evento() {
		return Fim_Evento;
	}

	public void setFim_Evento(Date fim_Evento) {
		Fim_Evento = fim_Evento;
	}

	public String getArea_de_Atuacao() {
		return Area_de_Atuacao;
	}

	public void setArea_de_Atuacao(String area_de_Atuacao) {
		Area_de_Atuacao = area_de_Atuacao;
	}

}
