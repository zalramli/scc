package com.its.scc.Activities.Eksternal.BeforeOrderBS.presenter;

public interface IEksternalBeforeOrderBSPresenter {
	void onSubmit(String id_eksternal, String id_jadwal_bs, String tanggal_bs);

	void onSubmitDetail(String id_detail_bs, String kode_bank_s, String id_softwate);

	void onLoadSemuaData(); // software
}
