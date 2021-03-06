package com.its.scc.Activities.Eksternal.ListInternal;

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

import com.its.scc.Activities.Eksternal.ListInternal.presenter.EksternalListInternalPresenter;
import com.its.scc.Activities.Eksternal.ListInternal.presenter.IEksternalListInternalPresenter;
import com.its.scc.Activities.Eksternal.ListInternal.view.IEksternalListInternalView;
import com.its.scc.Activities.Eksternal.ListJadwal.EksternalListJadwalActivity;
import com.its.scc.Activities.Eksternal.ListMateri.presenter.EksternalListMateriPresenter;
import com.its.scc.Adapters.AdapterListInternal;
import com.its.scc.Adapters.AdapterListMateri;
import com.its.scc.Models.Internal;
import com.its.scc.R;

import java.util.ArrayList;

import es.dmoral.toasty.Toasty;

public class EksternalListInternalActivity extends AppCompatActivity implements View.OnClickListener, IEksternalListInternalView {

	public static final String EXTRA_ID_MATERI_PROVE = "EXTRA_ID_MATERI_PROVE";
	public static final String EXTRA_NAMA_MATERI_PROVE = "EXTRA_NAMA_MATERI_PROVE";
	String id_materi_prove = "";
	String nama_materi_prove = "";

	IEksternalListInternalPresenter eksternalListInternalPresenter;

	private AdapterListInternal adapterListInternal;
	private RecyclerView recyclerView;

	Toolbar toolbar;

	private SwipeRefreshLayout swipeRefreshLayout;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_eksternal_list_internal);

		id_materi_prove = getIntent().getStringExtra(EXTRA_ID_MATERI_PROVE);
		nama_materi_prove = getIntent().getStringExtra(EXTRA_NAMA_MATERI_PROVE);

		eksternalListInternalPresenter = new EksternalListInternalPresenter(this, this);
		eksternalListInternalPresenter.inisiasiAwal(id_materi_prove);

		recyclerView = findViewById(R.id.recycle_view);

		toolbar = findViewById(R.id.toolbar);
		initActionBar();

		swipeRefreshLayout = findViewById(R.id.swipe_refresh_layout);

		swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
			@Override
			public void onRefresh() {
				// Your code to make your refresh action
				eksternalListInternalPresenter.inisiasiAwal(id_materi_prove);

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
	public void onSetupListView(ArrayList<Internal> dataModelArrayList) {
		adapterListInternal = new AdapterListInternal(this, dataModelArrayList);
		GridLayoutManager layoutManager = new GridLayoutManager(this, 2, GridLayoutManager.VERTICAL, false);
		recyclerView.setAdapter(adapterListInternal);
		recyclerView.setLayoutManager(layoutManager);
		recyclerView.setNestedScrollingEnabled(true);
		adapterListInternal.notifyDataSetChanged();

		adapterListInternal.setOnItemClickListener(new AdapterListInternal.ClickListener() {
			@Override
			public void onClick(View view, int position) {
				Intent intent = new Intent(getApplicationContext(), EksternalListJadwalActivity.class);
				intent.putExtra(EksternalListJadwalActivity.EXTRA_ID_MATERI_PROVE, id_materi_prove);
				intent.putExtra(EksternalListJadwalActivity.EXTRA_NAMA_MATERI_PROVE, nama_materi_prove);
				intent.putExtra(EksternalListJadwalActivity.EXTRA_ID_INTERNAL, dataModelArrayList.get(position).getId_internal());
				intent.putExtra(EksternalListJadwalActivity.EXTRA_NAMA_INTERNAL, dataModelArrayList.get(position).getNama());
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
		eksternalListInternalPresenter.inisiasiAwal(id_materi_prove);
	}
}
