package br.edu.ifpb.qmanager.entidade;

import java.util.HashMap;
import java.util.Map;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="erro")
public class QManagerErro {

	private int codigo;
	private String mensagem;

	private static final Map<Integer, String> erros = new HashMap<Integer, String>();
	static {
		// Instituição financiadora
		erros.put(10, "Erro: CNPJ inválido!");
		erros.put(11, "Erro: Nome da Instituição Financiadora inválido!");
		erros.put(12, "Erro: Sigla da Instituição Financiadora inválida!");
		erros.put(13, "Erro: Valor do orcamento inválido!");

		// Programa Institucional
		erros.put(14, "Erro: Nome do Programa Institucional inválido!");
		erros.put(15, "Erro: Sigla do Programa Institucional inválida!");
		erros.put(16, "Erro: Valor do orçamento inválido!");
		erros.put(17, "Erro: Identificador da Instituição Financiadora inválido!");

		// Edital
		erros.put(18, "Erro: Arquivo de Edital inválido!");
		erros.put(19, "Erro: Número de Edital inválido!");
		erros.put(20, "Erro: Ano de Edital inválido!");
		erros.put(27, "Erro: Número de vagas inválido!");
		erros.put(28, "Erro: Valor da bolsa do discente inválido!");
		erros.put(29, "Erro: Valor da bolsa do docente inválido!");
		erros.put(31, "Erro: Identificador do Programa Institucional inválido!");
		
		// Projeto
		erros.put(32, "Erro: Nome do projeto inválido!");
		erros.put(36, "Erro: Arquivo do Relatório Submetido inválido!");
		erros.put(37, "Erro: Arquivo do Relatório Parcial inválido!");
		erros.put(38, "Erro: Arquivo do Relatório Final inválido!");
		erros.put(39, "Erro: Número do processo inválido!");
		erros.put(41, "Erro: Identificador de Edital inválido!");
		
		// Pessoa
		erros.put(42, "Erro: Nome da pessoa inválido!");
		erros.put(43, "Erro: CPF inválido!");
		erros.put(44, "Erro: Matrícula inválida!");
		erros.put(45, "Erro: Endereco inválido!");
		erros.put(46, "Erro: CEP inválido!");
		erros.put(47, "Erro: Telefone inválido!");
		erros.put(48, "Erro: E-mail inválido!");
		erros.put(51, "Erro: Identificador da Instituição Bancária inválido!");
		erros.put(52, "Erro: Operação da conta inválida!");
		erros.put(53, "Erro: Número da conta inválido!");
		
		// Discente
		erros.put(50, "Erro: Identificador da Turma inválido!");
		
		// Docente
		erros.put(54, "Erro: Titulação inválida!");
		erros.put(55, "Erro: Cargo inválido!");
		erros.put(56, "Erro: Local de Trabalho inválido!");
		
		// Participação
		erros.put(57, "Erro: Identificador do Membro de Projeto inválido!");
		erros.put(58, "Erro: Identificador de Projeto inválido!");
		erros.put(62, "Erro: Valor da bolsa inválido!");
		
		// Instituição Bancária
		erros.put(63, "Erro: Nome do Banco inválido!");
		
		// Curso
		erros.put(64, "Erro: Nome do Curso inválido!");
		
		// Turma
		erros.put(67, "Erro: Identificador da Turma inválido!");
	}

	public QManagerErro() {
	}

	public QManagerErro(int erro) {
		setCodigo(erro);
		setMensagem(erros.get(erro));
	}

	@XmlElement
	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	@XmlElement
	public String getMensagem() {
		return mensagem;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}
}
