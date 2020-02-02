package com.its.scc.Activities.Eksternal.ListJadwal;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.os.Bundle;
import android.os.Handler;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

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
	public static final String EXTRA_ID_INTERNAL = "EXTRA_ID_INTERNAL";
	String id_materi_prove = "";
	String id_internal = "";

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
		id_internal = getIntent().getStringExtra(EXTRA_ID_INTERNAL);

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
//				Intent intent = new Intent(getApplicationContext(), EksternalListInternalActivity.class);
//				intent.putExtra(EksternalListInternalActivity.EXTRA_ID_MATERI_PROVE, dataModelArrayList.get(position).getId_materi_prove());
//				startActivity(intent);

				String status_booking = dataModelArrayList.get(position).getStatus_booking();

				if(status_booking.equals("Free")){
					onSuccessMessage(dataModelArrayList.get(position).getJam_mulai());
				} else{
					onErrorMessage("Jadwal Sudah Dipesan !");
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
