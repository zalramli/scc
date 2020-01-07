package com.its.xcc.Activities.Internal.Home;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.xcc.R;
import com.its.xcc.Activities.Internal.Home.presenter.IInternalHomePresenter;
import com.its.xcc.Activities.Internal.Home.presenter.InternalHomePresenter;
import com.its.xcc.Activities.Internal.Home.view.IInternalHomeView;
import com.its.xcc.Controllers.SessionManager;

import java.util.HashMap;

import es.dmoral.toasty.Toasty;

public class InternalHomeActivity extends AppCompatActivity implements View.OnClickListener, IInternalHomeView {

	IInternalHomePresenter iInternalHomePresenter;
	SessionManager sessionManager;
	String hakAkses;

	Toolbar toolbar;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_internal_home);

		iInternalHomePresenter = new InternalHomePresenter(this, this);
		sessionManager = new SessionManager(this);
		HashMap<String, String> user = sessionManager.getDataUser();
		hakAkses = user.get(sessionManager.HAK_AKSES);

		onSuccessMessage(hakAkses);

		initActionBar();
	}

	@Override
	public void onClick(View v) {

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
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {

			case android.R.id.home:
				onBackPressed();
				break;
		}
		return true;
	}
}
