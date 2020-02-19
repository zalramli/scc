package com.its.scc.Activities.Eksternal.BeforeOrderBS;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.its.scc.Activities.Eksternal.BeforeOrderBS.presenter.IEksternalBeforeOrderBSPresenter;
import com.its.scc.Activities.Eksternal.BeforeOrderBS.view.IEksternalBeforeOrderBSView;
import com.its.scc.R;

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

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_eksternal_before_order_bs);
	}

	@Override
	public void onClick(View v) {

	}
}
