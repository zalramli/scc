package com.its.scc.Activities.Eksternal.ListBankSoftware.presenter;

import android.content.Context;

import com.its.scc.Activities.Eksternal.ListBankSoftware.view.IEksternalListBankSoftwareView;
import com.its.scc.Controllers.BaseUrl;

public class EksternalListBankSoftwarePresenter implements IEksternalListBankSoftwarePresenter {

	Context context;
	IEksternalListBankSoftwareView eksternalListBankSoftwareView;

	BaseUrl baseUrl;

	public EksternalListBankSoftwarePresenter(Context context, IEksternalListBankSoftwareView eksternalListBankSoftwareView) {
		this.context = context;
		this.eksternalListBankSoftwareView = eksternalListBankSoftwareView;

		baseUrl = new BaseUrl();
	}
}
