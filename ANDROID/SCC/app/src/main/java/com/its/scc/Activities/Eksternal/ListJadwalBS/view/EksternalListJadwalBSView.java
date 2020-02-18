package com.its.scc.Activities.Eksternal.ListJadwalBS.view;

import com.its.scc.Models.JadwalBS;

import java.util.ArrayList;

public interface EksternalListJadwalBSView {
	void initActionBar();

	void onSetupListView(ArrayList<JadwalBS> dataModelArrayList);

	void onSuccessMessage(String message);

	void onErrorMessage(String message);
}
