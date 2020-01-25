package com.its.scc.Activities.Eksternal._Home;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.its.scc.Activities.Eksternal._Home.view.IEksternalHomeView;
import com.its.scc.R;

public class EksternalHomeActivity extends AppCompatActivity implements View.OnClickListener, IEksternalHomeView {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_eksternal_home);
	}

	@Override
	public void onClick(View v) {

	}
}
