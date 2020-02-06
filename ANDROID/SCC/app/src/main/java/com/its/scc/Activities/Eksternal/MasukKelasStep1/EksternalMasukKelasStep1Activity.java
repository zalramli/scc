package com.its.scc.Activities.Eksternal.MasukKelasStep1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.its.scc.Activities.Eksternal.ListProve.EksternalListProveActivity;
import com.its.scc.Activities.Eksternal.MasukKelasStep1.presenter.EksternalMasukKelasStep1Presenter;
import com.its.scc.Activities.Eksternal.MasukKelasStep1.presenter.IEksternalMasukKelasStep1Presenter;
import com.its.scc.Activities.Eksternal.MasukKelasStep1.view.IEksternalMasukKelasStep1View;
import com.its.scc.Activities.Eksternal._Home.EksternalHomeActivity;
import com.its.scc.Controllers.SessionManager;
import com.its.scc.R;

import java.util.HashMap;

import es.dmoral.toasty.Toasty;

public class EksternalMasukKelasStep1Activity extends AppCompatActivity implements View.OnClickListener, IEksternalMasukKelasStep1View {

	public static final String EXTRA_KODE_PROVE = "EXTRA_KODE_PROVE";
	public static final String EXTRA_KATA_SANDI = "EXTRA_KATA_SANDI";
	String kode_prove = "";
	String kata_sandi = "";

	String v_kata_sandi = "dd";

	TextView tvKodeProve;
	EditText edtKataSandi;
	ImageButton iBtnKataSandi;

	IEksternalMasukKelasStep1Presenter eksternalMasukKelasStep1Presenter;

	SessionManager sessionManager;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_eksternal_masuk_kelas_step1);

		eksternalMasukKelasStep1Presenter = new EksternalMasukKelasStep1Presenter(this, this);
		sessionManager = new SessionManager(this);

		tvKodeProve = findViewById(R.id.tv_kode_prove);
		edtKataSandi = findViewById(R.id.edt_kata_sandi);
		iBtnKataSandi = findViewById(R.id.i_btn_kata_sandi);

		kode_prove = getIntent().getStringExtra(EXTRA_KODE_PROVE);
		kata_sandi = getIntent().getStringExtra(EXTRA_KATA_SANDI);

		tvKodeProve.setText("Kode Masuk : " + kode_prove);

		iBtnKataSandi.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		if (v.getId() == R.id.i_btn_kata_sandi) {

			v_kata_sandi = edtKataSandi.getText().toString().trim();

			// cek apakah kata sandi cocok ?
			if (kata_sandi.equals(v_kata_sandi)) {

				HashMap<String, String> user = sessionManager.getDataUser();
				String id_eksternal = user.get(sessionManager.ID_USER);

				eksternalMasukKelasStep1Presenter.onSubmit(kode_prove, id_eksternal);

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
		Intent intent = new Intent(getApplicationContext(), EksternalListProveActivity.class);
		intent.putExtra(EksternalListProveActivity.EXTRA_TUJUAN, "kosong");
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
