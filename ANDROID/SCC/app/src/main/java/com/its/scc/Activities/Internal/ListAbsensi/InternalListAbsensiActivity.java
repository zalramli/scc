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
import com.its.scc.Activities.Internal.CekPasswordAbsensi.InternalCekPasswordAbsensiActivity;
import com.its.scc.Activities.Internal.CreateAbsensi.InternalCreateAbsensiActivity;
import com.its.scc.Activities.Internal.DetailAbsensi.InternalDetailAbsensiActivity;
import com.its.scc.Activities.Internal.ListAbsensi.presenter.IInternalListAbsensiPresenter;
import com.its.scc.Activities.Internal.ListAbsensi.presenter.InternalListAbsensiPresenter;
import com.its.scc.Activities.Internal.ListAbsensi.view.IInternalListAbsensiView;
import com.its.scc.Adapters.AdapterListAbsensi;
import com.its.scc.Controllers.SessionManager;
import com.its.scc.Models.Absensi;
import com.its.scc.R;

import java.util.ArrayList;
import java.util.HashMap;

import es.dmoral.toasty.Toasty;

public class InternalListAbsensiActivity extends AppCompatActivity implements View.OnClickListener, IInternalListAbsensiView {

	public static final String EXTRA_TUJUAN = "EXTRA_TUJUAN";

	IInternalListAbsensiPresenter internalListAbsensiPresenter;

	AdapterListAbsensi adapterListAbsensi;
	private RecyclerView recyclerView;

	Toolbar toolbar;

	private SwipeRefreshLayout swipeRefreshLayout;

	FloatingActionButton fab;

	SessionManager sessionManager;

	String db_id_absensi = "";
	String db_id_internal = "";
	String db_judul_absensi = "";
	String db_tgl_absensi = "";
	String db_jam_mulai = "";
	String db_jam_selesai = "";
	String db_status_absensi = "";
	String db_kata_sandi = "";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_internal_list_absensi);

		sessionManager = new SessionManager(this);

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
				// lihat detail absensi dan kirim data detail
				db_id_absensi = dataModelArrayList.get(position).getId_absensi();
				db_id_internal = dataModelArrayList.get(position).getId_internal();
				db_judul_absensi = dataModelArrayList.get(position).getJudul_absensi();
				db_tgl_absensi = dataModelArrayList.get(position).getTgl_absensi();
				db_jam_mulai = dataModelArrayList.get(position).getJam_mulai();
				db_jam_selesai = dataModelArrayList.get(position).getJam_selesai();
				db_status_absensi = dataModelArrayList.get(position).getStatus_absensi();
				db_kata_sandi = dataModelArrayList.get(position).getKata_sandi();

				HashMap<String, String> user = sessionManager.getDataUser();
				String id_internal = user.get(sessionManager.ID_USER);

				internalListAbsensiPresenter.cekAbsen(
					"" + db_id_absensi,
					"" + id_internal,
					"" + db_status_absensi
				);
			}
		});
	}

	@Override
	public void showAkses() {
		fab.show();
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
	public void keDetail() {
		Intent intent = new Intent(getApplicationContext(), InternalDetailAbsensiActivity.class);
		intent.putExtra(InternalDetailAbsensiActivity.EXTRA_ID_ABSENSI, db_id_absensi);
		// intent.putExtra(InternalDetailAbsensiActivity.EXTRA_ID_INTERNAL, db_id_internal);
		intent.putExtra(InternalDetailAbsensiActivity.EXTRA_JUDUL_ABSENSI, db_judul_absensi);
		intent.putExtra(InternalDetailAbsensiActivity.EXTRA_TGL_ABSENSI, db_tgl_absensi);
		intent.putExtra(InternalDetailAbsensiActivity.EXTRA_JAM_MULAI, db_jam_mulai);
		intent.putExtra(InternalDetailAbsensiActivity.EXTRA_JAM_SELESAI, db_jam_selesai);
		intent.putExtra(InternalDetailAbsensiActivity.EXTRA_STATUS_ABSENSI, db_status_absensi);
		intent.putExtra(InternalDetailAbsensiActivity.EXTRA_KATA_SANDI, db_kata_sandi);
		startActivity(intent);
	}

	@Override
	public void keKataSandi() {
		Intent intent = new Intent(getApplicationContext(), InternalCekPasswordAbsensiActivity.class);
		intent.putExtra(InternalCekPasswordAbsensiActivity.EXTRA_ID_ABSENSI, db_id_absensi);
		// intent.putExtra(InternalCekPasswordAbsensiActivity.EXTRA_ID_INTERNAL, db_id_internal);
		intent.putExtra(InternalCekPasswordAbsensiActivity.EXTRA_JUDUL_ABSENSI, db_judul_absensi);
		intent.putExtra(InternalCekPasswordAbsensiActivity.EXTRA_TGL_ABSENSI, db_tgl_absensi);
		intent.putExtra(InternalCekPasswordAbsensiActivity.EXTRA_JAM_MULAI, db_jam_mulai);
		intent.putExtra(InternalCekPasswordAbsensiActivity.EXTRA_JAM_SELESAI, db_jam_selesai);
		intent.putExtra(InternalCekPasswordAbsensiActivity.EXTRA_STATUS_ABSENSI, db_status_absensi);
		intent.putExtra(InternalCekPasswordAbsensiActivity.EXTRA_KATA_SANDI, db_kata_sandi);
		startActivity(intent);
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
