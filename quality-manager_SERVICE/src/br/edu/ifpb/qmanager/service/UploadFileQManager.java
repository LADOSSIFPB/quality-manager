package br.edu.ifpb.qmanager.service;

import java.util.Date;

import javax.annotation.security.PermitAll;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;

import org.apache.commons.io.FilenameUtils;
import org.jboss.resteasy.annotations.providers.multipart.MultipartForm;

import br.edu.ifpb.qmanager.dao.ArquivoEditalDAO;
import br.edu.ifpb.qmanager.dao.ArquivoParticipacaoDAO;
import br.edu.ifpb.qmanager.dao.ArquivoProjetoDAO;
import br.edu.ifpb.qmanager.dao.BancoUtil;
import br.edu.ifpb.qmanager.entidade.Arquivo;
import br.edu.ifpb.qmanager.entidade.ArquivoEdital;
import br.edu.ifpb.qmanager.entidade.ArquivoParticipacao;
import br.edu.ifpb.qmanager.entidade.ArquivoProjeto;
import br.edu.ifpb.qmanager.entidade.CodeErroQManager;
import br.edu.ifpb.qmanager.entidade.Edital;
import br.edu.ifpb.qmanager.entidade.Erro;
import br.edu.ifpb.qmanager.entidade.MapErroQManager;
import br.edu.ifpb.qmanager.entidade.Participacao;
import br.edu.ifpb.qmanager.entidade.Pessoa;
import br.edu.ifpb.qmanager.entidade.Projeto;
import br.edu.ifpb.qmanager.excecao.IOExceptionQManager;
import br.edu.ifpb.qmanager.excecao.SQLExceptionQManager;
import br.edu.ifpb.qmanager.form.FileUploadForm;
import br.edu.ifpb.qmanager.tipo.TipoArquivo;
import br.edu.ifpb.qmanager.tipo.TipoArquivoEdital;
import br.edu.ifpb.qmanager.tipo.TipoArquivoParticipacao;
import br.edu.ifpb.qmanager.tipo.TipoArquivoPessoa;
import br.edu.ifpb.qmanager.tipo.TipoArquivoProjeto;
import br.edu.ifpb.qmanager.util.FileUtil;

/**
 * Serviço de upload de arquivo.
 * 
 * @author Rhavy
 *
 */
@Path("/arquivo")
public class UploadFileQManager {

	/**
	 * Upload dos arquivos do Projeto.
	 * 
	 * @param idProjeto
	 * @param form
	 * @return response
	 * @author Rhavy Maia Guedes.
	 */
	@PermitAll
	@POST
	@Path("/upload/projeto/{idprojeto}/{tipoarquivoprojeto}")
	@Consumes(MediaType.MULTIPART_FORM_DATA + ";charset=UTF-8")
	@Produces("application/json")
	public Response uploadArquivoProjeto(
			@PathParam("idprojeto") String idProjeto,
			@PathParam("tipoarquivoprojeto") TipoArquivoProjeto tipoArquivoProjeto,
			@MultipartForm FileUploadForm form) {

		// Arquivo do projeto com extensão "pdf".
		ResponseBuilder builder = Response.status(Response.Status.NOT_MODIFIED);
		builder.expires(new Date());

		try {
			String nomeRealArquivo = form.getFileName();
			String extension = FilenameUtils.getExtension(nomeRealArquivo);

			if (extension.equalsIgnoreCase(FileUtil.PDF_FILE)) {

				// Nome do arquivo será o código do Projeto + CurrentTimeStamp
				// (milis).
				String nomeSistemaArquivo = FileUtil.getNomeSistemaArquivo(idProjeto,
						FileUtil.PDF_FILE);

				Projeto projeto = new Projeto();
				projeto.setIdProjeto(Integer.valueOf(idProjeto));

				Pessoa pessoa = new Pessoa();
				pessoa.setPessoaId(form.getIdPessoa());
							
				// Arquivo genérico.
				Arquivo arquivo = new Arquivo();				
				arquivo.setNomeRealArquivo(nomeRealArquivo);
				arquivo.setNomeSistemaArquivo(nomeSistemaArquivo);
				arquivo.setExtensaoArquivo(extension);
				arquivo.setCadastradorArquivo(pessoa);
				arquivo.setTipoArquivo(TipoArquivo.ARQUIVO_PROJETO);
				arquivo.setFile(form.getData());
				
				// Identificação do Arquivo de Projeto
				ArquivoProjeto arquivoProjeto = new ArquivoProjeto();
				arquivoProjeto.setArquivo(arquivo);
				arquivoProjeto.setProjeto(projeto);
				arquivoProjeto.setTipoArquivoProjeto(tipoArquivoProjeto);
				
				// Salvar no diretório
				FileUtil.writeFile(arquivo);				
				
				// Persistência do metadado do arquivo no banco de dados.	
				ArquivoProjetoDAO arquivoProjetoDAO = ArquivoProjetoDAO
						.getInstance();
				int idArquivoProjeto = arquivoProjetoDAO.insert(arquivoProjeto);

				if (idArquivoProjeto != BancoUtil.IDVAZIO) {
					
					arquivoProjeto.setIdArquivoProjeto(idArquivoProjeto);
					builder.status(Response.Status.OK).entity(arquivoProjeto);
					
				}

			} else {

				MapErroQManager me = new MapErroQManager(
						CodeErroQManager.FORMATO_ARQUIVO_INVALIDO);
				Erro erro = me.getErro();
				builder.status(Response.Status.NOT_ACCEPTABLE).entity(erro);
			}

		} catch (IOExceptionQManager | SQLExceptionQManager e) {

			Erro erro = e.getErro();
			builder.status(Response.Status.INTERNAL_SERVER_ERROR).entity(erro);
		}

		return builder.build();
	}

	/**
	 * Upload dos arquivos do Edital.
	 * 
	 * @param idEdital
	 * @param form
	 * @return response
	 * @author Rhavy Maia Guedes.
	 */
	@PermitAll
	@POST
	@Path("/upload/edital/{idedital}/{tipoarquivoedital}")
	@Consumes(MediaType.MULTIPART_FORM_DATA + ";charset=UTF-8")
	@Produces("application/json")
	public Response uploadArquivoEdital(
			@PathParam("idedital") String idEdital,
			@PathParam("tipoarquivoedital") TipoArquivoEdital tipoArquivoEdital,
			@MultipartForm FileUploadForm form) {

		// Arquivo do edital com extensão "pdf".
		ResponseBuilder builder = Response.status(Response.Status.NOT_MODIFIED);
		builder.expires(new Date());

		try {
			
			String nomeRealArquivo = form.getFileName();
			String extension = FilenameUtils.getExtension(nomeRealArquivo);

			if (extension.equalsIgnoreCase(FileUtil.PDF_FILE)) {

				// Nome do arquivo será o código do Edital + CurrentTimeStamp
				// (milis).
				String nomeSistemaArquivo = FileUtil.getNomeSistemaArquivo(
						idEdital, FileUtil.PDF_FILE);

				Edital edital = new Edital();
				edital.setIdEdital(Integer.valueOf(idEdital));

				Pessoa pessoa = new Pessoa();
				pessoa.setPessoaId(form.getIdPessoa());

				// Arquivo genérico.
				Arquivo arquivo = new Arquivo();
				arquivo.setNomeRealArquivo(nomeRealArquivo);
				arquivo.setNomeSistemaArquivo(nomeSistemaArquivo);
				arquivo.setExtensaoArquivo(extension);
				arquivo.setCadastradorArquivo(pessoa);
				arquivo.setTipoArquivo(TipoArquivo.ARQUIVO_EDITAL);
				arquivo.setFile(form.getData());

				// Identificação do Arquivo de Edital
				ArquivoEdital arquivoEdital = new ArquivoEdital();
				arquivoEdital.setArquivo(arquivo);
				arquivoEdital.setEdital(edital);
				arquivoEdital.setTipoArquivoEdital(tipoArquivoEdital);

				// Salvar no diretório
				FileUtil.writeFile(arquivo);

				// Persistência do metadado do arquivo no banco de dados.
				ArquivoEditalDAO arquivoEditalDAO = ArquivoEditalDAO
						.getInstance();
				int idArquivoEdital = arquivoEditalDAO.insert(arquivoEdital);

				// Verificação da geração do identificador do Arquivo de Edital;
				if (idArquivoEdital != BancoUtil.IDVAZIO) {

					arquivoEdital.setIdArquivoEdital(idArquivoEdital);
					builder.status(Response.Status.OK).entity(arquivoEdital);
				}

			} else {

				MapErroQManager me = new MapErroQManager(
						CodeErroQManager.FORMATO_ARQUIVO_INVALIDO);
				Erro erro = me.getErro();
				builder.status(Response.Status.NOT_ACCEPTABLE).entity(erro);
			}

		} catch (IOExceptionQManager | SQLExceptionQManager e) {

			Erro erro = e.getErro();
			builder.status(Response.Status.INTERNAL_SERVER_ERROR).entity(erro);
		}

		return builder.build();
	}
	
	/**
	 * Upload dos arquivos da Pessoa.
	 * 
	 * @param idPessoa
	 * @param form
	 * @return response
	 * @author Rhavy Maia Guedes.
	 */
	@PermitAll
	@POST
	@Path("/upload/pessoa/{idpessoa}/{tipoarquivopessoa}")
	@Consumes(MediaType.MULTIPART_FORM_DATA + ";charset=UTF-8")
	@Produces("application/json")
	public Response uploadArquivoPessoa(
			@PathParam("idpessoa") String idPessoa,
			@PathParam("tipoarquivopessoa") TipoArquivoPessoa tipoArquivoPessoa,
			@MultipartForm FileUploadForm form) {
		
		// Tipos de uploads: Pessoa.
		ResponseBuilder builder = Response.status(Response.Status.NOT_MODIFIED);
		builder.expires(new Date());

		return builder.build();
	}
	
	/**
	 * Upload dos arquivos da Participação em Projetos.
	 * 
	 * @param idPessoa
	 * @param form
	 * @return response
	 * @author Rhavy Maia Guedes.
	 */
	@PermitAll
	@POST
	@Path("/upload/participacao/{idparticipacao}/{tipoarquivoparticipacao}")
	@Consumes(MediaType.MULTIPART_FORM_DATA + ";charset=UTF-8")
	@Produces("application/json")
	public Response uploadArquivoParticipacao(
			@PathParam("idparticipacao") String idParticipacao,
			@PathParam("tipoarquivoparticipacao") TipoArquivoParticipacao tipoArquivoParticipacao,
			@MultipartForm FileUploadForm form) {

		// Arquivo da Participação com extensão "pdf".
		ResponseBuilder builder = Response.status(Response.Status.NOT_MODIFIED);
		builder.expires(new Date());

		try {

			String nomeRealArquivo = form.getFileName();
			String extension = FilenameUtils.getExtension(nomeRealArquivo);

			if (extension.equalsIgnoreCase(FileUtil.PDF_FILE)) {

				// Nome do arquivo será o código do Participação + CurrentTimeStamp
				// (milis).
				String nomeSistemaArquivo = FileUtil.getNomeSistemaArquivo(
						idParticipacao, FileUtil.PDF_FILE);

				Participacao participacao = new Participacao();
				participacao.setIdParticipacao(Integer.valueOf(idParticipacao));

				Pessoa pessoa = new Pessoa();
				pessoa.setPessoaId(form.getIdPessoa());

				// Arquivo genérico.
				Arquivo arquivo = new Arquivo();
				arquivo.setNomeRealArquivo(nomeRealArquivo);
				arquivo.setNomeSistemaArquivo(nomeSistemaArquivo);
				arquivo.setExtensaoArquivo(extension);
				arquivo.setCadastradorArquivo(pessoa);
				arquivo.setTipoArquivo(TipoArquivo.ARQUIVO_PARTICIPACAO);
				arquivo.setFile(form.getData());

				// Identificação do Arquivo de Participação.
				ArquivoParticipacao arquivoParticipacao = new ArquivoParticipacao();
				arquivoParticipacao.setArquivo(arquivo);
				arquivoParticipacao.setParticipacao(participacao);
				arquivoParticipacao.setTipoArquivoParticipacao(tipoArquivoParticipacao);

				// Salvar no diretório
				FileUtil.writeFile(arquivo);

				// Persistência do metadado do arquivo no banco de dados.
				ArquivoParticipacaoDAO arquivoParticipacaoDAO = ArquivoParticipacaoDAO
						.getInstance();
				int idArquivoParticipacao = arquivoParticipacaoDAO.insert(arquivoParticipacao);

				// Verificação da geração do identificador do Arquivo da Participação;
				if (idArquivoParticipacao != BancoUtil.IDVAZIO) {

					arquivoParticipacao.setIdArquivoParticipacao(idArquivoParticipacao);
					builder.status(Response.Status.OK).entity(arquivoParticipacao);
				}

			} else {

				MapErroQManager me = new MapErroQManager(
						CodeErroQManager.FORMATO_ARQUIVO_INVALIDO);
				Erro erro = me.getErro();
				builder.status(Response.Status.NOT_ACCEPTABLE).entity(erro);
			}

		} catch (IOExceptionQManager | SQLExceptionQManager e) {

			Erro erro = e.getErro();
			builder.status(Response.Status.INTERNAL_SERVER_ERROR).entity(erro);
		}

		return builder.build();
	}
}