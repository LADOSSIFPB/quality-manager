package br.edu.ifpb.qmanager.service;

import java.util.Date;

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

import br.edu.ifpb.qmanager.dao.ArquivoProjetoDAO;
import br.edu.ifpb.qmanager.entidade.ArquivoProjeto;
import br.edu.ifpb.qmanager.entidade.CodeErroQManager;
import br.edu.ifpb.qmanager.entidade.Erro;
import br.edu.ifpb.qmanager.entidade.MapErroQManager;
import br.edu.ifpb.qmanager.entidade.Pessoa;
import br.edu.ifpb.qmanager.entidade.Projeto;
import br.edu.ifpb.qmanager.excecao.IOExceptionQManager;
import br.edu.ifpb.qmanager.excecao.SQLExceptionQManager;
import br.edu.ifpb.qmanager.form.FileUploadForm;
import br.edu.ifpb.qmanager.util.FileUtil;

/**
 * Serviço de upload de arquivo.
 * 
 * @author Rhavy
 *
 */
@Path("/arquivo")
public class UploadFileQManager {
	
	@POST
	@Path("/upload/projeto/{idprojeto}")
	@Consumes(MediaType.MULTIPART_FORM_DATA + ";charset=UTF-8")
	@Produces("application/json")
	public Response uploadArquivoProjeto(@PathParam("idprojeto") String idProjeto, 
			@MultipartForm FileUploadForm form) {

		// Tipos de uploads: projeto (pdf).
		ResponseBuilder builder = Response.status(Response.Status.NOT_MODIFIED);
		builder.expires(new Date());
		
		try {
			String nomeRealArquivo = form.getFileName();
			String extension = FilenameUtils.getExtension(nomeRealArquivo);
			
			if (extension.equalsIgnoreCase(FileUtil.PDF_FILE)) {
				
				// Nome do arquivo será o código do Projeto + CurrentTimeStamp (milis).
				String nomeSistemaArquivo = getNomeSistemaArquivo(idProjeto, 
						FileUtil.PDF_FILE);
				
				Projeto projeto = new Projeto();
				projeto.setIdProjeto(Integer.valueOf(idProjeto));
				
				Pessoa pessoa = new Pessoa();
				pessoa.setPessoaId(form.getIdPessoa());
				
				ArquivoProjeto arquivoProjeto = new ArquivoProjeto();
				
				// Persistir nome real do arquivo.
				arquivoProjeto.setNomeRealArquivo(nomeRealArquivo);				
				arquivoProjeto.setNomeSistemaArquivo(nomeSistemaArquivo);
				arquivoProjeto.setExtensaoArquivo(extension);
				arquivoProjeto.setProjeto(projeto);
				arquivoProjeto.setPessoaUploader(pessoa);
				arquivoProjeto.setTipoArquivo(form.getTipoArquivo());
				
				FileUtil.writeFile(form.getData(), nomeSistemaArquivo);
				
				ArquivoProjetoDAO arquivoProjetoDAO = ArquivoProjetoDAO.getInstance();
				int idArquivoProjeto = arquivoProjetoDAO.insert(arquivoProjeto);
				
				arquivoProjeto.setIdArquivoProjeto(idArquivoProjeto);
				builder.status(Response.Status.OK).entity(arquivoProjeto);
			
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

	private String getNomeSistemaArquivo(String idProjeto, String extension) {
		Date agora = new Date();
		String nomeSistemaArquivo = idProjeto + "-" + Long.toString(
				agora.getTime()) + "." + FileUtil.PDF_FILE;
		return nomeSistemaArquivo;
	}
}