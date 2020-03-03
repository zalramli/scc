package com.its.scc.Activities.Internal.DetailAbsensi.view;

import com.its.scc.Models.Internal;

import java.util.ArrayList;

public interface IInternalDetailAbsensiView {
	void initActionBar();

	void setNilaiDefault();

	void onSetupListView(ArrayList<Internal> dataModelArrayList);

	void onSuccessMessage(String message);

	void onErrorMessage(String message);

	void backPressed();

	void keHalamanLain();
}
