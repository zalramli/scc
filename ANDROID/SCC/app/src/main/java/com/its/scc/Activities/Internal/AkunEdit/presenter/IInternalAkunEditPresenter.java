package com.its.scc.Activities.Internal.AkunEdit.presenter;

import android.graphics.Bitmap;

public interface IInternalAkunEditPresenter {
	void inisiasiAwal(String id_internal);

	void onUpdate(String id_internal,String nama, String no_hp, String akun_line, String username,String password, String hak_akses, String jabatan_managerial, String status_sj, String foto);

	String getStringImage(Bitmap bitmap);
}
