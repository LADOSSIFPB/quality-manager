package util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.InputStream;
import java.util.List;

import managedBean.GenericBean;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import net.sf.jasperreports.export.SimplePdfExporterConfiguration;

public class JasperGenerator<E> {

	private String realPath = GenericBean.getRealPath();

	private String jasperDirectory = "jasperreport";

	public InputStream getPdfPrintInputStream(String jasperFile, List<E> values)
			throws JRException {

		InputStream relatorioIS = null;
		ByteArrayOutputStream baos = new ByteArrayOutputStream();

		// Diretório e arquivo jasper.
		String arquivoJasper = realPath + jasperDirectory + File.separator
				+ jasperFile;

		JasperPrint print = JasperFillManager.fillReport(arquivoJasper, null,
				new JRBeanCollectionDataSource(values));

		// Gerar o stream para renderização no browser.
		JRPdfExporter exporter = new JRPdfExporter();
		exporter.setExporterInput(new SimpleExporterInput(print));
		exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(baos));
		SimplePdfExporterConfiguration configuration = new SimplePdfExporterConfiguration();
		exporter.setConfiguration(configuration);
		exporter.exportReport();

		relatorioIS = new ByteArrayInputStream(baos.toByteArray());

		return relatorioIS;
	}
}
