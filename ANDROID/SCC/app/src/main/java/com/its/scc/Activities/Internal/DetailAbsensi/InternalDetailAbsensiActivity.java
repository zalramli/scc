package com.its.scc.Activities.Internal.DetailAbsensi;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.os.Bundle;
import android.os.Handler;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.its.scc.Activities.Internal.DetailAbsensi.presenter.IInternalDetailAbsensiPresenter;
import com.its.scc.Activities.Internal.DetailAbsensi.presenter.InternalDetailAbsensiPresenter;
import com.its.scc.Activities.Internal.DetailAbsensi.view.IInternalDetailAbsensiView;
import com.its.scc.Adapters.AdapterListAbsensiAnggota;
import com.its.scc.Models.Internal;
import com.its.scc.R;

import java.util.ArrayList;

import es.dmoral.toasty.Toasty;

public class InternalDetailAbsensiActivity extends AppCompatActivity implements View.OnClickListener, IInternalDetailAbsensiView {

	public static final String EXTRA_ID_ABSENSI = "EXTRA_ID_ABSENSI";
	// public static final String EXTRA_ID_INTERNAL = "EXTRA_ID_INTERNAL";
	public static final String EXTRA_JUDUL_ABSENSI = "EXTRA_JUDUL_ABSENSI";
	public static final String EXTRA_TGL_ABSENSI = "EXTRA_TGL_ABSENSI";
	public static final String EXTRA_JAM_MULAI = "EXTRA_JAM_MULAI";
	public static final String EXTRA_JAM_SELESAI = "EXTRA_JAM_SELESAI";
	public static final String EXTRA_STATUS_ABSENSI = "EXTRA_STATUS_ABSENSI";
	public static final String EXTRA_KATA_SANDI = "EXTRA_KATA_SANDI";

	String id_absensi, id_internal, judul_absensi, tgl_absensi, jam_mulai, jam_selesai, status_absensi, kata_sandi;

	IInternalDetailAbsensiPresenter internalDetailAbsensiPresenter;

	private AdapterListAbsensiAnggota adapterListAbsensiAnggota;
	private RecyclerView recyclerView;

	Toolbar toolbar;

	private SwipeRefreshLayout swipeRefreshLayout;

	TextView tvJudulAbsensi, tvTglAbsensi, tvJamMulai, tvJamSelesai, tvStatusAbsensi;

	CardView cvItemAdapterListAbsensi;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_internal_detail_absensi);

		id_absensi = getIntent().getStringExtra(EXTRA_ID_ABSENSI);
		// id_internal = getIntent().getStringExtra(EXTRA_ID_INTERNAL);
		judul_absensi = getIntent().getStringExtra(EXTRA_JUDUL_ABSENSI);
		tgl_absensi = getIntent().getStringExtra(EXTRA_TGL_ABSENSI);
		jam_mulai = getIntent().getStringExtra(EXTRA_JAM_MULAI);
		jam_selesai = getIntent().getStringExtra(EXTRA_JAM_SELESAI);
		status_absensi = getIntent().getStringExtra(EXTRA_STATUS_ABSENSI);
		kata_sandi = getIntent().getStringExtra(EXTRA_KATA_SANDI);

		tvJudulAbsensi = findViewById(R.id.tv_judul_absensi);
		tvTglAbsensi = findViewById(R.id.tv_tgl_absensi);
		tvJamMulai = findViewById(R.id.tv_jam_mulai);
		tvJamSelesai = findViewById(R.id.tv_jam_selesai);
		tvStatusAbsensi = findViewById(R.id.tv_status_absensi);

		// Nilai Awal
		internalDetailAbsensiPresenter = new InternalDetailAbsensiPresenter(this, this);
		internalDetailAbsensiPresenter.onLoadSemuaData(
			"" + id_absensi);

		recyclerView = findViewById(R.id.recycle_view);

		toolbar = findViewById(R.id.toolbar);

		swipeRefreshLayout = findViewById(R.id.swipe_refresh_layout);

		swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
			@Override
			public void onRefresh() {
				// Your code to make your refresh action
				internalDetailAbsensiPresenter.onLoadSemuaData(
					"" + id_absensi);

				// CallYourRefreshingMethod();
				final Handler handler = new Handler();
				handler.postDelayed(new Runnable() {
					@Override
					public void run() {
						if (swipeRefreshLayout.isRefreshing()) {
							swipeRefreshLayout.setRefreshing(false);
						}
					}
				}, 1000);
			}
		});

		setNilaiDefault();
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
		tvJudulAbsensi.setText("" + judul_absensi);
		tvTglAbsensi.setText("Tanggal Acara : " + tgl_absensi);
		tvJamMulai.setText("Jam Mulai : " + jam_mulai);
		tvJamSelesai.setText("Jam Selesai : " + jam_selesai);
		tvStatusAbsensi.setText("" + status_absensi);
	}

	@Override
	public void onSetupListView(ArrayList<Internal> dataModelArrayList) {

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
	public void backPressed() {
		onBackPressed();
	}

	@Override
	public void keHalamanLain() {
//		Intent intent = new Intent(getApplicationContext(), EksternalListProveActivity.class);
//		intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//		startActivity(intent);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {

			case android.R.id.home:
				onBackPressed();
				break;
		}
		return true;
	}

	@Override
	protected void onResume() {
		super.onResume();
		internalDetailAbsensiPresenter.onLoadSemuaData(
			"" + id_absensi);
	}
}
