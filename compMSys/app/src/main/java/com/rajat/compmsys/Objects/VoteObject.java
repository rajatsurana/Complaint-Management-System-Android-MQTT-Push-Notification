package com.rajat.compmsys.Objects;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Rajat on 27-03-2016.
 */
public class VoteObject implements Parcelable {
    int down=0;
    int up=0;
    boolean canVote=true;
    String complaintId="";
    String vote_id="";

    public VoteObject(int down, int up, boolean canVote, String complaintId, String vote_id) {
        this.down = down;
        this.up = up;
        this.canVote = canVote;
        this.complaintId = complaintId;
        this.vote_id = vote_id;
    }

    public int getDown() {
        return down;
    }

    public void setDown(int down) {
        this.down = down;
    }

    public int getUp() {
        return up;
    }

    public void setUp(int up) {
        this.up = up;
    }

    public boolean isCanVote() {
        return canVote;
    }

    public void setCanVote(boolean canVote) {
        this.canVote = canVote;
    }

    public String getComplaintId() {
        return complaintId;
    }

    public void setComplaintId(String complaintId) {
        this.complaintId = complaintId;
    }

    public String getVote_id() {
        return vote_id;
    }

    public void setVote_id(String vote_id) {
        this.vote_id = vote_id;
    }

    protected VoteObject(Parcel in) {
        down = in.readInt();
        up = in.readInt();
        canVote = in.readByte() != 0x00;
        complaintId = in.readString();
        vote_id = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(down);
        dest.writeInt(up);
        dest.writeByte((byte) (canVote ? 0x01 : 0x00));
        dest.writeString(complaintId);
        dest.writeString(vote_id);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<VoteObject> CREATOR = new Parcelable.Creator<VoteObject>() {
        @Override
        public VoteObject createFromParcel(Parcel in) {
            return new VoteObject(in);
        }

        @Override
        public VoteObject[] newArray(int size) {
            return new VoteObject[size];
        }
    };
}