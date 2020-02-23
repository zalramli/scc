package com.its.scc.Activities.Eksternal.DetailBankSoftware;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.its.scc.Activities.Eksternal.DetailBankSoftware.view.IEksternalDetailBankSoftwareView;
import com.its.scc.Models.Software;
import com.its.scc.R;

import java.util.ArrayList;

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

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_eksternal_detail_bank_software);

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


	}

	@Override
	public void onClick(View v) {

	}

	@Override
	public void initActionBar() {

	}

	@Override
	public void setNilaiDefault() {

	}

	@Override
	public void onSetupListView(ArrayList<Software> dataModelArrayList) {

	}

	@Override
	public void onSuccessMessage(String message) {

	}

	@Override
	public void onErrorMessage(String message) {

	}

	@Override
	public void showDialogBatal() {

	}

	@Override
	public void showDialogSelesai() {

	}

	@Override
	public void backPressed() {

	}

	@Override
	public void keHalamanLain() {

	}
}
