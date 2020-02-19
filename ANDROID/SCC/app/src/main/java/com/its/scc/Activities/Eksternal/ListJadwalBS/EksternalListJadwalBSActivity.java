package com.its.scc.Activities.Eksternal.ListJadwalBS;

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

import com.its.scc.Activities.Eksternal.BeforeOrderBS.EksternalBeforeOrderBSActivity;
import com.its.scc.Activities.Eksternal.ListJadwalBS.presenter.EksternalListJadwalBSPresenter;
import com.its.scc.Activities.Eksternal.ListJadwalBS.presenter.IEksternalListJadwalBSPresenter;
import com.its.scc.Activities.Eksternal.ListJadwalBS.view.IEksternalListJadwalBSView;
import com.its.scc.Adapters.AdapterListJadwalBS;
import com.its.scc.Models.JadwalBS;
import com.its.scc.R;

import java.util.ArrayList;

import es.dmoral.toasty.Toasty;

public class EksternalListJadwalBSActivity extends AppCompatActivity implements View.OnClickListener , IEksternalListJadwalBSView {

	IEksternalListJadwalBSPresenter eksternalListJadwalBSPresenter;

	private AdapterListJadwalBS adapterListJadwalBS;
	private RecyclerView recyclerView;

	Toolbar toolbar;

	private SwipeRefreshLayout swipeRefreshLayout;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_eksternal_list_jadwal_bs);

		eksternalListJadwalBSPresenter = new EksternalListJadwalBSPresenter(this, this);
		eksternalListJadwalBSPresenter.onLoadSemuaData();

		recyclerView = findViewById(R.id.recycle_view);

		toolbar = findViewById(R.id.toolbar);
		initActionBar();

		swipeRefreshLayout = findViewById(R.id.swipe_refresh_layout);

		swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
			@Override
			public void onRefresh() {
				// Your code to make your refresh action
				eksternalListJadwalBSPresenter.onLoadSemuaData();

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
	public void onSetupListView(ArrayList<JadwalBS> dataModelArrayList) {
		adapterListJadwalBS = new AdapterListJadwalBS(this,dataModelArrayList);
		GridLayoutManager layoutManager = new GridLayoutManager(this,1,GridLayoutManager.VERTICAL,false);
		recyclerView.setAdapter(adapterListJadwalBS);
		recyclerView.setLayoutManager(layoutManager);
		recyclerView.setNestedScrollingEnabled(true);
		adapterListJadwalBS.notifyDataSetChanged();

		adapterListJadwalBS.setOnItemClickListener(new AdapterListJadwalBS.ClickListener() {
			@Override
			public void onClick(View view, int position) {
				onSuccessMessage(dataModelArrayList.get(position).getId_jadwal_bs());
				Intent intent = new Intent(getApplicationContext(), EksternalBeforeOrderBSActivity.class);
				intent.putExtra(EksternalBeforeOrderBSActivity.EXTRA_ID_JADWAL_BS, dataModelArrayList.get(position).getId_jadwal_bs());
				intent.putExtra(EksternalBeforeOrderBSActivity.EXTRA_ID_INTERNAL, dataModelArrayList.get(position).getId_internal());
				intent.putExtra(EksternalBeforeOrderBSActivity.EXTRA_HARI, dataModelArrayList.get(position).getHari());
				intent.putExtra(EksternalBeforeOrderBSActivity.EXTRA_JAM_MULAI, dataModelArrayList.get(position).getJam_mulai());
				intent.putExtra(EksternalBeforeOrderBSActivity.EXTRA_JAM_SELESAI, dataModelArrayList.get(position).getJam_selesai());
				intent.putExtra(EksternalBeforeOrderBSActivity.EXTRA_STATUS_BOOKING, dataModelArrayList.get(position).getStatus_booking());
				intent.putExtra(EksternalBeforeOrderBSActivity.EXTRA_STATUS_AKTIF, dataModelArrayList.get(position).getStatus_aktif());
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
		eksternalListJadwalBSPresenter.onLoadSemuaData();
	}
}
