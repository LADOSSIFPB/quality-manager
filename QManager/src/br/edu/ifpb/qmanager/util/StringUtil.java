package br.edu.ifpb.qmanager.util;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import br.edu.ifpb.qmanager.excecao.QManagerSQLException;

public class StringUtil {

	public static double tirarMascaraOrcamento(String orcamento) {

		orcamento = orcamento.replace(".", "");
		orcamento = orcamento.replace(",", ".");

		Double orc = Double.parseDouble(orcamento);

		return orc;

	}

	public static String tirarMascaraCNPJ(String cnpj) {

		cnpj = cnpj.replace(".", "");
		cnpj = cnpj.replace("/", "");
		cnpj = cnpj.replace("-", "");

		return cnpj;

	}

	/**
	 * @author Felipe Nascimento
	 * @param data "dd/MM/yyyy"
	 * @return Date
	 * @throws QManagerSQLException
	 */
	public static Date converterStringEmDataSQL(String data)
			throws QManagerSQLException {
		SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
		java.util.Date dataUtil = null;

		String[] dma = data.split("/");
		String dataSaida = dma[2] + "-" + dma[1] + "-" + dma[0];

		try {
			dataUtil = f.parse(dataSaida);
		} catch (ParseException e) {
			throw new QManagerSQLException(666, "'" + data + "'");
		}

		Date dataJDBC = new Date(dataUtil.getTime());

		return dataJDBC;
	}

}
