package com.erickogi14gmail.kale.Search;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v7.widget.CardView;
import android.view.ViewGroup;

import com.erickogi14gmail.kale.Dictionary.WordPojo;

import java.util.ArrayList;
import java.util.List;

public class CardFragmentPagerAdapter extends FragmentStatePagerAdapter implements CardAdapter {

    private List<CardFragment> fragments;
    private float baseElevation;
    private ArrayList<WordPojo> wordPojos;


    public CardFragmentPagerAdapter(FragmentManager fm, float baseElevation, ArrayList<WordPojo> wordPojos) {
        super(fm);
        fragments = new ArrayList<>();
        this.baseElevation = baseElevation;
        this.wordPojos=wordPojos;
        //this.no=no;

        for(int i = 0; i< wordPojos.size(); i++){
            addCardFragment(new CardFragment());
        }
    }

    @Override
    public float getBaseElevation() {
        return baseElevation;
    }

    @Override
    public CardView getCardViewAt(int position) {
        return fragments.get(position).getCardView();
    }

    @Override
    public int getCount() {
        return fragments.size();
    }

    @Override
    public Fragment getItem(int position) {

        return CardFragment.getInstance(position,wordPojos.get(position));

    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        Object fragment = super.instantiateItem(container, position);
        fragments.set(position, (CardFragment) fragment);
        return fragment;
    }

    public void addCardFragment(CardFragment fragment) {
        fragments.add(fragment);
    }

}
