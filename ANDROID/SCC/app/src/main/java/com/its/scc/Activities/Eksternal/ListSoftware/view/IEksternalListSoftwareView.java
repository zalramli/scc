package com.its.scc.Activities.Eksternal.ListSoftware.view;

import com.its.scc.Models.Software;

import java.util.ArrayList;

public interface IEksternalListSoftwareView {
	void initActionBar();

	void onSetupListView(ArrayList<Software> dataModelArrayList);

	void onSuccessMessage(String message);

	void onErrorMessage(String message);
}
