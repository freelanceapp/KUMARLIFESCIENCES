package com.infobite.life.ui.fragment;

import android.app.Dialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.infobite.life.modal.banner_modal.BannerMainModal;
import com.infobite.life.retrofit_provider.RetrofitService;
import com.infobite.life.retrofit_provider.WebResponse;
import com.infobite.life.utils.Alerts;

import infobite.kumar.life.R;
import retrofit2.Response;

public class BannerCode {
/*
    rv_banner = (RecyclerView) view.findViewById(R.id.rv_banner);

    bannerListAdapter = new BannerAdapter(mContext, bannerArrayList);
        rv_banner.setHasFixedSize(true);
        rv_banner.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, false));
        rv_banner.setAdapter(bannerListAdapter);
    bannerApi();
}
    private void bannerApi() {
        if (cd.isNetWorkAvailable()) {
            RetrofitService.getBannerData(new Dialog(mContext), retrofitApiClient.bannerData(), new WebResponse() {
                @Override
                public void onResponseSuccess(Response<?> result)  {
                    BannerMainModal mainModal = (BannerMainModal) result.body();
                    bannerArrayList.clear();
                    if (mainModal != null) {
                        if (mainModal.getData() != null) {
                            bannerArrayList.addAll(mainModal.getData());
                        }
                    }
                    bannerListAdapter.notifyDataSetChanged();
                }
                @Override
                public void onResponseFailed(String error) {
                    Alerts.show(mContext,error);
                }
            });
        }*/

/*public class BannerAdapter extends RecyclerView.Adapter<BannerAdapter.ViewHolder> {
    private ArrayList<Datum> banerImageList;
    private Context context;

    public BannerAdapter(Context context, ArrayList<Datum> banerImageList) {
        this.context = context;
        this.banerImageList = banerImageList;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater li = LayoutInflater.from(context);
        View rootview = li.inflate(R.layout.row_banner,null);
        return new ViewHolder(rootview);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        Datum bannerimage = banerImageList.get(i);
        Glide.with(context)
                .load(bannerimage.getOfferImage())
                .into(((ImageView)viewHolder.imageView_banner.findViewById(R.id.imageView_banner)));
    }
    @Override
    public int getItemCount() {
        return banerImageList.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder{
        private ImageView imageView_banner;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView_banner = itemView.findViewById(R.id.imageView_banner);
        }
    }
}
*/
    }
