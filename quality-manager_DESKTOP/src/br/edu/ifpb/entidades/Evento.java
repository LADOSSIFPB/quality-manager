package br.edu.ifpb.entidades;

import java.sql.Date;

//				Campos da tabela
//Nome, Descricao, Localidade, Ano, Inicio_Evento, Fim_Evento, Area_de_Atuacao

public class Evento {
	
	private int IDEvento;
	private String Nome;
	private String Descrição;
	private String Localidade; 
	private String Ano;
	private Date ÍnicioEvento;
	private Date FimEvento;
	private String ÁreaAtuação;
	
	/* Construtor utilizado para a criação de um objeto Evento quando for inserir
	 * um novo Evento ao sistema, de modo que a variável
	 * id_evento não precise ser setada (O próprio banco de dados define qual será o valor do
	 * Id da evento).
	 */
	public Evento(String nome, String descrição, String localidade, String ano,
			Date ínicioevento, Date fimevento, String áreaatuação) {
		
		setNome(nome);
		setDescrição(descrição);
		setLocalidade(localidade);
		setAno(ano);
		setÍnicioEvento(ínicioevento);
		setFimEvento(fimevento);
		setÁreaAtuação(áreaatuação);
		
	}
	
	/* Para as demais consultas, quando for preciso montar um Evento já existente,
	 * deve ser usado esse construtor, pois é necessário recuperar o ID da Evento.
	 */	
	public Evento(int idEvento, String nome, String descrição, String localidade,
			String ano,	Date ínicioEvento, Date fimEvento, String áreaAtuação) {
		
		setIDEvento(idEvento);
		setNome(nome);
		setDescrição(descrição);
		setLocalidade(localidade);
		setAno(ano);
		setÍnicioEvento(ínicioEvento);
		setFimEvento(fimEvento);
		setÁreaAtuação(áreaAtuação);
		
	}

	public int getIDEvento() {
		return IDEvento;
	}

	public void setIDEvento(int iDEvento) {
		IDEvento = iDEvento;
	}

	public String getNome() {
		return Nome;
	}

	public void setNome(String nome) {
		Nome = nome;
	}

	public String getDescrição() {
		return Descrição;
	}

	public void setDescrição(String descrição) {
		Descrição = descrição;
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

	public Date getÍnicioEvento() {
		return ÍnicioEvento;
	}

	public void setÍnicioEvento(Date ínicioEvento) {
		ÍnicioEvento = ínicioEvento;
	}

	public Date getFimEvento() {
		return FimEvento;
	}

	public void setFimEvento(Date fimEvento) {
		FimEvento = fimEvento;
	}

	public String getÁreaAtuação() {
		return ÁreaAtuação;
	}

	public void setÁreaAtuação(String áreaAtuação) {
		ÁreaAtuação = áreaAtuação;
	}

	

	

}
