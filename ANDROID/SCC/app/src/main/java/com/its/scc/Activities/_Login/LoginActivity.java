package com.its.scc.Activities._Login;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.its.scc.Activities._Login.presenter.ILoginPresenter;
import com.its.scc.Activities._Login.presenter.LoginPresenter;
import com.its.scc.Activities._Login.view.ILoginView;
import com.its.scc.R;

import es.dmoral.toasty.Toasty;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener, ILoginView {

	ILoginPresenter loginPresenter;

	EditText edtUsername, edtPassword;
	Button btn_login;
	Toolbar toolbar;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);

		loginPresenter = new LoginPresenter(this, this);

		edtUsername = findViewById(R.id.edt_username);
		edtPassword = findViewById(R.id.edt_password);
		btn_login = findViewById(R.id.btn_login);

		initActionBar();

		btn_login.setOnClickListener(this);
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
