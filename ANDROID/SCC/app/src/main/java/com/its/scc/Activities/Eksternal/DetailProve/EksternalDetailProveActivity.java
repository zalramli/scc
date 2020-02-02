package com.its.scc.Activities.Eksternal.DetailProve;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.its.scc.Activities.Eksternal.DetailProve.view.IEksternalDetailProveView;
import com.its.scc.R;

public class EksternalDetailProveActivity extends AppCompatActivity implements IEksternalDetailProveView {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_eksternal_detail_prove);
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
	public void backPressed() {

	}
}
