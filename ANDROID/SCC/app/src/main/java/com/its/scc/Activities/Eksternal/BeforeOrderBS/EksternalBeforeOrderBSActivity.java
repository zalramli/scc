package com.its.scc.Activities.Eksternal.BeforeOrderBS;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.its.scc.Activities.Eksternal.BeforeOrderBS.presenter.IEksternalBeforeOrderBSPresenter;
import com.its.scc.Activities.Eksternal.BeforeOrderBS.view.IEksternalBeforeOrderBSView;
import com.its.scc.R;

import es.dmoral.toasty.Toasty;

public class EksternalBeforeOrderBSActivity extends AppCompatActivity implements View.OnClickListener, IEksternalBeforeOrderBSView {


	public static final String EXTRA_ID_JADWAL_BS = "EXTRA_ID_JADWAL_BS";
	public static final String EXTRA_ID_INTERNAL = "EXTRA_ID_INTERNAL";
	public static final String EXTRA_HARI = "EXTRA_HARI";
	public static final String EXTRA_JAM_MULAI = "EXTRA_JAM_MULAI";
	public static final String EXTRA_JAM_SELESAI = "EXTRA_JAM_SELESAI";
	public static final String EXTRA_STATUS_BOOKING = "EXTRA_STATUS_BOOKING";
	public static final String EXTRA_STATUS_AKTIF = "EXTRA_STATUS_AKTIF";

	String id_jadwal_bs, id_internal, hari, jam_mulai, jam_selesai, status_booking, status_aktif;

	IEksternalBeforeOrderBSPresenter eksternalBeforeOrderBSPresenter;

	Toolbar toolbar;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_eksternal_before_order_bs);

		id_jadwal_bs = getIntent().getStringExtra(EXTRA_ID_JADWAL_BS);
		id_internal = getIntent().getStringExtra(EXTRA_ID_INTERNAL);
		hari = getIntent().getStringExtra(EXTRA_HARI);
		jam_mulai = getIntent().getStringExtra(EXTRA_JAM_MULAI);
		jam_selesai = getIntent().getStringExtra(EXTRA_JAM_SELESAI);
		status_booking = getIntent().getStringExtra(EXTRA_STATUS_BOOKING);
		status_aktif = getIntent().getStringExtra(EXTRA_STATUS_AKTIF);

		onSuccessMessage(id_jadwal_bs + id_internal + hari + jam_mulai + jam_selesai + status_booking + status_aktif);

		toolbar = findViewById(R.id.toolbar);
		initActionBar();
	}

	@Override
	public void onClick(View v) {

	}

	@Override
	public void initActionBar() {
		setSupportActionBar(toolbar);
		if (getSupportActionBar() != null) {
			getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		}
	}

	@Override
	public void setNilaiDefault() {

	}

	@Override
	public void onSuccessMessage(String message) {
		Toasty.success(this, message, Toast.LENGTH_SHORT).show();
	}

	@Override
	public void onErrorMessage(String message) {
		Toasty.error(this, message, Toast.LENGTH_SHORT).show();
	}

	@Override
	public void showDateDialog() {

	}

	@Override
	public void showDialog() {

	}

	@Override
	public void backPressed() {

	}

	@Override
	public void keHalamanLain() {

	}
}
