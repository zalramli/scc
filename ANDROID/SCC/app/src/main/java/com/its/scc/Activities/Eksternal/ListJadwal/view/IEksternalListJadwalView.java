package com.its.scc.Activities.Eksternal.ListJadwal.view;

import com.its.scc.Models.Jadwal;

import java.util.ArrayList;

public interface IEksternalListJadwalView {
	void initActionBar();

	void onSetupListView(ArrayList<Jadwal> dataModelArrayList);

	void onSuccessMessage(String message);

	void onErrorMessage(String message);
}
