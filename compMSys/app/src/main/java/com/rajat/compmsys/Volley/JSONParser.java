package com.rajat.compmsys.Volley;

import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.NotificationCompat;
import android.util.Log;
import android.widget.Toast;


import com.rajat.compmsys.MainActivity;
import com.rajat.compmsys.Objects.UserObject;
import com.rajat.compmsys.Tools.Tools;

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
                MainActivity.editor = MainActivity.sharedpreferences.edit();
                MainActivity.editor.putString("token", token);
                MainActivity.editor.apply();
                if(resultJson.has("user")){
                    userObj=resultJson.getJSONObject("user");
                    if (userObj.has("email")) {email = userObj.getString("email");}
                    if (userObj.has("_id")) {user_id = userObj.getString("_id");}
                    if (userObj.has("whoCreated")) {whoCreated = userObj.getString("whoCreated");}
                    if (userObj.has("hostel")) {hostel = userObj.getString("hostel");}
                    if (userObj.has("password")) {password = userObj.getString("password");}
                    if (userObj.has("message")) {message = userObj.getString("message");}
                    if (userObj.has("category")) {category = userObj.getString("category");}
                }


                user=new UserObject(email,user_id,whoCreated,category,hostel,password);

            } else if (resultJson.has("error")) {
                error = resultJson.getString("error");
            }
            Log.i("rajat", email + " " + user_id + " " + token + " " + category + " " + message+" "+error);
            Tools.showAlertDialog(email + " " + user_id  + " " + category + " " + message+" "+error, con);
        } catch (Exception e) {
            Log.i("rajat", "Exception: Login: " + e.getLocalizedMessage());
        }
    }

    //
    /*
        public static void FindProductsApiJsonParser(String JsonStringResult, Context con) {
        try {
            JSONArray products;
            JSONObject product;
            String created_at = "";
            String updated_at = "";
            String userId = "";
            int discount = 0;
            String description = "";
            int quantity = 0;
            int price = 0;
            String productId = "";
            String message = "";
            //create json object from response string
            ArrayList<ProductObject> productObjList = new ArrayList<ProductObject>();
            JSONObject resultJson = new JSONObject(JsonStringResult);
            if (resultJson.has("products")) {
                products = resultJson.getJSONArray("products");
                for (int i = 0; i < products.length(); i++) {
                    product = products.getJSONObject(i);
                    discount = product.getInt("discount");
                    quantity = product.getInt("quantity");
                    if(product.has("price")){
                    price = product.getInt("price");}
                    userId = product.getString("userId");
                    description = product.getString("description");
                    productId = product.getString("_id");
                    created_at = product.getString("created_at");
                    updated_at = product.getString("updated_at");
                    productObjList.add(new ProductObject(created_at, updated_at, userId, discount, description, quantity, price, productId));
                }
                // message=resultJson.getString("message");
            }
            Log.i("rajat", discount + " " + quantity + " " + price + " " + userId + " " + description + " " + productId + " size: " + productObjList.size() + " " + message);
            Tools.showAlertDialog(discount + " " + quantity + " " + price + " " + userId + " " + description + " " + productId + " " + message, con);
        } catch (Exception e) {
            Log.i("rajat", "Exception: Login: " + e.getLocalizedMessage());
        }
    }

    public static void CreateProductApiJsonParser(String JsonStringResult, Context con) {
        try {

            JSONObject product;

            String created_at = "";
            String updated_at = "";
            String userId = "";
            int discount = 0;
            String description = "";
            int quantity = 0;
            int price = 0;
            String productId = "";
            String message = "";
            //create json object from response string
            JSONObject resultJson = new JSONObject(JsonStringResult);
            if (resultJson.has("newProduct")) {
                product = resultJson.getJSONObject("newProduct");
                discount = product.getInt("discount");
                quantity = product.getInt("quantity");
                price = product.getInt("price");
                userId = product.getString("userId");
                description = product.getString("description");
                productId = product.getString("_id");
                created_at = product.getString("created_at");
                updated_at = product.getString("updated_at");
                message = resultJson.getString("message");
            }
            Log.i("rajat", discount + " " + quantity + " " + price + " " + userId + " " + description + " " + productId + " " + message);
            Tools.showAlertDialog(discount + " " + quantity + " " + price + " " + userId + " " + description + " " + productId + " " + message, con);
        } catch (Exception e) {
            Log.i("rajat", "Exception: Login: " + e.getLocalizedMessage());
        }
    }
     */
    //
}