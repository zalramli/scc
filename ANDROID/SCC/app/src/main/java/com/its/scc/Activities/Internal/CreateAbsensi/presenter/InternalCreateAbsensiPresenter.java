package com.its.scc.Activities.Internal.CreateAbsensi.presenter;

import android.content.Context;

import com.its.scc.Activities.Internal.CreateAbsensi.view.IInternalCreateAbsensiView;
import com.its.scc.Controllers.BaseUrl;

public class InternalCreateAbsensiPresenter implements IInternalCreateAbsensiPresenter {

	Context context;
	IInternalCreateAbsensiView internalCreateAbsensiView;

	BaseUrl baseUrl;

	public InternalCreateAbsensiPresenter(Context context, IInternalCreateAbsensiView internalCreateAbsensiView) {
		this.context = context;
		this.internalCreateAbsensiView = internalCreateAbsensiView;

		baseUrl = new BaseUrl();
	}

	@Override
	public void onSubmit(String id_internal, String judul_absensi, String tgl_absensi, String jam_mulai, String jam_selesai, String kata_sandi) {

	}
}
