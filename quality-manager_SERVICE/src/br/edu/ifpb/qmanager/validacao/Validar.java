package br.edu.ifpb.qmanager.validacao;

import java.util.Date;

import br.edu.ifpb.qmanager.entidade.Curso;
import br.edu.ifpb.qmanager.entidade.Discente;
import br.edu.ifpb.qmanager.entidade.Edital;
import br.edu.ifpb.qmanager.entidade.InstituicaoBancaria;
import br.edu.ifpb.qmanager.entidade.InstituicaoFinanciadora;
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

	public static int instituicaoFinanciadora(
			InstituicaoFinanciadora instituicaoFinanciadora) {

		String cnpj = instituicaoFinanciadora.getCnpj();
		String nomeInstituicaoFinanciadora = instituicaoFinanciadora
				.getNomeInstituicaoFinanciadora();
		String sigla = instituicaoFinanciadora.getSigla();
		double orcamento = instituicaoFinanciadora.getOrcamento();

		if (!nv.validate(cnpj, 14, 14))
			return QManagerCodeErro.CNPJ_INVALIDO;

		if (!sv.validate(nomeInstituicaoFinanciadora, 255))
			return 11;

		if (!sv.validate(sigla, 3, 10))
			return 12;

		if (!nv.isDoublePositivo(orcamento))
			return 13;

		return 0;

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
			return 14;

		if (!sv.validate(sigla, 3, 10))
			return 15;

		if (!nv.isDoublePositivo(orcamento))
			return 16;

		if (!nv.isInteiroPositivo(instituicaoFinanciadoraId))
			return 17;

		return 0;

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

		if (!sv.validate(arquivo, 255))
			return 18;

		if (!nv.isInteiroPositivo(numero))
			return 19;

		if (!nv.isInteiroPositivo(ano))
			return 20;

		// inicioInscricoes if (!dataMaiorHoje(inicioInscricoes)) return 21;

		// fimInscricoes if (!dataMaiorHoje(fimInscricoes)) return 22;

		if (!dv.validate(inicioInscricoes, fimInscricoes))
			return 23;

		// relatorioParcial if (!dataMaiorHoje(relatorioParcial)) return 24;

		// relatorioParcial if (!dataMaiorHoje(relatorioFinal)) return 25;

		if (!dv.validate(relatorioParcial, relatorioFinal))
			return 26;

		if (!nv.isInteiroPositivo(vagas))
			return 27;

		if (!nv.isDoublePositivo(bolsaDiscente))
			return 28;

		if (!nv.isDoublePositivo(bolsaDocente))
			return 29;

		/*
		 * TODO: if (!temTipoProjetoValido(tipoEdital)) return 30;
		 */

		if (!nv.isInteiroPositivo(programaInstitucionalId))
			return 31;

		return 0;

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
			return 32;

		// inicioProjeto if (!dataMaiorHoje(inicioProjeto)) return 33;

		// fimProjeto if (!dataMaiorHoje(fimProjeto)) return 34;

		if (!dv.validate(inicioProjeto, fimProjeto))
			return 35;

		if (!sv.validate(projetoSubmetido, 255))
			return 36;

		if (!sv.validate(relatorioParcial, 255))
			return 37;

		if (!sv.validate(relatorioFinal, 255))
			return 38;

		if (!nv.validate(processo, 21, 21))
			return 39;

		/*
		 * TODO: if (!temTipoProjetoValido(tipoProjeto)) return 40;
		 */

		if (!nv.isDoublePositivo(orcamento))
			return 13;

		if (!nv.isInteiroPositivo(idEdital))
			return 41;

		return 0;
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
			return 42;

		if (!nv.validate(cpf))
			return 43;

		if (!nv.validate(matricula, 11, 11))
			return 44;

		if (!sv.validate(endereco, 255))
			return 45;

		if (!nv.validate(cep))
			return 46;

		if (!nv.validate(telefone, 9))
			return 47;

		if (!ev.validate(email))
			return 48;

		if (!sv.validatePassword(senha))
			return 49;

		if (!nv.isInteiroPositivo(idTurma))
			return 50;

		if (!nv.isInteiroPositivo(idInstituicaoBancaria))
			return 51;

		if (!nv.validate(operacao, 3))
			return 52;

		if (!nv.validate(conta, 15))
			return 53;

		return 0;

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
			return 42;

		if (!nv.validate(cpf))
			return 43;

		if (!nv.validate(matricula, 11, 11))
			return 44;

		if (!sv.validate(endereco, 255))
			return 45;

		if (!nv.validate(cep))
			return 46;

		if (!nv.validate(telefone, 9))
			return 47;

		if (!ev.validate(email))
			return 48;

		if (!sv.validatePassword(senha))
			return 49;

		if (!sv.validate(titulacao, 45))
			return 54;

		if (!sv.validate(cargo, 45))
			return 55;

		if (!sv.validate(localTrabalho, 45))
			return 56;

		if (!nv.isInteiroPositivo(idInstituicaoBancaria))
			return 51;

		if (!nv.validate(operacao, 3))
			return 52;

		if (!nv.validate(conta, 15))
			return 53;

		return 0;

	}

	public static int participacao(Partipacao participacao) {
		int pessoaId = participacao.getMembroProjeto().getPessoaId();
		int idProjeto = participacao.getProjeto().getIdProjeto();
		Date inicioParticipacao = participacao.getInicioParticipacao();
		Date fimParticipacao = participacao.getFimParticipacao();
		double valorBolsa = participacao.getValorBolsa();

		if (!nv.isInteiroPositivo(pessoaId))
			return 57;

		if (!nv.isInteiroPositivo(idProjeto))
			return 58;

		// dataInicio if (!dataIgualHoje(inicioParticipacao)) return 59;

		// dataFim if (!dataMaiorHoje(fimParticipacao)) return 60;

		if (!dv.validate(inicioParticipacao, fimParticipacao))
			return 61;

		if (!nv.isDoublePositivo(valorBolsa))
			return 62;

		return 0;

	}

	public static int instituicaoBancaria(
			InstituicaoBancaria instituicaoBancaria) {
		String nomeBanco = instituicaoBancaria.getNomeBanco();

		if (!sv.validate(nomeBanco, 90))
			return 63;

		return 0;

	}

	public static int curso(Curso curso) {
		String nomeCurso = curso.getNomeCurso();

		if (!sv.validate(nomeCurso, 90))
			return 64;

		return 0;
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
			return 67;

		return 0;

	}

}
