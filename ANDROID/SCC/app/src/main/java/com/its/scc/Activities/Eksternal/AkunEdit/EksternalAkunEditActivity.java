package com.its.scc.Activities.Eksternal.AkunEdit;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.its.scc.Activities.Eksternal.AkunEdit.presenter.EksternalAkunPresenter;
import com.its.scc.Activities.Eksternal.AkunEdit.presenter.IEksternalAkunPresenter;
import com.its.scc.Activities.Eksternal.AkunEdit.view.IEksternalAkunView;
import com.its.scc.R;
import com.squareup.picasso.MemoryPolicy;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;

import java.io.IOException;

import es.dmoral.toasty.Toasty;

public class EksternalAkunEditActivity extends AppCompatActivity implements View.OnClickListener, IEksternalAkunView {

	IEksternalAkunPresenter eksternalAkunPresenter;

	Toolbar toolbar;

	ImageView ivFoto;
	EditText edtNama, edtNoHp, edtAkunLine, edtUsername, edtPassword, edtKonfirmasiPassword, edtAngkatan;
	Button btnUpdate;

	private Bitmap bitmap;
	String data_photo = "";

	public static final String EXTRA_ID_EKSTERNAL = "EXTRA_ID_EKSTERNAL";
	String id_eksternal = "";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_eksternal_akun_edit);

		toolbar = findViewById(R.id.toolbar);
		initActionBar();

		ivFoto = findViewById(R.id.iv_foto);
		edtNama = findViewById(R.id.edt_nama);
		edtNoHp = findViewById(R.id.edt_no_hp);
		edtAkunLine = findViewById(R.id.edt_akun_line);
		edtUsername = findViewById(R.id.edt_username);
		edtPassword = findViewById(R.id.edt_password);
		edtKonfirmasiPassword = findViewById(R.id.edt_konfirmasi_password);
		edtAngkatan = findViewById(R.id.edt_angkatan);

		btnUpdate = findViewById(R.id.btn_update);

		eksternalAkunPresenter = new EksternalAkunPresenter(this, this);
		id_eksternal = getIntent().getStringExtra(EXTRA_ID_EKSTERNAL);
		eksternalAkunPresenter.inisiasiAwal(id_eksternal);

		ivFoto.setOnClickListener(this);
		btnUpdate.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		if (v.getId() == R.id.iv_foto) {
			Intent intent = new Intent();
			intent.setType("image/*");
			intent.setAction(Intent.ACTION_GET_CONTENT);
			startActivityForResult(Intent.createChooser(intent, "Pilih Gambar"), 1);
		}
		if (v.getId() == R.id.btn_update) {
			showDialog();
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
	public void setNilaiDefault(String nama, String no_hp, String akun_line, String username, String angkatan, String foto) {
		edtNama.setText(nama);
		edtNoHp.setText(no_hp);
		edtAkunLine.setText(akun_line);
		edtUsername.setText(username);
		edtAngkatan.setText(angkatan);
		Picasso.get().load(foto).placeholder(R.drawable.ic_account_circle).memoryPolicy(MemoryPolicy.NO_CACHE, MemoryPolicy.NO_STORE).networkPolicy(NetworkPolicy.NO_CACHE, NetworkPolicy.NO_STORE).into(ivFoto);
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
	public void showDialog() {
		AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
			this);
		alertDialogBuilder.setTitle("Ingin Memperbarui Data Akun ?");
		alertDialogBuilder
			.setMessage("Klik Ya untuk melakukan update !")
			.setPositiveButton("Ya", new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface dialog, int id) {

					String inputNama = edtNama.getText().toString().trim();
					String inputNoHp = edtNoHp.getText().toString().trim();
					String inputAkunLine = edtAkunLine.getText().toString().trim();
					String inputUsername = edtUsername.getText().toString().trim();
					String inputPassword = edtPassword.getText().toString().trim();
					String inputKonfirmasi_password = edtKonfirmasiPassword.getText().toString().trim();
					String inputAngkatan = edtAngkatan.getText().toString().trim();

					String inputFoto = data_photo;

					boolean isEmpty = false;
					boolean isInvalidKonfirmasi = false;

					if (TextUtils.isEmpty(inputNama)) {
						isEmpty = true;
						edtNama.setError("Isi Data Dengan Lengkap");
					} else if (TextUtils.isEmpty(inputNoHp)) {
						isEmpty = true;
						edtNoHp.setError("Isi Data Dengan Lengkap");
					} else if (TextUtils.isEmpty(inputAkunLine)) {
						isEmpty = true;
						edtAkunLine.setError("Isi Data Dengan Lengkap");
					} else if (TextUtils.isEmpty(inputUsername)) {
						isEmpty = true;
						edtUsername.setError("Isi Data Dengan Lengkap");
					} else if (TextUtils.isEmpty(inputPassword)) {
						isEmpty = true;
						edtPassword.setError("Isi Data Dengan Lengkap");
					} else if (TextUtils.isEmpty(inputKonfirmasi_password)) {
						isEmpty = true;
						edtKonfirmasiPassword.setError("Isi Data Dengan Lengkap");
					} else if (TextUtils.isEmpty(inputAngkatan)) {
						isEmpty = true;
						edtAngkatan.setError("Isi Data Dengan Lengkap");
					}

					if (!inputPassword.equals(inputKonfirmasi_password)) {
						isInvalidKonfirmasi = true;
						edtKonfirmasiPassword.setError("Konfirmasi Password Salah");
					}

					try {

						if (!isEmpty && !isInvalidKonfirmasi) {
							eksternalAkunPresenter.onUpdate(id_eksternal, inputNama, inputNoHp, inputAkunLine, inputUsername, inputPassword, inputAngkatan, inputFoto);
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
		onBackPressed();
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

	// proses pengolahan gambar
	@Override
	protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
		super.onActivityResult(requestCode, resultCode, data);

		if (requestCode == 1 && resultCode == RESULT_OK && data != null && data.getData() != null) {
			Uri filePath = data.getData();
			try {

				bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), filePath);
				ivFoto.setImageBitmap(bitmap);

			} catch (IOException e) {
				e.printStackTrace();
				onErrorMessage("Gambar Error " + e.toString());
			}

			data_photo = eksternalAkunPresenter.getStringImage(bitmap);
		}
	}
}
