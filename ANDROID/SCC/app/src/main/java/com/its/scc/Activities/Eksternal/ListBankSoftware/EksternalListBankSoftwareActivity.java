package com.its.scc.Activities.Eksternal.ListBankSoftware;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.its.scc.Activities.Eksternal.ListBankSoftware.view.IEksternalListBankSoftwareView;
import com.its.scc.R;

public class EksternalListBankSoftwareActivity extends AppCompatActivity implements View.OnClickListener, IEksternalListBankSoftwareView {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_eksternal_list_bank_software);
	}

	@Override
	public void onClick(View v) {

	}
}
