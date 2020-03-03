package com.its.scc.Activities.Internal.CekPasswordAbsensi.presenter;

import android.content.Context;

import com.its.scc.Activities.Internal.CekPasswordAbsensi.view.IInternalCekPasswordAbsensiView;
import com.its.scc.Controllers.BaseUrl;

public class InternalCekPasswordAbsensiPresenter implements IInternalCekPasswordAbsensiPresenter {

	Context context;
	IInternalCekPasswordAbsensiView internalCekPasswordAbsensiView;

	BaseUrl baseUrl;

	public InternalCekPasswordAbsensiPresenter(Context context, IInternalCekPasswordAbsensiView internalCekPasswordAbsensiView) {
		this.context = context;
		this.internalCekPasswordAbsensiView = internalCekPasswordAbsensiView;

		baseUrl = new BaseUrl();
	}
}
