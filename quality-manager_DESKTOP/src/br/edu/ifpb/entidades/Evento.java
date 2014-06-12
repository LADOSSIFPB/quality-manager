package br.edu.ifpb.entidades;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

// Campos da tabela
// ID_evento, nome, descrição, localidade, ano, início_evento, fim_evento, área_atuação

public class Evento implements Entidade {
	
	private int IDEvento;
	private String Nome;
	private String Descrição;
	private String Localidade; 
	private String Ano;
	private Date InícioEvento;
	private Date FimEvento;
	private String ÁreaAtuação;
	
	/* Construtor utilizado para a criação de um objeto Evento quando for inserir
	 * um novo Evento ao sistema, de modo que a variável
	 * id_evento não precise ser setada (O próprio banco de dados define qual será o valor do
	 * identificador do evento).
	 */
	public Evento(String nome, String descrição, String localidade, String ano,
			String inícioEvento, String fimEvento, String áreaAtuação) throws ParseException {
		
		setNome(nome);
		setDescrição(descrição);
		setLocalidade(localidade);
		setAno(ano);
		setInícioEvento(recuperarDataJDBC(inícioEvento));
		setFimEvento(recuperarDataJDBC(fimEvento));
		setÁreaAtuação(áreaAtuação);
		
	}
	
	/* Para as demais consultas, quando for preciso montar um Evento já existente,
	 * deve ser usado esse construtor, pois é necessário recuperar o ID da Evento.
	 */	
	public Evento(int idEvento, String nome, String descrição, String localidade,
			String ano,	String inícioEvento, String fimEvento, String áreaAtuação) throws ParseException {
		
		setIDEvento(idEvento);
		setNome(nome);
		setDescrição(descrição);
		setLocalidade(localidade);
		setAno(ano);
		setInícioEvento(recuperarDataJDBC(inícioEvento));
		setFimEvento(recuperarDataJDBC(fimEvento));
		setÁreaAtuação(áreaAtuação);
		
	}
	
	//Após informar data no formato String, ele retornara um tipo java.sql.Date,
	//Pronto para ser inserido ao banco.
	public Date recuperarDataJDBC(String data) throws ParseException{
		SimpleDateFormat f = new SimpleDateFormat("dd/MM/yyyy");
		
		java.util.Date dataUtil = f.parse(data);
		
		java.sql.Date dataJDBC = new java.sql.Date(dataUtil.getTime());
		return dataJDBC;
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

	public Date getInícioEvento() {
		return InícioEvento;
	}

	public void setInícioEvento(Date inícioEvento) {
		InícioEvento = inícioEvento;
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
