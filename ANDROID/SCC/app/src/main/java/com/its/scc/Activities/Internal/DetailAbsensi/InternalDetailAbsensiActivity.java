package com.its.scc.Activities.Internal.DetailAbsensi;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.its.scc.Activities.Internal.DetailAbsensi.view.IInternalDetailAbsensiView;
import com.its.scc.R;

public class InternalDetailAbsensiActivity extends AppCompatActivity implements View.OnClickListener, IInternalDetailAbsensiView {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_internal_detail_absensi);
	}

	@Override
	public void onClick(View v) {

	}
}
