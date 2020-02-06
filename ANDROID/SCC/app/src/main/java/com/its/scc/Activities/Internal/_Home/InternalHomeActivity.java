package com.its.scc.Activities.Internal._Home;

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

import com.google.android.material.navigation.NavigationView;
import com.its.scc.Activities.Eksternal.ListJadwal.EksternalListJadwalActivity;
import com.its.scc.Activities.Eksternal.ListMateri.EksternalListMateriActivity;
import com.its.scc.Activities.Eksternal.ListProve.EksternalListProveActivity;
import com.its.scc.Activities.Internal.AkunEdit.InternalAkunEditActivity;
import com.its.scc.Activities.Internal._Home.view.IInternalHomeView;
import com.its.scc.Controllers.SessionManager;
import com.its.scc.R;

import java.util.HashMap;

public class InternalHomeActivity extends AppCompatActivity implements View.OnClickListener, IInternalHomeView {

	private DrawerLayout drawerLayout;
	private ActionBarDrawerToggle actionBarDrawerToggle;
	private NavigationView navigationView;

	CardView cvLinkProve, cvLinkListProve;

	SessionManager sessionManager;
	String id_internal = "";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_internal_home);

		drawerLayout = findViewById(R.id.drawer_layout_internal_home);
		navigationView = findViewById(R.id.navigation_view_internal);

		cvLinkListProve = findViewById(R.id.cv_link_list_prove); // link card view prove
		cvLinkProve = findViewById(R.id.cv_link_prove); // link card view prove

		sessionManager = new SessionManager(this);
		HashMap<String, String> user = sessionManager.getDataUser();
		id_internal = user.get(sessionManager.ID_USER);

		actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.Open, R.string.Close);
		drawerLayout.addDrawerListener(actionBarDrawerToggle);
		actionBarDrawerToggle.syncState();
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
			@Override
			public boolean onNavigationItemSelected(@NonNull MenuItem item) {
				int id = item.getItemId();
				switch (id) {
					case R.id.jadwal_prove:
						Intent intent = new Intent(getApplicationContext(), EksternalListJadwalActivity.class);
						intent.putExtra(EksternalListJadwalActivity.EXTRA_ID_MATERI_PROVE, "kosong");
						intent.putExtra(EksternalListJadwalActivity.EXTRA_NAMA_MATERI_PROVE, "kosong");
						intent.putExtra(EksternalListJadwalActivity.EXTRA_ID_INTERNAL, id_internal);
						intent.putExtra(EksternalListJadwalActivity.EXTRA_NAMA_INTERNAL, "kosong");
						startActivity(intent);
						break;
					default:
						return true;
				}
				return true;
			}
		});

		cvLinkListProve.setOnClickListener(this);
		cvLinkProve.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		if (v.getId() == R.id.cv_link_list_prove) {
			Intent intent = new Intent();
			intent = new Intent(getApplicationContext(), EksternalListProveActivity.class);
			intent.putExtra(EksternalListProveActivity.EXTRA_TUJUAN, "kosong");
			startActivity(intent);
		}
		if (v.getId() == R.id.cv_link_prove) {
			Intent intent = new Intent();
			intent = new Intent(getApplicationContext(), EksternalListMateriActivity.class);
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

	}

	@Override
	public void onErrorMessage(String message) {

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.action_bar_internal_home, menu);

//        View view = menu.findItem(R.id.menu_notification).getActionView();
//        badge = view.findViewById(R.id.badge);
//        notificationIcon = view.findViewById(R.id.notification_icon);
//
//        notificationIcon.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {

//            }
//        });

		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {

		Intent intent = new Intent();

		int id = item.getItemId();

		if (actionBarDrawerToggle.onOptionsItemSelected(item)) {
			return true;
		}
		if (id == R.id.menu_akun_saya) {
			intent = new Intent(getApplicationContext(), InternalAkunEditActivity.class);
			intent.putExtra(InternalAkunEditActivity.EXTRA_ID_INTERNAL, id_internal);
			startActivity(intent);
			return true;
		}
		if (id == R.id.menu_keluar) {
			showDialog();
			return true;
		}
		if (id == R.id.menu_pemberitahuan) {
			intent = new Intent(getApplicationContext(), EksternalListProveActivity.class);
			intent.putExtra(EksternalListProveActivity.EXTRA_TUJUAN, "ke_pemberitahuan");
			startActivity(intent);
			return true;
		}

		return super.onOptionsItemSelected(item);
	}

	@Override
	protected void onResume() {
		super.onResume();
	}
}
