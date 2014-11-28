package br.edu.ifpb.util;

import java.text.NumberFormat;
import java.util.Locale;

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

public abstract class Mascara {

	public static String unmask(String s) {
		return s.replaceAll("[.]", "").replaceAll("[-]", "")
				.replaceAll("[/]", "").replaceAll("[(]", "")
				.replaceAll("[)]", "").replaceAll("[R$]", "")
				.replaceAll("[$]", "").replaceAll("[,]", "");
	}

	public static TextWatcher insert(final String mask, final EditText ediTxt) {
		return new TextWatcher() {
			boolean isUpdating;
			String old = "";

			public void onTextChanged(CharSequence s, int start, int before,
					int count) {
				String str = Mascara.unmask(s.toString());
				String mascara = "";
				if (isUpdating) {
					old = str;
					isUpdating = false;
					return;
				}
				int i = 0;
				for (char m : mask.toCharArray()) {
					if (m != '#' && str.length() > old.length()) {
						mascara += m;
						continue;
					}
					try {
						mascara += str.charAt(i);
					} catch (Exception e) {
						break;
					}
					i++;
				}
				isUpdating = true;
				ediTxt.setText(mascara);
				ediTxt.setSelection(mascara.length());
			}

			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
			}

			public void afterTextChanged(Editable s) {
			}
		};
	}

	public static TextWatcher money(final EditText mText) {
		return new TextWatcher() {
			boolean isUpdating;
			NumberFormat mFormatter = NumberFormat
					.getCurrencyInstance(new Locale("pt", "BR"));

			@Override
			public void onTextChanged(CharSequence s, int start, int before,
					int count) {
				if (isUpdating) {
					isUpdating = false;
					return;
				}
				isUpdating = true;
				String str = s.toString();
				str = str.replaceAll("[^\\d]", "");

				boolean hasMask = ((str.indexOf("R$") > -1 || str.indexOf("$") > -1) && (str
						.indexOf(".") > -1 || str.indexOf(",") > -1));
				// Verificamos se existe máscara
				if (hasMask) {
					// Retiramos a máscara.
					str = str.replaceAll("[R$]", "").replaceAll("[,]", "")
							.replaceAll("[.]", "");
				}

				try {
					str = mFormatter.format(Double.parseDouble(str) / 100);
					mText.setText(str);
					mText.setSelection(mText.getText().length());
				} catch (NumberFormatException e) {
					s = "";
				}

			}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
				// TODO Auto-generated method stub

			}

			@Override
			public void afterTextChanged(Editable s) {
				// TODO Auto-generated method stub

			}
		};

	}

}
