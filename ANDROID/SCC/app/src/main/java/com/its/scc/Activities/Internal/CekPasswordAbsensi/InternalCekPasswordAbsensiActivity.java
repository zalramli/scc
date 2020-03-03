package com.its.scc.Activities.Internal.CekPasswordAbsensi;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.its.scc.Activities.Internal.CekPasswordAbsensi.view.IInternalCekPasswordAbsensiView;
import com.its.scc.R;

public class InternalCekPasswordAbsensiActivity extends AppCompatActivity implements View.OnClickListener, IInternalCekPasswordAbsensiView {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_internal_cek_password_absensi);
	}

	@Override
	public void onClick(View v) {

	}
}
