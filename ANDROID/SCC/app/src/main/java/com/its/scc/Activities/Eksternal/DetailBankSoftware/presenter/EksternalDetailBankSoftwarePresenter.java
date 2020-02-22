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

	@Override
	public void onLoadSemuaData(String kode_bank_s) {

	}

	@Override
	public void onBatal(String kode_bank_s) {

	}

	@Override
	public void onSelesai(String kode_bank_s) {

	}
}
