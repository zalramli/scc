package com.its.scc.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.its.scc.Controllers.BaseUrl;
import com.its.scc.Controllers.SessionManager;
import com.its.scc.Models.MateriProve;
import com.its.scc.R;

import java.util.ArrayList;

import butterknife.ButterKnife;

public class AdapterListMateri extends RecyclerView.Adapter<AdapterListMateri.ListMateriViewHolder> {

	Context context;
	ArrayList<MateriProve> dataModelArrayList;

	SessionManager sessionManager;
	BaseUrl baseUrl;
	private static ClickListener clickListener;

	public AdapterListMateri(Context context, ArrayList<MateriProve> dataModelArrayList) {
		this.context = context;
		this.dataModelArrayList = dataModelArrayList;

		sessionManager = new SessionManager(context);
		baseUrl = new BaseUrl();
	}

	@NonNull
	@Override
	public AdapterListMateri.ListMateriViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
		View itemView = LayoutInflater.from(context).inflate(R.layout.item_adapter_list_materi,parent,false);
		return new ListMateriViewHolder(itemView);
	}

	@Override
	public void onBindViewHolder(@NonNull AdapterListMateri.ListMateriViewHolder holder, int position) {
		holder.txtNama.setText(dataModelArrayList.get(position).getNama());
	}

	@Override
	public int getItemCount() {
		return dataModelArrayList.size();
	}

	public class ListMateriViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

		TextView txtNama;

		public ListMateriViewHolder(@NonNull View itemView) {
			super(itemView);

			txtNama = itemView.findViewById(R.id.txt_nama);

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
		AdapterListMateri.clickListener = clickListener;
	}

}
