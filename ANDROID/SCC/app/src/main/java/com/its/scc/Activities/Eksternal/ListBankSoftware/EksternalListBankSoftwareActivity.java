package com.its.scc.Activities.Eksternal.ListBankSoftware;

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

import com.its.scc.Activities.Eksternal.DetailBankSoftware.EksternalDetailBankSoftwareActivity;
import com.its.scc.Activities.Eksternal.ListBankSoftware.presenter.EksternalListBankSoftwarePresenter;
import com.its.scc.Activities.Eksternal.ListBankSoftware.presenter.IEksternalListBankSoftwarePresenter;
import com.its.scc.Activities.Eksternal.ListBankSoftware.view.IEksternalListBankSoftwareView;
import com.its.scc.Adapters.AdapterListBankSoftware;
import com.its.scc.Controllers.SessionManager;
import com.its.scc.Models.BankSoftware;
import com.its.scc.R;

import java.util.ArrayList;
import java.util.HashMap;

import es.dmoral.toasty.Toasty;

public class EksternalListBankSoftwareActivity extends AppCompatActivity implements View.OnClickListener, IEksternalListBankSoftwareView {

	public static final String EXTRA_TUJUAN = "EXTRA_TUJUAN";
	String tujuan = "";

	IEksternalListBankSoftwarePresenter eksternalListBankSoftwarePresenter;

	private AdapterListBankSoftware adapterListBankSoftware;
	private RecyclerView recyclerView;

	Toolbar toolbar;

	private SwipeRefreshLayout swipeRefreshLayout;

	SessionManager sessionManager;

	String id = "";
	String hakAkses = "";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_eksternal_list_bank_software);

		tujuan = getIntent().getStringExtra(EXTRA_TUJUAN);

		sessionManager = new SessionManager(this);
		HashMap<String, String> user = sessionManager.getDataUser();
		id = user.get(sessionManager.ID_USER);
		hakAkses = user.get(sessionManager.HAK_AKSES);

		eksternalListBankSoftwarePresenter = new EksternalListBankSoftwarePresenter(this, this);
		eksternalListBankSoftwarePresenter.inisiasiAwal(id, hakAkses, tujuan);

		recyclerView = findViewById(R.id.recycle_view);

		toolbar = findViewById(R.id.toolbar);
		initActionBar();

		swipeRefreshLayout = findViewById(R.id.swipe_refresh_layout);

		swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
			@Override
			public void onRefresh() {
				// Your code to make your refresh action
				eksternalListBankSoftwarePresenter.inisiasiAwal(id, hakAkses, tujuan);

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
	public void onSetupListView(ArrayList<BankSoftware> dataModelArrayList) {
		adapterListBankSoftware = new AdapterListBankSoftware(this, dataModelArrayList);
		GridLayoutManager layoutManager = new GridLayoutManager(this, 1, GridLayoutManager.VERTICAL, false);
		recyclerView.setAdapter(adapterListBankSoftware);
		recyclerView.setLayoutManager(layoutManager);
		recyclerView.setNestedScrollingEnabled(true);
		adapterListBankSoftware.notifyDataSetChanged();

		adapterListBankSoftware.setOnItemClickListener(new AdapterListBankSoftware.ClickListener() {
			@Override
			public void onClick(View view, int position) {
				Intent intent = new Intent(getApplicationContext(), EksternalDetailBankSoftwareActivity.class);
				intent.putExtra(EksternalDetailBankSoftwareActivity.EXTRA_KODE_BANK_S, dataModelArrayList.get(position).getKode_bank_s());
				intent.putExtra(EksternalDetailBankSoftwareActivity.EXTRA_ID_EKSTERNAL, dataModelArrayList.get(position).getId_eksternal());
				intent.putExtra(EksternalDetailBankSoftwareActivity.EXTRA_NAMA, dataModelArrayList.get(position).getNama());
				intent.putExtra(EksternalDetailBankSoftwareActivity.EXTRA_NO_HP, dataModelArrayList.get(position).getNo_hp());
				intent.putExtra(EksternalDetailBankSoftwareActivity.EXTRA_AKUN_LINE, dataModelArrayList.get(position).getAkun_line());
				intent.putExtra(EksternalDetailBankSoftwareActivity.EXTRA_ANGKATAN, dataModelArrayList.get(position).getAngkatan());
				intent.putExtra(EksternalDetailBankSoftwareActivity.EXTRA_FOTO, dataModelArrayList.get(position).getFoto());
				intent.putExtra(EksternalDetailBankSoftwareActivity.EXTRA_ID_JADWAL_BS, dataModelArrayList.get(position).getId_jadwal_bs());
				intent.putExtra(EksternalDetailBankSoftwareActivity.EXTRA_HARI, dataModelArrayList.get(position).getHari());
				intent.putExtra(EksternalDetailBankSoftwareActivity.EXTRA_JAM_MULAI, dataModelArrayList.get(position).getJam_mulai());
				intent.putExtra(EksternalDetailBankSoftwareActivity.EXTRA_JAM_SELESAI, dataModelArrayList.get(position).getJam_selesai());
				intent.putExtra(EksternalDetailBankSoftwareActivity.EXTRA_TANGGAL_BOOKING, dataModelArrayList.get(position).getTanggal_booking());
				intent.putExtra(EksternalDetailBankSoftwareActivity.EXTRA_TANGGAL_BS, dataModelArrayList.get(position).getTanggal_bs());
				intent.putExtra(EksternalDetailBankSoftwareActivity.EXTRA_STATUS_BS, dataModelArrayList.get(position).getStatus_bs());
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
		eksternalListBankSoftwarePresenter.inisiasiAwal(id, hakAkses, tujuan);
	}
}
