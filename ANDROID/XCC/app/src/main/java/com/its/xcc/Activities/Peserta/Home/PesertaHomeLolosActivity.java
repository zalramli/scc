package com.its.xcc.Activities.Peserta.Home;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.xcc.R;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PesertaHomeLolosActivity extends AppCompatActivity {

    public static final String EXTRA_NIM = "EXTRA_NIM";
    public static final String EXTRA_NAMA = "EXTRA_NAMA";
    public static final String EXTRA_STATUS = "EXTRA_STATUS";
    public static final String EXTRA_JABATAN = "EXTRA_JABATAN";

    TextView tvStatus,tvDeskripsi,tag;
    Animation smltobig,nothingtocome;
    ImageView imageView;
    Button btn_notif;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_peserta_home_lolos);

        smltobig = AnimationUtils.loadAnimation(this,R.anim.smltobig);
        nothingtocome = AnimationUtils.loadAnimation(this,R.anim.nothingtocome);

        imageView = (ImageView) findViewById(R.id.imgView);
        tvStatus = findViewById(R.id.tv_status);
        tvDeskripsi = findViewById(R.id.tv_deskripsi);
        tag = findViewById(R.id.tag);
        btn_notif = (Button) findViewById(R.id.btn_notif);

        imageView.startAnimation(smltobig);
        tvStatus.startAnimation(nothingtocome);
        tvDeskripsi.startAnimation(nothingtocome);
        tag.startAnimation(nothingtocome);
        btn_notif.startAnimation(nothingtocome);

        String nim = getIntent().getStringExtra(EXTRA_NIM);
        String nama = getIntent().getStringExtra(EXTRA_NAMA);
        String status = getIntent().getStringExtra(EXTRA_STATUS);
        String jabatan = getIntent().getStringExtra(EXTRA_JABATAN);

        String nama_lengkap = capitalize(nama);

        if (status.equals("Lolos")) {
            tvStatus.setText(nama_lengkap);
            tvDeskripsi.setText("Selamat datang keluarga baru SCC 2019/2020 \n Semangat menjadi bagian staff " + jabatan + "\n See you at the welcome party!");
        } else {
            tvStatus.setText(nama_lengkap);
        }


        btn_notif.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(),PesertaNotif.class);
                startActivity(i);
            }
        });
    }

    // Fungsi awal kata besar
    private String capitalize(String capString){
        StringBuffer capBuffer = new StringBuffer();
        Matcher capMatcher = Pattern.compile("([a-z])([a-z]*)", Pattern.CASE_INSENSITIVE).matcher(capString);
        while (capMatcher.find()){
            capMatcher.appendReplacement(capBuffer, capMatcher.group(1).toUpperCase() + capMatcher.group(2).toLowerCase());
        }

        return capMatcher.appendTail(capBuffer).toString();
    }
}
