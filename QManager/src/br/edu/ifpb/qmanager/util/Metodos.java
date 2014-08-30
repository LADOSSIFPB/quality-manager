package br.edu.ifpb.qmanager.util;

public class Metodos {

	public static double tirarMascara(String orcamento) {

		orcamento = orcamento.replace(".", "");
		orcamento = orcamento.replace(",", ".");

		Double orc = Double.parseDouble(orcamento);

		return orc;
	}

}
