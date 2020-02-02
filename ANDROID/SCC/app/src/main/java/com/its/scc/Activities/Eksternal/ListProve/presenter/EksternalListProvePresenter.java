package com.its.scc.Activities.Eksternal.ListProve.presenter;

import android.content.Context;

import com.its.scc.Activities.Eksternal.ListProve.view.IEksternalListProveView;
import com.its.scc.Controllers.BaseUrl;

public class EksternalListProvePresenter implements IEksternalListProvePresenter {

	Context context;
	IEksternalListProveView eksternalListProveView;

	BaseUrl baseUrl;

	public EksternalListProvePresenter(Context context, IEksternalListProveView eksternalListProveView) {
		this.context = context;
		this.eksternalListProveView = eksternalListProveView;

		baseUrl = new BaseUrl();
	}

	@Override
	public void inisiasiAwal(String id) {

	}
}
