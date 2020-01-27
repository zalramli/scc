package com.its.scc.Activities.Eksternal.ListMateri;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.its.scc.Activities.Eksternal.ListMateri.view.IEksternalListMateriView;
import com.its.scc.R;

public class EksternalListMateriActivity extends AppCompatActivity implements View.OnClickListener, IEksternalListMateriView {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_eksternal_list_materi);
	}

	@Override
	public void onClick(View v) {

	}
}
