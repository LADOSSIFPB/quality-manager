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
import br.edu.ifpb.qmanager.validate.NumeroValidator;
import br.edu.ifpb.qmanager.validate.StringValidator;

public class Validar {

	private static StringValidator sv = new StringValidator();
	private static NumeroValidator nv = new NumeroValidator();

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

		// nomeProgramaInstitucional
		if (!nomeTemApenasLetras(nomeProgramaInstitucional))
			return false;

		if (!nomeTemTamanhoPermitido(nomeProgramaInstitucional, 255))
			return false;

		// sigla
		if (!nomeTemApenasLetras(sigla))
			return false;

		if (!nomeTemTamanhoPermitido(sigla, 3, 10))
			return false;

		// orcamento
		if (!numeroPositivo(orcamento))
			return false;

		// instituicaoFinanciadoraId
		if (!numeroPositivo(instituicaoFinanciadoraId))
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

		// arquivo
		if (nomeTemTamanhoPermitido(arquivo, 255))
			return false;

		// numero
		if (!numeroPositivo(numero))
			return false;

		// ano
		if (!numeroPositivo(ano))
			return false;

		if (!anoValido(ano))
			return false;

		// inicioInscricoes
		if (!dataMaiorHoje(inicioInscricoes))
			return false;

		// fimInscricoes
		if (!dataMaiorHoje(fimInscricoes))
			return false;

		if (!dataCrescente(inicioInscricoes, fimInscricoes))
			return false;

		// relatorioParcial
		if (!dataMaiorHoje(relatorioParcial))
			return false;

		// relatorioParcial
		if (!dataMaiorHoje(relatorioFinal))
			return false;

		if (!dataCrescente(relatorioParcial, relatorioFinal))
			return false;

		// vagas
		if (!numeroPositivo(vagas))
			return false;

		// bolsaDiscente
		if (!numeroPositivo(bolsaDiscente))
			return false;

		// bolsaDocente
		if (!numeroPositivo(bolsaDocente))
			return false;

		// tipoEdital
		if (!temTipoProjetoValido(tipoEdital))
			return false;

		// programaInstitucionalId
		if (!numeroPositivo(programaInstitucionalId))
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

		// nomeProjeto
		if (!nomeTemApenasLetras(nomeProjeto))
			return false;

		if (!nomeTemTamanhoPermitido(nomeProjeto, 255))
			return false;

		// inicioProjeto
		if (!dataMaiorHoje(inicioProjeto))
			return false;

		// fimProjeto
		if (!dataMaiorHoje(fimProjeto))
			return false;

		if (!dataCrescente(inicioProjeto, fimProjeto))
			return false;

		// relatorioSubmetido
		if (!nomeTemTamanhoPermitido(relatorioSubmetido, 255))
			return false;

		// relatorioParcial
		if (!nomeTemTamanhoPermitido(relatorioParcial, 255))
			return false;

		// relatorioFinal
		if (!nomeTemTamanhoPermitido(relatorioFinal, 255))
			return false;

		// processo
		if (!nomeTemApenasNumeros(processo))
			return false;

		if (!nomeTemTamanhoEsperado(processo, 21))
			return false;

		// tipoPorjeto
		if (!temTipoProjetoValido(tipoProjeto))
			return false;

		// orcamento
		if (!numeroPositivo(orcamento))
			return false;

		// idEdital
		if (!numeroPositivo(idEdital))
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

		// nomePessoa
		if (!nomeTemApenasLetras(nomePessoa))
			return false;

		if (!nomeTemTamanhoPermitido(nomePessoa, 90))
			return false;

		// cpf
		if (!nomeTemApenasNumeros(cpf))
			return false;

		// matricula
		if (!nomeTemApenasNumeros(matricula))
			return false;

		// endereco
		if (!nomeTemTamanhoPermitido(endereco, 255))
			return false;

		// cep
		if (!nomeTemApenasNumeros(cep))
			return false;

		// telefone
		if (!nomeTemTamanhoPermitido(telefone, 9))
			return false;

		// email
		if (!nomeTemTamanhoPermitido(email, 90))
			return false;

		if (!emailValido(email))
			return false;

		// idTurma
		if (!numeroPositivo(idTurma))
			return false;

		// login
		if (!nomeTemTamanhoPermitido(login, 20, 90))
			return false;

		// senha
		if (!nomeTemTamanhoPermitido(senha, 25))
			return false;

		// idInstituicaoBancaria
		if (!numeroPositivo(idInstituicaoBancaria))
			return false;

		// operacao
		if (!nomeTemTamanhoPermitido(operacao, 3))
			return false;

		if (!nomeTemApenasNumeros(operacao))
			return false;

		// conta
		if (!nomeTemTamanhoPermitido(conta, 15))
			return false;

		if (!nomeTemApenasNumeros(conta))
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

		// nomePessoa
		if (!nomeTemApenasLetras(nomePessoa))
			return false;

		if (!nomeTemTamanhoPermitido(nomePessoa, 90))
			return false;

		// cpf
		if (!nomeTemApenasNumeros(cpf))
			return false;

		// matricula
		if (!nomeTemApenasNumeros(matricula))
			return false;

		// endereco
		if (!nomeTemTamanhoPermitido(endereco, 255))
			return false;

		// cep
		if (!nomeTemApenasNumeros(cep))
			return false;

		// telefone
		if (!nomeTemTamanhoPermitido(telefone, 9))
			return false;

		// email
		if (!nomeTemTamanhoPermitido(email, 90))
			return false;

		if (!emailValido(email))
			return false;

		// titulacao
		if (!nomeTemApenasLetras(titulacao))
			return false;

		if (!nomeTemTamanhoPermitido(titulacao, 45))
			return false;

		// cargo
		if (!nomeTemApenasLetras(cargo))
			return false;

		if (!nomeTemTamanhoPermitido(cargo, 45))
			return false;

		// localTrabalho
		if (!nomeTemApenasLetras(localTrabalho))
			return false;

		if (!nomeTemTamanhoPermitido(localTrabalho, 45))
			return false;

		// login
		if (!nomeTemTamanhoPermitido(login, 20, 90))
			return false;

		// senha
		if (!nomeTemTamanhoPermitido(senha, 25))
			return false;

		// idInstituicaoBancaria
		if (!numeroPositivo(idInstituicaoBancaria))
			return false;

		// operacao
		if (!nomeTemTamanhoPermitido(operacao, 3))
			return false;

		if (!nomeTemApenasNumeros(operacao))
			return false;

		// conta
		if (!nomeTemTamanhoPermitido(conta, 15))
			return false;

		if (!nomeTemApenasNumeros(conta))
			return false;

		return true;

	}

	public static boolean participacao(Partipacao participacao) {
		int pessoaId = participacao.getMembroProjeto().getPessoaId();
		int idProjeto = participacao.getProjeto().getIdProjeto();
		Date inicioParticipacao = participacao.getInicioParticipacao();
		Date fimParticipacao = participacao.getFimParticipacao();
		double valorBolsa = participacao.getValorBolsa();

		// pessoaId
		if (!numeroPositivo(pessoaId))
			return false;

		// idProjeto
		if (!numeroPositivo(idProjeto))
			return false;

		// dataInicio
		if (!dataIgualHoje(inicioParticipacao))
			return false;

		// dataFim
		if (!dataMaiorHoje(fimParticipacao))
			return false;

		if (!dataCrescente(inicioParticipacao, fimParticipacao))
			return false;

		// bolsista
		if (!numeroPositivo(valorBolsa))
			return false;

		return true;

	}

	public static boolean instituicaoBancaria(
			InstituicaoBancaria instituicaoBancaria) {
		String nomeBanco = instituicaoBancaria.getNomeBanco();

		// nomeBanco
		if (!nomeTemApenasLetras(nomeBanco))
			return false;

		if (!nomeTemTamanhoPermitido(nomeBanco, 90))
			return false;

		return true;

	}

	public static boolean curso(Curso curso) {
		String nomeCurso = curso.getNomeCurso();

		// nomeCurso
		if (!nomeTemTamanhoPermitido(nomeCurso, 90))
			return false;

		if (!nomeTemApenasLetras(nomeCurso))
			return false;

		return true;
	}

	public static boolean turma(Turma turma) {
		int periodoLetivo = turma.getPeriodoLetivo();
		char turno = turma.getTurno();
		int cursoId = turma.getCurso().getIdCurso();

		// ano
		if (!temPeriodoLetivoValido(periodoLetivo))
			return false;

		// turno
		if (!temTurnoValido(turno))
			return false;

		// cursoId
		if (!numeroPositivo(cursoId))
			return false;

		return true;

	}

}
