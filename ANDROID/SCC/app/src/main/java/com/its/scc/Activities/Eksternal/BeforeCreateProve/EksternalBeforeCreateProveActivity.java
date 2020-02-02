package com.its.scc.Activities.Eksternal.BeforeCreateProve;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.its.scc.Activities.Eksternal.BeforeCreateProve.presenter.EksternalBeforeCreateProvePresenter;
import com.its.scc.Activities.Eksternal.BeforeCreateProve.presenter.IEksternalBeforeCreateProvePresenter;
import com.its.scc.Activities.Eksternal.BeforeCreateProve.view.IEksternalBeforeCreateProveView;
import com.its.scc.Activities.Eksternal.ListProve.EksternalListProveActivity;
import com.its.scc.Activities.Eksternal._Home.EksternalHomeActivity;
import com.its.scc.Controllers.SessionManager;
import com.its.scc.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;

import es.dmoral.toasty.Toasty;

public class EksternalBeforeCreateProveActivity extends AppCompatActivity implements View.OnClickListener, IEksternalBeforeCreateProveView {

	public static final String EXTRA_ID_MATERI_PROVE = "EXTRA_ID_MATERI_PROVE";
	public static final String EXTRA_NAMA_MATERI_PROVE = "EXTRA_NAMA_MATERI_PROVE";
	public static final String EXTRA_ID_INTERNAL = "EXTRA_ID_INTERNAL";
	public static final String EXTRA_NAMA_INTERNAL = "EXTRA_NAMA_INTERNAL";
	public static final String EXTRA_ID_JADWAL = "EXTRA_ID_JADWAL";
	public static final String EXTRA_HARI_JADWAL = "EXTRA_HARI_JADWAL";
	public static final String EXTRA_JAM_MULAI = "EXTRA_JAM_MULAI";
	public static final String EXTRA_JAM_SELESAI = "EXTRA_JAM_SELESAI";
	String id_materi_prove = "";
	String nama_materi_prove = "";
	String id_internal = "";
	String nama_internal = "";
	String id_jadwal = "";
	String hari_jadwal = "";
	String jam_mulai = "";
	String jam_selesai = "";

	IEksternalBeforeCreateProvePresenter eksternalBeforeCreateProvePresenter;

	Toolbar toolbar;

	TextView tvNamaMateri, tvDetailProve, tvNamaInternal, tvTanggalProve;
	EditText edtDeskripsiMateri;
	Button btnSubmit, btnBatal;

	private DatePickerDialog datePickerDialog;
	private SimpleDateFormat dateFormatter;
	private SimpleDateFormat dayFormatter;

	String selected_hari = "";

	SessionManager sessionManager;
	String id_eksternal = "";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_eksternal_before_create_prove);

		sessionManager = new SessionManager(this);
		HashMap<String, String> user = sessionManager.getDataUser();

		String hakAkses = user.get(sessionManager.HAK_AKSES);

		if (hakAkses.equals("eksternal")) {
			id_eksternal = user.get(sessionManager.ID_USER);
		}

		id_materi_prove = getIntent().getStringExtra(EXTRA_ID_MATERI_PROVE);
		nama_materi_prove = getIntent().getStringExtra(EXTRA_NAMA_MATERI_PROVE);
		id_internal = getIntent().getStringExtra(EXTRA_ID_INTERNAL);
		nama_internal = getIntent().getStringExtra(EXTRA_NAMA_INTERNAL);
		id_jadwal = getIntent().getStringExtra(EXTRA_ID_JADWAL);
		hari_jadwal = getIntent().getStringExtra(EXTRA_HARI_JADWAL);
		jam_mulai = getIntent().getStringExtra(EXTRA_JAM_MULAI);
		jam_selesai = getIntent().getStringExtra(EXTRA_JAM_SELESAI);

		toolbar = findViewById(R.id.toolbar);
		initActionBar();

		dateFormatter = new SimpleDateFormat("yyyy-MM-dd", Locale.US);//"dd-MM-yyyy" "yyyy-MM-dd"
		dayFormatter = new SimpleDateFormat("EEE", Locale.US);//hari dalam english

		eksternalBeforeCreateProvePresenter = new EksternalBeforeCreateProvePresenter(this, this);

		tvNamaMateri = findViewById(R.id.tv_nama_materi);
		tvDetailProve = findViewById(R.id.tv_detail_prove);
		tvNamaInternal = findViewById(R.id.tv_nama_internal);
		tvTanggalProve = findViewById(R.id.tv_tanggal_prove);
		edtDeskripsiMateri = findViewById(R.id.edt_deskripsi_materi);
		btnSubmit = findViewById(R.id.btn_submit);
		btnBatal = findViewById(R.id.btn_batal);

		setNilaiDefault();

		tvTanggalProve.setOnClickListener(this);
		btnSubmit.setOnClickListener(this);
		btnBatal.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		if (v.getId() == R.id.tv_tanggal_prove) {
			showDateDialog();
		}
		if (v.getId() == R.id.btn_submit) {
			showDialog();
		}
		if (v.getId() == R.id.btn_batal) {
			keHalamanLain();
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
		tvNamaMateri.setText(nama_materi_prove);
		tvNamaInternal.setText("Nama : " + nama_internal);
		tvDetailProve.setText("Jadwal : " + hari_jadwal + " ( " + jam_mulai + " - " + jam_selesai + " )");
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

				tvTanggalProve.setText(dateFormatter.format(newDate.getTime()));
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
		alertDialogBuilder.setTitle("Ingin Membuat Prove ?");
		alertDialogBuilder
			.setMessage("Klik Ya untuk melakukan Prove !")
			.setPositiveButton("Ya", new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface dialog, int id) {

					String inputDeskripsiMateri = edtDeskripsiMateri.getText().toString().trim();
					String inputTanggalProve = tvTanggalProve.getText().toString().trim();

					boolean isEmpty = false;

					if (TextUtils.isEmpty(inputDeskripsiMateri)) {
						isEmpty = true;
						edtDeskripsiMateri.setError("Isi Data Dengan Lengkap");
					} else if (inputTanggalProve.equals("Tanggal Prove")) {
						isEmpty = true;
						tvTanggalProve.setError("Pilih Tanggal Kapan Prove !");
						onErrorMessage("Pilih Tanggal Kapan Prove !");
					} else if (TextUtils.isEmpty(id_eksternal)) {
						isEmpty = true;
						onErrorMessage("Error , Lakukan Login Kembali 1");
					}

					try {

						if (!isEmpty) {
							if (selected_hari.equals(hari_jadwal)) {
								eksternalBeforeCreateProvePresenter.onSubmit(id_eksternal, id_internal, id_materi_prove, id_jadwal, inputDeskripsiMateri, inputTanggalProve);
							} else {
								tvTanggalProve.setError("Pilih Hari Sesuai Jadwal Yang Tersedia !");
								onErrorMessage("Hari Dalam Tanggal Prove Tidak Sesuai Jadwal !");
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
		Intent intent = new Intent(getApplicationContext(), EksternalListProveActivity.class);
		intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		startActivity(intent);
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
