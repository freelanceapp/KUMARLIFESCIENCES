package com.infobite.life.ui.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.infobite.life.adapter.SlidingImage_Adapter;
import com.viewpagerindicator.CirclePageIndicator;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import infobite.kumar.life.R;


public class HomeFragment extends Fragment {
    CirclePageIndicator indicator;
    private static ViewPager mPager;
    private static int currentPage = 0;
    private static int NUM_PAGES = 0;
    private ArrayList<String> ImagesArray = new ArrayList<String>();
    View view;
    public HomeFragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view =  inflater.inflate(R.layout.fragment_home, container, false);

        init();

        return view;
    }

    private void init()
    {
        mPager = (ViewPager) view.findViewById(R.id.pager);
        indicator = (CirclePageIndicator) view.findViewById(R.id.indicator);

        ImagesArray.add("https://images.pexels.com/photos/415825/pexels-photo-415825.jpeg?auto=compress&cs=tinysrgb&dpr=1&w=500");
        ImagesArray.add("https://images.indianexpress.com/2017/12/meds-main.jpg");
        ImagesArray.add("http://theislander.net/wp-content/uploads/MSOS-Medicine-Medical-Supplies-Disposal--660x330.png");
        ImagesArray.add("https://d9np3dj86nsu2.cloudfront.net/image/b10e77c167e60da398488d7a1412a7f1");

        init(4);
    }

    private void init(int bannerLength) {
        SlidingImage_Adapter image_adapter1 = new SlidingImage_Adapter(getActivity(), ImagesArray);
        mPager.setAdapter(image_adapter1);
        indicator.setViewPager(mPager);
        image_adapter1.notifyDataSetChanged();
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

}
