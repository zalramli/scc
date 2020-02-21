package com.its.scc.Activities.Eksternal.DetailBankSoftware;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.its.scc.Activities.Eksternal.DetailBankSoftware.view.IEksternalDetailBankSoftwareView;
import com.its.scc.R;

public class EksternalDetailBankSoftwareActivity extends AppCompatActivity implements View.OnClickListener, IEksternalDetailBankSoftwareView {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_eksternal_detail_bank_software);
	}

	@Override
	public void onClick(View v) {

	}
}
