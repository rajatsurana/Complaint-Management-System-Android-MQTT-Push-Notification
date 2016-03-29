package com.rajat.compmsys.Volley;

import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.NotificationCompat;
import android.util.Log;
import android.widget.Toast;


import com.rajat.compmsys.LoginActivity;
import com.rajat.compmsys.MainActivity;

import com.rajat.compmsys.Objects.ComplaintObject;
import com.rajat.compmsys.Objects.UserObject;
import com.rajat.compmsys.Objects.VoteObject;
import com.rajat.compmsys.R;
import com.rajat.compmsys.Tools.Tools;
import com.rajat.compmsys.adapter.MyRecyclerViewAdapter;
import com.rajat.compmsys.addnewuser;
import com.rajat.compmsys.listview;
import com.rajat.compmsys.search;
import com.rajat.compmsys.vote_dialogbox;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by Rajat on 19-02-2016.
 */
public class JSONParser {
    public static void LoginApiJsonParser(String JsonStringResult, Context con) {
        try {
            String email = "";
            String user_id = "";
            String whoCreated="";
            String category="";
            String hostel="";
            String message="";
            String password="";
            String token = "";
            String error="";
            UserObject user ;
            JSONObject userObj;
            //create json object from response string
            JSONObject resultJson = new JSONObject(JsonStringResult);
            if (resultJson.has("token")) {
                token = resultJson.getString("token");

                if (resultJson.has("message")) {message = resultJson.getString("message");}
                if(resultJson.has("user")){
                    userObj=resultJson.getJSONObject("user");
                    if (userObj.has("email")) {email = userObj.getString("email");}
                    if (userObj.has("_id")) {user_id = userObj.getString("_id");}
                    if (userObj.has("whoCreated")) {whoCreated = userObj.getString("whoCreated");}
                    if (userObj.has("hostel")) {hostel = userObj.getString("hostel");}
                    if (userObj.has("password")) {password = userObj.getString("password");}

                    if (userObj.has("category")) {category = userObj.getString("category");}
                }


                user=new UserObject(email,user_id,whoCreated,category,hostel,password);

                Log.i("rajat", email + " " + user_id + " " + token + " " + category + " " + message+" "+error);
//                Tools.showAlertDialog(email + " " + user_id  + " " + category + " " + message+" "+error, con);
                Intent openH = new Intent(con, MainActivity.class);
                openH.putExtra("token", token);
                openH.putExtra("email", user.getEmail());
                openH.putExtra("id", user.getUser_id());
                openH.putExtra("hostel", user.getHostel());
                openH.putExtra("category", user.getCategory());
                openH.putExtra("whocreated",user.getWhoCreated());
                con.startActivity(openH);

            } else if (resultJson.has("error")) {
                error = resultJson.getString("error");
            }

        } catch (Exception e) {
            Log.i("rajat", "Exception: Login: " + e.getLocalizedMessage());
        }
    }

    public static void CreateUserApiJsonParser(String JsonStringResult, Context con) {
        try {
            String email = "";
            String user_id = "";
            String whoCreated="";
            String category="";
            String hostel="";
            String message="";
            String password="";
            //String token = "";
            //String error="";
            UserObject user ;
            JSONObject userObj;
            //create json object from response string
            JSONObject resultJson = new JSONObject(JsonStringResult);
            if (resultJson.has("message")) {
                //token = resultJson.getString("message");
                message = resultJson.getString("message");
                if(message.equals("user_created")){
                    if(resultJson.has("user")){
                        userObj=resultJson.getJSONObject("user");
                        if (userObj.has("email")) {email = userObj.getString("email");}
                        if (userObj.has("_id")) {user_id = userObj.getString("_id");}
                        if (userObj.has("whoCreated")) {whoCreated = userObj.getString("whoCreated");}
                        if (userObj.has("hostel")) {hostel = userObj.getString("hostel");}
                        if (userObj.has("password")) {password = userObj.getString("password");}
                        //if (resultJson.has("message")) {}
                        if (userObj.has("category")) {category = userObj.getString("category");}

                        user=new UserObject(email,user_id,whoCreated,category,hostel,password);
                        Toast.makeText(con, "User Created", Toast.LENGTH_SHORT).show();
                        addnewuser fragment = new addnewuser();
          /*  Bundle b=new Bundle();
            b.putInt("id",1);
            fragment.setArguments(b);
            //fragment.setArguments();*/

                        android.support.v4.app.FragmentTransaction fragmentTransaction =
                                ((FragmentActivity)con).getSupportFragmentManager().beginTransaction();
                        fragmentTransaction.replace(R.id.frame_container,fragment);
                        fragmentTransaction.commit();
                    }
                }else if (message.equals("user_already_exists")){
                    addnewuser fragment = new addnewuser();
          /*  Bundle b=new Bundle();
            b.putInt("id",1);
            fragment.setArguments(b);
            //fragment.setArguments();*/

                    android.support.v4.app.FragmentTransaction fragmentTransaction =
                            ((FragmentActivity)con).getSupportFragmentManager().beginTransaction();
                    fragmentTransaction.replace(R.id.frame_container,fragment);
                    fragmentTransaction.commit();
                    Toast.makeText(con, "User already exists", Toast.LENGTH_SHORT).show();
                    Log.i("rajat", "User already exists");
                }
                else{Toast.makeText(con, "unsucessful", Toast.LENGTH_SHORT).show();}
            }

            Log.i("rajat", email + " " + user_id + " "  + " " + category + " " + message);
            Tools.showAlertDialog(email + " " + user_id  + " " + category + " " + message, con);
        } catch (Exception e) {
            Log.i("rajat", "Exception: Login: " + e.getLocalizedMessage());
        }
    }

    public static void FindMyCreatedUsersApiJsonParser(String JsonStringResult, Context con) {
        try {
            String email = "";
            String user_id = "";
            String whoCreated="";
            String category="";
            String hostel="";
            String message="";
            String password="";
            //String token = "";
            //String error="";
            ArrayList<UserObject> userObjList = new ArrayList<UserObject>();
            UserObject user ;
            JSONObject userObj;
            JSONArray userObjectArr;
            //create json object from response string
            JSONObject resultJson = new JSONObject(JsonStringResult);
            if (resultJson.has("message")) {
                //token = resultJson.getString("message");
                message = resultJson.getString("message");
                if(message.equals("users_found")){
                    if(resultJson.has("users")){
                        userObjectArr = resultJson.getJSONArray("users");
                        for (int i = 0; i < userObjectArr.length(); i++) {
                            userObj=userObjectArr.getJSONObject(i);
                            if (userObj.has("email")) {email = userObj.getString("email");}
                            if (userObj.has("_id")) {user_id = userObj.getString("_id");}
                            if (userObj.has("whoCreated")) {whoCreated = userObj.getString("whoCreated");}
                            if (userObj.has("hostel")) {hostel = userObj.getString("hostel");}
                            if (userObj.has("password")) {password = userObj.getString("password");}
                            //if (resultJson.has("message")) {}
                            if (userObj.has("category")) {category = userObj.getString("category");}

                            user=new UserObject(email,user_id,whoCreated,category,hostel,password);
                            userObjList.add(user);
                        }
                    }
                }else if (message.equals("no_users_found")){
                    Log.i("rajat", "no_users_found");
                }
            }

            Log.i("rajat", email + " " + user_id + " "  + " " + category + " " + message);
            Tools.showAlertDialog(email + " " + user_id  + " " + category + " " + message +userObjList.size()+" :size", con);
        } catch (Exception e) {
            Log.i("rajat", "Exception: Login: " + e.getLocalizedMessage());
        }
    }

    public static void ChangePasswordApiJsonParser(String JsonStringResult, Context con) {
        try {
            String email = "";
            String user_id = "";
            String whoCreated="";
            String category="";
            String hostel="";
            String message="";
            String password="";
            //String token = "";
            //String error="";
            UserObject user ;
            JSONObject userObj;
            //create json object from response string
            JSONObject resultJson = new JSONObject(JsonStringResult);
            if (resultJson.has("message")) {
                //token = resultJson.getString("message");
                message = resultJson.getString("message");
                if(message.equals("password_changed")){
                    if(resultJson.has("user")){
                        userObj=resultJson.getJSONObject("user");
                        if (userObj.has("email")) {email = userObj.getString("email");}
                        if (userObj.has("_id")) {user_id = userObj.getString("_id");}
                        if (userObj.has("whoCreated")) {whoCreated = userObj.getString("whoCreated");}
                        if (userObj.has("hostel")) {hostel = userObj.getString("hostel");}
                        if (userObj.has("password")) {password = userObj.getString("password");}
                        //if (resultJson.has("message")) {}
                        if (userObj.has("category")) {category = userObj.getString("category");}

                        user=new UserObject(email,user_id,whoCreated,category,hostel,password);
                    }
                }else if (message.equals("username_or_password_incorrect")){
                    Log.i("rajat", "username_or_password_incorrect");
                }
            }

            Log.i("rajat", email + " " + user_id + " " +  " " + category + " " + message);
            Tools.showAlertDialog(email + " " + user_id  + " " + category + " " + message, con);
        } catch (Exception e) {
            Log.i("rajat", "Exception: Login: " + e.getLocalizedMessage());
        }
    }

    public static void NewComplaintApiJsonParser(String JsonStringResult, Context con) {
        try {
            String solver = "";
            String user_id = "";
            String place="";
            String description="";
            String status="";
            String message="";
            String topic="";
            String complaint_id="";
            int down=0;
            int up=0;
            boolean canVote=true;
            String complaintId="";
            String vote_id="";
            JSONObject complaintObj;
            JSONObject voteObj;
            ComplaintObject complaintObject;
            VoteObject voteObject;

            //create json object from response string
            JSONObject resultJson = new JSONObject(JsonStringResult);
            if (resultJson.has("message")) {
                //token = resultJson.getString("message");
                message = resultJson.getString("message");
                if(message.equals("complaint_created")){
                    if(resultJson.has("complaint")){
                        complaintObj=resultJson.getJSONObject("complaint");
                        if (complaintObj.has("solver")) {solver = complaintObj.getString("solver");}
                        if (complaintObj.has("_id")) {complaint_id = complaintObj.getString("_id");}
                        if (complaintObj.has("place")) {place = complaintObj.getString("place");}
                        if (complaintObj.has("description")) {description = complaintObj.getString("description");}
                        if (complaintObj.has("userId")) {user_id = complaintObj.getString("userId");}
                        //if (resultJson.has("message")) {}
                        if (complaintObj.has("topic")) {topic = complaintObj.getString("topic");}

                        complaintObject=new ComplaintObject(solver,user_id,place,description,status,topic,complaint_id);
                    }
                    if(resultJson.has("vote")){
                        voteObj=resultJson.getJSONObject("vote");
                        if (voteObj.has("up")) {up = voteObj.getInt("up");}
                        if (voteObj.has("down")) {down = voteObj.getInt("down");}
                        if (voteObj.has("_id")) {vote_id = voteObj.getString("_id");}
                        if (voteObj.has("canVote")) {canVote = voteObj.getBoolean("canVote");}
                        if (voteObj.has("complaintId")) {complaintId = voteObj.getString("complaintId");}

                        voteObject=new VoteObject(down,up,canVote,complaintId,vote_id);
                    }
                    VolleyClick.myComplaintsClick(MainActivity.sharedpreferences.getString("id",""),con);
                }
            }else{
                Log.i("rajat", "complaint not filed");
            }
            //Log.i("rajat", email + " " + user_id + " " +  " " + category + " " + message);
            Tools.showAlertDialog(topic + " " + place  + " " + description + " " + message, con);
        } catch (Exception e) {
            Log.i("rajat", "Exception: Login: " + e.getLocalizedMessage());
        }
    }

    public static void MyComplaintsApiJsonParser(String JsonStringResult, Context con) {
        try {
            String solver = "";
            String user_id = "";
            String place="";
            String description="";
            String status="";
            String message="";
            String topic="";
            String complaint_id="";

            JSONObject complaintObj;

            ComplaintObject complaintObject;

            ArrayList<ComplaintObject> complaintObjList = new ArrayList<ComplaintObject>();
            JSONArray complaints;
            //create json object from response string
            JSONObject resultJson = new JSONObject(JsonStringResult);
            if (resultJson.has("message")) {
                //token = resultJson.getString("message");
                message = resultJson.getString("message");
                if(message.equals("complaints_found")){
                    if(resultJson.has("complaints")){
                        complaints = resultJson.getJSONArray("complaints");
                        for (int i = 0; i < complaints.length(); i++) {
                            complaintObj = complaints.getJSONObject(i);
                            if (complaintObj.has("solver")) {solver = complaintObj.getString("solver");}
                            if (complaintObj.has("_id")) {complaint_id = complaintObj.getString("_id");}
                            if (complaintObj.has("place")) {place = complaintObj.getString("place");}
                            if (complaintObj.has("description")) {description = complaintObj.getString("description");}
                            if (complaintObj.has("userId")) {user_id = complaintObj.getString("userId");}
                            if (complaintObj.has("status")) {status = complaintObj.getString("status");}
                            //if (resultJson.has("message")) {}
                            if (complaintObj.has("topic")) {topic = complaintObj.getString("topic");}
                            complaintObject=new ComplaintObject(solver,user_id,place,description,status,topic,complaint_id);
                            complaintObjList.add(complaintObject);
                        }
                        //complaintObj=resultJson.getJSONObject("complaint");
                        Log.i("rajat","size:- "+complaintObjList.size());


                    }

                }else if(message.equals("no_complaints_found")){

                }


            } else {
                Log.i("rajat", "complaint not found");
            }
            //Log.i("rajat", email + " " + user_id + " " +  " " + category + " " + message);
            //Tools.showAlertDialog(topic + " " + user_id  + " " + solver + " " + message, con);
            listview fragment = new listview();
            Bundle b=new Bundle();
            b.putInt("id",0);
            b.putParcelableArrayList("allcomplaints",complaintObjList);
            fragment.setArguments(b);
            //fragment.setArguments();*/

            android.support.v4.app.FragmentTransaction fragmentTransaction =
                    ((FragmentActivity)con).getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.frame_container,fragment);
            fragmentTransaction.commit();
        } catch (Exception e) {
            Log.i("rajat", "Exception: Login: " + e.getLocalizedMessage());
        }
    }

    public static void SearchComplaintsApiJsonParser(String JsonStringResult, Context con) {
        try {
            String solver = "";
            String user_id = "";
            String place="";
            String description="";
            String status="";
            String message="";
            String topic="";
            String complaint_id="";

            JSONObject complaintObj;

            ComplaintObject complaintObject;

            ArrayList<ComplaintObject> complaintObjList = new ArrayList<ComplaintObject>();
            JSONArray complaints;
            //create json object from response string
            JSONObject resultJson = new JSONObject(JsonStringResult);
            if (resultJson.has("message")) {
                //token = resultJson.getString("message");
                message = resultJson.getString("message");
                if(message.equals("complaints_found")){
                    if(resultJson.has("complaints")){
                        complaints = resultJson.getJSONArray("complaints");
                        Log.i("rajat",complaints.length()+":complaints.len");
                        for (int i = 0; i < complaints.length(); i++) {

                            complaintObj = complaints.getJSONObject(i);
                            if (complaintObj.has("solver")) {solver = complaintObj.getString("solver");}
                            if (complaintObj.has("_id")) {complaint_id = complaintObj.getString("_id");}
                            if (complaintObj.has("place")) {place = complaintObj.getString("place");}
                            if (complaintObj.has("description")) {description = complaintObj.getString("description");}
                            if (complaintObj.has("userId")) {user_id = complaintObj.getString("userId");}
                            //if (resultJson.has("message")) {}
                            if (complaintObj.has("topic")) {topic = complaintObj.getString("topic");}

                            complaintObject=new ComplaintObject(solver,user_id,place,description,status,topic,complaint_id);
                            complaintObjList.add(complaintObject);
                        }
                        //complaintObj=resultJson.getJSONObject("complaint");
                        Log.i("rajat","size:- "+complaintObjList.size());
                    }
                    search.mAdapter = new MyRecyclerViewAdapter(complaintObjList);
                    search.mAdapter.notifyDataSetChanged();
                    search.mRecyclerView.setAdapter(search.mAdapter);

                }else if(message.equals("no_complaints_found")){

                }

            } else {
                Log.i("rajat", "complaint not found");
            }
            //Log.i("rajat", email + " " + user_id + " " +  " " + category + " " + message);
            Tools.showAlertDialog(topic + " " + user_id  + " " + solver + " " + message, con);
        } catch (Exception e) {
            Log.i("rajat", "Exception: Login: " + e.getLocalizedMessage());
        }
    }

    public static void SolverComplaintsApiJsonParser(String JsonStringResult, Context con) {
        try {
            String solver = "";
            String user_id = "";
            String place="";
            String description="";
            String status="";
            String message="";
            String topic="";
            String complaint_id="";

            JSONObject complaintObj;

            ComplaintObject complaintObject;

            ArrayList<ComplaintObject> complaintObjList = new ArrayList<ComplaintObject>();
            JSONArray complaints;
            //create json object from response string
            JSONObject resultJson = new JSONObject(JsonStringResult);
            if (resultJson.has("message")) {
                //token = resultJson.getString("message");
                message = resultJson.getString("message");
                if(message.equals("complaints_found")){
                    if(resultJson.has("complaints")){
                        complaints = resultJson.getJSONArray("complaints");
                        Log.i("rajat",complaints.length()+":complaints.len");
                        for (int i = 0; i < complaints.length(); i++) {

                            complaintObj = complaints.getJSONObject(i);
                            if (complaintObj.has("solver")) {solver = complaintObj.getString("solver");}
                            if (complaintObj.has("_id")) {complaint_id = complaintObj.getString("_id");}
                            if (complaintObj.has("place")) {place = complaintObj.getString("place");}
                            if (complaintObj.has("description")) {description = complaintObj.getString("description");}
                            if (complaintObj.has("userId")) {user_id = complaintObj.getString("userId");}
                            if (complaintObj.has("status")) {status = complaintObj.getString("status");}

                            //if (resultJson.has("message")) {}
                            if (complaintObj.has("topic")) {topic = complaintObj.getString("topic");}

                            complaintObject=new ComplaintObject(solver,user_id,place,description,status,topic,complaint_id);
                            complaintObjList.add(complaintObject);
                        }
                        //complaintObj=resultJson.getJSONObject("complaint");
                        Log.i("rajat","size:- "+complaintObjList.size());
                        listview fragment = new listview();
                        Bundle b=new Bundle();
                        b.putInt("id",1);
                        b.putParcelableArrayList("allcomplaints",complaintObjList);
                        fragment.setArguments(b);
                        //fragment.setArguments();*/

                        android.support.v4.app.FragmentTransaction fragmentTransaction =
                                ((FragmentActivity)con).getSupportFragmentManager().beginTransaction();
                        fragmentTransaction.replace(R.id.frame_container,fragment);
                        fragmentTransaction.commit();
                    }

                    //search.mAdapter = new MyRecyclerViewAdapter(complaintObjList);
                    //search.mAdapter.notifyDataSetChanged();
                    //search.mRecyclerView.setAdapter(search.mAdapter);

                }else if(message.equals("no_complaints_found")){

                }

            } else {
                Log.i("rajat", "complaint not found");
            }
            //Log.i("rajat", email + " " + user_id + " " +  " " + category + " " + message);
            Tools.showAlertDialog(topic + " " + user_id  + " " + solver + " " + message, con);
        } catch (Exception e) {
            Log.i("rajat", "Exception: Login: " + e.getLocalizedMessage());
        }
    }
    public static void ComplaintDescriptionApiJsonParser(String JsonStringResult, Context con) {
        try {
            String solver = "";
            String user_id = "";
            String place="";
            String description="";
            String status="";
            String message="";
            String topic="";
            String complaint_id="";
            JSONObject complaintObj;
            ComplaintObject complaintObject;
            //create json object from response string
            JSONObject resultJson = new JSONObject(JsonStringResult);
            if (resultJson.has("message")) {
                //token = resultJson.getString("message");
                message = resultJson.getString("message");
                if(message.equals("complaint_found")){
                    if(resultJson.has("complaint")){
                        complaintObj=resultJson.getJSONObject("complaint");
                        if (complaintObj.has("solver")) {solver = complaintObj.getString("solver");}
                        if (complaintObj.has("_id")) {complaint_id = complaintObj.getString("_id");
                        }
                        if (complaintObj.has("place")) {place = complaintObj.getString("place");
                        }
                        if (complaintObj.has("canVote")) {description = complaintObj.getString("description");}
                        if (complaintObj.has("userId")) {user_id = complaintObj.getString("userId");
                        }
                        //if (resultJson.has("message")) {}
                        if (complaintObj.has("topic")) {topic = complaintObj.getString("topic");}
                        complaintObject=new ComplaintObject(solver,user_id,place,description,status,topic,complaint_id);
                    }
                }else if(message.equals("no_complaint_found")){
                    Log.i("rajat", "no_complaint_found");
                }
            }else{
                Log.i("rajat", "complaint not found");
            }
            //Log.i("rajat", email + " " + user_id + " " +  " " + category + " " + message);
            Tools.showAlertDialog(topic + " " + user_id  + " " + solver + " " + message, con);
        } catch (Exception e) {
            Log.i("rajat", "Exception: Login: " + e.getLocalizedMessage());
        }
    }

    public static void ChangeComplaintStatusApiJsonParser(String JsonStringResult, Context con) {
        try {
            String solver = "";
            String user_id = "";
            String place="";
            String description="";
            String status="";
            String message="";
            String topic="";
            String complaint_id="";
            JSONObject complaintObj;
            ComplaintObject complaintObject;
            //create json object from response string
            JSONObject resultJson = new JSONObject(JsonStringResult);
            if (resultJson.has("message")) {
                //token = resultJson.getString("message");
                message = resultJson.getString("message");
                if(message.equals("complaint_status_updated")){
                    if(resultJson.has("complaint_status")){
                        status=resultJson.getString("complaint_status");

                        /*complaintObj=resultJson.getJSONObject("complaint");
                        if (complaintObj.has("solver")) {solver = complaintObj.getString("solver");}
                        if (complaintObj.has("_id")) {complaint_id = complaintObj.getString("_id");
                        }
                        if (complaintObj.has("place")) {place = complaintObj.getString("place");
                        }
                        if (complaintObj.has("canVote")) {description = complaintObj.getString("description");}
                        if (complaintObj.has("userId")) {user_id = complaintObj.getString("userId");
                        }
                        //if (resultJson.has("message")) {}
                        if (complaintObj.has("topic")) {topic = complaintObj.getString("topic");}
                        complaintObject=new ComplaintObject(solver,user_id,place,description,status,topic,complaint_id);*/
                    }
                    VolleyClick.solverComplaintsClick(MainActivity.sharedpreferences.getString("category",""),con);

                }else if(message.equals("no_complaint_found")){
                    Toast.makeText(con, "no_complaint_found", Toast.LENGTH_SHORT).show();
                    Log.i("rajat", "no_complaint_found");
                }
            }else{
                Log.i("rajat", "complaint not found");
            }
            //Log.i("rajat", email + " " + user_id + " " +  " " + category + " " + message);
            Tools.showAlertDialog(status + " " + message, con);
        } catch (Exception e) {
            Log.i("rajat", "Exception: Login: " + e.getLocalizedMessage());
        }
    }

    public static void DeleteComplaintApiJsonParser(String JsonStringResult, Context con) {
        try {
            String solver = "";
            String user_id = "";
            String place="";
            String description="";
            String status="";
            String message="";
            String topic="";
            String complaint_id="";
            JSONObject complaintObj;
            ComplaintObject complaintObject;
            //create json object from response string
            JSONObject resultJson = new JSONObject(JsonStringResult);
            if (resultJson.has("message")) {
                //token = resultJson.getString("message");
                message = resultJson.getString("message");
                if(message.equals("complaint_successfully_deleted")){
                    Log.i("rajat", "complaint_successfully_deleted");
                }else if(message.equals("complaint_not_exist")){
                    Log.i("rajat", "complaint_not_exist");
                }
            }else{
                Log.i("rajat", "complaint not found");
            }
            //Log.i("rajat", email + " " + user_id + " " +  " " + category + " " + message);
            Tools.showAlertDialog(status + " " + message, con);
        } catch (Exception e) {
            Log.i("rajat", "Exception: Login: " + e.getLocalizedMessage());
        }
    }

    public static void VoteApiJsonParser(String JsonStringResult, Context con) {
        try {

            String message="";

            int down=0;
            int up=0;
            boolean canVote=true;
            String complaintId="";
            String vote_id="";
            JSONObject complaintObj;
            JSONObject voteObj;
            ComplaintObject complaintObject;
            VoteObject voteObject;
            JSONArray userVoteArr;
            //create json object from response string
            JSONObject resultJson = new JSONObject(JsonStringResult);
            if (resultJson.has("message")) {
                //token = resultJson.getString("message");
                message = resultJson.getString("message");
                if(message.equals("voted_success")){

                    if(resultJson.has("vote")){
                        voteObj=resultJson.getJSONObject("vote");
                        if (voteObj.has("up")) {up = voteObj.getInt("up");}
                        if (voteObj.has("down")) {down = voteObj.getInt("down");}
                        if (voteObj.has("_id")) {vote_id = voteObj.getString("_id");}
                        if (voteObj.has("canVote")) {canVote = voteObj.getBoolean("canVote");}
                        if (voteObj.has("complaintId")) {complaintId = voteObj.getString("complaintId");}
                        if (voteObj.has("userVotesArr")){
                            userVoteArr = voteObj.getJSONArray("userVoteArr");

                        }
                        voteObject=new VoteObject(down,up,canVote,complaintId,vote_id);
                    }
                    vote_dialogbox.upvote.setImageDrawable(con.getResources().getDrawable(R.drawable.ic_thumb_up_black_48dp));
                    vote_dialogbox.text.setText("upvoted");
                    //if message voted_success then UI update
                }else if(message.equals("voting_over")){
                    Log.i("rajat", "voting_over");
                }else if(message.equals("no_voting_started")){
                    Log.i("rajat", "no_voting_started");
                }
            }
            //Log.i("rajat", email + " " + user_id + " " +  " " + category + " " + message);
            Tools.showAlertDialog(  " " + message, con);
        } catch (Exception e) {
            Log.i("rajat", "Exception: Login: " + e.getLocalizedMessage());
        }
    }

    public static void VoteStatusChangeApiJsonParser(String JsonStringResult, Context con) {
        try {
            String message="";
            int down=0;
            int up=0;
            boolean canVote=true;
            String complaintId="";
            String vote_id="";
            JSONObject complaintObj;
            JSONObject voteObj;
            ComplaintObject complaintObject;
            VoteObject voteObject;
            JSONArray userVoteArr;
            //create json object from response string
            JSONObject resultJson = new JSONObject(JsonStringResult);
            if (resultJson.has("message")) {
                //token = resultJson.getString("message");
                message = resultJson.getString("message");
                if(message.equals("vote_status_changed")){

                    if(resultJson.has("vote")){
                        voteObj=resultJson.getJSONObject("vote");
                        if (voteObj.has("up")) {up = voteObj.getInt("up");}
                        if (voteObj.has("down")) {down = voteObj.getInt("down");}
                        if (voteObj.has("_id")) {vote_id = voteObj.getString("_id");}
                        if (voteObj.has("canVote")) {canVote = voteObj.getBoolean("canVote");}
                        if (voteObj.has("complaintId")) {complaintId = voteObj.getString("complaintId");}
                        if (voteObj.has("userVotesArr")){
                            userVoteArr = voteObj.getJSONArray("userVoteArr");
                        }
                        voteObject=new VoteObject(down,up,canVote,complaintId,vote_id);
                    }
                    //if message voted_success then UI update
                }else if(message.equals("no_voting_started")){
                    Log.i("rajat", "no_voting_started");
                }
            }
            //Log.i("rajat", email + " " + user_id + " " +  " " + category + " " + message);
            Tools.showAlertDialog( " " + message, con);
        } catch (Exception e) {
            Log.i("rajat", "Exception: Login: " + e.getLocalizedMessage());
        }
    }

}