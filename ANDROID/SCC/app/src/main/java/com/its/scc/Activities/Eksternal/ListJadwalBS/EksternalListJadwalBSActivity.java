package com.its.scc.Activities.Eksternal.ListJadwalBS;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.its.scc.Activities.Eksternal.ListJadwalBS.view.EksternalListJadwalBSView;
import com.its.scc.R;

public class EksternalListJadwalBSActivity extends AppCompatActivity implements View.OnClickListener , EksternalListJadwalBSView {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_eksternal_list_jadwal_bs);
	}

	@Override
	public void onClick(View v) {

	}
}
