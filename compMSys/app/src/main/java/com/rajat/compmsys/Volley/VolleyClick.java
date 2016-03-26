package com.rajat.compmsys.Volley;

import android.content.Context;

//import com.rajat.moodle.Objects.CourseThreadObject;
import com.rajat.compmsys.Tools.CheckNetwork;
import com.rajat.compmsys.Tools.Tools;

/**
 * Created by Lenovo on 2/21/2016.
 */
public class VolleyClick {

    public static String cou_code;

    static int Notification=1,
            CourseAssignment=2,
            CourseList=3,
            ViewGrades=4,
            listAllCourseAssignment=5,
            viewCourseGrades=6,
            viewCourseThreads=7,
            viewParticularThread=8,
            createNewThread=9,
            postNewComment=10,
            NotificationService=11;

    public static void postNewComment(int thread_id,String description,Context context){
        CheckNetwork chkNet = new CheckNetwork(context);
        String URL = "http://192.168.43.200/threads/post_comment.json?thread_id="+thread_id+"&description="+description+"";
        if (!chkNet.checkNetwork()) {
            VolleySingleton.getInstance(context).getRequestQueue().getCache().clear();
            //CallVolley.afterLoginCall(URL, context, postNewComment);
        } else {
            Tools.showAlertDialog("Internet Available", context);
        }
    }
    public static void createNewThread(String title,String description,String course_code,Context context){
        CheckNetwork chkNet = new CheckNetwork(context);
        String URL = "http://192.168.43.200/threads/new.json?title="+title+"&description="+description+"&course_code="+course_code+"";
        if (!chkNet.checkNetwork()) {
            VolleySingleton.getInstance(context).getRequestQueue().getCache().clear();
            //CallVolley.afterLoginCall(URL, context, createNewThread);
        } else {
            Tools.showAlertDialog("Internet Available", context);
        }
    }

    public static void viewAllGrades(Context context){
        CheckNetwork chkNet = new CheckNetwork(context);
        String URL = "http://192.168.43.200/default/grades.json";
        if (!chkNet.checkNetwork()) {
            VolleySingleton.getInstance(context).getRequestQueue().getCache().clear();
            //CallVolley.afterLoginCall(URL, context, ViewGrades);
        } else {
            Tools.showAlertDialog("Internet Available", context);
        }
    }
    public static void listAllCourses(Context context){
        CheckNetwork chkNet = new CheckNetwork(context);
        String URL = "http://192.168.43.200/courses/list.json";
        if (!chkNet.checkNetwork()) {
            VolleySingleton.getInstance(context).getRequestQueue().getCache().clear();
            //CallVolley.afterLoginCall(URL, context, CourseList);
        } else {
            Tools.showAlertDialog("Internet Available", context);
        }
    }
    public static void onLogoutClick(Context context){
        CheckNetwork chkNet = new CheckNetwork(context);
        String URL = "http://192.168.43.200/default/logout.json";
        if (!chkNet.checkNetwork()) {
            VolleySingleton.getInstance(context).getRequestQueue().getCache().clear();
            CallVolley.makeLogoutCall(URL, context);
        } else {
            Tools.showAlertDialog("Internet Available", context);
        }
    }
    public static void onLoginClick(String email,String password,Context context){
        //String entryNumber1 = "cs5110281", studentName1="jasmeet";
        CheckNetwork chkNet = new CheckNetwork(context);
        String URL = "http://192.168.43.200:3000/login";
        if (!chkNet.checkNetwork()) {
            VolleySingleton.getInstance(context).getRequestQueue().getCache().clear();
            CallVolley.makeLoginCall(URL, context,email,password);
        } else {
            Tools.showAlertDialog("Internet Available", context);
        }
    }
    public static void onNotifyClick(Context context){
        CheckNetwork chkNet = new CheckNetwork(context);
        String URL = "http://192.168.43.200/default/notifications.json";
        if (!chkNet.checkNetwork()) {
            VolleySingleton.getInstance(context).getRequestQueue().getCache().clear();

            //CallVolley.afterLoginCall(URL, context, Notification);
        } else {
            Tools.showAlertDialog("Internet Available", context);
        }
    }


}
