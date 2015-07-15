package br.edu.ifpb.qmanager.entidade;

import java.util.HashMap;
import java.util.Map;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "erro")
public class MapErroQManager {

	private Erro erro;

	private static final Map<Integer, String> erros = new HashMap<Integer, String>();
	static {
		// Usuário
		erros.put(CodeErroQManager.USUARIO_INVALIDO, "Login errado!");
		erros.put(CodeErroQManager.SENHA_INVALIDA, "Senha errada!");

		// Instituição financiadora
		erros.put(CodeErroQManager.CNPJ_INVALIDO, "CNPJ inválido!");
		erros.put(CodeErroQManager.NOME_INSTITUICAO_FINANCIADORA_INVALIDA,
				"Nome da Instituição Financiadora inválido!");
		erros.put(CodeErroQManager.SIGLA_INSTITUICAO_FINANCIADORA_INVALIDA,
				"Sigla da Instituição Financiadora inválida!");
		erros.put(CodeErroQManager.VALOR_ORCAMENTO_INVALIDO,
				"Valor do orcamento inválido!");
		erros.put(CodeErroQManager.INSTITUICAO_FINANCIADORA_JA_CADASTRADA,
				"O CNPJ da Instituição Financiadora já foi cadastrado anteriormente.");

		// Programa Institucional
		erros.put(CodeErroQManager.NOME_PROGRAMA_INSTITUCIONAL_INVALIDO,
				"Nome do Programa Institucional inválido!");
		erros.put(CodeErroQManager.SIGLA_PROGRAMA_INSTITUCIONAL_INVALIDA,
				"Sigla do Programa Institucional inválida!");
		// TODO: Duplicado! erros.put(16, "Valor do orçamento inválido!");
		erros.put(CodeErroQManager.ID_INSTITUICAO_FINANCIADORA_INVALIDO,
				"Identificador da Instituição Financiadora inválido!");

		// Edital
		erros.put(CodeErroQManager.ARQUIVO_EDITAL_INVALIDO,
				"Arquivo de Edital inválido!");
		erros.put(CodeErroQManager.NUMERO_EDITAL_INVALIDO,
				"Número de Edital inválido!");
		erros.put(CodeErroQManager.ANO_EDITAL_INVALIDO,
				"Ano de Edital inválido!");
		erros.put(CodeErroQManager.NUMERO_VAGA_INVALIDO,
				"Número de vagas inválido!");
		erros.put(CodeErroQManager.VALOR_BOLSA_DISCENTE_INVALIDO,
				"Valor da bolsa do discente inválido!");
		erros.put(CodeErroQManager.VALOR_BOLSA_DOCENTE_INVALIDO,
				"Valor da bolsa do docente inválido!");
		erros.put(CodeErroQManager.ID_PROGRAMA_INSTITUCIONAL_INVALIDO,
				"Identificador do Programa Institucional inválido!");
		erros.put(CodeErroQManager.EDITAL_ASSOCIADO_INVALIDO,
				"Edital associado inválido.");

		// Projeto
		erros.put(CodeErroQManager.NOME_PROJETO_INVALIDO,
				"Nome do projeto inválido!");
		erros.put(CodeErroQManager.ARQUIVO_RELATORIO_INVALIDO,
				"Arquivo do Relatório Submetido inválido!");
		erros.put(CodeErroQManager.ARQUIVO_RELATORIO_PARCIAL_INVALIDO,
				"Arquivo do Relatório Parcial inválido!");
		erros.put(CodeErroQManager.ARQUIVO_RELATORIO_FINAL_INVALIDO,
				"Arquivo do Relatório Final inválido!");
		erros.put(CodeErroQManager.NUMERO_PROCESSO_INVALIDO,
				"Número do processo inválido!");
		erros.put(CodeErroQManager.ID_EDITAL_INVALIDO,
				"Identificador de Edital inválido!");

		// Pessoa
		erros.put(CodeErroQManager.NOME_PESSOA_INVALIDO,
				"Nome da pessoa inválido!");
		erros.put(CodeErroQManager.CPF_INVALIDO, "CPF inválido!");
		erros.put(CodeErroQManager.MATRICULA_INVALIDA, "Matrícula inválida!");
		erros.put(CodeErroQManager.ENDERECO_INVALIDO, "Endereco inválido!");
		erros.put(CodeErroQManager.CEP_INVALIDO, "CEP inválido!");
		erros.put(CodeErroQManager.TELEFONE_INVALIDO, "Telefone inválido!");
		erros.put(CodeErroQManager.EMAIL_INVALIDO, "E-mail inválido!");
		erros.put(CodeErroQManager.ID_INSTITUICAO_BANCARIA_INVALIDO,
				"Identificador da Instituição Bancária inválido!");
		erros.put(CodeErroQManager.OPERACAO_CONTA_INVALIDA,
				"Operação da conta inválida!");
		erros.put(CodeErroQManager.NUMERO_CONTA_INVALIDO,
				"Número da conta inválido!");

		// Discente
		erros.put(CodeErroQManager.ID_TURMA_INVALIDO,
				"Identificador da Turma inválido!");

		// Docente
		erros.put(CodeErroQManager.TITULACAO_INVALIDA, "Titulação inválida!");
		erros.put(CodeErroQManager.CARGO_INVALIDO, "Cargo inválido!");
		erros.put(CodeErroQManager.LOCAL_TRABALHO_INVALIDO,
				"Local de Trabalho inválido!");

		// Participação
		erros.put(CodeErroQManager.ID_MEMBRO_PROJETO_INVALIDO,
				"Identificador do Membro de Projeto inválido!");
		erros.put(CodeErroQManager.ID_PROJETO_INVALIDO,
				"Identificador de Projeto inválido!");
		erros.put(CodeErroQManager.VALOR_BOLSA_INVALIDO,
				"Valor da bolsa inválido!");
		erros.put(CodeErroQManager.PARTICIPACAO_DATA_INVALIDA, 
				"Data da participação inválida!"
				+ " Verifique se corresponde com a data de início das atividades"
				+ " no Edital associado.");

		// Instituição Bancária
		erros.put(CodeErroQManager.NOME_BANCO_INVALIDO,
				"Nome do Banco inválido!");

		// Curso
		erros.put(CodeErroQManager.NOME_CURSO_INVALIDO,
				"Nome do Curso inválido!");

		// TODO: Duplicado! Turma
		erros.put(CodeErroQManager.ID_TURMA_INVALIDO,
				"Identificador da Turma inválido!");

		// Arquivo inválido.
		erros.put(CodeErroQManager.FORMATO_ARQUIVO_INVALIDO,
				"Formato do arquivo inválido!");

		// Código do curso inexistente
		erros.put(CodeErroQManager.CURSO_INEXISTENTE,
				"Código do curso inexistente");

		// Código do curso inexistente
		erros.put(CodeErroQManager.SERVIDOR_HABILITADO_INEXISTENTE,
				"Siape do servidor inexistente");

		// Código do campus inexistente
		erros.put(CodeErroQManager.CAMPUS_INEXISTENTE,
				"Código do campus inexistente");

		// Código do campus inexistente
		erros.put(CodeErroQManager.SERVIDOR_JA_HABILITADO,
				"Servidor já foi habilitado anteriormente");

		// Orçamento do Recurso de Instituição Financiadora Insuficiente
		erros.put(CodeErroQManager.ORCAMENTO_IF_INSUFICIENTE,
				"Orçamento de Instituição Financiadora insuficiente");

		// Recurso Instituição Financiadora Inexistente
		erros.put(CodeErroQManager.ORCAMENTO_IF_INEXISTENTE,
				"Instituição Financiadora Inexistente");

		// Recurso Instituição Financiadora Inexistente
		erros.put(CodeErroQManager.ORCAMENTO_PI_INSUFICIENTE,
				"Orçamento de Programa Institucional insuficiente");

	}

	public MapErroQManager() {
	}

	public MapErroQManager(int erro) {

		this.erro = new Erro();

		this.erro.setCodigo(erro);

		String mensagem = erros.get(erro);
		if (mensagem == null) {
			this.erro
					.setMensagem("Erro interno no sistema. Contate equipe de desenvolvimento.");
		}

		this.erro.setMensagem(mensagem);
	}

	public Erro getErro() {
		return erro;
	}

	public void setErro(Erro erro) {
		this.erro = erro;
	}
}
