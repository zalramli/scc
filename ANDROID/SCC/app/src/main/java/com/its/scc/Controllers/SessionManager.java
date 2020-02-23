package com.its.scc.Controllers;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.widget.Toast;

import com.its.scc.Activities._Login.LoginActivity;
import com.its.scc.DB.DatabaseHelper;

import java.util.HashMap;

import es.dmoral.toasty.Toasty;

public class SessionManager {
	public Context context;
	SharedPreferences sharedPreferences;
	public SharedPreferences.Editor editor;
	int PRIVATE_MODE = 0;
	private static final String PREF_NAME = "LOGIN";

	public static final String STATUS_LOGIN = "STATUS_LOGIN";
	public static final String ID_USER = "ID_USER";
	public static final String NAMA = "NAMA";
	public static final String USERNAME = "USERNAME";
	public static final String HAK_AKSES = "HAK_AKSES";

	DatabaseHelper databaseHelper;

	public SessionManager(Context context) {
		this.context = context;

		sharedPreferences = context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
		editor = sharedPreferences.edit();

		databaseHelper = new DatabaseHelper(context);
	}

	public void setSessionLogin(String id_user, String nama, String username, String hakAkses) {
		editor.putBoolean(STATUS_LOGIN, true);
		editor.putString(ID_USER, id_user);
		editor.putString(NAMA, nama);
		editor.putString(USERNAME, username);
		editor.putString(HAK_AKSES, hakAkses);
		editor.apply();

		databaseHelper.deleteAllUser(); // delete all data in table

		long id = databaseHelper.insertInfoUser(
			"" + username
		);
	}

	public boolean getStatusLogin() {
		return sharedPreferences.getBoolean(STATUS_LOGIN, false);
	}

	public HashMap<String, String> getDataUser() {
		HashMap<String, String> user = new HashMap<>();

		String data_default = "Data Kosong";
		user.put(ID_USER, sharedPreferences.getString(ID_USER, data_default));
		user.put(NAMA, sharedPreferences.getString(NAMA, data_default));
		user.put(USERNAME, sharedPreferences.getString(USERNAME, data_default));
		user.put(HAK_AKSES, sharedPreferences.getString(HAK_AKSES, data_default));

		return user;
	}

	public void checkLogin() {
		if (!this.getStatusLogin()) {
			logout();

			String message = "Pastikan Anda Login Dahulu";
			Toasty.error(context, message, Toast.LENGTH_SHORT).show();
		}
	}

	public void logout() {
		editor.clear();
		editor.commit();

		databaseHelper.deleteAllUser(); // delete all data in table

		Intent intent = new Intent(context, LoginActivity.class);
		intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		context.startActivity(intent);
	}
}
