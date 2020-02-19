package com.its.scc.Activities.Eksternal.BeforeOrderBS.presenter;

import android.content.Context;

import com.its.scc.Activities.Eksternal.BeforeOrderBS.view.IEksternalBeforeOrderBSView;
import com.its.scc.Controllers.BaseUrl;

public class EksternalBeforeOrderBSPresenter implements IEksternalBeforeOrderBSPresenter {

	Context context;
	IEksternalBeforeOrderBSView eksternalBeforeOrderBSView;

	BaseUrl baseUrl;

	public EksternalBeforeOrderBSPresenter(Context context, IEksternalBeforeOrderBSView eksternalBeforeOrderBSView) {
		this.context = context;
		this.eksternalBeforeOrderBSView = eksternalBeforeOrderBSView;

		baseUrl = new BaseUrl();
	}

	@Override
	public void onSubmit(String id_eksternal, String id_jadwal_bs, String tanggal_bs) {

	}

	@Override
	public void onSubmitDetail(String id_detail_bs, String kode_bank_s, String id_softwate) {

	}

	@Override
	public void onLoadSemuaData() {

	}
}
