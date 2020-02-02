package com.its.scc.Activities.Eksternal.BeforeCreateProve.presenter;

import android.content.Context;

import com.its.scc.Activities.Eksternal.BeforeCreateProve.view.IEksternalBeforeCreateProveView;
import com.its.scc.Controllers.BaseUrl;

public class EksternalBeforeCreateProvePresenter implements IEksternalBeforeCreateProvePresenter {

	Context context;
	IEksternalBeforeCreateProveView eksternalBeforeCreateProveView;

	BaseUrl baseUrl;

	public EksternalBeforeCreateProvePresenter(Context context, IEksternalBeforeCreateProveView eksternalBeforeCreateProveView) {
		this.context = context;
		this.eksternalBeforeCreateProveView = eksternalBeforeCreateProveView;

		baseUrl = new BaseUrl();
	}

	@Override
	public void onSubmit(String id_eksternal, String id_internal, String id_materi_prove, String id_jadwal_prove, String deskripsi_materi, String tanggal_prove) {

	}
}
