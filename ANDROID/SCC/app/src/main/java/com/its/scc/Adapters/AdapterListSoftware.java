package com.its.scc.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.its.scc.Models.Software;
import com.its.scc.R;

import java.util.ArrayList;

import butterknife.ButterKnife;

public class AdapterListSoftware extends RecyclerView.Adapter<AdapterListSoftware.ListSoftwareViewHolder> {

	Context context;
	ArrayList<Software> dataModelArrayList;

	private static ClickListener clickListener;

	public AdapterListSoftware(Context context, ArrayList<Software> dataModelArrayList) {
		this.context = context;
		this.dataModelArrayList = dataModelArrayList;
	}

	@NonNull
	@Override
	public AdapterListSoftware.ListSoftwareViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
		View itemView = LayoutInflater.from(context).inflate(R.layout.item_adapter_list_software,parent,false);
		return new ListSoftwareViewHolder(itemView);
	}

	@Override
	public void onBindViewHolder(@NonNull AdapterListSoftware.ListSoftwareViewHolder holder, int position) {
		holder.tvNama.setText(dataModelArrayList.get(position).getNama());
		if (dataModelArrayList.get(position).getId() > 0){
			holder.ivDelete.setVisibility(View.VISIBLE);
		}
	}

	@Override
	public int getItemCount() {
		return dataModelArrayList.size();
	}

	public class ListSoftwareViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

		TextView tvNama;
		ImageView ivDelete;

		public ListSoftwareViewHolder(@NonNull View itemView) {
			super(itemView);

			tvNama = itemView.findViewById(R.id.tv_nama);
			ivDelete = itemView.findViewById(R.id.iv_delete);

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
		AdapterListSoftware.clickListener = clickListener;
	}

}
