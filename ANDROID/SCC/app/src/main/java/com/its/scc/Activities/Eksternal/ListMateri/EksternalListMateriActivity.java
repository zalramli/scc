package com.its.scc.Activities.Eksternal.ListMateri;

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

import com.its.scc.Activities.Eksternal.ListInternal.EksternalListInternalActivity;
import com.its.scc.Activities.Eksternal.ListMateri.presenter.EksternalListMateriPresenter;
import com.its.scc.Activities.Eksternal.ListMateri.presenter.IEksternalListMateriPresenter;
import com.its.scc.Activities.Eksternal.ListMateri.view.IEksternalListMateriView;
import com.its.scc.Adapters.AdapterListMateri;
import com.its.scc.Models.MateriProve;
import com.its.scc.R;

import java.util.ArrayList;

import es.dmoral.toasty.Toasty;

public class EksternalListMateriActivity extends AppCompatActivity implements View.OnClickListener, IEksternalListMateriView {

	IEksternalListMateriPresenter eksternalListMateriPresenter;

	private AdapterListMateri adapterListMateri;
	private RecyclerView recyclerView;

	Toolbar toolbar;

	private SwipeRefreshLayout swipeRefreshLayout;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_eksternal_list_materi);

		eksternalListMateriPresenter = new EksternalListMateriPresenter(this, this);
		eksternalListMateriPresenter.onLoadSemuaData();

		recyclerView = findViewById(R.id.recycle_view);

		toolbar = findViewById(R.id.toolbar);
		initActionBar();

		swipeRefreshLayout = findViewById(R.id.swipe_refresh_layout);

		swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
			@Override
			public void onRefresh() {
				// Your code to make your refresh action
				eksternalListMateriPresenter.onLoadSemuaData();

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
	public void onSetupListView(ArrayList<MateriProve> dataModelArrayList) {
		adapterListMateri = new AdapterListMateri(this,dataModelArrayList);
		GridLayoutManager layoutManager = new GridLayoutManager(this,1,GridLayoutManager.VERTICAL,false);
		recyclerView.setAdapter(adapterListMateri);
		recyclerView.setLayoutManager(layoutManager);
		recyclerView.setNestedScrollingEnabled(true);
		adapterListMateri.notifyDataSetChanged();

		adapterListMateri.setOnItemClickListener(new AdapterListMateri.ClickListener() {
			@Override
			public void onClick(View view, int position) {
				Intent intent = new Intent(getApplicationContext(), EksternalListInternalActivity.class);
				intent.putExtra(EksternalListInternalActivity.EXTRA_ID_MATERI_PROVE, dataModelArrayList.get(position).getId_materi_prove());
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
		eksternalListMateriPresenter.onLoadSemuaData();
	}
}
