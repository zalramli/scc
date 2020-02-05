package com.its.scc.Activities.Eksternal.DetailProve.presenter;

public interface IEksternalDetailProvePresenter {
	void onLoadSemuaData(String id_prove);

	void onKeluarProve(String id_eksternal, String id_prove);

	void onChangeRating(String id_prove, String id_eksternal, String rating, String id_jadwal_prove);
}
