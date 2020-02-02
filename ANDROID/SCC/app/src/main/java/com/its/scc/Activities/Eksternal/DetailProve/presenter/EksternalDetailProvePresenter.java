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
	public void onLoadSemuaData(String id_prove) {

	}
}
