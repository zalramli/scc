package com.its.scc.Activities.Eksternal.ListProve;

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

import com.its.scc.Activities.Eksternal.DetailProve.EksternalDetailProveActivity;
import com.its.scc.Activities.Eksternal.ListProve.presenter.EksternalListProvePresenter;
import com.its.scc.Activities.Eksternal.ListProve.presenter.IEksternalListProvePresenter;
import com.its.scc.Activities.Eksternal.ListProve.view.IEksternalListProveView;
import com.its.scc.Adapters.AdapterListProve;
import com.its.scc.Controllers.SessionManager;
import com.its.scc.Models.Prove;
import com.its.scc.R;

import java.util.ArrayList;
import java.util.HashMap;

import es.dmoral.toasty.Toasty;

public class EksternalListProveActivity extends AppCompatActivity implements View.OnClickListener, IEksternalListProveView {

	public static final String EXTRA_TUJUAN = "EXTRA_TUJUAN";
	IEksternalListProvePresenter eksternalListProvePresenter;

	private AdapterListProve adapterListProve;
	private RecyclerView recyclerView;

	Toolbar toolbar;

	private SwipeRefreshLayout swipeRefreshLayout;

	SessionManager sessionManager;

	String id = "";
	String hakAkses = "";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_eksternal_list_prove);

		sessionManager = new SessionManager(this);
		HashMap<String, String> user = sessionManager.getDataUser();
		id = user.get(sessionManager.ID_USER);
		hakAkses = user.get(sessionManager.HAK_AKSES);

		eksternalListProvePresenter = new EksternalListProvePresenter(this, this);
		eksternalListProvePresenter.inisiasiAwal(id, hakAkses);

		recyclerView = findViewById(R.id.recycle_view);

		toolbar = findViewById(R.id.toolbar);
		initActionBar();

		swipeRefreshLayout = findViewById(R.id.swipe_refresh_layout);

		swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
			@Override
			public void onRefresh() {
				// Your code to make your refresh action
				eksternalListProvePresenter.inisiasiAwal(id, hakAkses);

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
	public void onSetupListView(ArrayList<Prove> dataModelArrayList) {
		adapterListProve = new AdapterListProve(this, dataModelArrayList);
		GridLayoutManager layoutManager = new GridLayoutManager(this, 1, GridLayoutManager.VERTICAL, false);
		recyclerView.setAdapter(adapterListProve);
		recyclerView.setLayoutManager(layoutManager);
		recyclerView.setNestedScrollingEnabled(true);
		adapterListProve.notifyDataSetChanged();

		adapterListProve.setOnItemClickListener(new AdapterListProve.ClickListener() {
			@Override
			public void onClick(View view, int position) {
				Intent intent = new Intent(getApplicationContext(), EksternalDetailProveActivity.class);
				intent.putExtra(EksternalDetailProveActivity.EXTRA_ID_PROVE, dataModelArrayList.get(position).getId_prove());
				intent.putExtra(EksternalDetailProveActivity.EXTRA_ID_JADWAL_PROVE, dataModelArrayList.get(position).getId_jadwal_prove());
				intent.putExtra(EksternalDetailProveActivity.EXTRA_NAMA_MATERI_PROVE, dataModelArrayList.get(position).getNama_materi_prove());
				intent.putExtra(EksternalDetailProveActivity.EXTRA_HARI, dataModelArrayList.get(position).getHari());
				intent.putExtra(EksternalDetailProveActivity.EXTRA_JAM_MULAI, dataModelArrayList.get(position).getJam_mulai());
				intent.putExtra(EksternalDetailProveActivity.EXTRA_JAM_SELESAI, dataModelArrayList.get(position).getJam_selesai());
				intent.putExtra(EksternalDetailProveActivity.EXTRA_TANGGAL_PROVE, dataModelArrayList.get(position).getTanggal_prove());
				intent.putExtra(EksternalDetailProveActivity.EXTRA_KODE_PROVE, dataModelArrayList.get(position).getKode_prove());
				intent.putExtra(EksternalDetailProveActivity.EXTRA_KATA_SANDI, dataModelArrayList.get(position).getKata_sandi());
				intent.putExtra(EksternalDetailProveActivity.EXTRA_NAMA_INTERNAL, dataModelArrayList.get(position).getNama_internal());
				intent.putExtra(EksternalDetailProveActivity.EXTRA_STATUS_PROVE, dataModelArrayList.get(position).getStatus_prove());
				startActivity(intent);
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
		eksternalListProvePresenter.inisiasiAwal(id, hakAkses);
	}
}
