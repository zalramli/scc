package com.its.scc.Activities.Internal.CreateAbsensi;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.its.scc.Activities.Eksternal._Home.EksternalHomeActivity;
import com.its.scc.Activities.Internal.CreateAbsensi.presenter.IInternalCreateAbsensiPresenter;
import com.its.scc.Activities.Internal.CreateAbsensi.presenter.InternalCreateAbsensiPresenter;
import com.its.scc.Activities.Internal.CreateAbsensi.view.IInternalCreateAbsensiView;
import com.its.scc.Activities.Internal.ListAbsensi.InternalListAbsensiActivity;
import com.its.scc.Controllers.SessionManager;
import com.its.scc.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Locale;

import es.dmoral.toasty.Toasty;

public class InternalCreateAbsensiActivity extends AppCompatActivity implements View.OnClickListener, IInternalCreateAbsensiView {

	IInternalCreateAbsensiPresenter internalCreateAbsensiPresenter;

	Toolbar toolbar;

	EditText edtJudulAbsensi;
	TextView tvTglAbsensi, tvJamMulai, tvJamSelesai;
	Button btnSubmit, btnBatal;

	private DatePickerDialog datePickerDialog;
	private SimpleDateFormat dateFormatter;
	private SimpleDateFormat dayFormatter;

	String selected_hari = "";

	SessionManager sessionManager;
	String id_internal = "";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_internal_create_absensi);

		sessionManager = new SessionManager(this);
		HashMap<String, String> user = sessionManager.getDataUser();

		String hakAkses = user.get(sessionManager.HAK_AKSES);

		if (hakAkses.equals("internal")) {
			id_internal = user.get(sessionManager.ID_USER);
		}

		toolbar = findViewById(R.id.toolbar);
		initActionBar();

		dateFormatter = new SimpleDateFormat("yyyy-MM-dd", Locale.US);//"dd-MM-yyyy" "yyyy-MM-dd"
		dayFormatter = new SimpleDateFormat("EEE", Locale.US);//hari dalam english

		internalCreateAbsensiPresenter = new InternalCreateAbsensiPresenter(this, this);

		tvTglAbsensi = findViewById(R.id.tv_tgl_absensi);
		tvJamMulai = findViewById(R.id.tv_jam_mulai);
		tvJamSelesai = findViewById(R.id.tv_jam_selesai);
		edtJudulAbsensi = findViewById(R.id.edt_judul_absensi);
		btnSubmit = findViewById(R.id.btn_submit);
		btnBatal = findViewById(R.id.btn_batal);

		tvTglAbsensi.setOnClickListener(this);
		tvJamMulai.setOnClickListener(this);
		tvJamSelesai.setOnClickListener(this);
		btnSubmit.setOnClickListener(this);
		btnBatal.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		if (v.getId() == R.id.tv_tgl_absensi) {
			showDateDialog();
		}
		if (v.getId() == R.id.tv_jam_mulai) {
			showTimeDialog();
		}
		if (v.getId() == R.id.tv_jam_selesai) {
			showTimeDialog();
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

				tvTglAbsensi.setText(dateFormatter.format(newDate.getTime()));
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
	public void showTimeDialog() {

	}

	@Override
	public void showDialog() {
		AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
			this);
		alertDialogBuilder.setTitle("Ingin Membuat Absensi ?");
		alertDialogBuilder
			.setMessage("Klik Ya untuk Membuat Absensi !")
			.setPositiveButton("Ya", new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface dialog, int id) {

					String inputTglAbsensi = tvTglAbsensi.getText().toString().trim();
					String inputJamMulai = tvJamMulai.getText().toString().trim();
					String inputJamSelesai = tvJamSelesai.getText().toString().trim();
					String inputJudulAbsensi = edtJudulAbsensi.getText().toString().trim();

					boolean isEmpty = false;

					if (inputTglAbsensi.equals("Tanggal Acara")) {

						isEmpty = true;
						tvTglAbsensi.setError("Pilih Tanggal Absensi !");
						onErrorMessage("Pilih Tanggal Absensi !");

					} else if (inputJamMulai.equals("Jam Mulai")) {

						isEmpty = true;
						tvJamMulai.setError("Pilih Jam Mulai !");
						onErrorMessage("Pilih Tanggal Absensi !");

					} else if (inputJamSelesai.equals("Jam Selesai")) {

						isEmpty = true;
						tvJamSelesai.setError("Pilih Jam Selesai !");
						onErrorMessage("Pilih Tanggal Absensi !");

					} else if (TextUtils.isEmpty(inputJudulAbsensi)) {

						isEmpty = true;
						edtJudulAbsensi.setError("Isi Data Dengan Lengkap");

					} else if (TextUtils.isEmpty(id_internal)) {

						isEmpty = true;
						onErrorMessage("Error , Lakukan Login Kembali");

					}

					try {

						if (!isEmpty) {
							internalCreateAbsensiPresenter.onSubmit(
								"" + id_internal,
								"" + inputJudulAbsensi,
								"" + inputTglAbsensi,
								"" + inputJamMulai,
								"" + inputJamSelesai);
						}

					} catch (Exception e) {
						onErrorMessage("Terjadi Kesalahan Submit " + e.toString());
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
		Intent intent = new Intent(getApplicationContext(), InternalListAbsensiActivity.class);
		intent.putExtra(InternalListAbsensiActivity.EXTRA_TUJUAN, "kosong");
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
