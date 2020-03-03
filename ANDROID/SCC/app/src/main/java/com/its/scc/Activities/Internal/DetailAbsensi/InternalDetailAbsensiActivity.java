package com.its.scc.Activities.Internal.DetailAbsensi;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.its.scc.Activities.Internal.DetailAbsensi.presenter.IInternalDetailAbsensiPresenter;
import com.its.scc.Activities.Internal.DetailAbsensi.view.IInternalDetailAbsensiView;
import com.its.scc.Adapters.AdapterListAbsensiAnggota;
import com.its.scc.Models.Internal;
import com.its.scc.R;

import java.util.ArrayList;

public class InternalDetailAbsensiActivity extends AppCompatActivity implements View.OnClickListener, IInternalDetailAbsensiView {

	public static final String EXTRA_ID_ABSENSI = "EXTRA_ID_ABSENSI";
	public static final String EXTRA_ID_INTERNAL = "EXTRA_ID_INTERNAL";
	public static final String EXTRA_JUDUL_ABSENSI = "EXTRA_JUDUL_ABSENSI";
	public static final String EXTRA_TGL_ABSENSI = "EXTRA_TGL_ABSENSI";
	public static final String EXTRA_JAM_MULAI = "EXTRA_JAM_MULAI";
	public static final String EXTRA_JAM_SELESAI = "EXTRA_JAM_SELESAI";
	public static final String EXTRA_STATUS_ABSENSI = "EXTRA_STATUS_ABSENSI";
	public static final String EXTRA_KATA_SANDI = "EXTRA_KATA_SANDI";

	String id_absensi, id_internal, judul_absensi, tgl_absensi, jam_mulai, jam_selesai, status_absensi, kata_sandi;

//	IInternalDetailAbsensiPresenter internalDetailAbsensiPresenter;
//
//	private AdapterListAbsensiAnggota adapterListAbsensiAnggota;
//	private RecyclerView recyclerView;
//
//	Toolbar toolbar;
//
//	private SwipeRefreshLayout swipeRefreshLayout;
//
//	TextView tvNamaMateri, tvDetailJadwal, tvTanggalProve, tvKodeProve, tvKataSandi, tvNamaInternal, tvStatusProve, tvRating, tvKeluar;
//
//	ImageView ivKeluar, ivRating;
//
//	CardView cvItemAdapterListProve;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_internal_detail_absensi);

		id_absensi = getIntent().getStringExtra(EXTRA_ID_ABSENSI);
		id_internal = getIntent().getStringExtra(EXTRA_ID_INTERNAL);
		judul_absensi = getIntent().getStringExtra(EXTRA_JUDUL_ABSENSI);
		tgl_absensi = getIntent().getStringExtra(EXTRA_TGL_ABSENSI);
		jam_mulai = getIntent().getStringExtra(EXTRA_JAM_MULAI);
		jam_selesai = getIntent().getStringExtra(EXTRA_JAM_SELESAI);
		status_absensi = getIntent().getStringExtra(EXTRA_STATUS_ABSENSI);
		kata_sandi = getIntent().getStringExtra(EXTRA_KATA_SANDI);
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
	public void onSetupListView(ArrayList<Internal> dataModelArrayList) {

	}

	@Override
	public void onSuccessMessage(String message) {

	}

	@Override
	public void onErrorMessage(String message) {

	}

	@Override
	public void backPressed() {

	}

	@Override
	public void keHalamanLain() {

	}
}
