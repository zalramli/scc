package com.its.scc.Activities.Eksternal.ListInternal.view;

import com.its.scc.Models.Internal;

import java.util.ArrayList;

public interface IEksternalListInternalView {
	void initActionBar();

	void onSetupListView(ArrayList<Internal> dataModelArrayList);

	void onSuccessMessage(String message);

	void onErrorMessage(String message);
}
