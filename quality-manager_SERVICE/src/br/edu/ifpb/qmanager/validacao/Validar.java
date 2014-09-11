package br.edu.ifpb.qmanager.validacao;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

import br.edu.ifpb.qmanager.entidade.Curso;
import br.edu.ifpb.qmanager.entidade.Discente;
import br.edu.ifpb.qmanager.entidade.Edital;
import br.edu.ifpb.qmanager.entidade.Individuo;
import br.edu.ifpb.qmanager.entidade.InstituicaoBancaria;
import br.edu.ifpb.qmanager.entidade.InstituicaoFinanciadora;
import br.edu.ifpb.qmanager.entidade.Orientador;
import br.edu.ifpb.qmanager.entidade.Participacao;
import br.edu.ifpb.qmanager.entidade.ProgramaInstitucional;
import br.edu.ifpb.qmanager.entidade.Projeto;
import br.edu.ifpb.qmanager.entidade.Turma;

public class Validar {

	// Fatorar código comum às validações

	private static boolean nomeTemApenasLetras(String atributo) {
		atributo.trim();
		if (!atributo.isEmpty())
			for (char a : atributo.toCharArray()) {
				if ((a < 'A' || a > 'Z') && (a < 'a' || a > 'z'))
					return false;
			}
		else
			return false;
		return true;
	}

	private static boolean nomeTemApenasNumeros(String atributo) {
		atributo.trim();
		if (!atributo.isEmpty())
			for (char a : atributo.toCharArray()) {
				if (a < '0' || a > '9')
					return false;
			}
		else
			return false;
		return true;
	}

	private static boolean nomeTemTamanhoPermitido(String atributo,
			int tamanhoPermitido) {
		if (!atributo.isEmpty())
			return atributo.length() <= tamanhoPermitido;
		return false;
	}

	private static boolean nomeTemTamanhoPermitido(String atributo,
			int tamanhoMenor, int tamanhoMaior) {
		if (!atributo.isEmpty())
			return (atributo.length() >= tamanhoMenor)
					&& (atributo.length() <= tamanhoMaior);
		return false;
	}

	private static boolean nomeTemTamanhoEsperado(String atributo,
			int tamanhoEsperado) {
		if (!atributo.isEmpty())
			return atributo.length() == tamanhoEsperado;
		return false;
	}

	private static boolean numeroPositivo(double valor) {
		return valor >= 0.0;
	}

	private static boolean temPeriodoLetivoValido(int anoTurma) {
		return ((anoTurma >= 1) && (anoTurma <= 10));
	}

	private static boolean anoValido(int ano) {
		Calendar anoAtual = GregorianCalendar.getInstance();
		return ano <= anoAtual.get(Calendar.YEAR);
	}

	private static boolean dataMaiorHoje(Date data) {
		java.util.Date diaAtual = new java.util.Date();
		return data.getTime() > diaAtual.getTime();
	}

	private static boolean dataIgualHoje(Date data) {
		java.util.Date diaAtual = new java.util.Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		return data.toString().equals(sdf.format(diaAtual));
	}

	private static boolean dataCrescente(Date dataMenor, Date dataMaior) {
		return dataMenor.getTime() < dataMaior.getTime();
	}

	private static boolean temTipoProjetoValido(char tipo) {
		return ((tipo == 'P') || (tipo == 'E'));
	}

	private static boolean temTurnoValido(char turno) {
		return ((turno == 'M') || (turno == 'T') || (turno == 'N'));
	}

	private static boolean emailValido(String email) {
		String[] ini;
		String local;
		if (!email.isEmpty()) {
			if (email.contains("@") && (!email.contains(" "))) {
				ini = email.split("@");
				if (ini.length != 2)
					return false;

				local = new String(ini[1]);
				if (local.contains(".")) {
					ini = local.split("\\.");
					if (ini.length == 2) {
						if (ini[1].length() != 3)
							return false;
					} else if (ini.length == 3) {
						if (ini[1].length() != 3)
							return false;
						if (ini[2].length() != 2)
							return false;
					} else
						return false;
				} else
					return false;
			} else
				return false;
		}

		return true;
	}

	// Validações por classe

	public static boolean instituicaoFinanciadora(
			InstituicaoFinanciadora instituicaoFinanciadora) {

		String cnpj = instituicaoFinanciadora.getCnpj();
		String nomeInstituicaoFinanciadora = instituicaoFinanciadora
				.getNomeInstituicaoFinanciadora();
		String sigla = instituicaoFinanciadora.getSigla();
		double orcamento = instituicaoFinanciadora.getOrcamento();

		// cnpj
		if (!nomeTemApenasNumeros(cnpj))
			return false;

		if (!nomeTemTamanhoEsperado(cnpj, 14))
			return false;

		// nomeInstituicaoFinanciadora
		if (!nomeTemApenasLetras(nomeInstituicaoFinanciadora))
			return false;

		if (!nomeTemTamanhoPermitido(nomeInstituicaoFinanciadora, 255))
			return false;

		// sigla
		if (!nomeTemApenasLetras(sigla))
			return false;

		if (!nomeTemTamanhoPermitido(sigla, 3, 10))
			return false;

		// orcamento
		if (!numeroPositivo(orcamento))
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

	public static boolean discente(Individuo<Discente> individuo) {

		// Discente
		String nomePessoa = individuo.getIndividuo().getNomePessoa();
		String cpf = individuo.getIndividuo().getCpf();
		String matricula = individuo.getIndividuo().getMatricula();
		String endereco = individuo.getIndividuo().getEndereco();
		String cep = individuo.getIndividuo().getCep();
		String telefone = individuo.getIndividuo().getTelefone();
		String email = individuo.getIndividuo().getEmail();
		int idTurma = individuo.getIndividuo().getTurma().getIdTurma();

		// Usuario
		String login = individuo.getUsuario().getLogin();
		String senha = individuo.getUsuario().getSenha();

		// Dados Bancarios
		int idInstituicaoBancaria = individuo.getDadosBancarios()
				.getInstituicaoBancaria().getIdInstituicaoBancaria();
		String operacao = individuo.getDadosBancarios().getOperacao();
		String conta = individuo.getDadosBancarios().getConta();

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

	public static boolean orientador(Individuo<Orientador> individuo) {

		// Orientador
		String nomePessoa = individuo.getIndividuo().getNomePessoa();
		String cpf = individuo.getIndividuo().getCpf();
		String matricula = individuo.getIndividuo().getMatricula();
		String endereco = individuo.getIndividuo().getEndereco();
		String cep = individuo.getIndividuo().getCep();
		String telefone = individuo.getIndividuo().getTelefone();
		String email = individuo.getIndividuo().getEmail();
		String titulacao = individuo.getIndividuo().getTitulacao();
		String cargo = individuo.getIndividuo().getCargo();
		String localTrabalho = individuo.getIndividuo().getLocalTrabalho();

		// Usuario
		String login = individuo.getUsuario().getLogin();
		String senha = individuo.getUsuario().getSenha();

		// Dados Bancarios
		int idInstituicaoBancaria = individuo.getDadosBancarios()
				.getInstituicaoBancaria().getIdInstituicaoBancaria();
		String operacao = individuo.getDadosBancarios().getOperacao();
		String conta = individuo.getDadosBancarios().getConta();

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

	public static boolean participacaoDiscente(
			Participacao<Discente> participacao) {
		int pessoaId = participacao.getPessoa().getPessoaId();
		int idProjeto = participacao.getProjeto().getIdProjeto();
		Date dataInicio = participacao.getDataInicio();
		Date dataFim = participacao.getDataFim();
		double bolsista = participacao.getBolsista();

		// pessoaId
		if (!numeroPositivo(pessoaId))
			return false;

		// idProjeto
		if (!numeroPositivo(idProjeto))
			return false;

		// dataInicio
		if (!dataIgualHoje(dataInicio))
			return false;

		// dataFim
		if (!dataMaiorHoje(dataFim))
			return false;

		if (!dataCrescente(dataInicio, dataFim))
			return false;

		// bolsista
		if (!numeroPositivo(bolsista))
			return false;

		return true;

	}

	public static boolean participacaoOrientador(
			Participacao<Orientador> participacao) {
		int pessoaId = participacao.getPessoa().getPessoaId();
		int idProjeto = participacao.getProjeto().getIdProjeto();
		Date dataInicio = participacao.getDataInicio();
		Date dataFim = participacao.getDataFim();
		double bolsista = participacao.getBolsista();

		// pessoaId
		if (!numeroPositivo(pessoaId))
			return false;

		// idProjeto
		if (!numeroPositivo(idProjeto))
			return false;

		// dataInicio
		if (!dataIgualHoje(dataInicio))
			return false;

		// dataFim
		if (!dataMaiorHoje(dataFim))
			return false;

		if (!dataCrescente(dataInicio, dataFim))
			return false;

		// bolsista
		if (!numeroPositivo(bolsista))
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