package com.rajat.compmsys.Objects;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Rajat on 26-03-2016.
 */
public class UserObject implements Parcelable {
    String email = "";
    String user_id = "";
    String whoCreated="";
    String category="";
    String hostel="";
    String password="";

    public UserObject(String email, String user_id, String whoCreated, String category, String hostel, String password) {
        this.email = email;
        this.user_id = user_id;
        this.whoCreated = whoCreated;
        this.category = category;
        this.hostel = hostel;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getWhoCreated() {
        return whoCreated;
    }

    public void setWhoCreated(String whoCreated) {
        this.whoCreated = whoCreated;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getHostel() {
        return hostel;
    }

    public void setHostel(String hostel) {
        this.hostel = hostel;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    protected UserObject(Parcel in) {
        email = in.readString();
        user_id = in.readString();
        whoCreated = in.readString();
        category = in.readString();
        hostel = in.readString();
        password = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(email);
        dest.writeString(user_id);
        dest.writeString(whoCreated);
        dest.writeString(category);
        dest.writeString(hostel);
        dest.writeString(password);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<UserObject> CREATOR = new Parcelable.Creator<UserObject>() {
        @Override
        public UserObject createFromParcel(Parcel in) {
            return new UserObject(in);
        }

        @Override
        public UserObject[] newArray(int size) {
            return new UserObject[size];
        }
    };
}
