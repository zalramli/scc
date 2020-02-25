package com.its.scc.Activities.Internal.Absensi;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.its.scc.Activities.Internal.Absensi.view.IInternalListAbsensiView;
import com.its.scc.R;

public class InternalListAbsensiActivity extends AppCompatActivity implements View.OnClickListener , IInternalListAbsensiView {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_internal_list_absensi);
	}

	@Override
	public void onClick(View v) {
		
	}
}
