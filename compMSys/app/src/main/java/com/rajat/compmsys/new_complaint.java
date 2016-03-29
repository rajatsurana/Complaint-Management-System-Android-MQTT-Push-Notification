package com.rajat.compmsys;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.rajat.compmsys.Volley.VolleyClick;
import com.rajat.compmsys.adapter.Listobject;
import com.rajat.compmsys.adapter.MyRecyclerViewAdapter;

import java.io.File;
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

    ImageView image;
    Bitmap rotatedBitmap;
    View v;
    static final int CAMERA_PIC_REQUEST = 1111;
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
        v = inflater.inflate(R.layout.new_complaint, container, false);
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
             // if(!b.equals(null))Bitmap b= BitmapFactory.decodeResource(getResources(),R.drawable.ic_thumb_up_black_48dp);
           //   File file = new File(Environment.getExternalStorageDirectory()+File.separator + "image.jpg");
              //  image = (ImageView) v.findViewById(R.id.imageView_nc);
            //  Bitmap b= decodeSampledBitmapFromFile(file.getAbsolutePath(), 500, 500);
            //    Bitmap b2= Bitmap.createScaledBitmap(b,400,400,true);
                image = (ImageView) v.findViewById(R.id.imageView_nc);
                Bitmap bitmap = ((BitmapDrawable)image.getDrawable()).getBitmap();
                Bitmap bit=Bitmap.createScaledBitmap(bitmap,250,250,true);
            //  if(!rotatedBitmap.equals(null))rotatedBitmap= BitmapFactory.decodeResource(getResources(),R.drawable.ic_thumb_up_black_48dp);

                VolleyClick.newComplaintClick(bit,MainActivity.sharedpreferences.getString("id",""),item,place.getText().toString(),description.getText().toString(),"",MainActivity.sharedpreferences.getString("hostel",""),getContext());
            }
        });
        Button cam_button = (Button)v.findViewById(R.id.button_nc);
        cam_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
                File file = new File(Environment.getExternalStorageDirectory()+File.separator + "image.png");
                intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(file));
                startActivityForResult(intent, CAMERA_PIC_REQUEST);


            }
        });
        // Spinner Drop down elements
        List<String> categories = new ArrayList<String>();
        categories.add("Plumber");
        categories.add("Electrician");
        categories.add("Carpenter");
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
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == CAMERA_PIC_REQUEST && resultCode == Activity.RESULT_OK) {


            BitmapFactory.Options bmpFactoryOptions = new BitmapFactory.Options();
            bmpFactoryOptions.inJustDecodeBounds = false;
            File file = new File(Environment.getExternalStorageDirectory()+File.separator + "image.png");

            //imageFilePath image path which you pass with intent
            Bitmap bp = BitmapFactory.decodeFile(file.getAbsolutePath(), bmpFactoryOptions);

            //rotate image by 90 degrees
           /* Matrix rotateMatrix = new Matrix();
            rotateMatrix.postRotate(90);
            rotatedBitmap = Bitmap.createBitmap(bp, 0, 0, bp.getWidth(), bp.getHeight(), rotateMatrix, false);*/

            //add the image to the note through a function call
           // note.addImage(rotatedBitmap);
         //   note.saveImageToDevice(rotatedBitmap);

            image = (ImageView) v.findViewById(R.id.imageView_nc);
           // Bitmap b= decodeSampledBitmapFromFile(file.getAbsolutePath(), 500, 500);
          //  Bitmap b2= Bitmap.createScaledBitmap(b,400,400,true);
            image.setImageBitmap(bp);
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
    public static Bitmap decodeSampledBitmapFromFile(String path,
                                                     int reqWidth, int reqHeight) { // BEST QUALITY MATCH

        // First decode with inJustDecodeBounds=true to check dimensions
        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(path, options);

        // Calculate inSampleSize
        // Raw height and width of image
        final int height = options.outHeight;
        final int width = options.outWidth;
        options.inPreferredConfig = Bitmap.Config.RGB_565;
        int inSampleSize = 1;

        if (height > reqHeight) {
            inSampleSize = Math.round((float)height / (float)reqHeight);
        }

        int expectedWidth = width / inSampleSize;

        if (expectedWidth > reqWidth) {
            //if(Math.round((float)width / (float)reqWidth) > inSampleSize) // If bigger SampSize..
            inSampleSize = Math.round((float)width / (float)reqWidth);
        }


        options.inSampleSize = inSampleSize;

        // Decode bitmap with inSampleSize set
        options.inJustDecodeBounds = false;

        return BitmapFactory.decodeFile(path, options);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

}
