package br.edu.ifpb.qmanager.util;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

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

	public static String criptografar(String valorPlano)
			throws NoSuchAlgorithmException, UnsupportedEncodingException {

		MessageDigest algorithm = MessageDigest.getInstance("SHA-256");
		byte messageDigest[] = algorithm.digest(valorPlano.getBytes("UTF-8"));

		StringBuilder hexString = new StringBuilder();

		for (byte b : messageDigest) {
			hexString.append(String.format("%02X", 0xFF & b));
		}

		String senha = hexString.toString();

		return senha;
	}

	public static String dateSQLFormat(String data) {

		String modifiedDate = "";
		String[] date = data.split("/");

		for (int i = date.length - 1; i >= 0; i--) {
			if (i > 0)
				modifiedDate = modifiedDate + date[i] + "-";
			else
				modifiedDate = modifiedDate + date[i];
		}

		return modifiedDate;
	}

	public static void main(String[] args) {

		System.out.println(dateSQLFormat("01/02/2015"));

		/*
		 * String marcia = "Mg123456%"; String eri = "Ej123456%"; String rhavy =
		 * "Rg123456%"; String felipe = "Fn123456%"; String elaine =
		 * "Ec123456%"; String adriana = "Al123456%"; String divanira =
		 * "Df123456%";
		 * 
		 * try {
		 * 
		 * System.out.println("Senha Marcia = " + criptografar(marcia));
		 * System.out.println("Senha Eri = " + criptografar(eri));
		 * System.out.println("Senha Rhavy = " + criptografar(rhavy));
		 * System.out.println("Senha Felipe = " + criptografar(felipe));
		 * System.out.println("Senha Elaine = " + criptografar(elaine));
		 * System.out.println("Senha Adriana = " + criptografar(adriana));
		 * System.out.println("Senha Divanira = " + criptografar(divanira));
		 * 
		 * } catch (NoSuchAlgorithmException e) { e.printStackTrace(); } catch
		 * (UnsupportedEncodingException e) { e.printStackTrace(); }
		 */

	}

}
