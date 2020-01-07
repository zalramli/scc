package com.its.xcc.Activities._Login.presenter;

import android.content.Context;

import com.its.xcc.Activities._Login.view.ILoginActivityView;
import com.its.xcc.Controllers.BaseUrl;
import com.its.xcc.Controllers.SessionManager;

public class LoginActivityPresenter implements ILoginActivityPresenter {

	Context context;
	ILoginActivityView loginActivityView;

	BaseUrl baseUrl;

	SessionManager sessionManager;

	public LoginActivityPresenter(Context context, ILoginActivityView loginActivityView) {
		this.context = context;
		this.loginActivityView = loginActivityView;

		baseUrl = new BaseUrl();
		sessionManager = new SessionManager(context);
	}

	@Override
	public void onLogin(String username, String password, String hakAkses) {

	}
}
