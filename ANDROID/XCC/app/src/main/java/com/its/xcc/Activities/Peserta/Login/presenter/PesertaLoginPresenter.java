package com.its.xcc.Activities.Peserta.Login.presenter;

import android.content.Context;
import android.content.Intent;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.NetworkError;
import com.android.volley.NoConnectionError;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.ServerError;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.its.xcc.Activities.Peserta.Home.PesertaHomeLolosActivity;
import com.its.xcc.Activities.Peserta.Home.PesertaHomeTidakLolosActivity;
import com.its.xcc.Activities.Peserta.Login.view.IPesertaLoginView;
import com.its.xcc.Controllers.BaseUrl;
import com.its.xcc.Controllers.SessionManager;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class PesertaLoginPresenter implements IPesertaLoginPresenter {

    IPesertaLoginView pesertaLoginView;
    Context context;

    SessionManager sessionManager;

    BaseUrl baseUrl;
    String urlData;

    public PesertaLoginPresenter(IPesertaLoginView pesertaLoginView, Context context) {
        this.pesertaLoginView = pesertaLoginView;
        this.context = context;

        sessionManager = new SessionManager(context);

        baseUrl = new BaseUrl();
        urlData = baseUrl.getUrl_data();
    }

    @Override
    public void onLogin(String nim, String nama) {

        String BASE_URL_DATA = urlData + "login/"; // url http request

        StringRequest stringRequest = new StringRequest(Request.Method.POST, BASE_URL_DATA,
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

                                    String nim = object.getString("nim").trim();
                                    String nama = object.getString("nama").trim();
                                    String status = object.getString("status").trim();
                                    String jabatan = object.getString("jabatan").trim();

                                    // sessionManager.setSessionLogin(id_user, nama, username);

                                    Intent intent = new Intent();

                                    if (status.equals("Lolos")) {
                                        intent = new Intent(context, PesertaHomeLolosActivity.class);
                                    } else {
                                        intent = new Intent(context, PesertaHomeTidakLolosActivity.class);
                                    }

                                    intent.putExtra(PesertaHomeLolosActivity.EXTRA_NIM, nim);
                                    intent.putExtra(PesertaHomeLolosActivity.EXTRA_NAMA, nama);
                                    intent.putExtra(PesertaHomeLolosActivity.EXTRA_STATUS, status);
                                    intent.putExtra(PesertaHomeLolosActivity.EXTRA_JABATAN, jabatan);
                                    context.startActivity(intent);
                                }
                            } else {
                                pesertaLoginView.onErrorMessage(message);
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                            pesertaLoginView.onErrorMessage("Kesalahan Menerima Data : " + e.toString());
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                        if (error instanceof TimeoutError || error instanceof NoConnectionError) {
                            pesertaLoginView.onErrorMessage("Pastikan koneksi internet anda stabil");
                        } else if (error instanceof AuthFailureError) {
                            //TODO
                        } else if (error instanceof ServerError) {
                            //TODO
                        } else if (error instanceof NetworkError) {
                            //TODO
                        } else if (error instanceof ParseError) {
                            //TODO
                        }
                    }

                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("nim", nim);
                params.put("nama", nama);
                return params;
            }
        };
        stringRequest.setRetryPolicy(new DefaultRetryPolicy( 50000, 5, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        requestQueue.add(stringRequest);
    }
}
