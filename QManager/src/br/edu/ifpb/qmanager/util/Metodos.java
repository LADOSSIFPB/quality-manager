package br.edu.ifpb.qmanager.util;

public class Metodos {

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

}
