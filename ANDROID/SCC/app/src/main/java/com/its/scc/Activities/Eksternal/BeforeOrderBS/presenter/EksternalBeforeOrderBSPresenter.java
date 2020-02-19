package com.its.scc.Activities.Eksternal.BeforeOrderBS.presenter;

import android.content.Context;

import com.its.scc.Controllers.BaseUrl;

public class EksternalBeforeOrderBSPresenter implements IEksternalBeforeOrderBSPresenter {

	Context context;
	IEksternalBeforeOrderBSPresenter eksternalBeforeOrderBSPresenter;

	BaseUrl baseUrl;

	public EksternalBeforeOrderBSPresenter(Context context, IEksternalBeforeOrderBSPresenter eksternalBeforeOrderBSPresenter) {
		this.context = context;
		this.eksternalBeforeOrderBSPresenter = eksternalBeforeOrderBSPresenter;

		baseUrl = new BaseUrl();
	}

	@Override
	public void onSubmit(String kode_bank_s, String id_eksternal, String id_jadwal_bs, String tanggal_booking, String tanggal_bs, String status_bs) {

	}
}
