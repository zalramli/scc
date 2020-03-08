package com.its.scc.Activities.Eksternal.DetailBankSoftware.presenter;

public interface IEksternalDetailBankSoftwarePresenter {
	void onLoadSemuaData(String kode_bank_s);

	void onChangeRating(String kode_bank_s, String rating);

	void onHapus(String kode_bank_s);

	void onSelesai(String kode_bank_s);
}
