package com.its.scc.Activities.Eksternal.BeforeOrderBS;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.its.scc.Activities.Eksternal.BeforeOrderBS.view.IEksternalBeforeOrderBSView;
import com.its.scc.R;

public class EksternalBeforeOrderBSActivity extends AppCompatActivity implements View.OnClickListener, IEksternalBeforeOrderBSView {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_eksternal_before_order_bs);
	}

	@Override
	public void onClick(View v) {

	}
}
