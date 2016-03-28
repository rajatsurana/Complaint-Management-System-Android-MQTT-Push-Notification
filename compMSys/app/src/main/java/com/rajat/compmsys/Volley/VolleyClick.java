package com.rajat.compmsys.Volley;

import android.content.Context;
import android.graphics.Bitmap;
import android.widget.ImageView;

import com.rajat.compmsys.Tools.CheckNetwork;
import com.rajat.compmsys.Tools.Tools;

public class VolleyClick {

    public static void onLoginClick(String email,String password,Context context){
        CheckNetwork chkNet = new CheckNetwork(context);
        String URL = "http://192.168.43.196:3000/api/login";
        if (!chkNet.checkNetwork()) {
            VolleySingleton.getInstance(context).getRequestQueue().getCache().clear();
            CallVolley.makeLoginCall(URL, context,email,password);
        } else {
            Tools.showAlertDialog("Internet Available", context);
        }
    }
    public static void onLogoutClick(Context context){
        CheckNetwork chkNet = new CheckNetwork(context);
        String URL = "http://192.168.43.196:3000/api/login";
        if (!chkNet.checkNetwork()) {
            VolleySingleton.getInstance(context).getRequestQueue().getCache().clear();
            CallVolley.makeLogoutCall(URL, context);
        } else {
            Tools.showAlertDialog("Internet Available", context);
        }
    }

    public static void uploadImageClick(Bitmap bitmap,String userId,String complaintId,Context context){
        CheckNetwork chkNet = new CheckNetwork(context);
        String URL = "http://192.168.43.196:3000/api/upload";
        if (!chkNet.checkNetwork()) {
            VolleySingleton.getInstance(context).getRequestQueue().getCache().clear();
            CallVolley.uploadImageCall(URL, context, bitmap, userId, complaintId);
        } else {
            Tools.showAlertDialog("Internet Available", context);
        }
    }

    public static void getBitmapClick(String userId,String complaintId,ImageView iv,Context context){
        CheckNetwork chkNet = new CheckNetwork(context);
        String URL = "http://192.168.43.196:3000/api/downloads/"+userId+"/"+complaintId;
        if (!chkNet.checkNetwork()) {
            VolleySingleton.getInstance(context).getRequestQueue().getCache().clear();
            CallVolley.getBitmapFromUrl(URL, context, iv);
        } else {
            Tools.showAlertDialog("Internet Available", context);
        }
    }
    public static void createUserClick(String email,String password,String hostel,String category,String whoCreated,Context context){
        CheckNetwork chkNet = new CheckNetwork(context);
        String URL = "http://192.168.43.196:3000/api/users";
        if (!chkNet.checkNetwork()) {
            VolleySingleton.getInstance(context).getRequestQueue().getCache().clear();
            CallVolley.createUserCall(URL, context, email, password, hostel, category, whoCreated);
        } else {
            Tools.showAlertDialog("Internet Available", context);
        }
    }

    public static void findMyCreatedUsersClick(String whoCreated,Context context){
        CheckNetwork chkNet = new CheckNetwork(context);
        String URL = "http://192.168.43.196:3000/api/findUsers/"+whoCreated;
        if (!chkNet.checkNetwork()) {
            VolleySingleton.getInstance(context).getRequestQueue().getCache().clear();
            CallVolley.FindMyCreatedUsersCall(URL, context);
        } else {
            Tools.showAlertDialog("Internet Available", context);
        }
    }
    public static void changePasswordClick(String email,String password,String newPassword,Context context){
        CheckNetwork chkNet = new CheckNetwork(context);
        String URL = "http://192.168.43.196:3000/api/changePassword";
        if (!chkNet.checkNetwork()) {
            VolleySingleton.getInstance(context).getRequestQueue().getCache().clear();
            CallVolley.changePasswordCall(URL, context, email, password, newPassword);
        } else {
            Tools.showAlertDialog("Internet Available", context);
        }
    }
    public static void newComplaintClick(String userId,String solver,String place,  String description,String status,String hostel,Context context){
        CheckNetwork chkNet = new CheckNetwork(context);
        String URL = "http://192.168.43.196:3000/api/newComplaint";
        if (!chkNet.checkNetwork()) {
            VolleySingleton.getInstance(context).getRequestQueue().getCache().clear();
            CallVolley.NewComplaintCall(URL, context, userId, solver, place, description, status, hostel);
        } else {
            Tools.showAlertDialog("Internet Available", context);
        }
    }
    public static void myComplaintsClick(String userId,Context context){
        CheckNetwork chkNet = new CheckNetwork(context);
        String URL = "http://192.168.43.196:3000/api/myComplaints/"+userId;
        if (!chkNet.checkNetwork()) {
            VolleySingleton.getInstance(context).getRequestQueue().getCache().clear();
            CallVolley.MyComplaintsCall(URL, context);
        } else {
            Tools.showAlertDialog("Internet Available", context);
        }
    }

    public static void searchComplaintsClick(String topic,Context context){
        CheckNetwork chkNet = new CheckNetwork(context);
        String URL = "http://192.168.43.196:3000/api/searchComplaints/"+topic;
        if (!chkNet.checkNetwork()) {
            VolleySingleton.getInstance(context).getRequestQueue().getCache().clear();
            CallVolley.SearchComplaintsCall(URL, context);
        } else {
            Tools.showAlertDialog("Internet Available", context);
        }
    }
    public static void solverComplaintsClick(String solver,Context context){
        CheckNetwork chkNet = new CheckNetwork(context);
        String URL = "http://192.168.43.196:3000/api/solverComplaints/"+solver;
        if (!chkNet.checkNetwork()) {
            VolleySingleton.getInstance(context).getRequestQueue().getCache().clear();
            CallVolley.SolverComplaintsCall(URL, context);
        } else {
            Tools.showAlertDialog("Internet Available", context);
        }
    }
    public static void changeComplaintStatusClick(String complaintId,String status,Context context){
        CheckNetwork chkNet = new CheckNetwork(context);
        String URL = "http://192.168.43.196:3000/api/changeComplaintStatus";
        if (!chkNet.checkNetwork()) {
            VolleySingleton.getInstance(context).getRequestQueue().getCache().clear();
            CallVolley.ChangeComplaintStatusCall(URL, context, complaintId, status);
        } else {
            Tools.showAlertDialog("Internet Available", context);
        }
    }

    public static void complaintDescriptionClick(String complaintId,Context context){
        CheckNetwork chkNet = new CheckNetwork(context);
        String URL = "http://192.168.43.196:3000/api/complaintDescription/"+complaintId;
        if (!chkNet.checkNetwork()) {
            VolleySingleton.getInstance(context).getRequestQueue().getCache().clear();
            CallVolley.ComplaintDescriptionCall(URL, context);
        } else {
            Tools.showAlertDialog("Internet Available", context);
        }
    }

    public static void voteClick(String complaintId,String userId,boolean upVote,Context context){
        CheckNetwork chkNet = new CheckNetwork(context);
        String URL = "http://192.168.43.196:3000/api/vote";
        if (!chkNet.checkNetwork()) {
            VolleySingleton.getInstance(context).getRequestQueue().getCache().clear();
            CallVolley.VoteCall(URL, context, complaintId, userId, upVote);
        } else {
            Tools.showAlertDialog("Internet Available", context);
        }
    }
    public static void voteStatusChangeClick(String complaintId,boolean canVote,Context context){
        CheckNetwork chkNet = new CheckNetwork(context);
        String URL = "http://192.168.43.196:3000/api/voteStatusChange";
        if (!chkNet.checkNetwork()) {
            VolleySingleton.getInstance(context).getRequestQueue().getCache().clear();
            CallVolley.VoteStatusChangeCall(URL, context, complaintId, canVote);
        } else {
            Tools.showAlertDialog("Internet Available", context);
        }
    }
    public static void deleteComplaintClick(String complaintId,String userId,Context context){
        CheckNetwork chkNet = new CheckNetwork(context);
        String URL = "http://192.168.43.196:3000/api/deleteComplaint";
        if (!chkNet.checkNetwork()) {
            VolleySingleton.getInstance(context).getRequestQueue().getCache().clear();
            CallVolley.DeleteComplaintCall(URL, context, complaintId, userId);
        } else {
            Tools.showAlertDialog("Internet Available", context);
        }
    }
}