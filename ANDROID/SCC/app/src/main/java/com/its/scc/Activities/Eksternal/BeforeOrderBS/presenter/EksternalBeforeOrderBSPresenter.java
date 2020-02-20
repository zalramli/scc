package com.its.scc.Activities.Eksternal.BeforeOrderBS.presenter;

import android.content.Context;

import com.its.scc.Activities.Eksternal.BeforeOrderBS.view.IEksternalBeforeOrderBSView;
import com.its.scc.Controllers.BaseUrl;
import com.its.scc.DB.DBConstants;
import com.its.scc.DB.DatabaseHelper;
import com.its.scc.Models.Software;

import java.util.ArrayList;

public class EksternalBeforeOrderBSPresenter implements IEksternalBeforeOrderBSPresenter {

	Context context;
	IEksternalBeforeOrderBSView eksternalBeforeOrderBSView;

	ArrayList<Software> dataModelArrayList;

	BaseUrl baseUrl;
	DatabaseHelper databaseHelper;

	public EksternalBeforeOrderBSPresenter(Context context, IEksternalBeforeOrderBSView eksternalBeforeOrderBSView) {
		this.context = context;
		this.eksternalBeforeOrderBSView = eksternalBeforeOrderBSView;

		baseUrl = new BaseUrl();
		databaseHelper = new DatabaseHelper(context);
	}

	@Override
	public void onSubmit(String id_eksternal, String id_jadwal_bs, String tanggal_bs) {

	}

	@Override
	public void onSubmitDetail(String kode_bank_s, String id_softwate) {

	}

	@Override
	public void onLoadSemuaData() {
		dataModelArrayList = databaseHelper.getAllData(DBConstants.C_ID + " ASC");
		eksternalBeforeOrderBSView.onSetupListView(dataModelArrayList);
		if (dataModelArrayList.size() < 1) {
			eksternalBeforeOrderBSView.onErrorMessage("Tambah List Software !");
		}
	}

	@Override
	public void onDelete(String id) {
		databaseHelper.deleteInfo(id);
	}
}
