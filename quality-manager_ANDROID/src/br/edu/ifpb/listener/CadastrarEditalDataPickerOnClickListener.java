package br.edu.ifpb.listener;

import android.view.View;
import android.view.View.OnClickListener;
import br.edu.ifpb.activity.CadastrarEditalActivity;

public class CadastrarEditalDataPickerOnClickListener implements
		OnClickListener {

	private CadastrarEditalActivity activity;

	public CadastrarEditalDataPickerOnClickListener(
			CadastrarEditalActivity activity) {
		this.activity = activity;
	}

	@Override
	public void onClick(View view) {
		if (view == this.activity.getEditTextInicioInscricoes()) {
			this.activity.getInicioInscricoesPickerDialog().show();
		} else if (view == this.activity.getEditTextFimInscricoes()) {
			this.activity.getFimInscricoesPickerDialog().show();
		} else if (view == this.activity.getEditTextPrazoRelatorioParcial()) {
			this.activity.getPrazoRelatorioParcialPickerDialog().show();
		} else if(view == this.activity.getEditTextPrazoRelatorioFinal()) {
			this.activity.getPrazoRelatorioFinalPickerDialog().show();
		}
	}

}
