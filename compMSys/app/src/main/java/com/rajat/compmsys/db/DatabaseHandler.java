package com.rajat.compmsys.db;

/**
 * Created by Rajat on 22-10-2015.
 */

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.rajat.compmsys.Objects.ComplaintObject;

import java.util.ArrayList;
import java.util.List;


public class DatabaseHandler extends SQLiteOpenHelper {

    // for our logs
    public static final String TAG = "DatabaseHandler";

    // database version
    private static final int DATABASE_VERSION = 1;

    // database name
    protected static final String DATABASE_NAME = "complaintsDB";

    private static final String TABLE_COMPLAINTS= "complaints";
    private static final String KEY_ID="_id";
    private static final String KEY_COMPLAINT_ID="complaint_id";
    private static final String KEY_USER_ID="user_id";
    private static final String KEY_SOLVER="brand_id";
    private static final String KEY_DESCRIPTION="description";
    private static final String KEY_PLACE="place";
    private static final String KEY_STATUS="status";
    private static final String KEY_TOPIC="topic";


    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    private static final String CREATE_TABLE_COMPLAINTS = "CREATE TABLE "
            + TABLE_COMPLAINTS + "(" +  KEY_ID
            + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + KEY_COMPLAINT_ID + " TEXT,"
            + KEY_USER_ID + " TEXT,"
            + KEY_DESCRIPTION + " TEXT,"
            + KEY_SOLVER + " TEXT,"
            + KEY_PLACE + " TEXT,"
            + KEY_STATUS + " TEXT,"
            + KEY_TOPIC + " TEXT"
            + ")";
    // creating table
    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(CREATE_TABLE_COMPLAINTS);

    }

    // When upgrading the database, it will drop the current table and recreate.
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {


        db.execSQL("DROP TABLE IF EXISTS " + TABLE_COMPLAINTS);
        onCreate(db);
    }

    // create new record
    // @param myObj contains details to be added as single row.
    public boolean create(ComplaintObject myObj) {
Log.i("rajat","create Complaint");
        boolean createSuccessful = false;

        //if(!checkBrandIfExists(myObj.brandName)){

            SQLiteDatabase db = this.getWritableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM " + TABLE_COMPLAINTS, null);
        int count=0;
        if (c.getCount()>0) {
            c.moveToLast();

            count = c.getInt(c.getColumnIndex(KEY_ID));
        }
            ContentValues values = new ContentValues();
        values.put(KEY_ID, count+1);
            values.put(KEY_COMPLAINT_ID, myObj.getComplaint_id());
        values.put(KEY_USER_ID, myObj.getUser_id());
        values.put(KEY_PLACE, myObj.getPlace());
        values.put(KEY_SOLVER, myObj.getSolver());
        values.put(KEY_DESCRIPTION, myObj.getDescription());
        values.put(KEY_STATUS, myObj.getStatus());
        values.put(KEY_TOPIC, myObj.getTopic());
            createSuccessful = db.insert(TABLE_COMPLAINTS, null, values) > 0;

            // db.close();

            if(createSuccessful){
                Log.e(TAG, myObj.getDescription() + " created.");
            }
        //}

        return createSuccessful;
    }
        public ArrayList<ComplaintObject> readAllPublicComplaints() {

        ArrayList<ComplaintObject> recordsList = new ArrayList<ComplaintObject>();

        // select query
        String sql = "";
        sql += "SELECT * FROM " + TABLE_COMPLAINTS ;


        SQLiteDatabase db = this.getWritableDatabase();

        // execute the query
        Cursor cursor = db.rawQuery(sql, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                int myComplaintId= cursor.getInt(cursor.getColumnIndex(KEY_ID));
                // int productId = Integer.parseInt(cursor.getString(cursor.getColumnIndex(fieldProductId)));
                String complaintId=cursor.getString(cursor.getColumnIndex(KEY_COMPLAINT_ID));
                String userId=cursor.getString(cursor.getColumnIndex(KEY_USER_ID));
                String place=cursor.getString(cursor.getColumnIndex(KEY_PLACE));
                String solver=cursor.getString(cursor.getColumnIndex(KEY_SOLVER));
                String description=cursor.getString(cursor.getColumnIndex(KEY_DESCRIPTION));
                String status=cursor.getString(cursor.getColumnIndex(KEY_STATUS));
                String topic=cursor.getString(cursor.getColumnIndex(KEY_TOPIC));


                if(status.equals("Filed")){
                    ComplaintObject mv= new ComplaintObject(solver,userId,place,description,status,topic,complaintId);
                    // add to list
                    recordsList.add(mv);
                }

            } while (cursor.moveToNext());
        }

        cursor.close();
        //db.close();

        // return the list of records
        return recordsList;
    }

    public ArrayList<ComplaintObject> readAllPersonalComplaints() {

        ArrayList<ComplaintObject> recordsList = new ArrayList<ComplaintObject>();

        // select query
        String sql = "";
        sql += "SELECT * FROM " + TABLE_COMPLAINTS ;


        SQLiteDatabase db = this.getWritableDatabase();

        // execute the query
        Cursor cursor = db.rawQuery(sql, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                int myComplaintId= cursor.getInt(cursor.getColumnIndex(KEY_ID));
                // int productId = Integer.parseInt(cursor.getString(cursor.getColumnIndex(fieldProductId)));
                String complaintId=cursor.getString(cursor.getColumnIndex(KEY_COMPLAINT_ID));
                String userId=cursor.getString(cursor.getColumnIndex(KEY_USER_ID));
                String place=cursor.getString(cursor.getColumnIndex(KEY_PLACE));
                String solver=cursor.getString(cursor.getColumnIndex(KEY_SOLVER));
                String description=cursor.getString(cursor.getColumnIndex(KEY_DESCRIPTION));
                String status=cursor.getString(cursor.getColumnIndex(KEY_STATUS));
                String topic=cursor.getString(cursor.getColumnIndex(KEY_TOPIC));

                if(!status.equals("Filed")){
                ComplaintObject mv= new ComplaintObject(solver,userId,place,description,status,topic,complaintId);
                // add to list
                recordsList.add(mv);
                }

            } while (cursor.moveToNext());
        }

        cursor.close();
        //db.close();

        // return the list of records
        return recordsList;
    }



    public ComplaintObject readMyVehicleAtPosition(int position) {
        List<ComplaintObject> recordsList = new ArrayList<ComplaintObject>();

        // select query
        String sql = "";
        sql += "SELECT * FROM " + TABLE_COMPLAINTS +" WHERE " + KEY_ID + " = '" + position + "'";


        SQLiteDatabase db = this.getWritableDatabase();

        // execute the query
        Cursor cursor = db.rawQuery(sql, null);
        ComplaintObject mv=null;
        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {


                // int productId = Integer.parseInt(cursor.getString(cursor.getColumnIndex(fieldProductId)));
            String complaintId=cursor.getString(cursor.getColumnIndex(KEY_COMPLAINT_ID));
            String userId=cursor.getString(cursor.getColumnIndex(KEY_USER_ID));
            String place=cursor.getString(cursor.getColumnIndex(KEY_PLACE));
            String solver=cursor.getString(cursor.getColumnIndex(KEY_SOLVER));
            String description=cursor.getString(cursor.getColumnIndex(KEY_DESCRIPTION));
            String status=cursor.getString(cursor.getColumnIndex(KEY_STATUS));
            String topic=cursor.getString(cursor.getColumnIndex(KEY_TOPIC));

                mv= new ComplaintObject(solver,userId,place,description,status,topic,complaintId);//brand,model,plateNumb, Double.valueOf(mileag)
                // add to list
                //recordsList.add(mv);


        }

        cursor.close();
        //db.close();

        // return the list of records
        return mv;
    }

    public void deleteMyComplaint(String complaintId) {
        //SELECT MAX( `column` ) FROM `table` ;
        //ALTER TABLE `table` AUTO_INCREMENT = number;

        SQLiteDatabase db = this.getWritableDatabase();
        String sql = "DELETE FROM " +TABLE_COMPLAINTS +" WHERE " +KEY_COMPLAINT_ID +" = '" +complaintId+"'";
        //Cursor cursorDelete = db.rawQuery(sql, null);
        db.execSQL(sql);
       // cursorDelete.close();
      /*
        int prev = cardPos-1;
        String sqlCount = "SELECT "+KEY_MY_VEHICLE_BRAND_NAME+" FROM "+ TABLE_MY_VEHICLES;
        Cursor cursorCount = db.rawQuery(sqlCount, null);

        int offset=cursorCount.getCount()-cardPos;

        String sqliChange = "SELECT "+KEY_MY_VEHICLE_ID+" FROM "+TABLE_MY_VEHICLES +" LIMIT '" +prev +"','"+offset+"'";
            Cursor cursorUpdate=db.rawQuery(sqliChange,null);

       // MyVehicles mv=null;
Log.i("rajat",cursorUpdate.getCount()+" "+cursorCount.getCount()+" "+offset);
             //db.delete(TABLE_MY_VEHICLES, KEY_MY_VEHICLE_ID + "=" + cardPos, null);
        int in=0;
        //cursorCount.moveToFirst();
        if (cursorUpdate.moveToFirst()) {
            //cursorCount.moveToFirst();
            do {
                cursorCount.moveToPosition(cardPos-1+in);

                String sqliUpd= "UPDATE "+TABLE_MY_VEHICLES +" SET "+ KEY_MY_VEHICLE_ID +" = "+ cardPos+in +" WHERE "+ KEY_MY_VEHICLE_BRAND_NAME +" = "+ cursorCount.getString(cursorCount.getColumnIndex(KEY_MY_VEHICLE_BRAND_NAME)) ;

                db.execSQL(sqliUpd);
                //
                ContentValues cv = new ContentValues();
                cv.put(KEY_MY_VEHICLE_ID,cardPos+in);

                //db.update(TABLE_MY_VEHICLES,cv,KEY_MY_VEHICLE_BRAND_NAME + " = "+cursorCount.getString(cursorCount.getColumnIndex(KEY_MY_VEHICLE_BRAND_NAME)) ,null);
                // int productId = Integer.parseInt(cursor.getString(cursor.getColumnIndex(fieldProductId)));

                in++;
                //MyVehicles mv= new MyVehicles(brand,model,plateNumb,Double.valueOf(mileag));
                // add to list
                //recordsList.add(mv);

            } while (cursorUpdate.moveToNext());
        }*/
    }
    public void deleteAllMyComplaints() {
        //SELECT MAX( `column` ) FROM `table` ;
        //ALTER TABLE `table` AUTO_INCREMENT = number;

        SQLiteDatabase db = this.getWritableDatabase();
        String sql = "DELETE FROM " + TABLE_COMPLAINTS ;//+ " WHERE " + KEY_MY_VEHICLE_ID + " = '" + cardPos + "'";
        //Cursor cursorDelete = db.rawQuery(sql, null);
        db.execSQL(sql);
    }
}