package com.its.scc.Activities.Eksternal.BeforeCreateProve;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.its.scc.Activities.Eksternal.BeforeCreateProve.view.IEksternalBeforeCreateProveView;
import com.its.scc.R;

public class EksternalBeforeCreateProveActivity extends AppCompatActivity implements View.OnClickListener, IEksternalBeforeCreateProveView {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_eksternal_before_create_prove);
	}

	@Override
	public void onClick(View v) {

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

	@Override
	public void showDateDialog() {

	}

	@Override
	public void backPressed() {

	}
}
