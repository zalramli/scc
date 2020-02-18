package com.its.scc.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.its.scc.Controllers.BaseUrl;
import com.its.scc.Controllers.SessionManager;
import com.its.scc.Models.JadwalBS;
import com.its.scc.R;

import java.util.ArrayList;

import butterknife.ButterKnife;

public class AdapterListJadwalBS extends RecyclerView.Adapter<AdapterListJadwalBS.ListJadwalBSViewHolder> {

	Context context;
	ArrayList<JadwalBS> dataModelArrayList;

	SessionManager sessionManager;
	BaseUrl baseUrl;
	private static ClickListener clickListener;

	public AdapterListJadwalBS(Context context, ArrayList<JadwalBS> dataModelArrayList) {
		this.context = context;
		this.dataModelArrayList = dataModelArrayList;

		sessionManager = new SessionManager(context);
		baseUrl = new BaseUrl();
	}

	@NonNull
	@Override
	public AdapterListJadwalBS.ListJadwalBSViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
		View itemView = LayoutInflater.from(context).inflate(R.layout.item_adapter_list_jadwal_bs, parent, false);
		return new ListJadwalBSViewHolder(itemView);
	}

	@Override
	public void onBindViewHolder(@NonNull AdapterListJadwalBS.ListJadwalBSViewHolder holder, int position) {

	}

	@Override
	public int getItemCount() {
		return dataModelArrayList.size();
	}

	public class ListJadwalBSViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {


		public ListJadwalBSViewHolder(@NonNull View itemView) {
			super(itemView);

			ButterKnife.bind(this, itemView);
			itemView.setOnClickListener(this);
		}

		@Override
		public void onClick(View v) {
			clickListener.onClick(v, getAdapterPosition());
		}
	}

	public interface ClickListener {
		void onClick(View view, int position);
	}

	public void setOnItemClickListener(ClickListener clickListener) {
		AdapterListJadwalBS.clickListener = clickListener;
	}
}
