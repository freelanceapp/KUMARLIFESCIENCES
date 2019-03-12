package com.infobite.life.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.infobite.life.constant.Constant;
import com.infobite.life.modal.search_model.Datum;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import infobite.kumar.life.R;


public class SearchListAdapter extends RecyclerView.Adapter<SearchListAdapter.ViewHolder> implements Filterable {

    private List<Datum> filteredAllUserLists;
    private List<Datum> allUserLists;
    private Context context;
    private SearchAdapterListener searchAdapterListener;

    public SearchListAdapter(List<Datum> allUser, Context context, SearchAdapterListener searchAdapterListener) {
        filteredAllUserLists = new ArrayList<>();
        allUserLists = new ArrayList<>();
        this.allUserLists = allUser;
        this.filteredAllUserLists = allUser;
        this.context = context;
        this.searchAdapterListener = searchAdapterListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater li = LayoutInflater.from(context);
        View viewt = li.inflate(R.layout.row_productlist_layout, null);
        return new ViewHolder(viewt);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int i) {

        Datum gridDetailmodels = filteredAllUserLists.get(i);

        Glide.with(context)
                .load(gridDetailmodels.getProductImage())
                .into(viewHolder.imgMainProduct);

        viewHolder.tv_productName.setText(gridDetailmodels.getProductType());
       /* viewHolder.btnView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                searchAdapterListener.onSearchSelected(filteredAllUserLists.get(i));
            }
        });*/

        if (i == 0 || i == 1) {
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT);
            params.setMargins(24, 56, 24, 24);
            viewHolder.llItem.setLayoutParams(params);
        } else {
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT);
            params.setMargins(24, 12, 24, 12);
            viewHolder.llItem.setLayoutParams(params);
        }
    }

    @Override
    public int getItemCount() {
        return filteredAllUserLists.size();
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String charString = charSequence.toString();
                if (charString.isEmpty()) {
                    filteredAllUserLists = allUserLists;
                } else {
                    List<Datum> filteredList = new ArrayList<>();
                    for (Datum row : allUserLists) {
                        String regex = "(.)*(\\d)(.)*";
                        Pattern pattern = Pattern.compile(regex);
                        String msg = row.getProductType();
                        boolean containsNumber = pattern.matcher(msg).matches();
                        if (containsNumber) {
                            //filteredList.add(row);
                        } else {
                            if (row.getProductType().toLowerCase().contains(charString.toLowerCase()) ||
                                    row.getProductType().toLowerCase().contains(charString.toLowerCase())) {
                                filteredList.add(row);
                            }
                        }
                    }
                    filteredAllUserLists = filteredList;
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = filteredAllUserLists;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                filteredAllUserLists = (ArrayList<Datum>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView imgMainProduct;
        private TextView tv_productName, tvBio;
        private CardView llItem;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            llItem = itemView.findViewById(R.id.llItem);
            imgMainProduct = itemView.findViewById(R.id.imgMainProduct);
            tv_productName = itemView.findViewById(R.id.tv_productName);
        }
    }

    public interface SearchAdapterListener {
        void onSearchSelected(Datum contact);
    }
}

