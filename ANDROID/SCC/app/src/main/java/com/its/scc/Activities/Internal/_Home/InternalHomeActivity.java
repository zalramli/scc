package com.its.scc.Activities.Internal._Home;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.its.scc.Activities.Internal._Home.view.IInternalHomeView;
import com.its.scc.R;

public class InternalHomeActivity extends AppCompatActivity implements View.OnClickListener, IInternalHomeView {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_internal_home);
	}

	@Override
	public void onClick(View v) {

	}

	@Override
	public void showDialog() {

	}

	@Override
	public void onSuccessMessage(String message) {

	}

	@Override
	public void onErrorMessage(String message) {

	}
}
