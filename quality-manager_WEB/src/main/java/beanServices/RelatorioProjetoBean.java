package beanServices;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import managedBean.GenericBean;
import net.sf.jasperreports.engine.JRException;

import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

import util.JasperGenerator;
import br.edu.ifpb.qmanager.entidade.Pessoa;

@ManagedBean
@RequestScoped
public class RelatorioProjetoBean {

	public StreamedContent gerarRelatorio() {

		InputStream relatorio = null;
		List<Pessoa> pessoas = new ArrayList<Pessoa>();
		String jasperFile = "nomeDoRelatorio.jasper";

		try {

			// TODO: Consultar pessoas envolvidas nos projetos e popular a lista
			// de pessoas.

			// Gerar reltório pdf.
			JasperGenerator<Pessoa> jasper = new JasperGenerator<Pessoa>();

			// Informar qual o arquivo jasper para renderizar o relatório.
			// Enviar a lista contendo os objetos com os valores para
			// renderização.
			relatorio = jasper.getPdfPrintInputStream(jasperFile, pessoas);

		} catch (JRException e) {
			GenericBean.setMessage("erro.geracaoRelatorio",
					FacesMessage.SEVERITY_ERROR);
		}

		long timeMillisId = System.currentTimeMillis();

		// Stream retornado para o browser.
		return new DefaultStreamedContent(relatorio, "application/pdf",
				"arquivo_" + timeMillisId + ".pdf");
	}
}
