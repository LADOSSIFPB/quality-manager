package br.edu.ifpb.qmanager.entidade;

import java.util.HashMap;
import java.util.Map;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "erro")
public class MapErroQManager {

	private Erro erro;

	private static final Map<Integer, String> erros = new HashMap<Integer, String>();
	static {
		// uso múltiplo ou indefinido
		erros.put(CodeErroQManager.PESSOA_INVALIDA,
				"Favor informar identidade válida para pessoa dessa instância!");
		erros.put(CodeErroQManager.CADASTRADOR_INVALIDO,
				"Favor informar identidade válida para o cadastrador dessa instância!");
		erros.put(CodeErroQManager.PERIODO_INVALIDO,
				"Data inicial do período deve ser menor que a data final.");
		erros.put(CodeErroQManager.VALOR_ORCAMENTO_INVALIDO,
				"Valor do orcamento deve ser número real positivo.");

		// Usuário
		erros.put(CodeErroQManager.USUARIO_INVALIDO, "QManager não reconhece email ou matrícula.");
		erros.put(CodeErroQManager.SENHA_INVALIDA, "Senha inválida para este usuário.");

		// Instituição financiadora
		erros.put(CodeErroQManager.CNPJ_INVALIDO, "CNPJ deve ter 14 números.");
		erros.put(CodeErroQManager.NOME_INSTITUICAO_FINANCIADORA_INVALIDA,
				"Nome da Instituição Financiadora deve ter entre 3 e 255 caracteres.");
		erros.put(CodeErroQManager.SIGLA_INSTITUICAO_FINANCIADORA_INVALIDA,
				"Sigla da Instituição Financiadora deve ter entre 3 e 10 caracteres.");

		erros.put(CodeErroQManager.INSTITUICAO_FINANCIADORA_JA_CADASTRADA,
				"Consta em nossos registros Instituição Financiadora com CNPJ informado."
				+ " Verifique CNPJ e tente novamente.");
		erros.put(CodeErroQManager.INSTITUICAO_FINANCIADORA_INVALIDA,
				"Favor informar uma Instituição Financiadora que consta em nossos registros.");

		// Recurso Instituicao Financiadora
		erros.put(CodeErroQManager.RECURSO_INSTITUICAO_FINANCIADORA_INVALIDO,
				"Favor informar um Recurso de uma Instituição Financiadora que consta em nossos registros.");

		// Programa Institucional
		erros.put(CodeErroQManager.NOME_PROGRAMA_INSTITUCIONAL_INVALIDO,
				"Nome do Programa Institucional deve ter entre 3 e 255 caracteres.");
		erros.put(CodeErroQManager.SIGLA_PROGRAMA_INSTITUCIONAL_INVALIDA,
				"Sigla do Programa Institucional deve ter entre 3 e 32 caracteres.");
		
		erros.put(CodeErroQManager.PROGRAMA_INSTITUCIONAL_INVALIDO,
				"Favor informar um Programa Institucional que consta em nossos registros.");

		// Tipo Programa Institucional
		erros.put(CodeErroQManager.TIPO_PROGRAMA_INSTITUCIONAL_INVALIDO,
				"Favor informar um Tipo de Programa Institucional que consta em nossos registros.");

		// Edital
		erros.put(CodeErroQManager.NUMERO_EDITAL_INVALIDO,
				"Número de Edital deve ser número inteiro positivo.");
		erros.put(CodeErroQManager.ANO_EDITAL_INVALIDO,
				"Ano de Edital deve ser número inteiro positivo.");
		erros.put(CodeErroQManager.DESCRICAO_EDITAL_INVALIDA,
				"Descrição de edital ultrapassa 255 caracteres.");
		erros.put(CodeErroQManager.PERIODO_INSCRICAO_PROJETO_INVALIDO,
				"Datas para inscrição de Projeto inválidas!"
						+ " Verifique se corresponde ao período descrito"
						+ " no Edital associado.");
		erros.put(CodeErroQManager.QUANTIDADE_PROJETO_INVALIDO,
				"Quantidade de Projetos deve ser um número inteiro positivo.");
		erros.put(CodeErroQManager.NUMERO_VAGA_INVALIDO,
				"Número de vagas deve ser número inteiro positivo.");
		erros.put(CodeErroQManager.VALOR_BOLSA_DISCENTE_INVALIDO,
				"Valor da bolsa do discente deve ser número real positivo ou zero.");
		erros.put(CodeErroQManager.VALOR_BOLSA_DOCENTE_INVALIDO,
				"Valor da bolsa do docente deve ser número real positivo ou zero.");
		erros.put(CodeErroQManager.PERIODO_RELATORIO_INVALIDO,
				"Datas do período de relatório não estão em ordem crescente.");
		erros.put(CodeErroQManager.PERIODO_AVALIACAO_INVALIDO,
				"Datas do período de avaliação não estão em ordem crescente.");
		erros.put(CodeErroQManager.RESULTADO_PRELIMINAR_INVALIDO,
				"Data do resultado preliminar deve ser após data do fim da avaliação.");
		erros.put(CodeErroQManager.RECEBIMENTO_RECURSOS_INVALIDO,
				"Data do recebimento de recursos deve ser após data do resultado preliminar.");
		erros.put(CodeErroQManager.DIVULGACAO_RESULTADO_FINAL_INVALIDO,
				"Data da divulgação do resultado final deve ser após data do recebimento de recursos.");
		erros.put(CodeErroQManager.INICIO_ATIVIDADES_INVALIDO,
				"Data de início das atividades deve ser após data da divulgação do resultado final.");

		// Projeto
		erros.put(CodeErroQManager.PROJETO_INVALIDO,
				"Favor informar um Projeto que consta em nossos registros.");
		erros.put(CodeErroQManager.EDITAL_ASSOCIADO_INVALIDO,
				"Edital associado inválido. Favor verificar escolha do Edital.");
		erros.put(CodeErroQManager.NOME_PROJETO_INVALIDO,
				"Nome do projeto deve ter até 255 caracteres.");
		erros.put(CodeErroQManager.RESUMO_PROJETO_INVALIDO,
				"Resumo do projeto deve ter até 300 caracteres.");
		erros.put(CodeErroQManager.PERIODO_PROJETO_INVALIDO,
				"Datas para execução do Projeto inválidas!"
						+ " Verifique se está entre o período descrito"
						+ " no Edital associado.");
		erros.put(CodeErroQManager.NUMERO_PROCESSO_INVALIDO,
				"Número do processo deve ter 21 algarismos!");

		// Grande Area
		erros.put(CodeErroQManager.GRANDE_AREA_INVALIDA,
				"Favor informar Grande Área que consta em nossos registros.");

		// Area
		erros.put(CodeErroQManager.AREA_INVALIDA,
				"Favor informar Área que consta em nossos registros.");

		// TODO: Organizar abaixo
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
				"Identificador da Instituição Bancária deve ser um inteiro positivo.");
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

		// Campus
		erros.put(CodeErroQManager.CAMPUS_INVALIDO,
				"Favor informar um Campus que consta em nossos registros.");

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

		// chat
		erros.put(CodeErroQManager.CONVERSA_INVALIDA,
				"Favor informar conversa que consta em nossos registros.");
		erros.put(CodeErroQManager.NOME_CONVERSA_INVALIDO,
				"Nome chat deve ter menos de 255 caracteres.");
		
		// chatLine
		erros.put(CodeErroQManager.MENSAGEM_INVALIDA,
				"Coloque algum texto na mensagem.");
		erros.put(CodeErroQManager.QUANTIDADE_PESSOAS_CONVERSA_INVALIDA,
				"Deve haver pessoas na conversa.");
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
