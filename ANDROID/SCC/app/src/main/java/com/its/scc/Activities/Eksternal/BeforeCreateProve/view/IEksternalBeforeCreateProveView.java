package com.its.scc.Activities.Eksternal.BeforeCreateProve.view;

public interface IEksternalBeforeCreateProveView {
	void initActionBar();

	void setNilaiDefault();

	void onSuccessMessage(String message);

	void onErrorMessage(String message);

	void showDateDialog();

	void showDialog();

	void backPressed();

	void keHalamanLain();
}
