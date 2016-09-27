package com.rajat.compmsys;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.rajat.compmsys.Objects.ComplaintObject;
import com.rajat.compmsys.Volley.VolleyClick;
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
public class search extends Fragment {
    EditText search;
    int id;
    public static RecyclerView mRecyclerView;
    public static RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private OnFragmentInteractionListener mListener;
    ArrayList<ComplaintObject> values=new ArrayList<>();

    public search() {}
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
       /* Bundle b=getArguments();
        id=b.getInt("id");*/
      /*  for(int i=0;i<10;i++){
            ComplaintObject yo=new ComplaintObject("papa","arpit","girnar","supp","yo","gsgw","eqgag");
            values.add(yo);
        }*/
        View view=inflater.inflate(R.layout.search,container,false);
        search=(EditText)view.findViewById(R.id.search_string);
            Button button=(Button)view.findViewById(R.id.search_butt);
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    VolleyClick.searchComplaintsClick(search.getText().toString(),MainActivity.sharedpreferences.getString("id",""),getContext());
                 /*   ComplaintObject yo=new ComplaintObject("papa","arpit","girnar","su254534pp","yo","gsgw","eqgag");
                    values.add(yo);*/



                }
            });
        mRecyclerView = (RecyclerView) view.findViewById(R.id.my_recycler_view_1);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(mLayoutManager);
        mAdapter = new MyRecyclerViewAdapter(values);
        mRecyclerView.setAdapter(mAdapter);
        ((MyRecyclerViewAdapter) mAdapter).setOnItemClickListener(new MyRecyclerViewAdapter
                .MyClickListener() {
            @Override
            public void onItemClick(int position, View v) {
                // Toast.makeText(parent.getContext(), position, Toast.LENGTH_SHORT).show();
                //Bundle b=new Bundle();
                Intent openH = new Intent(getActivity(), vote_dialogbox.class);
                openH.putExtra("complaint",values.get(position));
                startActivity(openH);
            }
        });

        return view;
    }
    @Override
    public void onResume() {
        super.onResume();
   /*     ((MyRecyclerViewAdapter) mAdapter).setOnItemClickListener(new MyRecyclerViewAdapter
                .MyClickListener() {
            @Override
            public void onItemClick(int position, View v) {
                    // Toast.makeText(parent.getContext(), position, Toast.LENGTH_SHORT).show();
                    //Bundle b=new Bundle();
                    Intent openH = new Intent(getActivity(), vote_dialogbox.class);
                    startActivity(openH);
            }
        });*/
    }
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);}

    @Override
    public void onDetach() {
        super.onDetach();
    }
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
