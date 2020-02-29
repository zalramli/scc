package com.its.scc.Activities.Internal.ListAbsensi;

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

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.its.scc.Activities.Internal.CreateAbsensi.InternalCreateAbsensiActivity;
import com.its.scc.Activities.Internal.ListAbsensi.presenter.IInternalListAbsensiPresenter;
import com.its.scc.Activities.Internal.ListAbsensi.presenter.InternalListAbsensiPresenter;
import com.its.scc.Activities.Internal.ListAbsensi.view.IInternalListAbsensiView;
import com.its.scc.Adapters.AdapterListAbsensi;
import com.its.scc.Models.Absensi;
import com.its.scc.R;

import java.util.ArrayList;

import es.dmoral.toasty.Toasty;

public class InternalListAbsensiActivity extends AppCompatActivity implements View.OnClickListener, IInternalListAbsensiView {

	public static final String EXTRA_TUJUAN = "EXTRA_TUJUAN";
	IInternalListAbsensiPresenter internalListAbsensiPresenter;

	AdapterListAbsensi adapterListAbsensi;
	private RecyclerView recyclerView;

	Toolbar toolbar;

	private SwipeRefreshLayout swipeRefreshLayout;

	FloatingActionButton fab;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_internal_list_absensi);

		internalListAbsensiPresenter = new InternalListAbsensiPresenter(this, this);
		internalListAbsensiPresenter.onLoadSemuaData();

		recyclerView = findViewById(R.id.recycle_view);

		toolbar = findViewById(R.id.toolbar);
		initActionBar();

		fab = findViewById(R.id.fab);

		swipeRefreshLayout = findViewById(R.id.swipe_refresh_layout);

		swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
			@Override
			public void onRefresh() {
				// Your code to make your refresh action
				internalListAbsensiPresenter.onLoadSemuaData();

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

		fab.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		if (v.getId() == R.id.fab) {
			Intent intent = new Intent(getApplicationContext(), InternalCreateAbsensiActivity.class);
			startActivity(intent);
		}
	}

	@Override
	public void initActionBar() {
		setSupportActionBar(toolbar);
		if (getSupportActionBar() != null) {
			getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		}
	}

	@Override
	public void onSetupListView(ArrayList<Absensi> dataModelArrayList) {
		adapterListAbsensi = new AdapterListAbsensi(this, dataModelArrayList);
		GridLayoutManager layoutManager = new GridLayoutManager(this, 1, GridLayoutManager.VERTICAL, false);
		recyclerView.setAdapter(adapterListAbsensi);
		recyclerView.setLayoutManager(layoutManager);
		recyclerView.setNestedScrollingEnabled(true);
		adapterListAbsensi.notifyDataSetChanged();

		adapterListAbsensi.setOnItemClickListener(new AdapterListAbsensi.ClickListener() {
			@Override
			public void onClick(View view, int position) {
				// lihat detail absensi
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
		internalListAbsensiPresenter.onLoadSemuaData();
	}
}
