package com.its.scc.Activities.Eksternal.ListMateri.view;

import com.its.scc.Models.MateriProve;

import java.util.ArrayList;

public interface IEksternalListMateriView {
	void initActionBar();

	void onSetupListView(ArrayList<MateriProve> dataModelArrayList);

	void onSuccessMessage(String message);

	void onErrorMessage(String message);
}
