package com.erickogi14gmail.kale.Search;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.erickogi14gmail.kale.Dictionary.DbOperation;
import com.erickogi14gmail.kale.Dictionary.WordPojo;
import com.erickogi14gmail.kale.R;

import java.util.ArrayList;

/**
 * Created by Eric on 10/17/2017.
 */

public class FragmentResults extends Fragment {

    private CustomViewPager viewPager;
    private View view;
    DbOperation dbOperation;



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.fragment_results,container,false);
        dbOperation=new DbOperation(getContext());
        ArrayList<WordPojo> wordPojo=dbOperation.searchAllWord();
        int pos=getArguments().getInt("position");

        viewPager = (CustomViewPager) view.findViewById(R.id.viewPager);




        CardFragmentPagerAdapter pagerAdapter = new CardFragmentPagerAdapter(getActivity().getSupportFragmentManager(), dpToPixels(2, getContext()),wordPojo);
        ShadowTransformer fragmentCardShadowTransformer = new ShadowTransformer(viewPager, pagerAdapter);
        fragmentCardShadowTransformer.enableScaling(true);

        viewPager.setAdapter(pagerAdapter);
        viewPager.setPageTransformer(false, fragmentCardShadowTransformer);
        viewPager.setOffscreenPageLimit(1);
        viewPager.setPagingEnabled(true);

        int lastPosition=Integer.valueOf( 8);
        viewPager.setCurrentItem(pos-1);
        //getActivity().g
        FloatingActionButton floatingActionButton=(FloatingActionButton)getActivity().findViewById(R.id.fab);
        floatingActionButton.hide();
        return view;
    }

    public static float dpToPixels(int dp, Context context) {
        return dp * (context.getResources().getDisplayMetrics().density);
    }
}
