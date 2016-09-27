package com.rajat.compmsys.adapter;

/**
 * Created by Lenovo on 3/26/2016.
 */
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.rajat.compmsys.MainActivity;
import com.rajat.compmsys.Objects.ComplaintObject;
import com.rajat.compmsys.card_view;
import com.rajat.compmsys.db.DatabaseHandler;

import java.util.ArrayList;

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
                DatabaseHandler dbh = new DatabaseHandler(MainActivity.context);
                ArrayList<ComplaintObject> complainObjList=dbh.readAllPersonalComplaints();
                card_view tab1 = new card_view();
                Bundle b=new Bundle();
                b.putParcelableArrayList("complainObjList",complainObjList);
                b.putInt("id", 0);
                tab1.setArguments(b);
                return tab1;
            case 1:
                DatabaseHandler dbh2 = new DatabaseHandler(MainActivity.context);
                ArrayList<ComplaintObject> complainObjList2=dbh2.readAllPublicComplaints();
                card_view tab2 = new card_view();
                Bundle b1=new Bundle();
                b1.putParcelableArrayList("complainObjList",complainObjList2);
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