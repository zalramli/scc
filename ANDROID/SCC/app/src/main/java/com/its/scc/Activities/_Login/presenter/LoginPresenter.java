package com.its.scc.Activities._Login.presenter;

import android.content.Context;
import android.content.Intent;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.its.scc.Activities.Internal.Home.InternalHomeActivity;
import com.its.scc.Activities._Login.view.ILoginView;
import com.its.scc.Controllers.BaseUrl;
import com.its.scc.Controllers.SessionManager;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class LoginPresenter implements ILoginPresenter {

	Context context;
	ILoginView loginView;

	BaseUrl baseUrl;

	SessionManager sessionManager;

	public LoginPresenter(Context context, ILoginView loginView) {
		this.context = context;
		this.loginView = loginView;

		baseUrl = new BaseUrl();
		sessionManager = new SessionManager(context);
	}

	@Override
	public void onLogin(String username, String password) {
		String base_url = baseUrl.getUrlData();
		String URL_DATA = base_url + "login/masuk"; // url http request

		StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_DATA,
			new Response.Listener<String>() {
				@Override
				public void onResponse(String response) {
					try {
						JSONObject jsonObject = new JSONObject(response);
						String success = jsonObject.getString("success");
						String message = jsonObject.getString("message");

						JSONArray jsonArray = jsonObject.getJSONArray("tbl_data");

						if (success.equals("2")) {
							for (int i = 0; i < jsonArray.length(); i++) {
								JSONObject object = jsonArray.getJSONObject(i);

								String hakAkses = object.getString("hak_akses").trim();
								String id_user = "";
								String nama = object.getString("nama").trim();
								String username = object.getString("username").trim();

								Intent intent = new Intent();

								if (hakAkses.equals("internal")) {

									id_user = object.getString("id_internal").trim();
									intent = new Intent(context, InternalHomeActivity.class);

								} else if (hakAkses.equals("eksternal")) {

									id_user = object.getString("id_eksternal").trim();
//									intent = new Intent(context, PengajarHomeActivity.class);

								} else {

									sessionManager.logout();

								}

								sessionManager.setSessionLogin(id_user, nama, username, hakAkses);

								intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
								context.startActivity(intent);
							}
						} else if (success.equals("1")) {
							loginView.onErrorMessage(message);
						} else if (success.equals("0")) {
							loginView.onErrorMessage(message);
						}

					} catch (JSONException e) {
						e.printStackTrace();
						loginView.onErrorMessage("Kesalahan Menerima Data : " + e.toString());
					}
				}
			},
			new Response.ErrorListener() {
				@Override
				public void onErrorResponse(VolleyError error) {
					loginView.onErrorMessage("Periksa Koneksi Anda !, Error : " + error.toString());
				}
			}) {
			@Override
			protected Map<String, String> getParams() throws AuthFailureError {
				Map<String, String> params = new HashMap<>();
				params.put("username", username);
				params.put("password", password);
				return params;
			}
		};

		RequestQueue requestQueue = Volley.newRequestQueue(context);
		requestQueue.add(stringRequest);
	}
}
