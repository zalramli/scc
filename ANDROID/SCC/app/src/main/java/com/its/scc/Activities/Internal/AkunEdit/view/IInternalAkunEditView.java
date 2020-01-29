package com.its.scc.Activities.Internal.AkunEdit.view;

public interface IInternalAkunEditView {
	void initActionBar();

	void setNilaiDefault(String nama, String no_hp, String akun_line, String username, String hak_akses, String jabatan_managerial, String status_sj, String foto);

	void onSuccessMessage(String message);

	void onErrorMessage(String message);

	void showDialog();

	void backPressed();
}
