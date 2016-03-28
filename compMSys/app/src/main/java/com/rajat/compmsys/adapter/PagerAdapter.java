package com.rajat.compmsys.adapter;

/**
 * Created by Lenovo on 3/26/2016.
 */
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.rajat.compmsys.card_view;

public class PagerAdapter extends FragmentStatePagerAdapter {
    int mNumOfTabs;

    public PagerAdapter(FragmentManager fm, int NumOfTabs) {
        super(fm);
        this.mNumOfTabs = NumOfTabs;
    }

    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
                card_view tab1 = new card_view();
                Bundle b=new Bundle();
                b.putInt("id",0);
                tab1.setArguments(b);
                return tab1;
            case 1:
                card_view tab2 = new card_view();
                Bundle b1=new Bundle();
                b1.putInt("id",1);
                tab2.setArguments(b1);
                return tab2;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return mNumOfTabs;
    }
}