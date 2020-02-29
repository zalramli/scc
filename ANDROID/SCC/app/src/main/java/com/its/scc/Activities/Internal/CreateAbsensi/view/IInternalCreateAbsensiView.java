package com.its.scc.Activities.Internal.CreateAbsensi.view;

public interface IInternalCreateAbsensiView {
	void initActionBar();

	void onSuccessMessage(String message);

	void onErrorMessage(String message);

	void showDateDialog();

	void showTimeDialog();

	void showDialog();

	void backPressed();

	void keHalamanLain();
}
