package com.its.scc.Activities.Internal.DetailAbsensi.presenter;

import android.content.Context;

import com.its.scc.Activities.Internal.DetailAbsensi.view.IInternalDetailAbsensiView;
import com.its.scc.Controllers.BaseUrl;

public class InternalDetailAbsensiPresenter implements IInternalDetailAbsensiPresenter {

	Context context;
	IInternalDetailAbsensiView internalDetailAbsensiView;

	BaseUrl baseUrl;

	public InternalDetailAbsensiPresenter(Context context, IInternalDetailAbsensiView internalDetailAbsensiView) {
		this.context = context;
		this.internalDetailAbsensiView = internalDetailAbsensiView;

		baseUrl = new BaseUrl();
	}

	@Override
	public void onLoadSemuaData(String id_absensi) {

	}
}
