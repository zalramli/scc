package com.its.scc.Activities.Eksternal.DetailProve.view;

import com.its.scc.Models.Eksternal;

import java.util.ArrayList;

public interface IEksternalDetailProveView {
	void initActionBar();

	void setNilaiDefault();

	void onSetupListView(ArrayList<Eksternal> dataModelArrayList);

	void onSuccessMessage(String message);

	void onErrorMessage(String message);

	void showDialogKeluar();

	void showDialogRating();

	void backPressed();
}
