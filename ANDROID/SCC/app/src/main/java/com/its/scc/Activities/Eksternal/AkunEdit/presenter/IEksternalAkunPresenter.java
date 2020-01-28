package com.its.scc.Activities.Eksternal.AkunEdit.presenter;

import android.graphics.Bitmap;

public interface IEksternalAkunPresenter {
	void inisiasiAwal(String id_eksternal);

	void onUpdate(String id_eksternal, String nama, String no_hp, String akun_line, String username, String password, String angkatan, String foto);

	String getStringImage(Bitmap bitmap);
}
