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
}
