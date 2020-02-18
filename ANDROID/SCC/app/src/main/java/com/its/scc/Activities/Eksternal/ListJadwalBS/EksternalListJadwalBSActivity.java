package com.its.scc.Activities.Eksternal.ListJadwalBS;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.its.scc.Activities.Eksternal.ListJadwalBS.view.EksternalListJadwalBSView;
import com.its.scc.Models.JadwalBS;
import com.its.scc.R;

import java.util.ArrayList;

public class EksternalListJadwalBSActivity extends AppCompatActivity implements View.OnClickListener , EksternalListJadwalBSView {



	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_eksternal_list_jadwal_bs);
	}

	@Override
	public void onClick(View v) {

	}

	@Override
	public void initActionBar() {

	}

	@Override
	public void onSetupListView(ArrayList<JadwalBS> dataModelArrayList) {

	}

	@Override
	public void onSuccessMessage(String message) {

	}

	@Override
	public void onErrorMessage(String message) {

	}
}
