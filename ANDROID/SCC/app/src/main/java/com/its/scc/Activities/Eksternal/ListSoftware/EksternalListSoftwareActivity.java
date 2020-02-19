package com.its.scc.Activities.Eksternal.ListSoftware;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.its.scc.Activities.Eksternal.ListSoftware.view.IEksternalListSoftwareView;
import com.its.scc.R;

public class EksternalListSoftwareActivity extends AppCompatActivity implements View.OnClickListener, IEksternalListSoftwareView {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_eksternal_list_software);
	}

	@Override
	public void onClick(View v) {

	}
}
