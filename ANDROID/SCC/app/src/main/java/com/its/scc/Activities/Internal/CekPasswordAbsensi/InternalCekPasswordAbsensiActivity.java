package com.its.scc.Activities.Internal.CekPasswordAbsensi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.its.scc.Activities.Internal.CekPasswordAbsensi.presenter.IInternalCekPasswordAbsensiPresenter;
import com.its.scc.Activities.Internal.CekPasswordAbsensi.presenter.InternalCekPasswordAbsensiPresenter;
import com.its.scc.Activities.Internal.CekPasswordAbsensi.view.IInternalCekPasswordAbsensiView;
import com.its.scc.Activities.Internal.DetailAbsensi.InternalDetailAbsensiActivity;
import com.its.scc.Controllers.SessionManager;
import com.its.scc.R;

import java.util.HashMap;

import es.dmoral.toasty.Toasty;

public class InternalCekPasswordAbsensiActivity extends AppCompatActivity implements View.OnClickListener, IInternalCekPasswordAbsensiView {

	public static final String EXTRA_ID_ABSENSI = "EXTRA_ID_ABSENSI";
	public static final String EXTRA_JUDUL_ABSENSI = "EXTRA_JUDUL_ABSENSI";
	public static final String EXTRA_TGL_ABSENSI = "EXTRA_TGL_ABSENSI";
	public static final String EXTRA_JAM_MULAI = "EXTRA_JAM_MULAI";
	public static final String EXTRA_JAM_SELESAI = "EXTRA_JAM_SELESAI";
	public static final String EXTRA_STATUS_ABSENSI = "EXTRA_STATUS_ABSENSI";
	public static final String EXTRA_KATA_SANDI = "EXTRA_KATA_SANDI";

	String id_absensi = "";
	String judul_absensi = "";
	String tgl_absensi = "";
	String jam_mulai = "";
	String jam_selesai = "";
	String status_absensi = "";
	String kata_sandi = "";

	String v_kata_sandi = "dd";

	EditText edtKataSandi;
	ImageButton iBtnKataSandi;

	IInternalCekPasswordAbsensiPresenter internalCekPasswordAbsensiPresenter;

	SessionManager sessionManager;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_internal_cek_password_absensi);

		internalCekPasswordAbsensiPresenter = new InternalCekPasswordAbsensiPresenter(this, this);
		sessionManager = new SessionManager(this);

		edtKataSandi = findViewById(R.id.edt_kata_sandi);
		iBtnKataSandi = findViewById(R.id.i_btn_kata_sandi);

		id_absensi = getIntent().getStringExtra(EXTRA_ID_ABSENSI);
		judul_absensi = getIntent().getStringExtra(EXTRA_JUDUL_ABSENSI);
		tgl_absensi = getIntent().getStringExtra(EXTRA_TGL_ABSENSI);
		jam_mulai = getIntent().getStringExtra(EXTRA_JAM_MULAI);
		jam_selesai = getIntent().getStringExtra(EXTRA_JAM_SELESAI);
		status_absensi = getIntent().getStringExtra(EXTRA_STATUS_ABSENSI);
		kata_sandi = getIntent().getStringExtra(EXTRA_KATA_SANDI);

		iBtnKataSandi.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		if (v.getId() == R.id.i_btn_kata_sandi) {

			v_kata_sandi = edtKataSandi.getText().toString().trim();

			// cek apakah kata sandi cocok ?
			if (kata_sandi.equals(v_kata_sandi)) {

				HashMap<String, String> user = sessionManager.getDataUser();
				String id_internal = user.get(sessionManager.ID_USER);

				internalCekPasswordAbsensiPresenter.onSubmit(
					"" + id_absensi,
					"" + id_internal);

			} else {
				onErrorMessage("Kata Sandi Salah !");
			}
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
	public void keHalamanLain() {
		Intent intent = new Intent(getApplicationContext(), InternalDetailAbsensiActivity.class);
		intent.putExtra(InternalDetailAbsensiActivity.EXTRA_ID_ABSENSI, id_absensi);
		// intent.putExtra(InternalDetailAbsensiActivity.EXTRA_ID_INTERNAL, id_internal);
		intent.putExtra(InternalDetailAbsensiActivity.EXTRA_JUDUL_ABSENSI, judul_absensi);
		intent.putExtra(InternalDetailAbsensiActivity.EXTRA_TGL_ABSENSI, tgl_absensi);
		intent.putExtra(InternalDetailAbsensiActivity.EXTRA_JAM_MULAI, jam_mulai);
		intent.putExtra(InternalDetailAbsensiActivity.EXTRA_JAM_SELESAI, jam_selesai);
		intent.putExtra(InternalDetailAbsensiActivity.EXTRA_STATUS_ABSENSI, status_absensi);
		intent.putExtra(InternalDetailAbsensiActivity.EXTRA_KATA_SANDI, kata_sandi);
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
