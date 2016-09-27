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
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.rajat.compmsys.Volley.VolleyClick;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link addnewuser.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link addnewuser#//newInstance} factory method to
 * create an instance of this fragment.
 */
public class addnewuser extends Fragment {
    EditText newusername,newpassword,newpassword2;
    String item,item1;
    View v;
    private OnFragmentInteractionListener mListener;

    public addnewuser() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v=inflater.inflate(R.layout.fragment_addnewuser, container, false);
        // Spinner element
        Spinner spinner = (Spinner) v.findViewById(R.id.usertype_list);
        final Spinner spinner2=(Spinner)v.findViewById(R.id.hostel_list);

        // Spinner click listener
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {// On selecting a spinner item
                item1 = parent.getItemAtPosition(position).toString();
                if(item1.matches("Student")||item1.matches("Student-Admin")||item1.matches("Warden")){
                    spinner2.setVisibility(v.VISIBLE);
                }
                else{
                    spinner2.setVisibility(v.INVISIBLE);
                }
                // Showing selected spinner item
                Toast.makeText(parent.getContext(), "Selected: " + item1, Toast.LENGTH_LONG).show();
            }
            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub
            }

        });
        spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {// On selecting a spinner item
                item = parent.getItemAtPosition(position).toString();
                Toast.makeText(parent.getContext(), "Selected: " + item, Toast.LENGTH_LONG).show();
            }
            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub
            }

        });
        // Spinner Drop down elements
        List<String> categories = new ArrayList<String>();
        if(MainActivity.sharedpreferences.getString("category","").equals("StudentAdmin")){categories.add("Student");}
        else if(MainActivity.sharedpreferences.getString("category","").equals("Warden")){
            categories.add("Student");
            categories.add("Student-Admin");}
        else if(MainActivity.sharedpreferences.getString("category","").equals("Dean")){
            categories.add("Student");
            categories.add("Student-Admin");
            categories.add("Warden");}

        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_dropdown_item, categories);

        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        spinner.setAdapter(dataAdapter);
        List<String> hostels = new ArrayList<String>();
        hostels.add("Girnar");
        hostels.add("Udaigiri");
        hostels.add("Satpura");
        hostels.add("Kailash");
        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter2 = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_dropdown_item, hostels);

        // Drop down layout style - list view with radio button
        dataAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        spinner2.setAdapter(dataAdapter2);

        Button button= (Button)v.findViewById(R.id.create);
        newusername=(EditText)v.findViewById(R.id.newusername);
        newpassword=(EditText)v.findViewById(R.id.newpassowrd);
        newpassword2=(EditText)v.findViewById(R.id.passwordcheck);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                VolleyClick.createUserClick(newusername.getText().toString(),newpassword.getText().toString(),item,item1,MainActivity.sharedpreferences.getString("id",""),getContext());
             //   Toast.makeText(getContext(), "button pressed", Toast.LENGTH_SHORT).show();

            }
        });
    return v;}

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
