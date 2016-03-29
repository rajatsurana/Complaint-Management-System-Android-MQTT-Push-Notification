package com.rajat.compmsys;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.Toast;

import com.rajat.compmsys.mqtt.MQTTService;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link subscribe.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link subscribe#//newInstance} factory method to
 * create an instance of this fragment.
 */
public class subscribe extends Fragment {
    private OnFragmentInteractionListener mListener;
    Switch hostel,insti;
    public subscribe() {
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
        View v= inflater.inflate(R.layout.subscribe, container, false);
        hostel=(Switch)v.findViewById(R.id.switch1);
        insti=(Switch)v.findViewById(R.id.switch2);
        hostel.setChecked(true);
        insti.setChecked(true);
        hostel.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                String hostel=MainActivity.sharedpreferences.getString("hostel", "");
                if(isChecked){
                    MQTTService.doSubscribe(hostel);
                    if(!MQTTService.str.contains(hostel)){
                        MQTTService.str.add(hostel);
                        //MQTTService.itr.add(1);
                    }
                    Toast.makeText(getContext(),"Hostel notification ON", Toast.LENGTH_SHORT).show();}
                else{
                    MQTTService.doUnsubscribe(hostel);
                    if(MQTTService.str.contains(hostel)){
                        MQTTService.str.remove(hostel);
                        //MQTTService.itr.remove(1);
                    }
                    Toast.makeText(getContext(),"Hostel notification OFF", Toast.LENGTH_SHORT).show();}
                }
        });
        insti.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    MQTTService.doSubscribe("Institute");
                    if(!MQTTService.str.contains("Institute")){
                        MQTTService.str.add("Institute");
                        //MQTTService.itr.add(1);
                    }
                    Toast.makeText(getContext(),"Insti notification ON", Toast.LENGTH_SHORT).show();}
                else{
                    MQTTService.doUnsubscribe("Institute");
                    if(MQTTService.str.contains("Institute")){
                        MQTTService.str.remove("Institute");
                        //MQTTService.itr.remove(1);
                    }

                    Toast.makeText(getContext(),"Insti notification OFF", Toast.LENGTH_SHORT).show();}
            }
        });
        return v;
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
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
