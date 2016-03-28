package com.rajat.compmsys;

import android.content.Context;
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
import com.rajat.compmsys.adapter.Listobject;
import com.rajat.compmsys.adapter.MyRecyclerViewAdapter;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link //new_complaint.//OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link new_complaint#//newInstance} factory method to
 * create an instance of this fragment.
 */
public class new_complaint extends Fragment {
    String item;
    EditText description,place;
    public new_complaint() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.new_complaint, container, false);
        // Spinner element
        Spinner spinner = (Spinner) v.findViewById(R.id.spinner_type);
        description=(EditText) v.findViewById(R.id.description);
        place=(EditText) v.findViewById(R.id.place);

        // Spinner click listener
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {// On selecting a spinner item
                item = parent.getItemAtPosition(position).toString();

                // Showing selected spinner item
                Toast.makeText(parent.getContext(), "Selected: " + item, Toast.LENGTH_LONG).show();
            }
            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub
            }

        });
        Button button=(Button)v.findViewById(R.id.newcomplaintbutton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                VolleyClick.newComplaintClick(MainActivity.sharedpreferences.getString("id",""),item,place.getText().toString(),description.getText().toString(),"",MainActivity.sharedpreferences.getString("hostel",""),getContext());
            }
        });

        // Spinner Drop down elements
        List<String> categories = new ArrayList<String>();
        categories.add("Plumber");
        categories.add("Electrician");
        categories.add("carpenter");
        categories.add("LAN");
        categories.add("Other");
        categories.add("Warden");
        categories.add("Dean");

        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_dropdown_item, categories);

        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        spinner.setAdapter(dataAdapter);
        return v;
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

}
