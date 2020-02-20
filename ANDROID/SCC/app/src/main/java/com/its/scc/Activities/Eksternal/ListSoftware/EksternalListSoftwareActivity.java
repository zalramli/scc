package com.its.scc.Activities.Eksternal.ListSoftware;

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

import com.its.scc.Activities.Eksternal.ListSoftware.presenter.EksternalListSoftwarePresenter;
import com.its.scc.Activities.Eksternal.ListSoftware.presenter.IEksternalListSoftwarePresenter;
import com.its.scc.Activities.Eksternal.ListSoftware.view.IEksternalListSoftwareView;
import com.its.scc.Adapters.AdapterListSoftware;
import com.its.scc.DB.DatabaseHelper;
import com.its.scc.Models.Software;
import com.its.scc.R;

import java.util.ArrayList;

import es.dmoral.toasty.Toasty;

public class EksternalListSoftwareActivity extends AppCompatActivity implements View.OnClickListener, IEksternalListSoftwareView {

	IEksternalListSoftwarePresenter eksternalListSoftwarePresenter;

	AdapterListSoftware adapterListSoftware;
	private RecyclerView recyclerView;

	Toolbar toolbar;

	private SwipeRefreshLayout swipeRefreshLayout;

	DatabaseHelper dbHelper;

	String id_software = "";
	String nama = "";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_eksternal_list_software);

		// initiate database object in main function
		dbHelper = new DatabaseHelper(this);

		eksternalListSoftwarePresenter = new EksternalListSoftwarePresenter(this, this);
		eksternalListSoftwarePresenter.onLoadSemuaData();

		recyclerView = findViewById(R.id.recycle_view);

		toolbar = findViewById(R.id.toolbar);
		initActionBar();

		swipeRefreshLayout = findViewById(R.id.swipe_refresh_layout);

		swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
			@Override
			public void onRefresh() {
				// Your code to make your refresh action
				eksternalListSoftwarePresenter.onLoadSemuaData();

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
	public void onSetupListView(ArrayList<Software> dataModelArrayList) {
		adapterListSoftware = new AdapterListSoftware(this, dataModelArrayList);
		GridLayoutManager layoutManager = new GridLayoutManager(this, 1, GridLayoutManager.VERTICAL, false);
		recyclerView.setAdapter(adapterListSoftware);
		recyclerView.setLayoutManager(layoutManager);
		recyclerView.setNestedScrollingEnabled(true);
		adapterListSoftware.notifyDataSetChanged();

		adapterListSoftware.setOnItemClickListener(new AdapterListSoftware.ClickListener() {
			@Override
			public void onClick(View view, int position) {
				// menambah item dalam db android
				id_software = dataModelArrayList.get(position).getId_software();
				nama = dataModelArrayList.get(position).getNama();

				long id = dbHelper.insertInfo(
					"" + id_software,
					"" + nama
				);

				onSuccessMessage("id :" + id);
				onBackPressed();
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
		eksternalListSoftwarePresenter.onLoadSemuaData();
	}
}
