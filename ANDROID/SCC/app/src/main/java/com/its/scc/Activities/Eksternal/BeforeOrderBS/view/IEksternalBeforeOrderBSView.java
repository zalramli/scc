package com.its.scc.Activities.Eksternal.BeforeOrderBS.view;

public interface IEksternalBeforeOrderBSView {
	void initActionBar();

	void setNilaiDefault();

	void onSuccessMessage(String message);

	void onErrorMessage(String message);

	void showDateDialog();

	void showDialog();

	void backPressed();

	void keHalamanLain();
}
