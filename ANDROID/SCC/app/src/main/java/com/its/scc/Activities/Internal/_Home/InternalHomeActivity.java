package com.its.scc.Activities.Internal._Home;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.google.android.material.navigation.NavigationView;
import com.its.scc.Activities.Internal._Home.view.IInternalHomeView;
import com.its.scc.R;

public class InternalHomeActivity extends AppCompatActivity implements View.OnClickListener, IInternalHomeView {

	private DrawerLayout drawerLayout;
	private ActionBarDrawerToggle actionBarDrawerToggle;
	private NavigationView navigationView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_internal_home);

		drawerLayout = findViewById(R.id.drawer_layout_internal_home);
		navigationView = findViewById(R.id.navigation_view_admin);

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
					case R.id.absensi:
//						intent = new Intent(getApplicationContext(), AdminPengajarTampilActivity.class);
//						intent.putExtra(AdminPengajarTampilActivity.EXTRA_STATUS_ACTIVITY, "to_monitoring");
//						startActivity(intent);
						break;
					default:
						return true;
				}
				return true;
			}
		});
	}

	@Override
	public void onClick(View v) {

	}

	@Override
	public void showDialog() {

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

		int id = item.getItemId();

		if (actionBarDrawerToggle.onOptionsItemSelected(item)) {
			return true;
		}
		if (id == R.id.menu_akun_saya) {
//			startActivity(new Intent(getApplicationContext(), AdminAkunSayaActivity.class));
			return true;
		}
		if (id == R.id.menu_keluar) {
//			showDialog();
			return true;
		}

		return super.onOptionsItemSelected(item);
	}

	@Override
	protected void onResume() {
		super.onResume();
	}
}
