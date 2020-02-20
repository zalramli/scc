package com.its.scc.Activities.Eksternal.BeforeOrderBS.view;

import com.its.scc.Models.Software;

import java.util.ArrayList;

public interface IEksternalBeforeOrderBSView {
	void initActionBar();

	void setNilaiDefault();

	void onSetupListView(ArrayList<Software> dataModelArrayList);

	void onSuccessMessage(String message);

	void onErrorMessage(String message);

	void showDateDialog();

	void showDialog();

	void showDeleteDialog(final String id_sql);

	void backPressed();

	void keHalamanLain();
}
