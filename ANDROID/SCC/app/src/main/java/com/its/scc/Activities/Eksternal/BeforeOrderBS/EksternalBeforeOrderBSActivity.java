package com.its.scc.Activities.Eksternal.BeforeOrderBS;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.its.scc.Activities.Eksternal.BeforeOrderBS.presenter.IEksternalBeforeOrderBSPresenter;
import com.its.scc.Activities.Eksternal.BeforeOrderBS.view.IEksternalBeforeOrderBSView;
import com.its.scc.Activities.Eksternal.ListSoftware.EksternalListSoftwareActivity;
import com.its.scc.Activities.Eksternal._Home.EksternalHomeActivity;
import com.its.scc.Controllers.SessionManager;
import com.its.scc.Models.Software;
import com.its.scc.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Locale;

import es.dmoral.toasty.Toasty;

public class EksternalBeforeOrderBSActivity extends AppCompatActivity implements View.OnClickListener, IEksternalBeforeOrderBSView {

	public static final String EXTRA_ID_JADWAL_BS = "EXTRA_ID_JADWAL_BS";
	public static final String EXTRA_ID_INTERNAL = "EXTRA_ID_INTERNAL";
	public static final String EXTRA_HARI = "EXTRA_HARI";
	public static final String EXTRA_JAM_MULAI = "EXTRA_JAM_MULAI";
	public static final String EXTRA_JAM_SELESAI = "EXTRA_JAM_SELESAI";
	public static final String EXTRA_STATUS_BOOKING = "EXTRA_STATUS_BOOKING";
	public static final String EXTRA_STATUS_AKTIF = "EXTRA_STATUS_AKTIF";

	String id_jadwal_bs, id_internal, hari, jam_mulai, jam_selesai, status_booking, status_aktif;

	IEksternalBeforeOrderBSPresenter eksternalBeforeOrderBSPresenter;

	private RecyclerView recyclerView;

	Toolbar toolbar;

	private SwipeRefreshLayout swipeRefreshLayout;

	private DatePickerDialog datePickerDialog;
	private SimpleDateFormat dateFormatter;
	private SimpleDateFormat dayFormatter;

	TextView tvDetailBs, tvTanggalBs;
	Button btnBatal, btnSubmit;

	FloatingActionButton fab;

	String selected_hari = "";

	SessionManager sessionManager;
	String id_eksternal = "";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_eksternal_before_order_bs);

		dateFormatter = new SimpleDateFormat("yyyy-MM-dd", Locale.US);//"dd-MM-yyyy" "yyyy-MM-dd"
		dayFormatter = new SimpleDateFormat("EEE", Locale.US);//hari dalam english

		sessionManager = new SessionManager(this);
		HashMap<String, String> user = sessionManager.getDataUser();

		String hakAkses = user.get(sessionManager.HAK_AKSES);

		if (hakAkses.equals("eksternal")) {
			id_eksternal = user.get(sessionManager.ID_USER);
		}

		id_jadwal_bs = getIntent().getStringExtra(EXTRA_ID_JADWAL_BS);
		id_internal = getIntent().getStringExtra(EXTRA_ID_INTERNAL);
		hari = getIntent().getStringExtra(EXTRA_HARI);
		jam_mulai = getIntent().getStringExtra(EXTRA_JAM_MULAI);
		jam_selesai = getIntent().getStringExtra(EXTRA_JAM_SELESAI);
		status_booking = getIntent().getStringExtra(EXTRA_STATUS_BOOKING);
		status_aktif = getIntent().getStringExtra(EXTRA_STATUS_AKTIF);

		tvDetailBs = findViewById(R.id.tv_detail_bs);
		tvTanggalBs = findViewById(R.id.tv_tanggal_bs);
		btnBatal = findViewById(R.id.btn_batal);
		btnSubmit = findViewById(R.id.btn_submit);

		fab = findViewById(R.id.fab);

		toolbar = findViewById(R.id.toolbar);
		initActionBar();

		setNilaiDefault();

		tvTanggalBs.setOnClickListener(this);
		btnSubmit.setOnClickListener(this);
		btnBatal.setOnClickListener(this);
		fab.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		if (v.getId() == R.id.tv_tanggal_bs) {
			showDateDialog();
		}
		if (v.getId() == R.id.btn_submit) {
			showDialog();
		}
		if (v.getId() == R.id.btn_batal) {
			keHalamanLain();
		}
		if (v.getId() == R.id.fab) {
			Intent intent = new Intent(getApplicationContext(), EksternalListSoftwareActivity.class);
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
	public void setNilaiDefault() {
		tvDetailBs.setText(hari + " ( " + jam_mulai + " - " + jam_selesai + " )");
	}

	@Override
	public void onSetupListView(ArrayList<Software> dataModelArrayList) {

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
	public void showDateDialog() {
		/**
		 * Calendar untuk mendapatkan tanggal sekarang
		 */
		Calendar newCalendar = Calendar.getInstance();

		/**
		 * Initiate DatePicker dialog
		 */
		datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {

			@Override
			public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {

				/**
				 * Method ini dipanggil saat kita selesai memilih tanggal di DatePicker
				 */

				/**
				 * Set Calendar untuk menampung tanggal yang dipilih
				 */
				Calendar newDate = Calendar.getInstance();
				newDate.set(year, monthOfYear, dayOfMonth);

				/**
				 * Update TextView dengan tanggal yang kita pilih
				 */

				selected_hari = dayFormatter.format(newDate.getTime());
				// Sunday, Monday, Tuesday, Wednesday, Thursday, Friday, Saturday

				if (selected_hari.equals("Sun")) {
					selected_hari = "Minggu";
				} else if (selected_hari.equals("Mon")) {
					selected_hari = "Senin";
				} else if (selected_hari.equals("Tue")) {
					selected_hari = "Selasa";
				} else if (selected_hari.equals("Wed")) {
					selected_hari = "Rabu";
				} else if (selected_hari.equals("Thu")) {
					selected_hari = "Kamis";
				} else if (selected_hari.equals("Fri")) {
					selected_hari = "Jumat";
				} else if (selected_hari.equals("Sat")) {
					selected_hari = "Sabtu";
				}

				tvTanggalBs.setText(dateFormatter.format(newDate.getTime()));
			}

		}, newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));

		// membuat minimal date hari +1
		newCalendar.add(Calendar.DATE, 1);
		datePickerDialog.getDatePicker().setMinDate(newCalendar.getTimeInMillis());

		// membuat maximal date hari +13
		newCalendar.add(Calendar.DATE, 13);
		datePickerDialog.getDatePicker().setMaxDate(newCalendar.getTimeInMillis());

		// mengembalikan date seperti semula
		newCalendar.add(Calendar.DATE, -14);

		/**
		 * Tampilkan DatePicker dialog
		 */
		datePickerDialog.show();
	}

	@Override
	public void showDialog() {
		AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
			this);
		alertDialogBuilder.setTitle("Ingin Membuat Bank Software ?");
		alertDialogBuilder
			.setMessage("Klik Ya untuk melakukan Bank Software !")
			.setPositiveButton("Ya", new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface dialog, int id) {

					String inputTanggalBS = tvTanggalBs.getText().toString().trim();

					boolean isEmpty = false;

					if (inputTanggalBS.equals("Tanggal Bank Software")) {
						isEmpty = true;
						tvTanggalBs.setError("Pilih Tanggal Kapan Bank Software !");
						onErrorMessage("Pilih Tanggal Kapan Bank Software !");
					} else if (TextUtils.isEmpty(id_eksternal)) {
						isEmpty = true;
						onErrorMessage("Error , Lakukan Login Kembali !");
					}

					try {

						if (!isEmpty) {
							if (selected_hari.equals(hari)) {
								// eksternalBeforeOrderBSPresenter.onSubmit(id_eksternal, id_jadwal_bs, inputTanggalBS);
							} else {
								tvTanggalBs.setError("Pilih Hari Sesuai Jadwal Yang Tersedia !");
								onErrorMessage("Hari Dalam Tanggal Bank Software Tidak Sesuai Jadwal !");
							}
						}

					} catch (Exception e) {
						onErrorMessage("Terjadi Kesalahan Daftar " + e.toString());
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

//		Intent intent = new Intent(getApplicationContext(), EksternalListProveActivity.class);
//		intent.putExtra(EksternalListProveActivity.EXTRA_TUJUAN, "kosong");
//		intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//		startActivity(intent);

	}

	@Override
	public void keHalamanLain() {
		Intent intent = new Intent(getApplicationContext(), EksternalHomeActivity.class);
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
}
