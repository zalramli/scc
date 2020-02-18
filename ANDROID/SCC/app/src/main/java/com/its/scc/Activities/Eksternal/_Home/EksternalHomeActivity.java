package com.its.scc.Activities.Eksternal._Home;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;
import com.its.scc.Activities.Eksternal.AkunEdit.EksternalAkunEditActivity;
import com.its.scc.Activities.Eksternal.ListJadwalBS.EksternalListJadwalBSActivity;
import com.its.scc.Activities.Eksternal.ListMateri.EksternalListMateriActivity;
import com.its.scc.Activities.Eksternal.ListProve.EksternalListProveActivity;
import com.its.scc.Activities.Eksternal._Home.presenter.EksternalHomePresenter;
import com.its.scc.Activities.Eksternal._Home.presenter.IEksternalHomePresenter;
import com.its.scc.Activities.Eksternal._Home.view.IEksternalHomeView;
import com.its.scc.Controllers.SessionManager;
import com.its.scc.R;

import java.util.HashMap;

import es.dmoral.toasty.Toasty;

public class EksternalHomeActivity extends AppCompatActivity implements View.OnClickListener, IEksternalHomeView {

	private DrawerLayout drawerLayout;
	private ActionBarDrawerToggle actionBarDrawerToggle;
	private NavigationView navigationView;

	SessionManager sessionManager;

	CardView cvLinkProve, cvLinkListProve, cvLinkBs;

	EditText edtKodeProve;

	ImageButton iBtnCariKodeProve;

	IEksternalHomePresenter eksternalHomePresenter;

	String kode_prove = "";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_eksternal_home);

		drawerLayout = findViewById(R.id.drawer_layout_eksternal_home);
		navigationView = findViewById(R.id.navigation_view_eksternal);

		sessionManager = new SessionManager(this);

		cvLinkProve = findViewById(R.id.cv_link_prove); // link card view prove
		cvLinkListProve = findViewById(R.id.cv_link_list_prove); // link card view prove
		cvLinkBs = findViewById(R.id.cv_link_bs);

		edtKodeProve = findViewById(R.id.edt_kode_prove);

		iBtnCariKodeProve = findViewById(R.id.i_btn_cari_kode_prove);

		eksternalHomePresenter = new EksternalHomePresenter(this, this);

		actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.Open, R.string.Close);
		drawerLayout.addDrawerListener(actionBarDrawerToggle);
		actionBarDrawerToggle.syncState();
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
			@Override
			public boolean onNavigationItemSelected(@NonNull MenuItem item) {
				int id = item.getItemId();
				Intent intent = new Intent();
				switch (id) {
					case R.id.edit_akun:
						HashMap<String, String> user = sessionManager.getDataUser();
						String id_eksternal = user.get(sessionManager.ID_USER);

						intent = new Intent(getApplicationContext(), EksternalAkunEditActivity.class);
						intent.putExtra(EksternalAkunEditActivity.EXTRA_ID_EKSTERNAL, id_eksternal);
						startActivity(intent);
						break;
					case R.id.keluar:
						showDialog();
						break;
					default:
						return true;
				}
				return true;
			}
		});

		cvLinkProve.setOnClickListener(this);
		cvLinkListProve.setOnClickListener(this);
		cvLinkBs.setOnClickListener(this);
		iBtnCariKodeProve.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		if (v.getId() == R.id.cv_link_prove) {
			Intent intent = new Intent();
			intent = new Intent(getApplicationContext(), EksternalListMateriActivity.class);
			startActivity(intent);
		}
		if (v.getId() == R.id.cv_link_list_prove) {
			Intent intent = new Intent();
			intent = new Intent(getApplicationContext(), EksternalListProveActivity.class);
			intent.putExtra(EksternalListProveActivity.EXTRA_TUJUAN, "kosong");
			startActivity(intent);
		}
		if (v.getId() == R.id.i_btn_cari_kode_prove) {
			kode_prove = edtKodeProve.getText().toString().trim();
			eksternalHomePresenter.onSubmit(kode_prove);
		}
		if (v.getId() == R.id.cv_link_bs) {
			Intent intent = new Intent();
			intent = new Intent(getApplicationContext(), EksternalListJadwalBSActivity.class);
			startActivity(intent);
		}
	}

	@Override
	public void showDialog() {
		AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
			this);
		alertDialogBuilder.setTitle("Ingin Keluar ?");
		alertDialogBuilder
			.setMessage("Klik Ya untuk Keluar Aplikasi !")
			.setPositiveButton("Ya", new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface dialog, int id) {

					try {
						sessionManager.logout();
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
	public void onSuccessMessage(String message) {
		Toasty.success(this, message, Toast.LENGTH_SHORT).show();
	}

	@Override
	public void onErrorMessage(String message) {
		Toasty.error(this, message, Toast.LENGTH_SHORT).show();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {

//		int id = item.getItemId();

		if (actionBarDrawerToggle.onOptionsItemSelected(item)) {
			return true;
		}
//		if (id == R.id.menu_akun_saya) {
//			return true;
//		}

		return super.onOptionsItemSelected(item);
	}

	@Override
	protected void onResume() {
		super.onResume();
	}
}
