package com.its.scc.Activities.Internal.CekPasswordAbsensi;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.its.scc.Activities.Internal.CekPasswordAbsensi.view.IInternalCekPasswordAbsensiView;
import com.its.scc.R;

public class InternalCekPasswordAbsensiActivity extends AppCompatActivity implements View.OnClickListener, IInternalCekPasswordAbsensiView {

	public static final String EXTRA_ID_ABSENSI = "EXTRA_ID_ABSENSI";
	public static final String EXTRA_ID_INTERNAL = "EXTRA_ID_INTERNAL";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_internal_cek_password_absensi);
	}

	@Override
	public void onClick(View v) {

	}
}
