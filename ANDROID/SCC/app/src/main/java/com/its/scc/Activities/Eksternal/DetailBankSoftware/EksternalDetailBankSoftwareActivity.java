package com.its.scc.Activities.Eksternal.DetailBankSoftware;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.its.scc.Activities.Eksternal.DetailBankSoftware.view.IEksternalDetailBankSoftwareView;
import com.its.scc.Models.Software;
import com.its.scc.R;

import java.util.ArrayList;

public class EksternalDetailBankSoftwareActivity extends AppCompatActivity implements View.OnClickListener, IEksternalDetailBankSoftwareView {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_eksternal_detail_bank_software);
	}

	@Override
	public void onClick(View v) {

	}

	@Override
	public void initActionBar() {

	}

	@Override
	public void setNilaiDefault() {

	}

	@Override
	public void onSetupListView(ArrayList<Software> dataModelArrayList) {

	}

	@Override
	public void onSuccessMessage(String message) {

	}

	@Override
	public void onErrorMessage(String message) {

	}

	@Override
	public void showDialogBatal() {

	}

	@Override
	public void showDialogSelesai() {

	}

	@Override
	public void backPressed() {

	}

	@Override
	public void keHalamanLain() {

	}
}
