package com.its.scc.Activities._Pendaftaran.presenter;

import android.graphics.Bitmap;

public interface IPendaftaranPresenter {
	void onSubmit(String nama, String no_hp, String akun_line, String username, String password, String angkatan, String foto);

	String getStringImage(Bitmap bitmap);
}
