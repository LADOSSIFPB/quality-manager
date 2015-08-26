package br.edu.ifpb.qmanager.entidade;

public class CodeErroQManager {

	private static int index = 1;

	// uso múltiplo ou indefinido
	public static final int CADASTRADOR_INVALIDO = index++;
	public static final int PERIODO_INVALIDO = index++;
	public static final int VALOR_ORCAMENTO_INVALIDO = index++;

	// Usuário
	public static final int USUARIO_INVALIDO = index++;
	public static final int SENHA_INVALIDA = index++;

	// Instituição Financiadora
	public static final int CNPJ_INVALIDO = index++;
	public static final int NOME_INSTITUICAO_FINANCIADORA_INVALIDA = index++;
	public static final int SIGLA_INSTITUICAO_FINANCIADORA_INVALIDA = index++;

	public static final int INSTITUICAO_FINANCIADORA_JA_CADASTRADA = index++;
	public static final int INSTITUICAO_FINANCIADORA_INVALIDA = index++;

	// Recurso Instituicao Financiadora
	public static final int RECURSO_INSTITUICAO_FINANCIADORA_INVALIDO = index++;

	// Programa Institucional
	public static final int NOME_PROGRAMA_INSTITUCIONAL_INVALIDO = index++;
	public static final int SIGLA_PROGRAMA_INSTITUCIONAL_INVALIDA = index++;
	
	public static final int PROGRAMA_INSTITUCIONAL_INVALIDO = index++;

	// Tipo Programa Institucional
	public static final int TIPO_PROGRAMA_INSTITUCIONAL_INVALIDO = index++;

	// Edital
	public static final int NUMERO_EDITAL_INVALIDO = index++;
	public static final int ANO_EDITAL_INVALIDO = index++;
	public static final int DESCRICAO_EDITAL_INVALIDA = index++;
	public static final int PERIODO_INSCRICAO_PROJETO_INVALIDO = index++;
	public static final int QUANTIDADE_PROJETO_INVALIDO = index++;
	public static final int NUMERO_VAGA_INVALIDO = index++;
	public static final int VALOR_BOLSA_DISCENTE_INVALIDO = index++;
	public static final int VALOR_BOLSA_DOCENTE_INVALIDO = index++;
	public static final int PERIODO_RELATORIO_INVALIDO = index++;
	public static final int PERIODO_AVALIACAO_INVALIDO = index++;
	public static final int RESULTADO_PRELIMINAR_INVALIDO = index++;
	public static final int RECEBIMENTO_RECURSOS_INVALIDO = index++;
	public static final int DIVULGACAO_RESULTADO_FINAL_INVALIDO = index++;
	public static final int INICIO_ATIVIDADES_INVALIDO = index++;

	// Projeto
	public static final int PROJETO_INVALIDO = index++;
	public static final int NOME_PROJETO_INVALIDO = index++;
	public static final int RESUMO_PROJETO_INVALIDO = index++;
	public static final int PERIODO_PROJETO_INVALIDO = index++;
	public static final int NUMERO_PROCESSO_INVALIDO = index++;

	// Grande Area
	public static final int GRANDE_AREA_INVALIDA = index++;

	// Area
	public static final int AREA_INVALIDA = index++;

	// TODO: organizar abaixo

	public static final int NOME_PESSOA_INVALIDO = index++;
	public static final int CPF_INVALIDO = index++;
	public static final int MATRICULA_INVALIDA = index++;
	public static final int ENDERECO_INVALIDO = index++;
	public static final int CEP_INVALIDO = index++;
	public static final int TELEFONE_INVALIDO = index++;
	public static final int EMAIL_INVALIDO = index++;
	public static final int ID_INSTITUICAO_BANCARIA_INVALIDO = index++;

	public static final int OPERACAO_CONTA_INVALIDA = index++;
	public static final int NUMERO_CONTA_INVALIDO = index++;
	public static final int ID_TURMA_INVALIDO = index++;
	public static final int TITULACAO_INVALIDA = index++;
	public static final int CARGO_INVALIDO = index++;
	public static final int LOCAL_TRABALHO_INVALIDO = index++;
	public static final int ID_MEMBRO_PROJETO_INVALIDO = index++;
	public static final int INTERVALO_PARTICIPACAO_INVALIDO = index++;
	public static final int VALOR_BOLSA_INVALIDO = index++;
	public static final int NOME_BANCO_INVALIDO = index++;
	public static final int NOME_CURSO_INVALIDO = index++;
	public static final int ID_CURSO_INVALIDO = index++;

	public static final int FORMATO_ARQUIVO_INVALIDO = index++;
	public static final int PROBLEMA_MANIPULAR_ARQUIVO = index++;

	public static final int CURSO_INEXISTENTE = index++;
	public static final int CAMPUS_INVALIDO = index++;

	public static final int SERVIDOR_HABILITADO_INEXISTENTE = index++;
	public static final int SERVIDOR_JA_HABILITADO = index++;

	public static final int EDITAL_ASSOCIADO_INVALIDO = index++;
	public static final int PARTICIPACAO_DATA_INVALIDA = index++;

	public static final int ORCAMENTO_IF_INSUFICIENTE = index++;
	public static final int ORCAMENTO_IF_INEXISTENTE = index++;
	public static final int ORCAMENTO_PI_INSUFICIENTE = index++;

	// chat
	public static final int CONVERSA_INVALIDA = index++;
	public static final int NOME_CONVERSA_INVALIDO = index++;

	// chatLine
	public static final int MENSAGEM_INVALIDA = index;
	public static final int QUANTIDADE_PESSOAS_CONVERSA_INVALIDA = index++;

	// pessoa
	public static final int PESSOA_INVALIDA = index++;

}
