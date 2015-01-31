package br.edu.ifpb.util;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import br.edu.ifpb.activity.LoginActivity;

public class SessionManager {

	SharedPreferences sharedPreferences;
	Editor editor;
	Context context;
	int PRIVATE_MODE = 0;

	private static final String SHAREDPREFERENCES_NAME = "SessionUserQManager";
	private static final String IS_LOGIN = "IsLoggedIn";
	public static final String KEY_ID = "id";

	public SessionManager(Context context) {
		this.context = context;
		sharedPreferences = context.getSharedPreferences(
				SHAREDPREFERENCES_NAME, PRIVATE_MODE);
		editor = sharedPreferences.edit();
	}

	public void createLoginSession(int id) {

		editor.putBoolean(IS_LOGIN, true);
		editor.putInt(KEY_ID, id);
		editor.commit();
	}

	public int getUserDetails() {
		int user;
		// user id
		user = sharedPreferences.getInt(KEY_ID, 0);

		return user;
	}

	public void logoutUser() {
		editor.clear();
		editor.commit();

		Intent i = new Intent(context, LoginActivity.class);
		i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

		// Adicionar nova Flag para iniciar nova Activity
		i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

		context.startActivity(i);
	}

	public boolean checkLogin() {
		return sharedPreferences.getBoolean(IS_LOGIN, false);
	}
}