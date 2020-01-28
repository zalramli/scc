package com.its.scc.Activities._Pendaftaran.view;

public interface IPendaftaranView {
	void initActionBar();

	void onSuccessMessage(String message);

	void onErrorMessage(String message);

	void showDialog();

	void backPressed();
}
