package com.its.scc.Activities.Eksternal.ListProve;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.its.scc.Activities.Eksternal.ListProve.view.IEksternalListProveView;
import com.its.scc.Models.Prove;
import com.its.scc.R;

import java.util.ArrayList;

public class EksternalListProveActivity extends AppCompatActivity implements View.OnClickListener, IEksternalListProveView {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_eksternal_list_prove);
	}

	@Override
	public void onClick(View v) {

	}

	@Override
	public void initActionBar() {

	}

	@Override
	public void onSetupListView(ArrayList<Prove> dataModelArrayList) {

	}

	@Override
	public void onSuccessMessage(String message) {

	}

	@Override
	public void onErrorMessage(String message) {

	}
}
