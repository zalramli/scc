package com.its.scc.Activities.Eksternal.DetailBankSoftware;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.its.scc.Activities.Eksternal.DetailBankSoftware.presenter.EksternalDetailBankSoftwarePresenter;
import com.its.scc.Activities.Eksternal.DetailBankSoftware.presenter.IEksternalDetailBankSoftwarePresenter;
import com.its.scc.Activities.Eksternal.DetailBankSoftware.view.IEksternalDetailBankSoftwareView;
import com.its.scc.Activities.Eksternal._Home.EksternalHomeActivity;
import com.its.scc.Adapters.AdapterListSoftware;
import com.its.scc.Controllers.SessionManager;
import com.its.scc.Models.Software;
import com.its.scc.R;

import java.util.ArrayList;
import java.util.HashMap;

import es.dmoral.toasty.Toasty;

public class EksternalDetailBankSoftwareActivity extends AppCompatActivity implements View.OnClickListener, IEksternalDetailBankSoftwareView {

	public static final String EXTRA_KODE_BANK_S = "EXTRA_KODE_BANK_S";
	public static final String EXTRA_ID_EKSTERNAL = "EXTRA_ID_EKSTERNAL";
	public static final String EXTRA_NAMA = "EXTRA_NAMA";
	public static final String EXTRA_NO_HP = "EXTRA_NO_HP";
	public static final String EXTRA_AKUN_LINE = "EXTRA_AKUN_LINE";
	public static final String EXTRA_ANGKATAN = "EXTRA_ANGKATAN";
	public static final String EXTRA_FOTO = "EXTRA_FOTO";
	public static final String EXTRA_ID_JADWAL_BS = "EXTRA_ID_JADWAL_BS";
	public static final String EXTRA_HARI = "EXTRA_HARI";
	public static final String EXTRA_JAM_MULAI = "EXTRA_JAM_MULAI";
	public static final String EXTRA_JAM_SELESAI = "EXTRA_JAM_SELESAI";
	public static final String EXTRA_TANGGAL_BOOKING = "EXTRA_TANGGAL_BOOKING";
	public static final String EXTRA_TANGGAL_BS = "EXTRA_TANGGAL_BS";
	public static final String EXTRA_STATUS_BS = "EXTRA_STATUS_BS";

	String kode_bank_s, id_eksternal, nama, no_hp, akun_line, angkatan, foto, id_jadwal_bs, hari, jam_mulai, jam_selesai, tanggal_booking, tanggal_bs, status_bs;

	IEksternalDetailBankSoftwarePresenter eksternalDetailBankSoftwarePresenter;

	private AdapterListSoftware adapterListSoftware;
	private RecyclerView recyclerView;

	Toolbar toolbar;

	private SwipeRefreshLayout swipeRefreshLayout;

	SessionManager sessionManager;
	String hak_akses = "";

	CardView cvItemAdapterListBankSoftware;

	Button btnHapus, btnSelesai;

	TextView tvDetailBs, tvTanggalBs, tvNamaEksternal, tvNoHp, tvAkunLine;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_eksternal_detail_bank_software);

		sessionManager = new SessionManager(this);
		HashMap<String, String> user = sessionManager.getDataUser();
		hak_akses = user.get(sessionManager.HAK_AKSES);

		kode_bank_s = getIntent().getStringExtra(EXTRA_KODE_BANK_S);
		id_eksternal = getIntent().getStringExtra(EXTRA_ID_EKSTERNAL);
		nama = getIntent().getStringExtra(EXTRA_NAMA);
		no_hp = getIntent().getStringExtra(EXTRA_NO_HP);
		akun_line = getIntent().getStringExtra(EXTRA_AKUN_LINE);
		angkatan = getIntent().getStringExtra(EXTRA_ANGKATAN);
		foto = getIntent().getStringExtra(EXTRA_FOTO);
		id_jadwal_bs = getIntent().getStringExtra(EXTRA_ID_JADWAL_BS);
		hari = getIntent().getStringExtra(EXTRA_HARI);
		jam_mulai = getIntent().getStringExtra(EXTRA_JAM_MULAI);
		jam_selesai = getIntent().getStringExtra(EXTRA_JAM_SELESAI);
		tanggal_booking = getIntent().getStringExtra(EXTRA_TANGGAL_BOOKING);
		tanggal_bs = getIntent().getStringExtra(EXTRA_TANGGAL_BS);
		status_bs = getIntent().getStringExtra(EXTRA_STATUS_BS);

		cvItemAdapterListBankSoftware = findViewById(R.id.cv_item_adapter_list_bank_software);

		tvDetailBs = findViewById(R.id.tv_detail_bs);
		tvTanggalBs = findViewById(R.id.tv_tanggal_bs);
		tvNamaEksternal = findViewById(R.id.tv_nama_eksternal);
		tvNoHp = findViewById(R.id.tv_no_hp);
		tvAkunLine = findViewById(R.id.tv_akun_line);

		btnHapus = findViewById(R.id.btn_hapus);
		btnSelesai = findViewById(R.id.btn_selesai);

		eksternalDetailBankSoftwarePresenter = new EksternalDetailBankSoftwarePresenter(this, this);
		eksternalDetailBankSoftwarePresenter.onLoadSemuaData(kode_bank_s);

		recyclerView = findViewById(R.id.recycle_view);

		toolbar = findViewById(R.id.toolbar);

		swipeRefreshLayout = findViewById(R.id.swipe_refresh_layout);

		swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
			@Override
			public void onRefresh() {
				// Your code to make your refresh action
				eksternalDetailBankSoftwarePresenter.onLoadSemuaData(kode_bank_s);

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

		setNilaiDefault();
		initActionBar();

		btnHapus.setOnClickListener(this);
		btnSelesai.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		if (v.getId() == R.id.btn_hapus) {
			showDialogHapus(); // hapus
		}
		if (v.getId() == R.id.btn_selesai) {
			showDialogSelesai(); // selesai
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
	public void setNilaiDefault() {

		// warna cardview jika status
		if (status_bs.equals("Belum Selesai")) {
			cvItemAdapterListBankSoftware.setCardBackgroundColor(Color.RED);
		} else if (status_bs.equals("Batal")) {
			cvItemAdapterListBankSoftware.setCardBackgroundColor(Color.GRAY);
		}

		if (hak_akses.equals("internal") && status_bs.equals("Belum Selesai")) {
			btnSelesai.setVisibility(View.VISIBLE);
			btnHapus.setVisibility(View.GONE);
		} else if (hak_akses.equals("eksternal") && status_bs.equals("Belum Selesai")) {
			btnSelesai.setVisibility(View.GONE);
			btnHapus.setVisibility(View.VISIBLE);
		}

		tvDetailBs.setText(hari + " ( " + jam_mulai + " - " + jam_selesai + " )");
		tvTanggalBs.setText(tanggal_bs);

		tvNamaEksternal.setText(nama + " (" + angkatan + ")");
		tvNoHp.setText(no_hp);
		tvAkunLine.setText(akun_line);
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
	public void showDialogHapus() {
		AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
			this);
		alertDialogBuilder.setTitle("Ingin Membatalkan dan Hapus Bank Software ?");
		alertDialogBuilder
			.setMessage("Klik Ya Untuk Hapus Bank Software !")
			.setPositiveButton("Ya", new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface dialog, int id) {

					try {

						eksternalDetailBankSoftwarePresenter.onHapus(kode_bank_s);

					} catch (Exception e) {
						onErrorMessage("Terjadi Kesalahan Hapus " + e.toString());
					}

				}
			})
			.setNegativeButton("Tidak", new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface dialog, int id) {
					dialog.cancel();
				}
			});

		AlertDialog alertDialog = alertDialogBuilder.create();
		alertDialog.show();
	}

	@Override
	public void showDialogSelesai() {
		AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
			this);
		alertDialogBuilder.setTitle("Ingin Menyelesaikan Bank Software ?");
		alertDialogBuilder
			.setMessage("Klik Ya Untuk Menyelesaikan Bank Software !")
			.setPositiveButton("Ya", new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface dialog, int id) {

					try {

						eksternalDetailBankSoftwarePresenter.onSelesai(kode_bank_s);

					} catch (Exception e) {
						onErrorMessage("Terjadi Kesalahan Update " + e.toString());
					}

				}
			})
			.setNegativeButton("Tidak", new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface dialog, int id) {
					dialog.cancel();
				}
			});

		AlertDialog alertDialog = alertDialogBuilder.create();
		alertDialog.show();
	}

	@Override
	public void backPressed() {
		onBackPressed();
	}

	@Override
	public void keHalamanLain() {
		Intent intent = new Intent(getApplicationContext(), EksternalHomeActivity.class);
//		intent.putExtra(EksternalHomeActivity.EXTRA_TUJUAN, "kosong");
		intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
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
		eksternalDetailBankSoftwarePresenter.onLoadSemuaData(kode_bank_s);
	}
}
