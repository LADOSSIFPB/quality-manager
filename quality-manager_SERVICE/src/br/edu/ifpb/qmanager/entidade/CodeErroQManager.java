package br.edu.ifpb.qmanager.entidade;

public class CodeErroQManager {

	private static int index = 1;

	// uso múltiplo ou indefinido
	public static final int CADASTRADOR_INVALIDO = index++;
	public static final int PERIODO_INVALIDO = index++;
	public static final int VALOR_ORCAMENTO_INVALIDO = index++;
	public static final int CAMPUS_INVALIDO = index++;

	// Usuário
	public static final int USUARIO_INVALIDO = index++;
	public static final int SENHA_INVALIDA = index++;

	// Instituição Financiadora
	public static final int CNPJ_INVALIDO = index++;
	public static final int TAMANHO_NOME_INSTITUICAO_FINANCIADORA_INVALIDA = index++;
	public static final int NOME_INSTITUICAO_FINANCIADORA_INVALIDA = index++;
	public static final int SIGLA_INSTITUICAO_FINANCIADORA_INVALIDA = index++;

	public static final int INSTITUICAO_FINANCIADORA_JA_CADASTRADA = index++;
	public static final int INSTITUICAO_FINANCIADORA_INVALIDA = index++;

	// Recurso Instituição Financiadora
	public static final int RECURSO_INSTITUICAO_FINANCIADORA_INVALIDO = index++;
	public static final int ORCAMENTO_INSTITUICAO_FINANCIADORA_INSUFICIENTE = index++;

	// Programa Institucional
	public static final int NOME_PROGRAMA_INSTITUCIONAL_INVALIDO = index++;
	public static final int SIGLA_PROGRAMA_INSTITUCIONAL_INVALIDA = index++;
	public static final int PROGRAMA_INSTITUCIONAL_INVALIDO = index++;
	public static final int TAMANHO_NOME_PROGRAMA_INSTITUCIONAL_INVALIDO = index++;

	// Recurso Programa Institucional
	public static final int ORCAMENTO_PROGRAMA_INSTITUCIONAL_INSUFICIENTE = index++;

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
	public static final int EDITAL_ASSOCIADO_INVALIDO = index++;
	public static final int PROJETO_INVALIDO = index++;
	public static final int NOME_PROJETO_INVALIDO = index++;
	public static final int RESUMO_PROJETO_INVALIDO = index++;
	public static final int PERIODO_PROJETO_INVALIDO = index++;
	public static final int NUMERO_PROCESSO_INVALIDO = index++;
	public static final int QUANTIDADE_PROJETOO_INVALIDO = index++;

	// Grande Área
	public static final int GRANDE_AREA_INVALIDA = index++;

	// Área
	public static final int AREA_INVALIDA = index++;

	// Instituição Bancária
	public static final int INSTITUICAO_BANCARIA_INVALIDA = index++;
	public static final int NOME_INSTITUICAO_BANCARIA_INVALIDO = index++;

	// Tipo Pessoa
	public static final int TIPO_PESSOA_INVALIDO = index++;

	// Pessoa
	public static final int PESSOA_INVALIDA = index++;
	public static final int NOME_PESSOA_INVALIDO = index++;
	public static final int CPF_INVALIDO = index++;
	public static final int MATRICULA_INVALIDA = index++;
	public static final int ENDERECO_INVALIDO = index++;
	public static final int CEP_INVALIDO = index++;
	public static final int TELEFONE_INVALIDO = index++;
	public static final int EMAIL_INVALIDO = index++;
	public static final int URL_LATTES_INVALIDO = index++;

	// Dados Bancários
	public static final int DADOS_BANCARIOS_INVALIDO = index++;
	public static final int OPERACAO_CONTA_INVALIDA = index++;
	public static final int NUMERO_CONTA_INVALIDO = index++;

	// Discente - Turma
	public static final int TURMA_INVALIDA = index++;

	// Servidor
	public static final int TITULACAO_INVALIDA = index++;
	public static final int CARGO_SERVIDOR_INVALIDO = index++;
	public static final int LOCAL_TRABALHO_INVALIDO = index++;
	public static final int SERVIDOR_HABILITADO_INEXISTENTE = index++;
	public static final int SERVIDOR_JA_HABILITADO = index++;

	// Tipo Participação
	public static final int TIPO_PARTICIPACAO = index++;

	// Participação
	public static final int INTERVALO_PARTICIPACAO_INVALIDO = index++;
	public static final int PARTICIPACAO_DATA_INVALIDA = index++;

	// Curso
	public static final int CURSO_INVALIDO = index++;
	public static final int NOME_CURSO_INVALIDO = index++;
	public static final int COORDENADOR_CURSO_INVALIDO = index++;

	// Turma

	// Arquivo
	public static final int FORMATO_ARQUIVO_INVALIDO = index++;
	public static final int PROBLEMA_MANIPULAR_ARQUIVO = index++;

	// chat
	public static final int CONVERSA_INVALIDA = index++;
	public static final int NOME_CONVERSA_INVALIDO = index++;

	// chatLine
	public static final int MENSAGEM_INVALIDA = index;
	public static final int QUANTIDADE_PESSOAS_CONVERSA_INVALIDA = index++;

}
