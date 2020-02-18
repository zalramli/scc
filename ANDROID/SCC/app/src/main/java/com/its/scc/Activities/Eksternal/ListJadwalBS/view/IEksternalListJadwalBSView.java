package com.its.scc.Activities.Eksternal.ListJadwalBS.view;

import com.its.scc.Models.JadwalBS;

import java.util.ArrayList;

public interface IEksternalListJadwalBSView {
	void initActionBar();

	void onSetupListView(ArrayList<JadwalBS> dataModelArrayList);

	void onSuccessMessage(String message);

	void onErrorMessage(String message);
}
