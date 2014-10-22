package br.edu.ifpb.qmanager.validacao;

import java.util.Date;

import br.edu.ifpb.qmanager.entidade.Curso;
import br.edu.ifpb.qmanager.entidade.Discente;
import br.edu.ifpb.qmanager.entidade.Edital;
import br.edu.ifpb.qmanager.entidade.InstituicaoBancaria;
import br.edu.ifpb.qmanager.entidade.InstituicaoFinanciadora;
import br.edu.ifpb.qmanager.entidade.Login;
import br.edu.ifpb.qmanager.entidade.Orientador;
import br.edu.ifpb.qmanager.entidade.Partipacao;
import br.edu.ifpb.qmanager.entidade.ProgramaInstitucional;
import br.edu.ifpb.qmanager.entidade.Projeto;
import br.edu.ifpb.qmanager.entidade.QManagerCodeErro;
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

		// testa se recebeu email ou matrícula (somente números) válida
		if (ev.validate(identificador) || nv.validate(identificador))
			valido = true;

		if (!valido)
			return QManagerCodeErro.USUARIO_INVALIDO;

		if (!sv.validatePassword(senha))
			return QManagerCodeErro.SENHA_INVALIDA;

		return VALIDACAO_OK;

	}

	public static int instituicaoFinanciadora(
			InstituicaoFinanciadora instituicaoFinanciadora) {

		String cnpj = instituicaoFinanciadora.getCnpj();
		String nomeInstituicaoFinanciadora = instituicaoFinanciadora
				.getNomeInstituicaoFinanciadora();
		String siglaInstituicaoFinanceira = instituicaoFinanciadora.getSigla();
		double orcamento = instituicaoFinanciadora.getOrcamento();

		if (!nv.validate(cnpj, 14, 14))
			return QManagerCodeErro.CNPJ_INVALIDO;

		if (!sv.validate(nomeInstituicaoFinanciadora, 255))
			return QManagerCodeErro.NOME_INSTITUICAO_FINANCIADORA_INVALIDA;

		if (!sv.validate(siglaInstituicaoFinanceira, 3, 10))
			return QManagerCodeErro.SIGLA_INSTITUICAO_FINANCIADORA_INVALIDA;

		if (!nv.isDoublePositivo(orcamento))
			return QManagerCodeErro.VALOR_ORCAMENTO_INVALIDO;

		return VALIDACAO_OK;
	}

	public static int programaInstitucional(
			ProgramaInstitucional programaInstitucional) {

		String nomeProgramaInstitucional = programaInstitucional
				.getNomeProgramaInstitucional();
		String sigla = programaInstitucional.getSigla();
		double orcamento = programaInstitucional.getOrcamento();
		int instituicaoFinanciadoraId = programaInstitucional
				.getInstituicaoFinanciadora().getIdInstituicaoFinanciadora();

		if (!sv.validate(nomeProgramaInstitucional, 255))
			return QManagerCodeErro.NOME_PROGRAMA_INSTITUCIONAL_INVALIDO;

		if (!sv.validate(sigla, 3, 10))
			return QManagerCodeErro.SIGLA_PROGRAMA_INSTITUCIONAL_INVALIDA;

		if (!nv.isDoublePositivo(orcamento))
			return QManagerCodeErro.VALOR_ORCAMENTO_INVALIDO;

		if (!nv.isInteiroPositivo(instituicaoFinanciadoraId))
			return QManagerCodeErro.ID_INSITUICAO_FINANCIADORA_INVALIDO;

		return VALIDACAO_OK;

	}

	public static int edital(Edital edital) {

		String arquivo = edital.getArquivo();
		int numero = edital.getNumero();
		int ano = edital.getAno();
		Date inicioInscricoes = edital.getInicioInscricoes();
		Date fimInscricoes = edital.getFimInscricoes();
		Date relatorioParcial = edital.getRelatorioParcial();
		Date relatorioFinal = edital.getRelatorioFinal();
		int vagas = edital.getVagas();
		double bolsaDiscente = edital.getBolsaDiscente();
		double bolsaDocente = edital.getBolsaDocente();
		char tipoEdital = edital.getTipoEdital();
		int programaInstitucionalId = edital.getProgramaInstitucional()
				.getIdProgramaInstitucional();

		// TODO: Tratar quando a opção de enviar arquivo estiver disponível no formulário
		/* if (!sv.validate(arquivo, 255))
			return QManagerCodeErro.ARQUIVO_EDITAL_INVALIDO; */

		if (!nv.isInteiroPositivo(numero))
			return QManagerCodeErro.NUMERO_EDITAL_INVALIDO;

		if (!nv.isInteiroPositivo(ano))
			return QManagerCodeErro.ANO_EDITAL_INVALIDO;

		// inicioInscricoes if (!dataMaiorHoje(inicioInscricoes)) return 21;

		// fimInscricoes if (!dataMaiorHoje(fimInscricoes)) return 22;

		if (!dv.validate(inicioInscricoes, fimInscricoes))
			return 23;

		// relatorioParcial if (!dataMaiorHoje(relatorioParcial)) return 24;

		// relatorioParcial if (!dataMaiorHoje(relatorioFinal)) return 25;

		if (!dv.validate(relatorioParcial, relatorioFinal))
			return 26;

		if (!nv.isInteiroPositivo(vagas))
			return QManagerCodeErro.NUMERO_VAGA_INVALIDO;

		if (!nv.isDoublePositivo(bolsaDiscente))
			return QManagerCodeErro.VALOR_BOLSA_DISCENTE_INVALIDO;

		if (!nv.isDoublePositivo(bolsaDocente))
			return QManagerCodeErro.VALOR_BOLSA_DOCENTE_INVALIDO;

		// TODO: if (!temTipoProjetoValido(tipoEdital)) return 30;

		if (!nv.isInteiroPositivo(programaInstitucionalId))
			return QManagerCodeErro.ID_PROGRAMA_INSTITUCIONAL_INVALIDO;

		return VALIDACAO_OK;

	}

	public static int projeto(Projeto projeto) {

		String nomeProjeto = projeto.getNomeProjeto();
		Date inicioProjeto = projeto.getInicioProjeto();
		Date fimProjeto = projeto.getFimProjeto();
		String projetoSubmetido = projeto.getProjetoSubmetido();
		String relatorioParcial = projeto.getRelatorioParcial();
		String relatorioFinal = projeto.getRelatorioFinal();
		String processo = projeto.getProcesso();
		char tipoProjeto = projeto.getTipoProjeto();
		double orcamento = projeto.getOrcamento();
		int idEdital = projeto.getEdital().getIdEdital();

		if (!sv.validate(nomeProjeto, 255))
			return QManagerCodeErro.NOME_PROJETO_INVALIDO;

		// inicioProjeto if (!dataMaiorHoje(inicioProjeto)) return 33;

		// fimProjeto if (!dataMaiorHoje(fimProjeto)) return 34;

		if (!dv.validate(inicioProjeto, fimProjeto))
			return 35;

		if (!sv.validate(projetoSubmetido, 255))
			return QManagerCodeErro.ARQUIVO_RELATORIO_INVALIDO;

		if (!sv.validate(relatorioParcial, 255))
			return QManagerCodeErro.ARQUIVO_RELATORIO_PARCIAL_INVALIDO;

		if (!sv.validate(relatorioFinal, 255))
			return QManagerCodeErro.ARQUIVO_RELATORIO_FINAL_INVALIDO;

		if (!nv.validate(processo, 21, 21))
			return QManagerCodeErro.NUMERO_PROCESSO_INVALIDO;

		/*
		 * TODO: if (!temTipoProjetoValido(tipoProjeto)) return 40;
		 */

		if (!nv.isDoublePositivo(orcamento))
			return QManagerCodeErro.VALOR_ORCAMENTO_INVALIDO;

		if (!nv.isInteiroPositivo(idEdital))
			return QManagerCodeErro.ID_EDITAL_INVALIDO;

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
			return QManagerCodeErro.NOME_PESSOA_INVALIDO;

		if (!nv.validate(cpf))
			return QManagerCodeErro.CPF_INVALIDO;

		if (!nv.validate(matricula, 11, 11))
			return QManagerCodeErro.MATRICULA_INVALIDA;

		if (!sv.validate(endereco, 255))
			return QManagerCodeErro.ENDERECO_INVALIDO;

		if (!nv.validate(cep))
			return QManagerCodeErro.CEP_INVALIDO;

		if (!nv.validate(telefone, 11))
			return QManagerCodeErro.TELEFONE_INVALIDO;

		if (!ev.validate(email))
			return QManagerCodeErro.EMAIL_INVALIDO;

		if (!sv.validatePassword(senha))
			return QManagerCodeErro.SENHA_INVALIDA;

		if (!nv.isInteiroPositivo(idTurma))
			return QManagerCodeErro.ID_TURMA_INVALIDO;

		if (!nv.isInteiroPositivo(idInstituicaoBancaria))
			return QManagerCodeErro.ID_INSTITUICAO_BANCARIA_INVALIDO;

		if (!nv.validate(operacao, 3))
			return QManagerCodeErro.OPERACAO_CONTA_INVALIDA;

		if (!nv.validate(conta, 15))
			return QManagerCodeErro.NUMERO_CONTA_INVALIDO;

		return VALIDACAO_OK;
	}

	public static int orientador(Orientador orientador) {

		// Orientador
		String nomePessoa = orientador.getNomePessoa();
		String cpf = orientador.getCpf();
		String matricula = orientador.getMatricula();
		String endereco = orientador.getEndereco();
		String cep = orientador.getCep();
		String telefone = orientador.getTelefone();
		String email = orientador.getEmail();
		String titulacao = orientador.getTitulacao();
		String cargo = orientador.getCargo();
		String localTrabalho = orientador.getLocalTrabalho();
		String senha = orientador.getSenha();

		// Dados Bancarios
		int idInstituicaoBancaria = orientador.getDadosBancarios()
				.getInstituicaoBancaria().getIdInstituicaoBancaria();
		String operacao = orientador.getDadosBancarios().getOperacao();
		String conta = orientador.getDadosBancarios().getConta();

		if (!sv.validate(nomePessoa, 90))
			return QManagerCodeErro.NOME_PESSOA_INVALIDO;

		if (!nv.validate(cpf))
			return QManagerCodeErro.CPF_INVALIDO;

		if (!nv.validate(matricula, 11, 11))
			return QManagerCodeErro.MATRICULA_INVALIDA;

		if (!sv.validate(endereco, 255))
			return QManagerCodeErro.ENDERECO_INVALIDO;

		if (!nv.validate(cep))
			return QManagerCodeErro.CEP_INVALIDO;

		if (!nv.validate(telefone, 11))
			return QManagerCodeErro.TELEFONE_INVALIDO;

		if (!ev.validate(email))
			return QManagerCodeErro.EMAIL_INVALIDO;

		if (!sv.validatePassword(senha))
			return QManagerCodeErro.SENHA_INVALIDA;

		if (!sv.validate(titulacao, 45))
			return QManagerCodeErro.TITULACAO_INVALIDA;

		if (!sv.validate(cargo, 45))
			return QManagerCodeErro.CARGO_INVALIDO;

		if (!sv.validate(localTrabalho, 45))
			return QManagerCodeErro.LOCAL_TRABALHO_INVALIDO;

		if (!nv.isInteiroPositivo(idInstituicaoBancaria))
			return QManagerCodeErro.ID_INSTITUICAO_BANCARIA_INVALIDO;

		if (!nv.validate(operacao, 3))
			return QManagerCodeErro.OPERACAO_CONTA_INVALIDA;

		if (!nv.validate(conta, 15))
			return QManagerCodeErro.NUMERO_CONTA_INVALIDO;

		return VALIDACAO_OK;
	}

	public static int participacao(Partipacao participacao) {
		int pessoaId = participacao.getMembroProjeto().getPessoaId();
		int idProjeto = participacao.getProjeto().getIdProjeto();
		Date inicioParticipacao = participacao.getInicioParticipacao();
		Date fimParticipacao = participacao.getFimParticipacao();
		double valorBolsa = participacao.getValorBolsa();

		if (!nv.isInteiroPositivo(pessoaId))
			return QManagerCodeErro.ID_MEMBRO_PROJETO_INVALIDO;

		if (!nv.isInteiroPositivo(idProjeto))
			return QManagerCodeErro.ID_PROJETO_INVALIDO;

		// dataInicio if (!dataIgualHoje(inicioParticipacao)) return 59;

		// dataFim if (!dataMaiorHoje(fimParticipacao)) return 60;

		if (!dv.validate(inicioParticipacao, fimParticipacao))
			return 61;

		if (!nv.isDoublePositivo(valorBolsa))
			return QManagerCodeErro.VALOR_BOLSA_INVALIDO;

		return VALIDACAO_OK;
	}

	public static int instituicaoBancaria(
			InstituicaoBancaria instituicaoBancaria) {
		String nomeBanco = instituicaoBancaria.getNomeBanco();

		if (!sv.validate(nomeBanco, 90))
			return QManagerCodeErro.NOME_BANCO_INVALIDO;

		return VALIDACAO_OK;
	}

	public static int curso(Curso curso) {
		String nomeCurso = curso.getNomeCurso();

		if (!sv.validate(nomeCurso, 90))
			return QManagerCodeErro.NOME_CURSO_INVALIDO;

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
			return QManagerCodeErro.ID_CURSO_INVALIDO;

		return VALIDACAO_OK;
	}

}
