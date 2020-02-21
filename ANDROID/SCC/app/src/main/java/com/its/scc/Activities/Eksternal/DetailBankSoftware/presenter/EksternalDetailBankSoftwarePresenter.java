package com.its.scc.Activities.Eksternal.DetailBankSoftware.presenter;

import android.content.Context;

import com.its.scc.Activities.Eksternal.DetailBankSoftware.view.IEksternalDetailBankSoftwareView;
import com.its.scc.Controllers.BaseUrl;

public class EksternalDetailBankSoftwarePresenter implements IEksternalDetailBankSoftwarePresenter {

	Context context;
	IEksternalDetailBankSoftwareView eksternalDetailBankSoftwareView;

	BaseUrl baseUrl;

	public EksternalDetailBankSoftwarePresenter(Context context, IEksternalDetailBankSoftwareView eksternalDetailBankSoftwareView) {
		this.context = context;
		this.eksternalDetailBankSoftwareView = eksternalDetailBankSoftwareView;

		baseUrl = new BaseUrl();
	}
}
