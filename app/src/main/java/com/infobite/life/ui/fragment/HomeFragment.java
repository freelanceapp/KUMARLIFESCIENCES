package com.infobite.life.ui.fragment;

import android.app.Dialog;
import android.content.Context;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.infobite.life.adapter.OurValuesAdapter;
import com.infobite.life.adapter.SlidingImage_Adapter;
import com.infobite.life.constant.Constant;
import com.infobite.life.modal.OurValuesModal;
import com.infobite.life.modal.banner_modal.BannerMainModal;
import com.infobite.life.modal.banner_modal.Datum;
import com.infobite.life.retrofit_provider.RetrofitService;
import com.infobite.life.retrofit_provider.WebResponse;
import com.infobite.life.utils.Alerts;
import com.infobite.life.utils.BaseFragment;
import com.infobite.life.utils.ConnectionDirector;
import com.viewpagerindicator.CirclePageIndicator;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import infobite.kumar.life.R;
import retrofit2.Response;

import static com.infobite.life.ui.activity.HomeNavigationActivity.fragmentHomeManager;


public class HomeFragment extends BaseFragment {
    SlidingImage_Adapter bannerListAdapter;
    CirclePageIndicator indicator;
    private static ViewPager mPager;
    private static int currentPage = 0;
    private static int NUM_PAGES = 0;
    private ArrayList<Datum> bannerImage = new ArrayList<>();
    private RecyclerView rvOurValues;
    private OurValuesAdapter ourValuesAdapter;
    private ArrayList<OurValuesModal> ourValuesList = new ArrayList<>();
    View view;
    TextView btnRead;
    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view =  inflater.inflate(R.layout.fragment_home, container, false);
        activity = getActivity();
        mContext = getActivity();
        cd = new ConnectionDirector(mContext);
        retrofitApiClient = RetrofitService.getRetrofit();
        bannerApi();
        init();
        return view;
    }

    private void init()
    {
        mPager = (ViewPager) view.findViewById(R.id.pager);
        indicator = (CirclePageIndicator) view.findViewById(R.id.indicator);
        btnRead = view.findViewById(R.id.btnRead);
        btnRead.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
            @Override
            public void onClick(View view) {
                btnRead.setBackground(getResources().getDrawable(R.drawable.round_button));
                startFragment(Constant.AboutFragment, new AboutUs());

            }
        });
        rvOurValues = view.findViewById(R.id.rv_outvalues);

        ourValuesList.add(new OurValuesModal("Together"));
        ourValuesList.add(new OurValuesModal("Respect"));
        ourValuesList.add(new OurValuesModal("Unity"));
        ourValuesList.add(new OurValuesModal("Society"));
        ourValuesList.add(new OurValuesModal("Totality"));

        ourValuesAdapter = new OurValuesAdapter(mContext, ourValuesList);
        rvOurValues.setHasFixedSize(true);
        rvOurValues.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, false));
        rvOurValues.setAdapter(ourValuesAdapter);

        init(4);
    }

    private void init(int bannerLength) {
        bannerListAdapter = new SlidingImage_Adapter(mContext, bannerImage);
        mPager.setAdapter(bannerListAdapter);
        indicator.setViewPager(mPager);
        bannerListAdapter.notifyDataSetChanged();
        final float density = getResources().getDisplayMetrics().density;
        indicator.setRadius(3 * density);
        NUM_PAGES = bannerLength;
        final Handler handler = new Handler();
        final Runnable Update = new Runnable() {
            @Override
            public void run() {
                if (currentPage == NUM_PAGES) {
                    currentPage = 0;
                }
                mPager.setCurrentItem(currentPage++, true);
            }
        };
        Timer swipeTimer = new Timer();
        swipeTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.post(Update);
            }
        }, 3000, 3000);
        indicator.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                currentPage = position;
            }

            @Override
            public void onPageScrolled(int pos, float arg1, int arg2) {

            }

            @Override
            public void onPageScrollStateChanged(int pos) {

            }
        });
    }
    private void startFragment(String tag, Fragment fragment) {
        fragmentHomeManager
                .beginTransaction()
                .setCustomAnimations(R.anim.right_enter, R.anim.left_out)
                .replace(R.id.home_content_frame, fragment, tag).commit();
    }

    /*@Override
    public void onClick(View view) {
        switch (view.getId())
        {
            case R.id.btnRead :
               startFragment(Constant.InfoFragment, new IAppDetailFragment());
                break;
        }
    }*/
    private void bannerApi() {
        if (cd.isNetWorkAvailable()) {
            RetrofitService.getBannerData(new Dialog(mContext), retrofitApiClient.bannerData(), new WebResponse() {
                @Override
                public void onResponseSuccess(Response<?> result) {
                    BannerMainModal mainModal = (BannerMainModal) result.body();
                    bannerImage.clear();
                    if (mainModal != null) {
                        if (mainModal.getData() != null) {
                            bannerImage.addAll(mainModal.getData());
                        }
                    }
                    bannerListAdapter.notifyDataSetChanged();
                }

                @Override
                public void onResponseFailed(String error) {
                    Alerts.show(mContext, error);
                }
            });
        }
    }
}
