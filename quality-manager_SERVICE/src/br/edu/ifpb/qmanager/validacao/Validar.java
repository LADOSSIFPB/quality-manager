package br.edu.ifpb.qmanager.validacao;

import java.util.Date;
import java.util.Map;

import br.edu.ifpb.qmanager.chat.Chat;
import br.edu.ifpb.qmanager.chat.ChatLine;
import br.edu.ifpb.qmanager.entidade.CodeErroQManager;
import br.edu.ifpb.qmanager.entidade.Curso;
import br.edu.ifpb.qmanager.entidade.DadosBancarios;
import br.edu.ifpb.qmanager.entidade.Discente;
import br.edu.ifpb.qmanager.entidade.Edital;
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
import br.edu.ifpb.qmanager.entidade.TipoProgramaInstitucional;
import br.edu.ifpb.qmanager.entidade.Titulacao;
import br.edu.ifpb.qmanager.entidade.Turma;
import br.edu.ifpb.qmanager.validate.DataValidator;
import br.edu.ifpb.qmanager.validate.EmailValidator;
import br.edu.ifpb.qmanager.validate.NumeroValidator;
import br.edu.ifpb.qmanager.validate.StringValidator;

public class Validar {

	private static StringValidator sv = new StringValidator();
	private static NumeroValidator nv = new NumeroValidator();
	private static EmailValidator ev = new EmailValidator();
	private static DataValidator dv = new DataValidator();

	public static int VALIDACAO_OK = 0;

	public static int login(Login login) {

		boolean valido = false;
		
		String identificador = login.getIdentificador();
		String senha = login.getSenha();

		// E-mail ou Matrícula(somente números).
		if (ev.validate(identificador) || nv.validate(identificador))
			valido = true;

		if (!valido)
			return CodeErroQManager.USUARIO_INVALIDO;

		if (!sv.validatePassword(senha))
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

		if (!nv.validate(cnpj, 14, 14))
			return CodeErroQManager.CNPJ_INVALIDO;

		if (!sv.validate(nomeInstituicaoFinanciadora, 3, 255))
			return CodeErroQManager.NOME_INSTITUICAO_FINANCIADORA_INVALIDA;

		if (!sv.validate(siglaInstituicaoFinanciadora, 3, 10))
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

		if (!nv.isDoublePositivo(orcamento))
			return CodeErroQManager.VALOR_ORCAMENTO_INVALIDO;

		if (!dv.validate(validadeInicial, validadeFinal))
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

		if (!sv.validate(nomeProgramaInstitucional, 3, 255))
			return CodeErroQManager.NOME_PROGRAMA_INSTITUCIONAL_INVALIDO;

		if (!sv.validate(sigla, 3, 32))
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

		if (!nv.isDoublePositivo(orcamento))
			return CodeErroQManager.VALOR_ORCAMENTO_INVALIDO;

		if (!dv.validate(validadeInicial, validadeFinal))
			return CodeErroQManager.PERIODO_INVALIDO;

		return validacao;
	}

	public static int edital(Edital edital) {

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
		Date recebimentoRecursos = edital.getReceberRecursos();
		Date divulgacaoResultadoFinal = edital.getResultadoFinal();
		Date inicioAtividades = edital.getInicioAtividades();

		if (!nv.isInteiroPositivo(numero))
			return CodeErroQManager.NUMERO_EDITAL_INVALIDO;

		if (!nv.isInteiroPositivo(ano))
			return CodeErroQManager.ANO_EDITAL_INVALIDO;

		if (!sv.validate(descricao, 255))
			return CodeErroQManager.DESCRICAO_EDITAL_INVALIDA;
		
		if (!dv.validate(inicioInscricoes, fimInscricoes))
			return CodeErroQManager.PERIODO_INSCRICAO_PROJETO_INVALIDO;

		if (!nv.isInteiroPositivo(qtdProjetosAprovados))
			return CodeErroQManager.QUANTIDADE_PROJETO_INVALIDO; // Adicionar mensagem.
		
		if (!nv.isInteiroPositivo(vagasBolsistasDiscentePorProjeto))
			return CodeErroQManager.NUMERO_VAGA_INVALIDO;
		
		if (!nv.isDoublePositivo(bolsaDiscente))
			return CodeErroQManager.VALOR_BOLSA_DISCENTE_INVALIDO;
		
		if (!nv.isInteiroPositivo(vagasVoluntariosPorProjeto))
			return CodeErroQManager.NUMERO_VAGA_INVALIDO;
		
		if (!nv.isInteiroPositivo(vagasBolsistasDocentePorProjeto))
			return CodeErroQManager.NUMERO_VAGA_INVALIDO;		

		if (!nv.isDoublePositivo(bolsaDocente))
			return CodeErroQManager.VALOR_BOLSA_DOCENTE_INVALIDO;		
		
		if (!dv.validate(relatorioParcial, relatorioFinal))
			return CodeErroQManager.PERIODO_RELATORIO_INVALIDO; // Verificar mensagem.
		
		if (!dv.validate(inicioAvaliacoes, fimAvaliacoes))
			return CodeErroQManager.PERIODO_AVALIACAO_INVALIDO; // Adicionar mensagem.
		
		if (!dv.validate(fimAvaliacoes, resultadoPreliminar))
			return CodeErroQManager.PERIODO_AVALIACAO_INVALIDO; // Adicionar código e mensagem.
		
		if (!dv.validate(resultadoPreliminar, recebimentoRecursos))
			return CodeErroQManager.PERIODO_AVALIACAO_INVALIDO; // Adicionar código e mensagem.
		
		if (!dv.validate(recebimentoRecursos, divulgacaoResultadoFinal))
			return CodeErroQManager.PERIODO_AVALIACAO_INVALIDO; // Adicionar código e mensagem.
		
		if (!dv.validate(divulgacaoResultadoFinal, inicioAtividades))
			return CodeErroQManager.PERIODO_AVALIACAO_INVALIDO; // Adicionar código e mensagem.		
		
		if (edital.getProgramaInstitucional() == null)
			return CodeErroQManager.ID_PROGRAMA_INSTITUCIONAL_INVALIDO;
		
		int programaInstitucionalId = edital.getProgramaInstitucional()
				.getIdProgramaInstitucional();
		
		if (!nv.isInteiroPositivo(programaInstitucionalId))
			return CodeErroQManager.ID_PROGRAMA_INSTITUCIONAL_INVALIDO;

		return VALIDACAO_OK;
	}

	public static int projeto(Projeto projeto) {

		String nomeProjeto = projeto.getNomeProjeto();
		Date inicioProjeto = projeto.getInicioProjeto();
		Date fimProjeto = projeto.getFimProjeto();
		String relatorioParcial = projeto.getRelatorioParcial();
		String relatorioFinal = projeto.getRelatorioFinal();
		String processo = projeto.getProcesso();
		double orcamento = projeto.getOrcamento();
		int idEdital = projeto.getEdital().getIdEdital();

		if (!sv.validate(nomeProjeto, 255))
			return CodeErroQManager.NOME_PROJETO_INVALIDO;

		// inicioProjeto if (!dataMaiorHoje(inicioProjeto)) return 33;

		// fimProjeto if (!dataMaiorHoje(fimProjeto)) return 34;

		if (!dv.validate(inicioProjeto, fimProjeto))
			return 35; // Adicionar constante e mensagem.

		/*
		 * if (!sv.validate(projetoSubmetido, 255)) return
		 * QManagerCodeErro.ARQUIVO_RELATORIO_INVALIDO;
		 * 
		 * if (!sv.validate(relatorioParcial, 255)) return
		 * QManagerCodeErro.ARQUIVO_RELATORIO_PARCIAL_INVALIDO;
		 * 
		 * if (!sv.validate(relatorioFinal, 255)) return
		 * QManagerCodeErro.ARQUIVO_RELATORIO_FINAL_INVALIDO;
		 */

		if (!nv.validate(processo, 21, 21))
			return CodeErroQManager.NUMERO_PROCESSO_INVALIDO;

		/*
		 * if (!temTipoProjetoValido(tipoProjeto)) return 40;
		 */

		/*
		 * if (!nv.isDoublePositivo(orcamento)) return
		 * QManagerCodeErro.VALOR_ORCAMENTO_INVALIDO;
		 */
		if (!nv.isInteiroPositivo(idEdital))
			return CodeErroQManager.ID_EDITAL_INVALIDO;

		return VALIDACAO_OK;
	}

	public static int discente(Discente discente) {

		// Discente
		String nomePessoa = discente.getNomePessoa();
		String cpf = discente.getCpf();
		String matricula = discente.getMatricula();
		String endereco = discente.getEndereco();
		String cep = discente.getCep();
		String telefone = discente.getTelefone();
		String email = discente.getEmail();
		int idTurma = discente.getTurma().getIdTurma();
		String senha = discente.getSenha();

		// Dados Bancarios
		int idInstituicaoBancaria = discente.getDadosBancarios()
				.getInstituicaoBancaria().getIdInstituicaoBancaria();
		String operacao = discente.getDadosBancarios().getOperacao();
		String conta = discente.getDadosBancarios().getConta();

		if (!sv.validate(nomePessoa, 90))
			return CodeErroQManager.NOME_PESSOA_INVALIDO;

		if (!nv.validate(cpf))
			return CodeErroQManager.CPF_INVALIDO;

		if (!nv.validate(matricula, 11, 11))
			return CodeErroQManager.MATRICULA_INVALIDA;

		if (!sv.validate(endereco, 255))
			return CodeErroQManager.ENDERECO_INVALIDO;

		if (!nv.validate(cep))
			return CodeErroQManager.CEP_INVALIDO;

		if (!nv.validate(telefone, 11))
			return CodeErroQManager.TELEFONE_INVALIDO;

		if (!ev.validate(email))
			return CodeErroQManager.EMAIL_INVALIDO;

		if (!sv.validatePassword(senha))
			return CodeErroQManager.SENHA_INVALIDA;

		if (!nv.isInteiroPositivo(idTurma))
			return CodeErroQManager.ID_TURMA_INVALIDO;

		if (!nv.isInteiroPositivo(idInstituicaoBancaria))
			return CodeErroQManager.ID_INSTITUICAO_BANCARIA_INVALIDO;

		if (!nv.validate(operacao, 3))
			return CodeErroQManager.OPERACAO_CONTA_INVALIDA;

		if (!nv.validate(conta, 15))
			return CodeErroQManager.NUMERO_CONTA_INVALIDO;

		return VALIDACAO_OK;
	}

	public static int servidor(Servidor servidor) {

		// Orientador
		String nomePessoa = servidor.getNomePessoa();
		String cpf = servidor.getCpf();
		String matricula = servidor.getMatricula();
		String endereco = servidor.getEndereco();
		String cep = servidor.getCep();
		String telefone = servidor.getTelefone();
		String email = servidor.getEmail();
		Titulacao titulacao = servidor.getTitulacao();
		String senha = servidor.getSenha();

		// Dados Bancarios
		int idInstituicaoBancaria = 0;
		String operacao = null;
		String conta = null;

		if (!sv.validate(nomePessoa, 90))
			return CodeErroQManager.NOME_PESSOA_INVALIDO;

		if (!nv.validate(cpf))
			return CodeErroQManager.CPF_INVALIDO;

		if (!nv.validate(matricula, 11, 11))
			return CodeErroQManager.MATRICULA_INVALIDA;

		// Somente validar endereço caso esteja completo.
		if (endereco != null && cep != null && telefone != null) {
			if (!sv.validate(endereco, 255))
				return CodeErroQManager.ENDERECO_INVALIDO;

			if (!nv.validate(cep))
				return CodeErroQManager.CEP_INVALIDO;

			if (!nv.validate(telefone, 10))
				return CodeErroQManager.TELEFONE_INVALIDO;
		}

		if (!ev.validate(email))
			return CodeErroQManager.EMAIL_INVALIDO;

		if (!sv.validatePassword(senha))
			return CodeErroQManager.SENHA_INVALIDA;

		if (titulacao == null) {
			return CodeErroQManager.TITULACAO_INVALIDA;
		} else {
			if (!nv.isInteiroPositivo(titulacao.getIdTitulacao()))
				return CodeErroQManager.TITULACAO_INVALIDA;
		}

		// Validar dados bancários caso esteja completo.
		DadosBancarios dadosBancarios = servidor.getDadosBancarios();
		if (dadosBancarios != null) {

			idInstituicaoBancaria = dadosBancarios.getInstituicaoBancaria()
					.getIdInstituicaoBancaria();

			operacao = dadosBancarios.getOperacao();

			conta = dadosBancarios.getConta();

			if (!nv.isInteiroPositivo(idInstituicaoBancaria))
				return CodeErroQManager.ID_INSTITUICAO_BANCARIA_INVALIDO;

			if (!nv.validate(operacao, 3))
				return CodeErroQManager.OPERACAO_CONTA_INVALIDA;

			if (!nv.validate(conta, 15))
				return CodeErroQManager.NUMERO_CONTA_INVALIDO;
		}

		return VALIDACAO_OK;
	}

	public static int participacao(Participacao participacao) {
		int pessoaId = participacao.getPessoa().getPessoaId();
		int idProjeto = participacao.getProjeto().getIdProjeto();
		Date inicioParticipacao = participacao.getInicioParticipacao();
		Date fimParticipacao = participacao.getFimParticipacao();
		double valorBolsa = participacao.getValorBolsa();

		if (!nv.isInteiroPositivo(pessoaId))
			return CodeErroQManager.ID_MEMBRO_PROJETO_INVALIDO;

		if (!nv.isInteiroPositivo(idProjeto))
			return CodeErroQManager.ID_PROJETO_INVALIDO;

		// dataInicio if (!dataIgualHoje(inicioParticipacao)) return 59;

		// dataFim if (!dataMaiorHoje(fimParticipacao)) return 60;

		if (!dv.validate(inicioParticipacao, fimParticipacao))
			return CodeErroQManager.INTERVALO_PARTICIPACAO_INVALIDO;

		if (!nv.isDoublePositivo(valorBolsa))
			return CodeErroQManager.VALOR_BOLSA_INVALIDO;

		return VALIDACAO_OK;
	}
	
	public static int participacaoEdital(Participacao participacao) {
		
		Date inicioParticipacao = participacao.getInicioParticipacao();
		Date fimParticipacao = participacao.getFimParticipacao();
		
		Edital edital = participacao.getProjeto().getEdital();
		Date inicioAtividadeEdital = edital.getInicioAtividades();
		
		if (!dv.validate(inicioAtividadeEdital, inicioParticipacao))
			return CodeErroQManager.PARTICIPACAO_DATA_INVALIDA;
		
		if (!dv.validate(inicioAtividadeEdital, fimParticipacao))
			return CodeErroQManager.PARTICIPACAO_DATA_INVALIDA;
		
		return VALIDACAO_OK;
	}

	public static int instituicaoBancaria(
			InstituicaoBancaria instituicaoBancaria) {
		String nomeBanco = instituicaoBancaria.getNomeBanco();

		if (!sv.validate(nomeBanco, 90))
			return CodeErroQManager.NOME_BANCO_INVALIDO;

		return VALIDACAO_OK;
	}

	public static int curso(Curso curso) {
		String nomeCurso = curso.getNomeCurso();

		if (!sv.validate(nomeCurso, 90))
			return CodeErroQManager.NOME_CURSO_INVALIDO;

		return VALIDACAO_OK;
	}

	public static int turma(Turma turma) {
		int periodoLetivo = turma.getPeriodoLetivo();
		char turno = turma.getTurno();
		int cursoId = turma.getCurso().getIdCurso();

		/*
		 * TODO: // ano if (!temPeriodoLetivoValido(periodoLetivo)) return 65;
		 * 
		 * if (!temTurnoValido(turno)) return 66;
		 */

		if (!nv.isInteiroPositivo(cursoId))
			return CodeErroQManager.ID_CURSO_INVALIDO;

		return VALIDACAO_OK;
	}
	
	public static int chat(Chat chat) {
		
		if (chat == null)
			return CodeErroQManager.CONVERSA_INVALIDA;

		String nome = chat.getNome();

		if (!sv.validate(nome, 255))
			return CodeErroQManager.NOME_CONVERSA_INVALIDO;
		
		if (chat.getPessoas() == null)
			return CodeErroQManager.QUANTIDADE_PESSOAS_CONVERSA_INVALIDA;

		int validacao;
		for (Pessoa pessoa : chat.getPessoas()) {
			validacao = validarIdentificacaoPessoa(pessoa);
			if (validacao != VALIDACAO_OK)
				return validacao;
		}

		return VALIDACAO_OK;
	}

	public static int chatLine(ChatLine chatLine) {
		if (chatLine == null)
			return CodeErroQManager.CONVERSA_INVALIDA;
		
		Chat chat = chatLine.getChat();
		Pessoa remetente = chatLine.getRemetente();
		String mensagem = chatLine.getMensagem();
		Map<Pessoa, Boolean> pessoas = chatLine.getPessoas();
		
		int validacao = validarIdentificacaoPessoa(remetente);
		if (validacao != VALIDACAO_OK)
			return validacao;

		if (pessoas == null)
			return CodeErroQManager.QUANTIDADE_PESSOAS_CONVERSA_INVALIDA;

		for (Map.Entry<Pessoa, Boolean> p : pessoas.entrySet()) {
			validacao = validarIdentificacaoPessoa(p.getKey());
			if (validacao != VALIDACAO_OK)
				return validacao;
		}

		validacao = validarChat(chat);
		if (validacao != VALIDACAO_OK)
			return validacao;
		
		if (!sv.validate(mensagem, 65535))
			return CodeErroQManager.TAMANHO_MENSAGEM_INVALIDO;
		
		return VALIDACAO_OK;
	}

	/*
	 * Funções internas e refatoramentos
	 */

	private static int validarChat(Chat chat) {
		if (chat == null)
			return CodeErroQManager.CONVERSA_INVALIDA;
		if (!nv.isInteiroPositivo(chat.getIdChat()))
			return CodeErroQManager.CONVERSA_INVALIDA;
		return VALIDACAO_OK;
	}

	private static int validarIdentificacaoPessoa(Pessoa pessoa) {
		if (pessoa == null)
			return CodeErroQManager.PESSOA_INVALIDA;
		if (!nv.isInteiroPositivo(pessoa.getPessoaId()))
			return CodeErroQManager.PESSOA_INVALIDA;
		return VALIDACAO_OK;
	}

	private static int validarIdentificacaoCadastrador(Servidor cadastrador) {
		if (cadastrador == null)
			return CodeErroQManager.CADASTRADOR_INVALIDO;
		if (!nv.isInteiroPositivo(cadastrador.getPessoaId()))
			return CodeErroQManager.CADASTRADOR_INVALIDO;
		return VALIDACAO_OK;
	}

	private static int validarIdentificacaoInstituicaoFinanciadora(
			InstituicaoFinanciadora instituicaoFinanciadora) {
		if (instituicaoFinanciadora == null)
			return CodeErroQManager.INSTITUICAO_FINANCIADORA_INVALIDA;
		if (!nv.isInteiroPositivo(instituicaoFinanciadora.getIdInstituicaoFinanciadora()))
			return CodeErroQManager.INSTITUICAO_FINANCIADORA_INVALIDA;
		return VALIDACAO_OK;
	}

	private static int validarIdentificacaoProgramaInstitucional(
			ProgramaInstitucional programaInstitucional) {
		if (programaInstitucional == null)
			return CodeErroQManager.PROGRAMA_INSTITUCIONAL_INVALIDO;
		if (!nv.isInteiroPositivo(programaInstitucional.getIdProgramaInstitucional()))
			return CodeErroQManager.PROGRAMA_INSTITUCIONAL_INVALIDO;
		return VALIDACAO_OK;
	}

	private static int validarIdentificacaoTipoProgramaInstitucional(
			TipoProgramaInstitucional tipoProgramaInstitucional) {
		if (tipoProgramaInstitucional == null)
			return CodeErroQManager.TIPO_PROGRAMA_INSTITUCIONAL_INVALIDO;
		if (!nv.isInteiroPositivo(tipoProgramaInstitucional.getIdTipoProgramaInstitucional()))
			return CodeErroQManager.TIPO_PROGRAMA_INSTITUCIONAL_INVALIDO;
		return VALIDACAO_OK;
	}

	private static int validarIdentificacaoRecursoInstituicaoFinanciadora(
			RecursoInstituicaoFinanciadora recursoInstituicaoFinanciadora) {
		if (recursoInstituicaoFinanciadora == null)
			return CodeErroQManager.RECURSO_INSTITUICAO_FINANCIADORA_INVALIDO;
		if (!nv.isInteiroPositivo(recursoInstituicaoFinanciadora.getIdRecursoIF()))
			return CodeErroQManager.RECURSO_INSTITUICAO_FINANCIADORA_INVALIDO;
		return VALIDACAO_OK;
	}
}
