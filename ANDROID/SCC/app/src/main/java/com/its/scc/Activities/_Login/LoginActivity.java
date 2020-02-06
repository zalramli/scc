package com.its.scc.Activities._Login;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.its.scc.Activities.Eksternal._Home.EksternalHomeActivity;
import com.its.scc.Activities.Internal._Home.InternalHomeActivity;
import com.its.scc.Activities._Login.presenter.ILoginPresenter;
import com.its.scc.Activities._Login.presenter.LoginPresenter;
import com.its.scc.Activities._Login.view.ILoginView;
import com.its.scc.Activities._Pendaftaran.PendaftaranActivity;
import com.its.scc.Controllers.SessionManager;
import com.its.scc.R;

import java.util.HashMap;

import es.dmoral.toasty.Toasty;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener, ILoginView {

	ILoginPresenter loginPresenter;

	EditText edtUsername, edtPassword;
	Button btn_login;
	Toolbar toolbar;

	CardView cvLinkDaftar;

	SessionManager sessionManager;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);

		loginPresenter = new LoginPresenter(this, this);

		edtUsername = findViewById(R.id.edt_username);
		edtPassword = findViewById(R.id.edt_password);
		btn_login = findViewById(R.id.btn_login);

		cvLinkDaftar = findViewById(R.id.cv_link_daftar);

		initActionBar();

		// logic jika sudah login
		sessionManager = new SessionManager(this);
		boolean status_login = sessionManager.getStatusLogin();
		if (status_login) {
			HashMap<String, String> user = sessionManager.getDataUser();
			String hakAkses = user.get(sessionManager.HAK_AKSES);

			Intent intent = new Intent();

			if (hakAkses.equals("internal")) {
				intent = new Intent(getApplicationContext(), InternalHomeActivity.class);
			} else if (hakAkses.equals("eksternal")) {
				intent = new Intent(getApplicationContext(), EksternalHomeActivity.class);
			}
		}

		btn_login.setOnClickListener(this);
		cvLinkDaftar.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		if (v.getId() == R.id.btn_login) {
			boolean isEmpty = false;

			String inputUsername = edtUsername.getText().toString().trim();
			String inputPassword = edtPassword.getText().toString().trim();

			if (TextUtils.isEmpty(inputUsername)) {
				isEmpty = true;
				edtUsername.setError("Isi Username Dengan Benar");
			} else if (TextUtils.isEmpty(inputPassword)) {
				isEmpty = true;
				edtPassword.setError("Isi Password Dengan Benar");
			}

			if (!isEmpty) {
				loginPresenter.onLogin(inputUsername, inputPassword);
			}
		}

		if (v.getId() == R.id.cv_link_daftar) {
			Intent intent = new Intent(getApplicationContext(), PendaftaranActivity.class);
			startActivity(intent);
		}
	}

	@Override
	public void initActionBar() {
		setSupportActionBar(toolbar);
		if (getSupportActionBar() != null) {
			getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		}
	}

	@Override
	public void onSuccessMessage(String message) {
		Toasty.success(this, message, Toast.LENGTH_SHORT).show();
	}

	@Override
	public void onErrorMessage(String message) {
		Toasty.error(this, message, Toast.LENGTH_SHORT).show();
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {

			case android.R.id.home:
				onBackPressed();
				break;
		}
		return true;
	}
}
