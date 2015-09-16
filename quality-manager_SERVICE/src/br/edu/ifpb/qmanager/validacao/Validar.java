package br.edu.ifpb.qmanager.validacao;

import java.util.Date;
import java.util.List;

import br.edu.ifpb.qmanager.chat.Conversa;
import br.edu.ifpb.qmanager.chat.Mensagem;
import br.edu.ifpb.qmanager.entidade.Area;
import br.edu.ifpb.qmanager.entidade.Campus;
import br.edu.ifpb.qmanager.entidade.CargoServidor;
import br.edu.ifpb.qmanager.entidade.CodeErroQManager;
import br.edu.ifpb.qmanager.entidade.Curso;
import br.edu.ifpb.qmanager.entidade.DadosBancarios;
import br.edu.ifpb.qmanager.entidade.Departamento;
import br.edu.ifpb.qmanager.entidade.Discente;
import br.edu.ifpb.qmanager.entidade.Edital;
import br.edu.ifpb.qmanager.entidade.EditalCampusSubmissao;
import br.edu.ifpb.qmanager.entidade.GrandeArea;
import br.edu.ifpb.qmanager.entidade.InstituicaoBancaria;
import br.edu.ifpb.qmanager.entidade.InstituicaoFinanciadora;
import br.edu.ifpb.qmanager.entidade.Login;
import br.edu.ifpb.qmanager.entidade.Participacao;
import br.edu.ifpb.qmanager.entidade.Pessoa;
import br.edu.ifpb.qmanager.entidade.ProgramaInstitucional;
import br.edu.ifpb.qmanager.entidade.Projeto;
import br.edu.ifpb.qmanager.entidade.RecursoInstituicaoFinanciadora;
import br.edu.ifpb.qmanager.entidade.RecursoProgramaInstitucional;
import br.edu.ifpb.qmanager.entidade.Servidor;
import br.edu.ifpb.qmanager.entidade.TipoParticipacao;
import br.edu.ifpb.qmanager.entidade.TipoPessoa;
import br.edu.ifpb.qmanager.entidade.TipoProgramaInstitucional;
import br.edu.ifpb.qmanager.entidade.Titulacao;
import br.edu.ifpb.qmanager.entidade.Turma;
import br.edu.ifpb.qmanager.validate.DataValidator;
import br.edu.ifpb.qmanager.validate.EmailValidator;
import br.edu.ifpb.qmanager.validate.NumeroValidator;
import br.edu.ifpb.qmanager.validate.StringValidator;

public class Validar {

	private static StringValidator stringValidator = new StringValidator();
	private static NumeroValidator numeroValidator = new NumeroValidator();
	private static EmailValidator emailValidator = new EmailValidator();
	private static DataValidator dataValidator = new DataValidator();

	public static int VALIDACAO_OK = 0;

	public static int login(Login login) {

		boolean valido = false;
		
		String identificador = login.getIdentificador();
		String senha = login.getSenha();

		// E-mail ou Matrícula(somente números).
		if (emailValidator.validate(identificador) || numeroValidator.validate(identificador))
			valido = true;

		if (!valido)
			return CodeErroQManager.USUARIO_INVALIDO;

		if (!stringValidator.validatePassword(senha))
			return CodeErroQManager.SENHA_INVALIDA;

		return VALIDACAO_OK;
	}

	public static int instituicaoFinanciadora(
			InstituicaoFinanciadora instituicaoFinanciadora) {

		String cnpj = instituicaoFinanciadora.getCnpj();
		String nomeInstituicaoFinanciadora = instituicaoFinanciadora
				.getNomeInstituicaoFinanciadora();
		String siglaInstituicaoFinanciadora = instituicaoFinanciadora.getSigla();

		Servidor cadastrador = instituicaoFinanciadora.getCadastrador();

		int validacao = validarIdentificacaoCadastrador(cadastrador);
		if (validacao != VALIDACAO_OK)
			return validacao;

		if (!numeroValidator.validate(cnpj, 14, 14))
			return CodeErroQManager.CNPJ_INVALIDO;
		
		if (!stringValidator.validate(nomeInstituicaoFinanciadora))
			return CodeErroQManager.NOME_INSTITUICAO_FINANCIADORA_INVALIDA;

		if (!stringValidator.validate(nomeInstituicaoFinanciadora, 3, 255))
			return CodeErroQManager.TAMANHO_NOME_INSTITUICAO_FINANCIADORA_INVALIDA;

		if (!stringValidator.validate(siglaInstituicaoFinanciadora, 3, 10))
			return CodeErroQManager.SIGLA_INSTITUICAO_FINANCIADORA_INVALIDA;

		return VALIDACAO_OK;
	}

	public static int recursoInstituicaoFinanciadora(
			RecursoInstituicaoFinanciadora recursoInstituicaoFinanciadora) {

		double orcamento = recursoInstituicaoFinanciadora.getOrcamento();
		Date validadeInicial = recursoInstituicaoFinanciadora.getValidadeInicial();
		Date validadeFinal = recursoInstituicaoFinanciadora.getValidadeFinal();
		// boolean recursoValido = recursoInstituicaoFinanciadora.isRecursoValido();

		Servidor cadastrador = recursoInstituicaoFinanciadora.getCadastrador();
		InstituicaoFinanciadora instituicaoFinanciadora = 
				recursoInstituicaoFinanciadora.getInstituicaoFinanciadora();

		int validacao = validarIdentificacaoCadastrador(cadastrador);
		if (validacao != VALIDACAO_OK)
			return validacao;

		validacao = validarIdentificacaoInstituicaoFinanciadora(instituicaoFinanciadora);
		if (validacao != VALIDACAO_OK)
			return validacao;

		if (!numeroValidator.isDoublePositivo(orcamento))
			return CodeErroQManager.VALOR_ORCAMENTO_INVALIDO;

		if (!dataValidator.datesInOrder(validadeInicial, validadeFinal))
			return CodeErroQManager.PERIODO_INVALIDO;

		return VALIDACAO_OK;
	}

	public static int programaInstitucional(
			ProgramaInstitucional programaInstitucional) {

		int validacao = VALIDACAO_OK;
		
		String nomeProgramaInstitucional = programaInstitucional.getNomeProgramaInstitucional();
		String sigla = programaInstitucional.getSigla();

		Servidor cadastrador = programaInstitucional.getCadastrador();
		TipoProgramaInstitucional tipoProgramaInstitucional = 
				programaInstitucional.getTipoProgramaInstitucional();
		InstituicaoFinanciadora instituicaoFinanciadora = 
				programaInstitucional.getInstituicaoFinanciadora();

		validacao = validarIdentificacaoCadastrador(cadastrador);
		if (validacao != VALIDACAO_OK)
			return validacao;

		validacao = validarIdentificacaoTipoProgramaInstitucional(
				tipoProgramaInstitucional);
		if (validacao != VALIDACAO_OK)
			return validacao;

		validacao = validarIdentificacaoInstituicaoFinanciadora(
				instituicaoFinanciadora);
		if (validacao != VALIDACAO_OK)
			return validacao;
		
		if (!stringValidator.validate(nomeProgramaInstitucional))
			return CodeErroQManager.NOME_PROGRAMA_INSTITUCIONAL_INVALIDO;

		if (!stringValidator.validate(nomeProgramaInstitucional, 3, 255))
			return CodeErroQManager.TAMANHO_NOME_PROGRAMA_INSTITUCIONAL_INVALIDO;

		if (!stringValidator.validate(sigla, 3, 32))
			return CodeErroQManager.SIGLA_PROGRAMA_INSTITUCIONAL_INVALIDA;

		return validacao;
	}

	public static int recursoProgramaInstitucional(
			RecursoProgramaInstitucional recursoProgramaInstitucional) {

		int validacao = VALIDACAO_OK;
		double orcamento = recursoProgramaInstitucional.getOrcamento();
		Date validadeInicial = recursoProgramaInstitucional.getValidadeInicial();
		Date validadeFinal = recursoProgramaInstitucional.getValidadeFinal();
		// boolean recursoValido = recursoProgramaInstitucional.isRecursoValido();
		
		Servidor cadastrador = recursoProgramaInstitucional.getCadastrador();
		ProgramaInstitucional programaInstitucional = 
				recursoProgramaInstitucional.getProgramaInstitucional();
		RecursoInstituicaoFinanciadora recursoInstituicaoFinanciadora = 
				recursoProgramaInstitucional.getRecursoInstituicaoFinanciadora();

		validacao = validarIdentificacaoCadastrador(cadastrador);
		if (validacao != VALIDACAO_OK)
			return validacao;

		validacao = validarIdentificacaoProgramaInstitucional(programaInstitucional);
		if (validacao != VALIDACAO_OK)
			return validacao;

		validacao = validarIdentificacaoRecursoInstituicaoFinanciadora(
				recursoInstituicaoFinanciadora);
		if (validacao != VALIDACAO_OK)
			return validacao;

		if (!numeroValidator.isDoublePositivo(orcamento))
			return CodeErroQManager.VALOR_ORCAMENTO_INVALIDO;

		if (!dataValidator.datesInOrder(validadeInicial, validadeFinal))
			return CodeErroQManager.PERIODO_INVALIDO;

		return validacao;
	}

	public static int edital(Edital edital) {

		int validacao = VALIDACAO_OK;
		int numero = edital.getNumero();
		int ano = edital.getAno();		
		String descricao = edital.getDescricao();		
		Date inicioInscricoes = edital.getInicioInscricoes();
		Date fimInscricoes = edital.getFimInscricoes();		
		int qtdProjetosAprovados = edital.getQuantidadeProjetosAprovados();		
		int vagasBolsistasDiscentePorProjeto = edital.getVagasBolsistasDiscentePorProjeto();		
		int vagasVoluntariosPorProjeto = edital.getVagasVoluntariosPorProjeto();
		int vagasBolsistasDocentePorProjeto = edital.getVagasBolsistasDocentePorProjeto();
		double bolsaDiscente = edital.getBolsaDiscente();
		double bolsaDocente = edital.getBolsaDocente();		
		Date relatorioParcial = edital.getRelatorioParcial();
		Date relatorioFinal = edital.getRelatorioFinal();		
		Date inicioAvaliacoes = edital.getInicioAvaliacao();
		Date fimAvaliacoes = edital.getFimAvaliacao();
		Date resultadoPreliminar = edital.getResultadoPreliminar();
		Date recebimentoRecursos = edital.getInicioRecursos();
		Date divulgacaoResultadoFinal = edital.getResultadoFinal();
		Date inicioAtividades = edital.getInicioAtividades();
		ProgramaInstitucional programaInstitucional = edital.getProgramaInstitucional();

		validacao = validarIdentificacaoProgramaInstitucional(programaInstitucional);
		
		if (validacao != VALIDACAO_OK)
			return validacao;

		if (!numeroValidator.isInteiroPositivo(numero))
			return CodeErroQManager.NUMERO_EDITAL_INVALIDO;

		if (!numeroValidator.isInteiroPositivo(ano))
			return CodeErroQManager.ANO_EDITAL_INVALIDO;

		if (!stringValidator.validate(descricao, 255))
			return CodeErroQManager.DESCRICAO_EDITAL_INVALIDA;

		if (!dataValidator.datesInOrder(inicioInscricoes, fimInscricoes))
			return CodeErroQManager.PERIODO_INSCRICAO_PROJETO_INVALIDO;

		if (!numeroValidator.isInteiroPositivo(qtdProjetosAprovados))
			return CodeErroQManager.QUANTIDADE_PROJETO_INVALIDO; //TODO: Adicionar mensagem.

		if (!numeroValidator.isInteiroPositivo(vagasBolsistasDiscentePorProjeto))
			return CodeErroQManager.NUMERO_VAGA_INVALIDO;

		if (!numeroValidator.isDoublePositivo(bolsaDiscente))
			return CodeErroQManager.VALOR_BOLSA_DISCENTE_INVALIDO;

		if (!numeroValidator.isInteiroPositivo(vagasVoluntariosPorProjeto))
			return CodeErroQManager.NUMERO_VAGA_INVALIDO;

		if (!numeroValidator.isInteiroPositivo(vagasBolsistasDocentePorProjeto))
			return CodeErroQManager.NUMERO_VAGA_INVALIDO;

		if (!numeroValidator.isDoublePositivo(bolsaDocente))
			return CodeErroQManager.VALOR_BOLSA_DOCENTE_INVALIDO;

		if (!dataValidator.datesInOrder(relatorioParcial, relatorioFinal))
			return CodeErroQManager.PERIODO_RELATORIO_INVALIDO;

		if (!dataValidator.datesInOrder(inicioAvaliacoes, fimAvaliacoes))
			return CodeErroQManager.PERIODO_AVALIACAO_INVALIDO;

		if (!dataValidator.datesInOrder(fimAvaliacoes, resultadoPreliminar))
			return CodeErroQManager.RESULTADO_PRELIMINAR_INVALIDO;

		if (!dataValidator.datesInOrder(resultadoPreliminar, recebimentoRecursos))
			return CodeErroQManager.RECEBIMENTO_RECURSOS_INVALIDO;

		if (!dataValidator.datesInOrder(recebimentoRecursos, divulgacaoResultadoFinal))
			return CodeErroQManager.DIVULGACAO_RESULTADO_FINAL_INVALIDO;

		if (!dataValidator.datesInOrder(divulgacaoResultadoFinal, inicioAtividades))
			return CodeErroQManager.INICIO_ATIVIDADES_INVALIDO;

		return VALIDACAO_OK;
	}

	public static int projeto(Projeto projeto) {

		int validacao = VALIDACAO_OK;
		String nomeProjeto = projeto.getNomeProjeto();
		String resumoProjeto = projeto.getResumoProjeto();
		
		Date inicioProjeto = projeto.getInicioProjeto();
		Date fimProjeto = projeto.getFimProjeto();
		
		String processo = projeto.getProcesso();
		
		// TODO: Verificar no Modelo Conceitual a validade do valor para a previsão de Orçamento do Projeto.
		double orcamento = projeto.getOrcamento();

		Edital edital = projeto.getEdital();
		Campus campus = projeto.getCampus();
		GrandeArea grandeArea = projeto.getGrandeArea();
		Area area = projeto.getArea();
		Servidor cadastrador = projeto.getCadastrador();

		validacao = validarIdentificacaoCadastrador(cadastrador);
		if (validacao != VALIDACAO_OK)
			return validacao;

		validacao = validarIdentificacaoEdital(edital);
		if (validacao != VALIDACAO_OK)
			return validacao;

		validacao = validarIdentificacaoCampus(campus);
		if (validacao != VALIDACAO_OK)
			return validacao;

		validacao = validarIdentificacaoGrandeArea(grandeArea);
		if (validacao != VALIDACAO_OK)
			return validacao;

		validacao = validarIdentificacaoArea(area);
		if (validacao != VALIDACAO_OK)
			return validacao;

		if (!stringValidator.validate(nomeProjeto, 255))
			return CodeErroQManager.NOME_PROJETO_INVALIDO;

		if (resumoProjeto == null || resumoProjeto.length() > 300)
			return CodeErroQManager.RESUMO_PROJETO_INVALIDO;

		//TODO: Datas de início e final de execução do Projeto são definidos pelo Edital. Analisar esse caso. 
		if (inicioProjeto != null 
				&& fimProjeto != null
				&& !dataValidator.datesInOrder(inicioProjeto, fimProjeto))
			return CodeErroQManager.PERIODO_PROJETO_INVALIDO;

		if ((processo != null) && (!numeroValidator.validate(processo, 21, 21)))
			return CodeErroQManager.NUMERO_PROCESSO_INVALIDO;

		return VALIDACAO_OK;
	}

	public static int instituicaoBancaria(InstituicaoBancaria instituicaoBancaria) {

		int validacao = VALIDACAO_OK;
		String cnpj = instituicaoBancaria.getCnpj();
		String nomeBanco = instituicaoBancaria.getNomeBanco();
		Servidor cadastrador = instituicaoBancaria.getCadastrador();

		validacao = validarIdentificacaoCadastrador(cadastrador);
		if (validacao != VALIDACAO_OK)
			return validacao;

		if (!numeroValidator.validate(cnpj, 14, 14))
			return CodeErroQManager.CNPJ_INVALIDO;

		if (!stringValidator.validate(nomeBanco, 90))
			return CodeErroQManager.NOME_INSTITUICAO_BANCARIA_INVALIDO;

		return VALIDACAO_OK;
	}

	public static int pessoa(Pessoa pessoa) {

		int validacao = VALIDACAO_OK;
		String nomePessoa = pessoa.getNomePessoa();
		String cpf = pessoa.getCpf();
		// Date nascimento = pessoa.getNascimento();
		String matricula = pessoa.getMatricula();
		String endereco = pessoa.getEndereco();
		String cep = pessoa.getCep();
		String telefone = pessoa.getTelefone();
		String email = pessoa.getEmail();
		String urlLattes = pessoa.getUrlLattes();
		TipoPessoa tipoPessoa = pessoa.getTipoPessoa();
		Campus campus = pessoa.getCampus();
		DadosBancarios dadosBancarios = pessoa.getDadosBancarios();

		validacao = validarIdentificacaoTipoPessoa(tipoPessoa);
		if (validacao != VALIDACAO_OK)
			return validacao;

		validacao = validarIdentificacaoCampus(campus);
		if (validacao != VALIDACAO_OK)
			return validacao;

		validacao = dadosBancarios(dadosBancarios);
		if (validacao != VALIDACAO_OK)
			return validacao;

		if (!stringValidator.validate(nomePessoa, 90))
			return CodeErroQManager.NOME_PESSOA_INVALIDO;

		if (!numeroValidator.validate(cpf))
			return CodeErroQManager.CPF_INVALIDO;

		if (!numeroValidator.validate(matricula, 20))
			return CodeErroQManager.MATRICULA_INVALIDA;

		if (endereco != null && cep != null && telefone != null) {
			if (!stringValidator.validate(endereco, 255))
				return CodeErroQManager.ENDERECO_INVALIDO;

			if (!numeroValidator.validate(cep))
				return CodeErroQManager.CEP_INVALIDO;

			if (!numeroValidator.validate(telefone, 12))
				return CodeErroQManager.TELEFONE_INVALIDO;
		}

		if (!emailValidator.validate(email))
			return CodeErroQManager.EMAIL_INVALIDO;

		if (!stringValidator.validate(urlLattes, 255))
			return CodeErroQManager.URL_LATTES_INVALIDO;

		return VALIDACAO_OK;
	}

	public static int dadosBancarios(DadosBancarios dadosBancarios) {

		int validacao = VALIDACAO_OK;

		if (dadosBancarios != null) {
			if (!numeroValidator.isInteiroPositivo(dadosBancarios.getIdDadosBancarios()))
				return CodeErroQManager.DADOS_BANCARIOS_INVALIDO;

			InstituicaoBancaria instituicaoBancaria = dadosBancarios
					.getInstituicaoBancaria();
			String operacao = dadosBancarios.getOperacao();
			String conta = dadosBancarios.getConta();

			validacao = validarIdentificacaoInstituicaoBancaria(instituicaoBancaria);
			if (validacao != VALIDACAO_OK)
				return validacao;

			if (operacao != null && !numeroValidator.validate(operacao, 3))
				return CodeErroQManager.OPERACAO_CONTA_INVALIDA;

			if (!numeroValidator.validate(conta, 15))
				return CodeErroQManager.NUMERO_CONTA_INVALIDO;
		}

		return VALIDACAO_OK;
	}

	public static int discente(Discente discente) {

		int validacao = pessoa(discente);
		if (validacao != VALIDACAO_OK)
			return validacao;

		Turma turma = discente.getTurma();

		validacao = validarIdentificacaoTurma(turma);
		if (validacao != VALIDACAO_OK)
			return validacao;

		return VALIDACAO_OK;
	}

	public static int servidor(Servidor servidor) {

		int validacao = VALIDACAO_OK;
		Titulacao titulacao = servidor.getTitulacao();
		CargoServidor cargoServidor = servidor.getCargoServidor();
		Departamento departamento = servidor.getDepartamento();

		validacao = pessoa(servidor);
		if (validacao != VALIDACAO_OK)
			return validacao;

		validacao = validarIdentificacaoTitulacao(titulacao);
		if (validacao != VALIDACAO_OK)
			return validacao;

		validacao = validarIdentificacaoCargoServidor(cargoServidor);
		if (validacao != VALIDACAO_OK)
			return validacao;

		validacao = validarIdentificacaoDepartamento(departamento);
		if (validacao != VALIDACAO_OK)
			return validacao;

		return VALIDACAO_OK;
	}

	public static int participacao(Participacao participacao) {

		int validacao = VALIDACAO_OK;
		Projeto projeto = participacao.getProjeto();
		Pessoa pessoa = participacao.getPessoa();
		Date inicioParticipacao = participacao.getInicioParticipacao();
		Date fimParticipacao = participacao.getFimParticipacao();
		TipoParticipacao tipoParticipacao = participacao.getTipoParticipacao();

		validacao = validarIdentificacaoPessoa(pessoa);
		if (validacao != VALIDACAO_OK)
			return validacao;

		validacao = validarIdentificacaoProjeto(projeto);
		if (validacao != VALIDACAO_OK)
			return validacao;

		validacao = validarIdentificacaoTipoParticipacao(tipoParticipacao);
		if (validacao != VALIDACAO_OK)
			return validacao;
		
		if (inicioParticipacao != null && fimParticipacao != null) {
			if (!dataValidator.datesInOrder(inicioParticipacao, fimParticipacao))
				return CodeErroQManager.INTERVALO_PARTICIPACAO_INVALIDO;
		}		

		return VALIDACAO_OK;
	}

	public static int participacaoEdital(Participacao participacao) {
		
		Date inicioParticipacao = participacao.getInicioParticipacao();
		Date fimParticipacao = participacao.getFimParticipacao();		
		
		Date inicioAtividadeProjeto = participacao.getProjeto()
				.getInicioProjeto();
		
		if (inicioAtividadeProjeto != null) {
			
			if (inicioParticipacao != null 
					&& !dataValidator.datesInOrder(
							inicioAtividadeProjeto, inicioParticipacao))
				return CodeErroQManager.PARTICIPACAO_DATA_INVALIDA;
				
			if (fimParticipacao != null 
					&& !dataValidator.datesInOrder(
							inicioParticipacao, fimParticipacao))
					return CodeErroQManager.PARTICIPACAO_DATA_INVALIDA;
		}		
		
		return VALIDACAO_OK;
	}

	public static int curso(Curso curso) {

		int validacao = VALIDACAO_OK;
		String nomeCurso = curso.getNomeCurso();
		Servidor coordenador = curso.getCoordenador();
		Servidor cadastrador = curso.getCadastrador();

		validacao = validarIdentificacaoCadastrador(cadastrador);
		if (validacao != VALIDACAO_OK)
			return validacao;

		validacao = validarIdentificacaoCoordenadorCurso(coordenador);
		if (validacao != VALIDACAO_OK)
			return validacao;

		if (!stringValidator.validate(nomeCurso, 90))
			return CodeErroQManager.NOME_CURSO_INVALIDO;

		return VALIDACAO_OK;
	}

	// TODO: reavaliar validação, composição e DAO de Turma.
	public static int turma(Turma turma) {

		int validacao = VALIDACAO_OK;
		int periodoLetivo = turma.getPeriodoLetivo();
		char turno = turma.getTurno();
		Curso curso = turma.getCurso();

		validacao = validarIdentificacaoCurso(curso);
		if (validacao != VALIDACAO_OK)
			return validacao;

		return VALIDACAO_OK;
	}

	public static int conversa(Conversa conversa) {

		if (conversa == null)
			return CodeErroQManager.CONVERSA_INVALIDA;

		String nome = conversa.getNome();

		if (!stringValidator.validate(nome, 255))
			return CodeErroQManager.NOME_CONVERSA_INVALIDO;

		if (conversa.getPessoas() == null)
			return CodeErroQManager.QUANTIDADE_PESSOAS_CONVERSA_INVALIDA;

		int validacao;
		for (Pessoa pessoa : conversa.getPessoas()) {
			validacao = validarIdentificacaoPessoa(pessoa);
			if (validacao != VALIDACAO_OK)
				return validacao;
		}

		return VALIDACAO_OK;
	}

	public static int mensagem(Mensagem mensagem) {
		if (mensagem == null)
			return CodeErroQManager.CONVERSA_INVALIDA;

		Conversa chat = mensagem.getConversa();
		Pessoa remetente = mensagem.getRemetente();
		String texto = mensagem.getMensagem();

		int validacao = validarIdentificacaoPessoa(remetente);
		if (validacao != VALIDACAO_OK)
			return validacao;

		validacao = validarIdentificacaoChat(chat);
		if (validacao != VALIDACAO_OK)
			return validacao;

		if (texto == null)
			return CodeErroQManager.MENSAGEM_INVALIDA;

		return VALIDACAO_OK;
	}

	/*
	 * Funções internas e refatoramentos
	 */

	private static int validarIdentificacaoChat(Conversa chat) {
		if (chat == null)
			return CodeErroQManager.CONVERSA_INVALIDA;
		if (!numeroValidator.isInteiroPositivo(chat.getIdConversa()))
			return CodeErroQManager.CONVERSA_INVALIDA;
		return VALIDACAO_OK;
	}

	private static int validarIdentificacaoPessoa(Pessoa pessoa) {
		if (pessoa == null)
			return CodeErroQManager.PESSOA_INVALIDA;
		if (!numeroValidator.isInteiroPositivo(pessoa.getPessoaId()))
			return CodeErroQManager.PESSOA_INVALIDA;
		return VALIDACAO_OK;
	}

	private static int validarIdentificacaoCadastrador(Servidor cadastrador) {
		if (cadastrador == null)
			return CodeErroQManager.CADASTRADOR_INVALIDO;
		if (!numeroValidator.isInteiroPositivo(cadastrador.getPessoaId()))
			return CodeErroQManager.CADASTRADOR_INVALIDO;
		return VALIDACAO_OK;
	}

	private static int validarIdentificacaoInstituicaoFinanciadora(
			InstituicaoFinanciadora instituicaoFinanciadora) {
		if (instituicaoFinanciadora == null)
			return CodeErroQManager.INSTITUICAO_FINANCIADORA_INVALIDA;
		if (!numeroValidator.isInteiroPositivo(instituicaoFinanciadora.getIdInstituicaoFinanciadora()))
			return CodeErroQManager.INSTITUICAO_FINANCIADORA_INVALIDA;
		return VALIDACAO_OK;
	}

	private static int validarIdentificacaoProgramaInstitucional(
			ProgramaInstitucional programaInstitucional) {
		
		if (programaInstitucional == null)
			return CodeErroQManager.PROGRAMA_INSTITUCIONAL_INVALIDO;
		
		if (!numeroValidator.isInteiroPositivo(programaInstitucional.getIdProgramaInstitucional()))
			return CodeErroQManager.PROGRAMA_INSTITUCIONAL_INVALIDO;
		
		return VALIDACAO_OK;
	}

	private static int validarIdentificacaoTipoProgramaInstitucional(
			TipoProgramaInstitucional tipoProgramaInstitucional) {
		if (tipoProgramaInstitucional == null)
			return CodeErroQManager.TIPO_PROGRAMA_INSTITUCIONAL_INVALIDO;
		if (!numeroValidator.isInteiroPositivo(tipoProgramaInstitucional.getIdTipoProgramaInstitucional()))
			return CodeErroQManager.TIPO_PROGRAMA_INSTITUCIONAL_INVALIDO;
		return VALIDACAO_OK;
	}

	private static int validarIdentificacaoRecursoInstituicaoFinanciadora(
			RecursoInstituicaoFinanciadora recursoInstituicaoFinanciadora) {
		if (recursoInstituicaoFinanciadora == null)
			return CodeErroQManager.RECURSO_INSTITUICAO_FINANCIADORA_INVALIDO;
		if (!numeroValidator.isInteiroPositivo(recursoInstituicaoFinanciadora.getIdRecursoIF()))
			return CodeErroQManager.RECURSO_INSTITUICAO_FINANCIADORA_INVALIDO;
		return VALIDACAO_OK;
	}

	private static int validarIdentificacaoEdital(Edital edital) {
		if (edital == null)
			return CodeErroQManager.EDITAL_ASSOCIADO_INVALIDO;
		if (!numeroValidator.isInteiroPositivo(edital.getIdEdital()))
			return CodeErroQManager.EDITAL_ASSOCIADO_INVALIDO;
		return VALIDACAO_OK;
	}
	
	public static int editalCampiSubmissao(EditalCampusSubmissao editalCampiSubmissao) {
		
		Edital edital = editalCampiSubmissao.getEdital();
		Campus campus = editalCampiSubmissao.getCampus();
		int qtdProjeto = editalCampiSubmissao.getQuantidadeProjeto();
		
		int validacao = validarIdentificacaoEdital(edital);
		if (validacao != VALIDACAO_OK)
			return validacao;
		
		validacao = validarIdentificacaoCampus(campus);
		if (validacao != VALIDACAO_OK)
			return validacao;
		
		if (!numeroValidator.isMaiorZero(qtdProjeto))
			return CodeErroQManager.QUANTIDADE_PROJETOO_INVALIDO;		
		
		return VALIDACAO_OK;
	}

	private static int validarIdentificacaoCampus(Campus campus) {
		if (campus == null)
			return CodeErroQManager.CAMPUS_INVALIDO;
		if (!numeroValidator.isInteiroPositivo(campus.getIdCampusInstitucional()))
			return CodeErroQManager.CAMPUS_INVALIDO;
		return VALIDACAO_OK;
	}
	
	private static int validarIdentificacaoGrandeArea(GrandeArea grandeArea) {
		if (grandeArea == null)
			return CodeErroQManager.GRANDE_AREA_INVALIDA;
		if (!numeroValidator.isInteiroPositivo(grandeArea.getIdGrandeArea()))
			return CodeErroQManager.GRANDE_AREA_INVALIDA;
		return VALIDACAO_OK;
	}
	
	private static int validarIdentificacaoArea(Area area) {
		if (area == null)
			return CodeErroQManager.AREA_INVALIDA;
		if (!numeroValidator.isInteiroPositivo(area.getIdArea()))
			return CodeErroQManager.AREA_INVALIDA;
		return VALIDACAO_OK;
	}

	private static int validarIdentificacaoProjeto(Projeto projeto) {
		if (projeto == null)
			return CodeErroQManager.PROJETO_INVALIDO;
		if (!numeroValidator.isInteiroPositivo(projeto.getIdProjeto()))
			return CodeErroQManager.PROJETO_INVALIDO;
		return VALIDACAO_OK;
	}

	private static int validarIdentificacaoTipoPessoa(TipoPessoa tipoPessoa) {
		if (tipoPessoa == null)
			return CodeErroQManager.TIPO_PESSOA_INVALIDO;
		if (!numeroValidator.isInteiroPositivo(tipoPessoa.getIdTipoPessoa()))
			return CodeErroQManager.TIPO_PESSOA_INVALIDO;
		return VALIDACAO_OK;
	}

	private static int validarIdentificacaoInstituicaoBancaria(
			InstituicaoBancaria instituicaoBancaria) {
		if (instituicaoBancaria == null)
			return CodeErroQManager.INSTITUICAO_BANCARIA_INVALIDA;
		if (!numeroValidator.isInteiroPositivo(instituicaoBancaria.getIdInstituicaoBancaria()))
			return CodeErroQManager.INSTITUICAO_BANCARIA_INVALIDA;
		return VALIDACAO_OK;
	}

	private static int validarIdentificacaoTurma(Turma turma) {
		if (turma == null)
			return CodeErroQManager.TURMA_INVALIDA;
		if (!numeroValidator.isInteiroPositivo(turma.getIdTurma()))
			return CodeErroQManager.TURMA_INVALIDA;
		return VALIDACAO_OK;
	}

	private static int validarIdentificacaoTitulacao(Titulacao titulacao) {
		if (titulacao == null)
			return CodeErroQManager.TITULACAO_INVALIDA;
		if (!numeroValidator.isInteiroPositivo(titulacao.getIdTitulacao()))
			return CodeErroQManager.TITULACAO_INVALIDA;
		return VALIDACAO_OK;
	}

	private static int validarIdentificacaoCargoServidor(CargoServidor cargoServidor) {
		if (cargoServidor == null)
			return CodeErroQManager.CARGO_SERVIDOR_INVALIDO;
		if (!numeroValidator.isInteiroPositivo(cargoServidor.getIdCargoServidor()))
			return CodeErroQManager.CARGO_SERVIDOR_INVALIDO;
		return VALIDACAO_OK;
	}

	private static int validarIdentificacaoDepartamento(Departamento departamento) {
		if (departamento == null)
			return CodeErroQManager.LOCAL_TRABALHO_INVALIDO;
		if (!numeroValidator.isInteiroPositivo(departamento.getIdDepartamento()))
			return CodeErroQManager.LOCAL_TRABALHO_INVALIDO;
		return VALIDACAO_OK;
	}

	private static int validarIdentificacaoTipoParticipacao(TipoParticipacao tipoParticipacao) {
		if (tipoParticipacao == null)
			return CodeErroQManager.TIPO_PARTICIPACAO;
		if (!numeroValidator.isInteiroPositivo(tipoParticipacao.getIdTipoParticipacao()))
			return CodeErroQManager.TIPO_PARTICIPACAO;
		return VALIDACAO_OK;
	}

	private static int validarIdentificacaoCoordenadorCurso(Servidor cadastrador) {
		if (cadastrador == null)
			return CodeErroQManager.COORDENADOR_CURSO_INVALIDO;
		if (!numeroValidator.isInteiroPositivo(cadastrador.getPessoaId()))
			return CodeErroQManager.COORDENADOR_CURSO_INVALIDO;
		return VALIDACAO_OK;
	}

	private static int validarIdentificacaoCurso(Curso curso) {
		if (curso == null)
			return CodeErroQManager.CURSO_INVALIDO;
		if (!numeroValidator.isInteiroPositivo(curso.getIdCurso()))
			return CodeErroQManager.CURSO_INVALIDO;
		return VALIDACAO_OK;
	}
}
