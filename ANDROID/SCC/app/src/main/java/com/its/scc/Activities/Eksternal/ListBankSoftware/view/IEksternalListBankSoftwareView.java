package com.its.scc.Activities.Eksternal.ListBankSoftware.view;

import com.its.scc.Models.BankSoftware;

import java.util.ArrayList;

public interface IEksternalListBankSoftwareView {
	void initActionBar();

	void onSetupListView(ArrayList<BankSoftware> dataModelArrayList);

	void onSuccessMessage(String message);

	void onErrorMessage(String message);
}
