package com.its.xcc.Activities._Login;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.xcc.R;
import com.its.xcc.Activities._Login.presenter.ILoginActivityPresenter;
import com.its.xcc.Activities._Login.presenter.LoginActivityPresenter;
import com.its.xcc.Activities._Login.view.ILoginActivityView;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener, ILoginActivityView {

	ILoginActivityPresenter loginActivityPresenter;

	EditText edt_username, edt_password;
	Button btn_login;

	Toolbar toolbar;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);

		loginActivityPresenter = new LoginActivityPresenter(this, this);

		edt_username = findViewById(R.id.edt_username);
		edt_password = findViewById(R.id.edt_password);
		btn_login = findViewById(R.id.btn_login);

		initActionBar();

		btn_login.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		if (v.getId() == R.id.btn_login) {

		}
	}

	@Override
	public void initActionBar() {

	}

	@Override
	public void onSuccessMessage(String message) {

	}

	@Override
	public void onErrorMessage(String message) {

	}
}
