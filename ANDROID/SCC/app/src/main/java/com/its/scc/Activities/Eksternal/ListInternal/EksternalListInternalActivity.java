package com.its.scc.Activities.Eksternal.ListInternal;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.its.scc.Activities.Eksternal.ListInternal.view.IEksternalListInternalView;
import com.its.scc.R;

public class EksternalListInternalActivity extends AppCompatActivity implements View.OnClickListener , IEksternalListInternalView {

	public static final String EXTRA_ID_MATERI_PROVE = "EXTRA_ID_MATERI_PROVE";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_eksternal_list_internal);
	}

	@Override
	public void onClick(View v) {

	}
}
