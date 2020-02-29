package com.its.scc.Activities.Internal.CreateAbsensi.view;

public interface IInternalCreateAbsensiView {
	void initActionBar();

	void onSuccessMessage(String message);

	void onErrorMessage(String message);

	void showDateDialog();

	void showHoursPicker();

	void showHoursPicker2();

	void showDialog();

	void backPressed();

	void keHalamanLain();
}
