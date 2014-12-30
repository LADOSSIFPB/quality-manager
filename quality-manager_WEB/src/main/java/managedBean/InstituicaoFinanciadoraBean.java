package managedBean;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.Response;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import br.edu.ifpb.qmanager.entidade.Erro;
import br.edu.ifpb.qmanager.entidade.InstituicaoFinanciadora;
import br.edu.ifpb.qmanager.util.IntegerUtil;

@ManagedBean
@RequestScoped
public class InstituicaoFinanciadoraBean extends GenericBean implements
		BeanInterface, Serializable {

	private static final long serialVersionUID = -3749706911495000581L;

	private InstituicaoFinanciadora instituicaoFinanciadora = new InstituicaoFinanciadora();

	private List<InstituicaoFinanciadora> instituicoesFinanciadoras;

	public InstituicaoFinanciadora getInstituicaoFinanciadora() {
		return instituicaoFinanciadora;
	}

	public void setInstituicaoFinanciadora(
			InstituicaoFinanciadora instituicaoFinanciadora) {
		this.instituicaoFinanciadora = instituicaoFinanciadora;
	}

	public String createEdit(InstituicaoFinanciadora instituicao) {

		if (instituicao == null) {
			GenericBean
					.sendRedirect(PathRedirect.cadastrarInstituicaoFinanciadora);

		} else {

			IntegerUtil integerUtil = new IntegerUtil(
					instituicao.getIdInstituicaoFinanciadora());

			Response response = service.consultarInstituicao(integerUtil);

			this.instituicaoFinanciadora = response
					.readEntity(new GenericType<InstituicaoFinanciadora>() {
					});

		}

		return PathRedirect.cadastrarInstituicaoFinanciadora;
	}

	@Override
	public void save() {

		if (instituicaoFinanciadora.getIdInstituicaoFinanciadora() == 0) {
			PessoaBean pessoaBean = getPessoaBean(FacesContext
					.getCurrentInstance());

			instituicaoFinanciadora.getGestor().setPessoaId(
					pessoaBean.getPessoaId());

			Response mensagem = service
					.cadastrarInstituicao(instituicaoFinanciadora);
		} else {
			Response mensagem = service
					.editarInstituicaoFinanciadora(instituicaoFinanciadora);
		}
	}

	public List<InstituicaoFinanciadora> getInstituicoesFinanciadoras() {

		Response response = service.consultarInstituicoes();

		// TODO: em caso de erro, redirecionar para página de erro
		if (response.getStatus() != 200) {
			Erro qme = response.readEntity(new GenericType<Erro>() {
			});

			// utilizar essa mensagem pro cliente
			qme.getMensagem();
			qme.getCodigo(); // esse código é só pra você saber que existe esse
								// campo

		}

		this.instituicoesFinanciadoras = response
				.readEntity(new GenericType<ArrayList<InstituicaoFinanciadora>>() {
				});

		return instituicoesFinanciadoras;
	}

	public void setInstituicoesFinanciadoras(
			List<InstituicaoFinanciadora> instituicoesFinanciadoras) {
		this.instituicoesFinanciadoras = instituicoesFinanciadoras;
	}

	public String detalhesInstituicao(
			InstituicaoFinanciadora instituicaoFinanciadora) {

		this.instituicaoFinanciadora = instituicaoFinanciadora;

		return PathRedirect.exibirInstituicaoFinanciadora;

	}

	public String reportAll(
			List<InstituicaoFinanciadora> instituicoesFinanciadoras)
			throws JRException, IOException {

		JasperReport report = JasperCompileManager
				.compileReport("E:/java/desenvolvimento/workspace/quality-manager_WEB/WebContent/resources/relatorio/instituicoesFinanciadoras.jrxml");
		JasperPrint print = JasperFillManager.fillReport(report, null,
				new JRBeanCollectionDataSource(instituicoesFinanciadoras));
		JasperExportManager
				.exportReportToPdfFile(
						print,
						"E:/java/desenvolvimento/workspace/quality-manager_WEB/WebContent/resources/relatorio/RelatorioInstituicaoFinanciadora.pdf");

		System.out.println("Relatório gerado.");
		
		return PathRedirect.exibirRelatorioInstituicaoFinanciadora;
	}
}
