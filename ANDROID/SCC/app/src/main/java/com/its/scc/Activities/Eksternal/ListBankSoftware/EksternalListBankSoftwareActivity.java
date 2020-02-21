package com.its.scc.Activities.Eksternal.ListBankSoftware;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.os.Bundle;
import android.view.View;

import com.its.scc.Activities.Eksternal.ListBankSoftware.presenter.EksternalListBankSoftwarePresenter;
import com.its.scc.Activities.Eksternal.ListBankSoftware.presenter.IEksternalListBankSoftwarePresenter;
import com.its.scc.Activities.Eksternal.ListBankSoftware.view.IEksternalListBankSoftwareView;
import com.its.scc.Adapters.AdapterListBankSoftware;
import com.its.scc.Controllers.SessionManager;
import com.its.scc.Models.BankSoftware;
import com.its.scc.R;

import java.util.ArrayList;

public class EksternalListBankSoftwareActivity extends AppCompatActivity implements View.OnClickListener, IEksternalListBankSoftwareView {

	public static final String EXTRA_TUJUAN = "EXTRA_TUJUAN";
	String tujuan = "";

	IEksternalListBankSoftwarePresenter eksternalListBankSoftwarePresenter;

	private AdapterListBankSoftware adapterListBankSoftware;
	private RecyclerView recyclerView;

	Toolbar toolbar;

	private SwipeRefreshLayout swipeRefreshLayout;

	SessionManager sessionManager;

	String id = "";
	String hakAkses = "";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_eksternal_list_bank_software);

		eksternalListBankSoftwarePresenter = new EksternalListBankSoftwarePresenter(this,this);

	}

	@Override
	public void onClick(View v) {

	}

	@Override
	public void initActionBar() {

	}

	@Override
	public void onSetupListView(ArrayList<BankSoftware> dataModelArrayList) {

	}

	@Override
	public void onSuccessMessage(String message) {

	}

	@Override
	public void onErrorMessage(String message) {

	}
}
