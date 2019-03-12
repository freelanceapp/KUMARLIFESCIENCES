package com.infobite.life.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.infobite.life.modal.OurValuesModal;

import java.util.ArrayList;

import infobite.kumar.life.R;

public class OurValuesAdapter extends RecyclerView.Adapter<OurValuesAdapter.ViewHolder> {
    private Context mContext;
    private ArrayList<OurValuesModal> ourValuesList;

    public OurValuesAdapter(Context mContext, ArrayList<OurValuesModal> ourValuesList) {
        this.mContext = mContext;
        this.ourValuesList = ourValuesList;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater li = LayoutInflater.from(mContext);
        View rootview = li.inflate(R.layout.row_ourvalues, null);
        return new ViewHolder(rootview);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        OurValuesModal ourValuesModal = ourValuesList.get(i);
        viewHolder.valueName.setText(ourValuesModal.getValuesName());

    }

    @Override
    public int getItemCount() {
        return ourValuesList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView valueName,ordNo;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            valueName = itemView.findViewById(R.id.tv_our_values);

        }
    }
}
