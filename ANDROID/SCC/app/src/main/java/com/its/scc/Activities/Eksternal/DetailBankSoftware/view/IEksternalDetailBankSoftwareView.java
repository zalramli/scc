package com.its.scc.Activities.Eksternal.DetailBankSoftware.view;

import com.its.scc.Models.Software;

import java.util.ArrayList;

public interface IEksternalDetailBankSoftwareView {
	void initActionBar();

	void setNilaiDefault();

	void onSetupListView(ArrayList<Software> dataModelArrayList);

	void onSuccessMessage(String message);

	void onErrorMessage(String message);

	void showDialogBatal();

	void showDialogSelesai();

	void backPressed();

	void keHalamanLain();
}
