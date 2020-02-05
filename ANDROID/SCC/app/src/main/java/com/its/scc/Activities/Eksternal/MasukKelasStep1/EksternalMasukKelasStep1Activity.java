package com.its.scc.Activities.Eksternal.MasukKelasStep1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.its.scc.Activities.Eksternal.MasukKelasStep1.view.IEksternalMasukKelasStep1View;
import com.its.scc.R;

public class EksternalMasukKelasStep1Activity extends AppCompatActivity implements View.OnClickListener, IEksternalMasukKelasStep1View {

	public static final String EXTRA_KODE_PROVE = "EXTRA_KODE_PROVE";
	public static final String EXTRA_KATA_SANDI = "EXTRA_KATA_SANDI";
	String kode_prove = "";
	String kata_sandi = "";

	TextView tvKodeProve;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_eksternal_masuk_kelas_step1);

		kode_prove = getIntent().getStringExtra(EXTRA_KODE_PROVE);
		kata_sandi = getIntent().getStringExtra(EXTRA_KATA_SANDI);

		tvKodeProve = findViewById(R.id.tv_kode_prove);
		tvKodeProve.setText("Kode Masuk : " + kode_prove);
	}

	@Override
	public void onClick(View v) {

	}
}
