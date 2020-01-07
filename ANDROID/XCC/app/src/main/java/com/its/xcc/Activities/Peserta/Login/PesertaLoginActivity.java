package com.its.xcc.Activities.Peserta.Login;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.its.xcc.Activities.Peserta.Login.presenter.IPesertaLoginPresenter;
import com.its.xcc.Activities.Peserta.Login.presenter.PesertaLoginPresenter;
import com.its.xcc.Activities.Peserta.Login.view.IPesertaLoginView;
import com.example.xcc.R;

import es.dmoral.toasty.Toasty;

public class PesertaLoginActivity extends AppCompatActivity implements View.OnClickListener, IPesertaLoginView {

    RelativeLayout rellay1,rellay2;
    Handler handler = new Handler();
    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            rellay1.setVisibility(View.VISIBLE);
            rellay2.setVisibility(View.VISIBLE);
        }
    };
    EditText edtNim, edtNama;
    Button btnLogin;

    IPesertaLoginPresenter pesertaLoginPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_peserta_login);

        rellay1 = (RelativeLayout) findViewById(R.id.rellay1);
        rellay2 = (RelativeLayout) findViewById(R.id.rellay2);

        handler.postDelayed(runnable,2000);

        edtNim = findViewById(R.id.edt_nim);
        edtNama = findViewById(R.id.edt_nama);
        btnLogin = findViewById(R.id.btn_login);

        pesertaLoginPresenter = new PesertaLoginPresenter(this, this);

        btnLogin.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btn_login) {

            String inputNim = edtNim.getText().toString().trim();
            String inputNama = edtNama.getText().toString().trim();

            // validasi
            boolean isEmpty = false;

            if (TextUtils.isEmpty(inputNim)){
                isEmpty = true;
                edtNim.setError("NRP Tidak Boleh Kosong !");
            }
            if (TextUtils.isEmpty(inputNama)){
                isEmpty = true;
                edtNama.setError("Nama Tidak Boleh Kosong !");
            }

            if (!isEmpty){
                pesertaLoginPresenter.onLogin(inputNim, inputNama);
            }
        }
    }

    @Override
    public void onErrorMessage(String message) {
        Toasty.error(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onSuccessMessage(String message) {
        Toasty.success(this, message, Toast.LENGTH_SHORT).show();
    }
}
