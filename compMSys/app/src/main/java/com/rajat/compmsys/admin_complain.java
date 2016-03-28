package com.rajat.compmsys;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link admin_complain.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link admin_complain#//newInstance} factory method to
 * create an instance of this fragment.
 */
public class admin_complain extends Fragment {
    ImageView image;
    TextView vote;
    Button button;
     public admin_complain() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v= inflater.inflate(R.layout.admin_complain, container, false);
        button =(Button)v.findViewById(R.id.post_button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getContext(), "button clicked", Toast.LENGTH_SHORT).show();

            }
        });
        Spinner spinner = (Spinner) v.findViewById(R.id.complain_status);

        // Spinner click listener
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {// On selecting a spinner item
                String item = parent.getItemAtPosition(position).toString();

                // Showing selected spinner item
                Toast.makeText(parent.getContext(), "Selected: " + item, Toast.LENGTH_SHORT).show();
            }
            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub
            }

        });


        // Spinner Drop down elements
        List<String> categories = new ArrayList<String>();
        categories.add("Resolved");
        categories.add("Unresolved");
        categories.add("Denied");
        categories.add("Inprogress");

        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_dropdown_item, categories);

        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        spinner.setAdapter(dataAdapter);
        vote =(TextView)v.findViewById(R.id.vote);
        vote.setText("No of votes: "+" ");
        return v;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

    }

    @Override
    public void onDetach() {
        super.onDetach();

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
