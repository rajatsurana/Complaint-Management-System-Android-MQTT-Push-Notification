package com.rajat.compmsys;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.rajat.compmsys.Objects.ComplaintObject;
import com.rajat.compmsys.adapter.Listobject;
import com.rajat.compmsys.adapter.MyRecyclerViewAdapter;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link listview.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link listview#//newInstance} factory method to
 * create an instance of this fragment.
 */
public class listview extends Fragment {

    int id;

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private OnFragmentInteractionListener mListener;
    ArrayList<ComplaintObject> values=new ArrayList<>();
    FloatingActionButton fab;

    public listview() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Bundle b = getArguments();
        id = b.getInt("id");
        values=b.getParcelableArrayList("allcomplaints");
        View view = inflater.inflate(R.layout.listview_fragment, container, false);
        if (id == 0) {
            fab = (FloatingActionButton) view.findViewById(R.id.fab);
            fab.setVisibility(view.VISIBLE);
            fab.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    new_complaint fragment = new new_complaint();
                    //fragment.setArguments();

                    android.support.v4.app.FragmentTransaction fragmentTransaction =
                            ((FragmentActivity) getContext()).getSupportFragmentManager().beginTransaction();
                    fragmentTransaction.replace(R.id.frame_container, fragment);
                    fragmentTransaction.commit();
                }
            });
        }

        mRecyclerView = (RecyclerView) view.findViewById(R.id.my_recycler_view_2);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(mLayoutManager);
        mAdapter = new MyRecyclerViewAdapter(values);
        mRecyclerView.setAdapter(mAdapter);
        ((MyRecyclerViewAdapter) mAdapter).setOnItemClickListener(new MyRecyclerViewAdapter
                .MyClickListener() {
            @Override
            public void onItemClick(int position, View v) {

                if (id == 0) {
                    // Toast.makeText(parent.getContext(), position, Toast.LENGTH_SHORT).show();
                    //Bundle b=new Bundle();
                    Intent openH = new Intent(getActivity(), imagedialog.class);
                    startActivity(openH);
                } else {
                    admin_complain fragment = new admin_complain();
                  /*  Bundle b=new Bundle();
                    b.putInt("id",1);
                    fragment.setArguments(b);
                    //fragment.setArguments();*/

                    android.support.v4.app.FragmentTransaction fragmentTransaction =
                            getActivity().getSupportFragmentManager().beginTransaction();
                    fragmentTransaction.replace(R.id.frame_container,fragment);
                    fragmentTransaction.commit();
                }
            }
        });

            return view;
        }

    @Override
    public void onResume() {
        super.onResume();

    }
        @Override
        public void onAttach (Context context){
            super.onAttach(context);
        }

        @Override
        public void onDetach () {
            super.onDetach();
        }
        public interface OnFragmentInteractionListener {
            // TODO: Update argument type and name
            void onFragmentInteraction(Uri uri);
        }
    }

