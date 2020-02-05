package com.its.scc.Activities.Eksternal.ListJadwal;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.its.scc.Activities.Eksternal.BeforeCreateProve.EksternalBeforeCreateProveActivity;
import com.its.scc.Activities.Eksternal.ListJadwal.presenter.EksternalListJadwalPresenter;
import com.its.scc.Activities.Eksternal.ListJadwal.presenter.IEksternalListJadwalPresenter;
import com.its.scc.Activities.Eksternal.ListJadwal.view.IEksternalListJadwalView;
import com.its.scc.Adapters.AdapterListJadwal;
import com.its.scc.Models.Jadwal;
import com.its.scc.R;

import java.util.ArrayList;

import es.dmoral.toasty.Toasty;

public class EksternalListJadwalActivity extends AppCompatActivity implements View.OnClickListener, IEksternalListJadwalView {

	public static final String EXTRA_ID_MATERI_PROVE = "EXTRA_ID_MATERI_PROVE";
	public static final String EXTRA_NAMA_MATERI_PROVE = "EXTRA_NAMA_MATERI_PROVE";
	public static final String EXTRA_ID_INTERNAL = "EXTRA_ID_INTERNAL";
	public static final String EXTRA_NAMA_INTERNAL = "EXTRA_NAMA_INTERNAL";
	String id_materi_prove = "";
	String nama_materi_prove = "";
	String id_internal = "";
	String nama_internal = "";

	IEksternalListJadwalPresenter eksternalListJadwalPresenter;

	private AdapterListJadwal adapterListJadwal;
	private RecyclerView recyclerView;

	Toolbar toolbar;

	private SwipeRefreshLayout swipeRefreshLayout;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_eksternal_list_jadwal);

		id_materi_prove = getIntent().getStringExtra(EXTRA_ID_MATERI_PROVE);
		nama_materi_prove = getIntent().getStringExtra(EXTRA_NAMA_MATERI_PROVE);
		id_internal = getIntent().getStringExtra(EXTRA_ID_INTERNAL);
		nama_internal = getIntent().getStringExtra(EXTRA_NAMA_INTERNAL);

		recyclerView = findViewById(R.id.recycle_view);

		toolbar = findViewById(R.id.toolbar);
		initActionBar();

		swipeRefreshLayout = findViewById(R.id.swipe_refresh_layout);

		eksternalListJadwalPresenter = new EksternalListJadwalPresenter(this, this);
		eksternalListJadwalPresenter.inisiasiAwal(id_internal);

		swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
			@Override
			public void onRefresh() {
				// Your code to make your refresh action
				eksternalListJadwalPresenter.inisiasiAwal(id_internal);

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
	public void onSetupListView(ArrayList<Jadwal> dataModelArrayList) {
		adapterListJadwal = new AdapterListJadwal(this, dataModelArrayList);
		GridLayoutManager layoutManager = new GridLayoutManager(this, 1, GridLayoutManager.VERTICAL, false);
		recyclerView.setAdapter(adapterListJadwal);
		recyclerView.setLayoutManager(layoutManager);
		recyclerView.setNestedScrollingEnabled(true);
		adapterListJadwal.notifyDataSetChanged();

		adapterListJadwal.setOnItemClickListener(new AdapterListJadwal.ClickListener() {
			@Override
			public void onClick(View view, int position) {
				String status_booking = dataModelArrayList.get(position).getStatus_booking();
				if (!status_booking.equals("Free")) {
					onErrorMessage("Jadwal Sudah Tidak Tersedia !");
				} else {
					Intent intent = new Intent(getApplicationContext(), EksternalBeforeCreateProveActivity.class);
					intent.putExtra(EksternalBeforeCreateProveActivity.EXTRA_ID_MATERI_PROVE, id_materi_prove);
					intent.putExtra(EksternalBeforeCreateProveActivity.EXTRA_NAMA_MATERI_PROVE, nama_materi_prove);
					intent.putExtra(EksternalBeforeCreateProveActivity.EXTRA_ID_INTERNAL, id_internal);
					intent.putExtra(EksternalBeforeCreateProveActivity.EXTRA_NAMA_INTERNAL, nama_internal);
					intent.putExtra(EksternalBeforeCreateProveActivity.EXTRA_ID_JADWAL, dataModelArrayList.get(position).getId_jadwal_prove());
					intent.putExtra(EksternalBeforeCreateProveActivity.EXTRA_HARI_JADWAL, dataModelArrayList.get(position).getHari());
					intent.putExtra(EksternalBeforeCreateProveActivity.EXTRA_JAM_MULAI, dataModelArrayList.get(position).getJam_mulai());
					intent.putExtra(EksternalBeforeCreateProveActivity.EXTRA_JAM_SELESAI, dataModelArrayList.get(position).getJam_selesai());
					startActivity(intent);
				}
			}
		});
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
		eksternalListJadwalPresenter.inisiasiAwal(id_internal);
	}
}
