package com.its.xcc.Activities.Peserta.Home;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.xcc.R;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PesertaHomeTidakLolosActivity extends AppCompatActivity implements View.OnClickListener {

    public static final String EXTRA_NIM = "EXTRA_NIM";
    public static final String EXTRA_NAMA = "EXTRA_NAMA";
    public static final String EXTRA_STATUS = "EXTRA_STATUS";

    TextView tvStatus2,tvDeskripsi2,tag2;

    Animation smltobig,nothingtocome2;
    ImageView imageView2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_peserta_home_tidak_lolos);

        smltobig = AnimationUtils.loadAnimation(this,R.anim.smltobig);
        nothingtocome2 = AnimationUtils.loadAnimation(this,R.anim.nothingtocome);

        imageView2 = (ImageView) findViewById(R.id.imgView2);
        tvStatus2 = findViewById(R.id.tv_status2);
        tvDeskripsi2 = findViewById(R.id.tv_deskripsi2);
        tag2 = findViewById(R.id.tag2);

        imageView2.startAnimation(smltobig);
        tvStatus2.startAnimation(nothingtocome2);
        tvDeskripsi2.startAnimation(nothingtocome2);
        tag2.startAnimation(nothingtocome2);

        String nim = getIntent().getStringExtra(EXTRA_NIM);
        String nama = getIntent().getStringExtra(EXTRA_NAMA);
        String status = getIntent().getStringExtra(EXTRA_STATUS);

        String nama_lengkap = capitalize(nama);

        if (status.equals("Lolos")) {
            tvStatus2.setText(nama_lengkap);
        } else {
            tvStatus2.setText(nama_lengkap);
        }
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

    @Override
    public void onClick(View v) {

    }
}
