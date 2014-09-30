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
		erros.put(QManagerCodeErro.CNPJ_INVALIDO, 
				"Erro: CNPJ inválido!");
		erros.put(QManagerCodeErro.INSTITUICAO_FINANCIADORA_INVALIDA, 
				"Erro: Nome da Instituição Financiadora inválido!");
		erros.put(QManagerCodeErro.SIGLA_INSTITUICAO_FINANCIADORA_INVALIDA, 
				"Erro: Sigla da Instituição Financiadora inválida!");
		erros.put(QManagerCodeErro.VALOR_ORCAMENTO_INVALIDO, 
				"Erro: Valor do orcamento inválido!");

		// Programa Institucional
		erros.put(QManagerCodeErro.PROGRAMA_INSTITUCIONAL_INVALIDO, 
				"Erro: Nome do Programa Institucional inválido!");
		erros.put(QManagerCodeErro.SIGLA_PROGRAMA_INSTITUCIONAL_INVALIDA, 
				"Erro: Sigla do Programa Institucional inválida!");
		//TODO: Duplicado! erros.put(16, "Erro: Valor do orçamento inválido!");
		erros.put(QManagerCodeErro.ID_INSITUICAO_FINANCIADORA_INVALIDO, 
				"Erro: Identificador da Instituição Financiadora inválido!");

		// Edital
		erros.put(QManagerCodeErro.ARQUIVO_EDITAL_INVALIDO, 
				"Erro: Arquivo de Edital inválido!");
		erros.put(QManagerCodeErro.NUMERO_EDITAL_INVALIDO, 
				"Erro: Número de Edital inválido!");
		erros.put(QManagerCodeErro.ANO_EDITAL_INVALIDO, 
				"Erro: Ano de Edital inválido!");
		erros.put(QManagerCodeErro.NUMERO_VAGA_INVALIDO, 
				"Erro: Número de vagas inválido!");
		erros.put(QManagerCodeErro.VALOR_BOLSA_DISCENTE_INVALIDO, 
				"Erro: Valor da bolsa do discente inválido!");
		erros.put(QManagerCodeErro.VALOR_BOLSA_DOCENTE_INVALIDO, 
				"Erro: Valor da bolsa do docente inválido!");
		erros.put(QManagerCodeErro.ID_PROGRAMA_INSTITUCIONAL_INVALIDO, 
				"Erro: Identificador do Programa Institucional inválido!");
		
		// Projeto
		erros.put(QManagerCodeErro.NOME_PROJETO_INVALIDO, 
				"Erro: Nome do projeto inválido!");
		erros.put(QManagerCodeErro.ARQUIVO_RELATORIO_INVALIDO, 
				"Erro: Arquivo do Relatório Submetido inválido!");
		erros.put(QManagerCodeErro.ARQUIVO_RELATORIO_PARCIAL_INVALIDO, 
				"Erro: Arquivo do Relatório Parcial inválido!");
		erros.put(QManagerCodeErro.ARQUIVO_RELATORIO_FINAL_INVALIDO, 
				"Erro: Arquivo do Relatório Final inválido!");
		erros.put(QManagerCodeErro.NUMERO_PROCESSO_INVALIDO, 
				"Erro: Número do processo inválido!");
		erros.put(QManagerCodeErro.ID_EDITAL_INVALIDO, 
				"Erro: Identificador de Edital inválido!");
		
		// Pessoa
		erros.put(QManagerCodeErro.NOME_PESSOA_INVALIDO, 
				"Erro: Nome da pessoa inválido!");
		erros.put(QManagerCodeErro.CPF_INVALIDO, 
				"Erro: CPF inválido!");
		erros.put(QManagerCodeErro.MATRICULA_INVALIDA, 
				"Erro: Matrícula inválida!");
		erros.put(QManagerCodeErro.ENDERECO_INVALIDO, 
				"Erro: Endereco inválido!");
		erros.put(QManagerCodeErro.CEP_INVALIDO, 
				"Erro: CEP inválido!");
		erros.put(QManagerCodeErro.TELEFONE_INVALIDO, 
				"Erro: Telefone inválido!");
		erros.put(QManagerCodeErro.EMAIL_INVALIDO, 
				"Erro: E-mail inválido!");
		erros.put(QManagerCodeErro.ID_INSTITUICAO_BANCARIA_INVALIDO, 
				"Erro: Identificador da Instituição Bancária inválido!");
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
