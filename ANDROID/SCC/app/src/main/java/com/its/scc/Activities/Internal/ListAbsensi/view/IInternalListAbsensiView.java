package com.its.scc.Activities.Internal.ListAbsensi.view;

import com.its.scc.Models.Absensi;

import java.util.ArrayList;

public interface IInternalListAbsensiView {
	void initActionBar();

	void onSetupListView(ArrayList<Absensi> dataModelArrayList);

	void showAkses();

	void onSuccessMessage(String message);

	void onErrorMessage(String message);

	void keDetail();

	void keKataSandi();
}
