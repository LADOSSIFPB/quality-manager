package br.edu.ifpb.util;

import android.app.Activity;

public abstract class GenericAlertDiolog {

	private Activity activity;

	public GenericAlertDiolog(Activity activity) {
		this.activity = activity;
	}

	public abstract void showAlertDialog();
	
	public Activity getActivity() {
		return activity;
	}

	public void setActivity(Activity activity) {
		this.activity = activity;
	}
}
