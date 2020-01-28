package com.its.scc.Activities.Eksternal.AkunEdit.view;

public interface IEksternalAkunView {
	void initActionBar();

	void setNilaiDefault(String nama, String no_hp, String akun_line, String username, String angkatan, String foto);

	void onSuccessMessage(String message);

	void onErrorMessage(String message);

	void showDialog();

	void backPressed();
}
