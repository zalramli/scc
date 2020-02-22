package com.its.scc.Activities.Eksternal.DetailBankSoftware.presenter;

public interface IEksternalDetailBankSoftwarePresenter {
	void onLoadSemuaData(String kode_bank_s);

	void onBatal(String kode_bank_s);

	void onSelesai(String kode_bank_s);
}
