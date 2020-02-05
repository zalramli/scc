package com.its.scc.Activities.Eksternal.DetailProve;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.its.scc.Activities.Eksternal.DetailProve.presenter.EksternalDetailProvePresenter;
import com.its.scc.Activities.Eksternal.DetailProve.presenter.IEksternalDetailProvePresenter;
import com.its.scc.Activities.Eksternal.DetailProve.view.IEksternalDetailProveView;
import com.its.scc.Activities.Eksternal.ListProve.EksternalListProveActivity;
import com.its.scc.Adapters.AdapterListEksternal;
import com.its.scc.Controllers.SessionManager;
import com.its.scc.Models.Eksternal;
import com.its.scc.R;

import java.util.ArrayList;
import java.util.HashMap;

import es.dmoral.toasty.Toasty;

public class EksternalDetailProveActivity extends AppCompatActivity implements View.OnClickListener, IEksternalDetailProveView {

	public static final String EXTRA_ID_PROVE = "EXTRA_ID_PROVE";
	public static final String EXTRA_ID_JADWAL_PROVE = "EXTRA_ID_JADWAL_PROVE";
	public static final String EXTRA_NAMA_MATERI_PROVE = "EXTRA_NAMA_MATERI_PROVE";
	public static final String EXTRA_HARI = "EXTRA_HARI";
	public static final String EXTRA_JAM_MULAI = "EXTRA_JAM_MULAI";
	public static final String EXTRA_JAM_SELESAI = "EXTRA_JAM_SELESAI";
	public static final String EXTRA_TANGGAL_PROVE = "EXTRA_TANGGAL_PROVE";
	public static final String EXTRA_KODE_PROVE = "EXTRA_KODE_PROVE";
	public static final String EXTRA_KATA_SANDI = "EXTRA_KATA_SANDI";
	public static final String EXTRA_NAMA_INTERNAL = "EXTRA_NAMA_INTERNAL";
	public static final String EXTRA_STATUS_PROVE = "EXTRA_STATUS_PROVE";

	String id_prove, id_jadwal_prove, nama_materi_prove, hari_jadwal, jam_mulai, jam_selesai, tanggal_prove, kode_prove, kata_sandi, nama_internal, status_prove;

	IEksternalDetailProvePresenter eksternalDetailProvePresenter;

	private AdapterListEksternal adapterListEksternal;
	private RecyclerView recyclerView;

	Toolbar toolbar;

	private SwipeRefreshLayout swipeRefreshLayout;

	TextView tvNamaMateri, tvDetailJadwal, tvTanggalProve, tvKodeProve, tvKataSandi, tvNamaInternal, tvStatusProve, tvRating, tvKeluar;

	ImageView ivKeluar, ivRating;

	SessionManager sessionManager;
	String hak_akses = "";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_eksternal_detail_prove);

		sessionManager = new SessionManager(this);
		HashMap<String, String> user = sessionManager.getDataUser();
		hak_akses = user.get(sessionManager.HAK_AKSES);

		tvNamaMateri = findViewById(R.id.tv_nama_materi);
		tvDetailJadwal = findViewById(R.id.tv_detail_jadwal);
		tvTanggalProve = findViewById(R.id.tv_tanggal_prove);
		tvKodeProve = findViewById(R.id.tv_kode_prove);
		tvKataSandi = findViewById(R.id.tv_kata_sandi);
		tvNamaInternal = findViewById(R.id.tv_nama_internal);
		tvStatusProve = findViewById(R.id.tv_status_prove);
		tvRating = findViewById(R.id.tv_rating);
		tvKeluar = findViewById(R.id.tv_keluar);

		ivRating = findViewById(R.id.iv_rating);
		ivKeluar = findViewById(R.id.iv_keluar);

		id_prove = getIntent().getStringExtra(EXTRA_ID_PROVE);
		id_jadwal_prove = getIntent().getStringExtra(EXTRA_ID_JADWAL_PROVE);
		nama_materi_prove = getIntent().getStringExtra(EXTRA_NAMA_MATERI_PROVE);
		hari_jadwal = getIntent().getStringExtra(EXTRA_HARI);
		jam_mulai = getIntent().getStringExtra(EXTRA_JAM_MULAI);
		jam_selesai = getIntent().getStringExtra(EXTRA_JAM_SELESAI);
		tanggal_prove = getIntent().getStringExtra(EXTRA_TANGGAL_PROVE);
		kode_prove = getIntent().getStringExtra(EXTRA_KODE_PROVE);
		kata_sandi = getIntent().getStringExtra(EXTRA_KATA_SANDI);
		nama_internal = getIntent().getStringExtra(EXTRA_NAMA_INTERNAL);
		status_prove = getIntent().getStringExtra(EXTRA_STATUS_PROVE);

		eksternalDetailProvePresenter = new EksternalDetailProvePresenter(this, this);
		eksternalDetailProvePresenter.onLoadSemuaData(id_prove);

		recyclerView = findViewById(R.id.recycle_view);

		toolbar = findViewById(R.id.toolbar);

		swipeRefreshLayout = findViewById(R.id.swipe_refresh_layout);

		swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
			@Override
			public void onRefresh() {
				// Your code to make your refresh action
				eksternalDetailProvePresenter.onLoadSemuaData(id_prove);

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

		ivKeluar.setOnClickListener(this);
		ivRating.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		if (v.getId() == R.id.iv_keluar) {
			showDialogKeluar();
		}
		if (v.getId() == R.id.iv_rating) {
			showDialogRating();
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

		if (hak_akses.equals("internal") || status_prove.equals("Batal")) {
			ivKeluar.setVisibility(View.GONE);
			ivRating.setVisibility(View.GONE);
			tvKeluar.setVisibility(View.GONE);
			tvRating.setVisibility(View.GONE);
		}

		tvNamaMateri.setText(nama_materi_prove);
		tvDetailJadwal.setText("Jadwal : " + hari_jadwal + " ( " + jam_mulai + " - " + jam_selesai + " )");
		tvTanggalProve.setText("Tanggal Prove : " + tanggal_prove);
		tvKodeProve.setText("Kode Prove : " + kode_prove);
		tvKataSandi.setText("Kata Sandi : " + kata_sandi);
		tvNamaInternal.setText("Pembimbing : " + nama_internal);
		tvStatusProve.setText("Status : " + status_prove);
	}

	@Override
	public void onSetupListView(ArrayList<Eksternal> dataModelArrayList) {
		adapterListEksternal = new AdapterListEksternal(this, dataModelArrayList);
		GridLayoutManager layoutManager = new GridLayoutManager(this, 1, GridLayoutManager.VERTICAL, false);
		recyclerView.setAdapter(adapterListEksternal);
		recyclerView.setLayoutManager(layoutManager);
		recyclerView.setNestedScrollingEnabled(true);
		adapterListEksternal.notifyDataSetChanged();

		adapterListEksternal.setOnItemClickListener(new AdapterListEksternal.ClickListener() {
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
	public void showDialogKeluar() {
		AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
			this);
		alertDialogBuilder.setTitle("Ingin Keluar Pertemuan Prove ?");
		alertDialogBuilder
			.setMessage("Klik Ya untuk Keluar !")
			.setPositiveButton("Ya", new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface dialog, int id) {

					try {

						HashMap<String, String> user = sessionManager.getDataUser();
						String id_eksternal = "";

						if (hak_akses.equals("eksternal")) {
							id_eksternal = user.get(sessionManager.ID_USER);
							eksternalDetailProvePresenter.onKeluarProve(id_prove, id_eksternal, id_jadwal_prove);

						} else {
							onErrorMessage("Harus Login Sebagai Eksternal !");
						}

					} catch (Exception e) {
						onErrorMessage("Terjadi Kesalahan " + e.toString());
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
	public void showDialogRating() {

		final AlertDialog.Builder popDialog = new AlertDialog.Builder(this);

		LinearLayout linearLayout = new LinearLayout(this);
		final RatingBar rating = new RatingBar(this);

		LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
			LinearLayout.LayoutParams.WRAP_CONTENT,
			LinearLayout.LayoutParams.WRAP_CONTENT
		);

		linearLayout.setGravity(Gravity.CENTER_HORIZONTAL);

		rating.setLayoutParams(lp);
		rating.setNumStars(5);
		rating.setStepSize(1);

		//add ratingBar to linearLayout
		linearLayout.addView(rating);

		popDialog.setIcon(android.R.drawable.btn_star_big_on);
		popDialog.setTitle("Tambahkan Rating Prove : ");

		//add linearLayout to dailog
		popDialog.setView(linearLayout);

		rating.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
			@Override
			public void onRatingChanged(RatingBar ratingBar, float v, boolean b) {
				System.out.println("Rated val:" + v);
			}
		});

		// Button OK
		popDialog.setPositiveButton(android.R.string.ok,
			new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface dialog, int which) {

					String val_rating = String.valueOf(rating.getProgress());

					HashMap<String, String> user = sessionManager.getDataUser();
					String hak_akses = user.get(sessionManager.HAK_AKSES);
					String id_eksternal = "";

					if (hak_akses.equals("eksternal")) {

						id_eksternal = user.get(sessionManager.ID_USER);

						eksternalDetailProvePresenter.onChangeRating(id_prove, id_eksternal, val_rating, id_jadwal_prove);
						onSuccessMessage("Anda Sudah Menyelesaikan Prove, Terima Kasih :)");
						backPressed();

					} else {
						onErrorMessage("Harus Login Sebagai Eksternal !");
					}
				}

			})

			// Button Cancel
			.setNegativeButton("Cancel",
				new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int id) {
						dialog.cancel();
					}
				});

		popDialog.create();
		popDialog.show();

	}

	@Override
	public void backPressed() {
		onBackPressed();
	}

	@Override
	public void keHalamanLain() {
		Intent intent = new Intent(getApplicationContext(), EksternalListProveActivity.class);
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
		eksternalDetailProvePresenter.onLoadSemuaData(id_prove);
	}
}
