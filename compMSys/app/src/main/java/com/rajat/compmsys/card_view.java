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

import com.rajat.compmsys.Objects.ComplaintObject;
import com.rajat.compmsys.adapter.Listobject;
import com.rajat.compmsys.adapter.MyRecyclerViewAdapter;
import com.rajat.compmsys.adapter.SwipeableRecyclerViewTouchListener;
import com.rajat.compmsys.db.DatabaseHandler;

import java.util.ArrayList;

/**
 * A simple {@link //Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link card_view.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link card_view#//newInstance} factory method to
 * create an instance of this fragment.
 */
public class card_view extends Fragment {
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private OnFragmentInteractionListener mListener;
    ArrayList<ComplaintObject> values=new ArrayList<>();

    public card_view() {}
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Bundle b= getArguments();
        values=b.getParcelableArrayList("complainObjList");
        View view= inflater.inflate(R.layout.cardview, container, false);
       /* for(int i=0;i<10;i++){
            Listobject yo=new Listobject("supp","yo");
            values.add(yo);
        }*/
        // for(int i=0;i<10;i++){
        //ComplaintObject yo=new ComplaintObject("papa","arpit","girnar","supp","yo","gsgw","eqgag");
        //values.add(yo);
        //}
        mRecyclerView = (RecyclerView) view.findViewById(R.id.my_recycler_view);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(mLayoutManager);
        mAdapter = new MyRecyclerViewAdapter(values);
        mRecyclerView.setAdapter(mAdapter);

        SwipeableRecyclerViewTouchListener swipeTouchListener =
                new SwipeableRecyclerViewTouchListener(mRecyclerView,
                        new SwipeableRecyclerViewTouchListener.SwipeListener() {


                            @Override
                            public boolean canSwipeLeft(int position) {
                                return true;
                            }

                            @Override
                            public boolean canSwipeRight(int position) {
                                return true;
                            }

                            @Override
                            public void onDismissedBySwipeLeft(RecyclerView recyclerView, int[] reverseSortedPositions) {
                                for (int position : reverseSortedPositions) {
                                    DatabaseHandler dbh =new DatabaseHandler(getActivity());
                                    dbh.deleteMyComplaint(values.get(position).getComplaint_id());
                                    values.remove(position);

                                    mAdapter.notifyItemRemoved(position);
                                }
                                mAdapter.notifyDataSetChanged();
                            }
                            @Override
                            public void onDismissedBySwipeRight(RecyclerView recyclerView, int[] reverseSortedPositions) {
                                for (int position : reverseSortedPositions) {

                                    DatabaseHandler dbh =new DatabaseHandler(getActivity());
                                    dbh.deleteMyComplaint(values.get(position).getComplaint_id());
                                    values.remove(position);
                                    mAdapter.notifyItemRemoved(position);
                                }
                                mAdapter.notifyDataSetChanged();
                            }
                        });
        mRecyclerView.addOnItemTouchListener(swipeTouchListener);
        ((MyRecyclerViewAdapter) mAdapter).setOnItemClickListener(new MyRecyclerViewAdapter
                .MyClickListener() {
            @Override
            public void onItemClick(int position, View v) {
                if(notification.pagertab==1){
                    Intent openH = new Intent(getActivity(), vote_dialogbox.class);
                    openH.putExtra("complaint",values.get(position));

                    startActivity(openH);}
                else{
                  /*  Intent openH = new Intent(getActivity(), imagedialog.class);
                    openH.putExtra("complaint",values.get(position));
                    startActivity(openH);*/}

                //    Toast.makeText(getContext(), "Selected: " + values.get(position).first, Toast.LENGTH_SHORT).show();
            }
        });
        return view;
    }




    @Override
    public void onResume() {
        super.onResume();

    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
