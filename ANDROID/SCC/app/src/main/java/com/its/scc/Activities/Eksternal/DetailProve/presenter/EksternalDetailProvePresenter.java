package com.its.scc.Activities.Eksternal.DetailProve.presenter;

import android.content.Context;

import com.its.scc.Activities.Eksternal.DetailProve.view.IEksternalDetailProveView;
import com.its.scc.Controllers.BaseUrl;

public class EksternalDetailProvePresenter implements IEksternalDetailProvePresenter {

	Context context;
	IEksternalDetailProveView eksternalDetailProveView;

	BaseUrl baseUrl;

	public EksternalDetailProvePresenter(Context context, IEksternalDetailProveView eksternalDetailProveView) {
		this.context = context;
		this.eksternalDetailProveView = eksternalDetailProveView;

		baseUrl =  new BaseUrl();
	}

	@Override
	public void onSubmit(String id_eksternal, String id_internal, String id_materi_prove, String id_jadwal_prove, String deskripsi_materi, String tanggal_booking, String tanggal_prove, String kode_prove, String kata_sandi) {

	}
}
