package com.its.scc.Activities.Internal.CreateAbsensi;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.its.scc.Activities.Internal.CreateAbsensi.presenter.IInternalCreateAbsensiPresenter;
import com.its.scc.Activities.Internal.CreateAbsensi.view.IInternalCreateAbsensiView;
import com.its.scc.Controllers.SessionManager;
import com.its.scc.R;

import java.text.SimpleDateFormat;

public class InternalCreateAbsensiActivity extends AppCompatActivity implements View.OnClickListener, IInternalCreateAbsensiView {

	IInternalCreateAbsensiPresenter internalCreateAbsensiPresenter;

	Toolbar toolbar;

	TextView tvNamaMateri, tvDetailProve, tvNamaInternal, tvTanggalProve;
	EditText edtDeskripsiMateri;
	Button btnSubmit, btnBatal;

	private DatePickerDialog datePickerDialog;
	private SimpleDateFormat dateFormatter;
	private SimpleDateFormat dayFormatter;

	String selected_hari = "";

	SessionManager sessionManager;
	String id_eksternal = "";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_internal_create_absensi);
	}

	@Override
	public void onClick(View v) {

	}

	@Override
	public void initActionBar() {

	}

	@Override
	public void onSuccessMessage(String message) {

	}

	@Override
	public void onErrorMessage(String message) {

	}
}
