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
				"CNPJ inválido!");
		erros.put(QManagerCodeErro.NOME_INSTITUICAO_FINANCIADORA_INVALIDA, 
				"Nome da Instituição Financiadora inválido!");
		erros.put(QManagerCodeErro.SIGLA_INSTITUICAO_FINANCIADORA_INVALIDA, 
				"Sigla da Instituição Financiadora inválida!");
		erros.put(QManagerCodeErro.VALOR_ORCAMENTO_INVALIDO, 
				"Valor do orcamento inválido!");

		// Programa Institucional
		erros.put(QManagerCodeErro.NOME_PROGRAMA_INSTITUCIONAL_INVALIDO, 
				"Nome do Programa Institucional inválido!");
		erros.put(QManagerCodeErro.SIGLA_PROGRAMA_INSTITUCIONAL_INVALIDA, 
				"Sigla do Programa Institucional inválida!");
		//TODO: Duplicado! erros.put(16, "Valor do orçamento inválido!");
		erros.put(QManagerCodeErro.ID_INSITUICAO_FINANCIADORA_INVALIDO, 
				"Identificador da Instituição Financiadora inválido!");

		// Edital
		erros.put(QManagerCodeErro.ARQUIVO_EDITAL_INVALIDO, 
				"Arquivo de Edital inválido!");
		erros.put(QManagerCodeErro.NUMERO_EDITAL_INVALIDO, 
				"Número de Edital inválido!");
		erros.put(QManagerCodeErro.ANO_EDITAL_INVALIDO, 
				"Ano de Edital inválido!");
		erros.put(QManagerCodeErro.NUMERO_VAGA_INVALIDO, 
				"Número de vagas inválido!");
		erros.put(QManagerCodeErro.VALOR_BOLSA_DISCENTE_INVALIDO, 
				"Valor da bolsa do discente inválido!");
		erros.put(QManagerCodeErro.VALOR_BOLSA_DOCENTE_INVALIDO, 
				"Valor da bolsa do docente inválido!");
		erros.put(QManagerCodeErro.ID_PROGRAMA_INSTITUCIONAL_INVALIDO, 
				"Identificador do Programa Institucional inválido!");
		
		// Projeto
		erros.put(QManagerCodeErro.NOME_PROJETO_INVALIDO, 
				"Nome do projeto inválido!");
		erros.put(QManagerCodeErro.ARQUIVO_RELATORIO_INVALIDO, 
				"Arquivo do Relatório Submetido inválido!");
		erros.put(QManagerCodeErro.ARQUIVO_RELATORIO_PARCIAL_INVALIDO, 
				"Arquivo do Relatório Parcial inválido!");
		erros.put(QManagerCodeErro.ARQUIVO_RELATORIO_FINAL_INVALIDO, 
				"Arquivo do Relatório Final inválido!");
		erros.put(QManagerCodeErro.NUMERO_PROCESSO_INVALIDO, 
				"Número do processo inválido!");
		erros.put(QManagerCodeErro.ID_EDITAL_INVALIDO, 
				"Identificador de Edital inválido!");
		
		// Pessoa
		erros.put(QManagerCodeErro.NOME_PESSOA_INVALIDO, 
				"Nome da pessoa inválido!");
		erros.put(QManagerCodeErro.CPF_INVALIDO, 
				"CPF inválido!");
		erros.put(QManagerCodeErro.MATRICULA_INVALIDA, 
				"Matrícula inválida!");
		erros.put(QManagerCodeErro.ENDERECO_INVALIDO, 
				"Endereco inválido!");
		erros.put(QManagerCodeErro.CEP_INVALIDO, 
				"CEP inválido!");
		erros.put(QManagerCodeErro.TELEFONE_INVALIDO, 
				"Telefone inválido!");
		erros.put(QManagerCodeErro.EMAIL_INVALIDO, 
				"E-mail inválido!");
		erros.put(QManagerCodeErro.ID_INSTITUICAO_BANCARIA_INVALIDO, 
				"Identificador da Instituição Bancária inválido!");
		erros.put(QManagerCodeErro.OPERACAO_CONTA_INVALIDA, 
				"Operação da conta inválida!");
		erros.put(QManagerCodeErro.NUMERO_CONTA_INVALIDO, 
				"Número da conta inválido!");
		
		// Discente
		erros.put(QManagerCodeErro.ID_TURMA_INVALIDO, 
				"Identificador da Turma inválido!");
		
		// Docente
		erros.put(QManagerCodeErro.TITULACAO_INVALIDA, 
				"Titulação inválida!");
		erros.put(QManagerCodeErro.CARGO_INVALIDO, "Cargo inválido!");
		erros.put(56, "Local de Trabalho inválido!");
		
		// Participação
		erros.put(QManagerCodeErro.ID_MEMBRO_PROJETO_INVALIDO, 
				"Identificador do Membro de Projeto inválido!");
		erros.put(QManagerCodeErro.ID_PROJETO_INVALIDO, 
				"Identificador de Projeto inválido!");
		erros.put(62, "Valor da bolsa inválido!");
		
		// Instituição Bancária
		erros.put(QManagerCodeErro.NOME_BANCO_INVALIDO, 
				"Nome do Banco inválido!");
		
		// Curso
		erros.put(QManagerCodeErro.NOME_CURSO_INVALIDO, 
				"Nome do Curso inválido!");
		
		//TODO: Duplicado! Turma
		erros.put(QManagerCodeErro.ID_TURMA_INVALIDO, 
				"Identificador da Turma inválido!");
	}

	public QManagerErro() {
	}

	public QManagerErro(int erro) {
		setCodigo(erro);
		
		String mensagem = erros.get(erro);
		if (mensagem == null) {
			setMensagem("Mensagem de erro não encontrada.");
		}
		
		setMensagem(mensagem);		
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
