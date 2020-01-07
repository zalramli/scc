package com.its.xcc.Activities.Internal.Home.presenter;

import android.content.Context;

import com.its.xcc.Activities.Internal.Home.view.IInternalHomeView;
import com.its.xcc.Controllers.BaseUrl;

public class InternalHomePresenter implements IInternalHomePresenter {

	Context context;
	IInternalHomeView iInternalHomeView;

	BaseUrl baseUrl;

	public InternalHomePresenter(Context context, IInternalHomeView iInternalHomeView) {
		this.context = context;
		this.iInternalHomeView = iInternalHomeView;

		baseUrl = new BaseUrl();
	}
}
