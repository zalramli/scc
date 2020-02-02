package com.its.scc.Activities.Eksternal.ListProve.view;

import com.its.scc.Models.Prove;

import java.util.ArrayList;

public interface IEksternalListProveView {
	void initActionBar();

	void onSetupListView(ArrayList<Prove> dataModelArrayList);

	void onSuccessMessage(String message);

	void onErrorMessage(String message);
}
