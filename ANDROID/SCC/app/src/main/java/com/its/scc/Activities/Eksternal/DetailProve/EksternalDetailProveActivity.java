package com.its.scc.Activities.Eksternal.DetailProve;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.os.Bundle;

import com.its.scc.Activities.Eksternal.DetailProve.presenter.IEksternalDetailProvePresenter;
import com.its.scc.Activities.Eksternal.DetailProve.view.IEksternalDetailProveView;
import com.its.scc.Adapters.AdapterListEksternal;
import com.its.scc.Models.Eksternal;
import com.its.scc.R;

import java.util.ArrayList;

public class EksternalDetailProveActivity extends AppCompatActivity implements IEksternalDetailProveView {

	IEksternalDetailProvePresenter eksternalDetailProvePresenter;

	private AdapterListEksternal adapterListEksternal;
	private RecyclerView recyclerView;

	Toolbar toolbar;

	private SwipeRefreshLayout swipeRefreshLayout;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_eksternal_detail_prove);
	}

	@Override
	public void initActionBar() {

	}

	@Override
	public void setNilaiDefault() {

	}

	@Override
	public void onSetupListView(ArrayList<Eksternal> dataModelArrayList) {

	}

	@Override
	public void onSuccessMessage(String message) {

	}

	@Override
	public void onErrorMessage(String message) {

	}

	@Override
	public void showDialog() {

	}

	@Override
	public void backPressed() {

	}
}
