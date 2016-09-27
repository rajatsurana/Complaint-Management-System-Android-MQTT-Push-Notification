package com.rajat.compmsys.Objects;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Rajat on 27-03-2016.
 */
public class ComplaintObject implements Parcelable {
    String solver = "";
    String user_id = "";
    String place="";
    String description="";
    String status="";
    String topic="";
    String complaint_id="";

    public ComplaintObject(String solver, String user_id, String place, String description, String status, String topic, String complaint_id) {
        this.solver = solver;
        this.user_id = user_id;
        this.place = place;
        this.description = description;
        this.status = status;
        this.topic = topic;
        this.complaint_id = complaint_id;
    }

    public String getSolver() {
        return solver;
    }

    public void setSolver(String solver) {
        this.solver = solver;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getComplaint_id() {
        return complaint_id;
    }

    public void setComplaint_id(String complaint_id) {
        this.complaint_id = complaint_id;
    }

    protected ComplaintObject(Parcel in) {
        solver = in.readString();
        user_id = in.readString();
        place = in.readString();
        description = in.readString();
        status = in.readString();
        topic = in.readString();
        complaint_id = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(solver);
        dest.writeString(user_id);
        dest.writeString(place);
        dest.writeString(description);
        dest.writeString(status);
        dest.writeString(topic);
        dest.writeString(complaint_id);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<ComplaintObject> CREATOR = new Parcelable.Creator<ComplaintObject>() {
        @Override
        public ComplaintObject createFromParcel(Parcel in) {
            return new ComplaintObject(in);
        }

        @Override
        public ComplaintObject[] newArray(int size) {
            return new ComplaintObject[size];
        }
    };
}