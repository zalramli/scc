package com.its.scc.Activities.Eksternal.BankSoftware;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.its.scc.Activities.Eksternal.BankSoftware.view.IEksternalBankSoftwareView;
import com.its.scc.R;

public class EksternalBankSoftwareActivity extends AppCompatActivity implements View.OnClickListener, IEksternalBankSoftwareView {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_eksternal_bank_software);
	}

	@Override
	public void onClick(View v) {

	}
}
