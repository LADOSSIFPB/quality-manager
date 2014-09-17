package br.edu.ifpb.qmanager.validacao;

import java.sql.Date;

import br.edu.ifpb.qmanager.entidade.Curso;
import br.edu.ifpb.qmanager.entidade.Discente;
import br.edu.ifpb.qmanager.entidade.Edital;
import br.edu.ifpb.qmanager.entidade.InstituicaoBancaria;
import br.edu.ifpb.qmanager.entidade.InstituicaoFinanciadora;
import br.edu.ifpb.qmanager.entidade.Orientador;
import br.edu.ifpb.qmanager.entidade.Partipacao;
import br.edu.ifpb.qmanager.entidade.ProgramaInstitucional;
import br.edu.ifpb.qmanager.entidade.Projeto;
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

	public static boolean instituicaoFinanciadora(
			InstituicaoFinanciadora instituicaoFinanciadora) {

		String cnpj = instituicaoFinanciadora.getCnpj();
		String nomeInstituicaoFinanciadora = instituicaoFinanciadora
				.getNomeInstituicaoFinanciadora();
		String sigla = instituicaoFinanciadora.getSigla();
		double orcamento = instituicaoFinanciadora.getOrcamento();

		if (!nv.validate(cnpj, 14))
			return false;

		if (!sv.validate(nomeInstituicaoFinanciadora, 255))
			return false;

		if (!sv.validate(sigla, 3, 10))
			return false;

		if (!nv.isDoublePositivo(orcamento))
			return false;

		return true;

	}

	public static boolean programaInstitucional(
			ProgramaInstitucional programaInstitucional) {

		String nomeProgramaInstitucional = programaInstitucional
				.getNomeProgramaInstitucional();
		String sigla = programaInstitucional.getSigla();
		double orcamento = programaInstitucional.getOrcamento();
		int instituicaoFinanciadoraId = programaInstitucional
				.getInstituicaoFinanciadora().getIdInstituicaoFinanciadora();

		if (!sv.validate(nomeProgramaInstitucional, 255))
			return false;

		if (!sv.validate(sigla, 3, 10))
			return false;

		if (!nv.isDoublePositivo(orcamento))
			return false;

		if (!nv.isInteiroPositivo(instituicaoFinanciadoraId))
			return false;

		return true;

	}

	public static boolean edital(Edital edital) {
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
			return false;

		if (!nv.isInteiroPositivo(numero))
			return false;

		if (!nv.isInteiroPositivo(ano))
			return false;

		/*
		 * TODO: if (!nv.isInteiroPositivo(ano)) return false;
		 * 
		 * // inicioInscricoes if (!dataMaiorHoje(inicioInscricoes)) return
		 * false;
		 * 
		 * // fimInscricoes if (!dataMaiorHoje(fimInscricoes)) return false;
		 * 
		 * if (!dataCrescente(inicioInscricoes, fimInscricoes)) return false;
		 * 
		 * // relatorioParcial if (!dataMaiorHoje(relatorioParcial)) return
		 * false;
		 * 
		 * // relatorioParcial if (!dataMaiorHoje(relatorioFinal)) return false;
		 * 
		 * if (!dataCrescente(relatorioParcial, relatorioFinal)) return false;
		 */

		if (!nv.isInteiroPositivo(vagas))
			return false;

		if (!nv.isDoublePositivo(bolsaDiscente))
			return false;

		if (!nv.isDoublePositivo(bolsaDocente))
			return false;

		/*
		 * TODO: if (!temTipoProjetoValido(tipoEdital)) return false;
		 */

		if (!nv.isInteiroPositivo(programaInstitucionalId))
			return false;

		return true;

	}

	public static boolean projeto(Projeto projeto) {

		String nomeProjeto = projeto.getNomeProjeto();
		Date inicioProjeto = projeto.getInicioProjeto();
		Date fimProjeto = projeto.getFimProjeto();
		String relatorioSubmetido = projeto.getRelatorioSubmetido();
		String relatorioParcial = projeto.getRelatorioParcial();
		String relatorioFinal = projeto.getRelatorioFinal();
		String processo = projeto.getProcesso();
		char tipoProjeto = projeto.getTipoProjeto();
		double orcamento = projeto.getOrcamento();
		int idEdital = projeto.getEdital().getIdEdital();

		if (!sv.validate(nomeProjeto, 255))
			return false;

		/*
		 * TODO: // inicioProjeto if (!dataMaiorHoje(inicioProjeto)) return
		 * false;
		 * 
		 * // fimProjeto if (!dataMaiorHoje(fimProjeto)) return false;
		 * 
		 * if (!dataCrescente(inicioProjeto, fimProjeto)) return false;
		 */

		if (!sv.validate(relatorioSubmetido, 255))
			return false;

		if (!sv.validate(relatorioParcial, 255))
			return false;

		if (!sv.validate(relatorioFinal, 255))
			return false;

		if (!nv.validate(processo, 21))
			return false;

		/*
		 * TODO: if (!temTipoProjetoValido(tipoProjeto)) return false;
		 */

		if (!nv.isDoublePositivo(orcamento))
			return false;

		if (!nv.isInteiroPositivo(idEdital))
			return false;

		return true;
	}

	public static boolean discente(Discente discente) {

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
			return false;

		if (!nv.validate(cpf))
			return false;

		if (!nv.validate(matricula))
			return false;

		if (!sv.validate(endereco, 255))
			return false;

		if (!nv.validate(cep))
			return false;

		if (!nv.validate(telefone, 9))
			return false;

		if (!ev.validate(email))
			return false;

		if (!nv.isInteiroPositivo(idTurma))
			return false;

		if (!nv.isInteiroPositivo(idInstituicaoBancaria))
			return false;

		if (!nv.validate(operacao, 3))
			return false;

		if (!nv.validate(conta, 15))
			return false;

		return true;

	}

	public static boolean orientador(Orientador orientador) {

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
			return false;

		if (!nv.validate(cpf))
			return false;

		if (!nv.validate(matricula))
			return false;

		if (!sv.validate(endereco, 255))
			return false;

		if (!nv.validate(cep))
			return false;

		if (!nv.validate(telefone, 9))
			return false;

		if (!ev.validate(email))
			return false;

		if (!sv.validate(titulacao, 45))
			return false;

		if (!sv.validate(cargo, 45))
			return false;

		if (!sv.validate(localTrabalho, 45))
			return false;

		if (!nv.isInteiroPositivo(idInstituicaoBancaria))
			return false;

		if (!nv.validate(operacao, 3))
			return false;

		if (!nv.validate(conta, 15))
			return false;

		return true;

	}

	public static boolean participacao(Partipacao participacao) {
		int pessoaId = participacao.getMembroProjeto().getPessoaId();
		int idProjeto = participacao.getProjeto().getIdProjeto();
		Date inicioParticipacao = participacao.getInicioParticipacao();
		Date fimParticipacao = participacao.getFimParticipacao();
		double valorBolsa = participacao.getValorBolsa();

		if (!nv.isInteiroPositivo(pessoaId))
			return false;

		if (!nv.isInteiroPositivo(idProjeto))
			return false;

		/*
		 * TODO: // dataInicio if (!dataIgualHoje(inicioParticipacao)) return
		 * false;
		 * 
		 * // dataFim if (!dataMaiorHoje(fimParticipacao)) return false;
		 * 
		 * if (!dataCrescente(inicioParticipacao, fimParticipacao)) return
		 * false;
		 */

		if (!nv.isDoublePositivo(valorBolsa))
			return false;

		return true;

	}

	public static boolean instituicaoBancaria(
			InstituicaoBancaria instituicaoBancaria) {
		String nomeBanco = instituicaoBancaria.getNomeBanco();

		if (!sv.validate(nomeBanco, 90))
			return false;

		return true;

	}

	public static boolean curso(Curso curso) {
		String nomeCurso = curso.getNomeCurso();

		if (!sv.validate(nomeCurso, 90))
			return false;

		return true;
	}

	public static boolean turma(Turma turma) {
		int periodoLetivo = turma.getPeriodoLetivo();
		char turno = turma.getTurno();
		int cursoId = turma.getCurso().getIdCurso();

		/*
		 * TODO: // ano if (!temPeriodoLetivoValido(periodoLetivo)) return
		 * false;
		 * 
		 * if (!temTurnoValido(turno)) return false;
		 */

		if (!nv.isInteiroPositivo(cursoId))
			return false;

		return true;

	}

}
