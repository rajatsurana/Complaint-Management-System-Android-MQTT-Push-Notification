package com.rajat.compmsys.Objects;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Rajat on 27-03-2016.
 */
public class UserVoteArr implements Parcelable {
    String userId;
    boolean upVote;

    public UserVoteArr(String userId, boolean upVote) {
        this.userId = userId;
        this.upVote = upVote;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public boolean isUpVote() {
        return upVote;
    }

    public void setUpVote(boolean upVote) {
        this.upVote = upVote;
    }

    protected UserVoteArr(Parcel in) {
        userId = in.readString();
        upVote = in.readByte() != 0x00;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(userId);
        dest.writeByte((byte) (upVote ? 0x01 : 0x00));
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<UserVoteArr> CREATOR = new Parcelable.Creator<UserVoteArr>() {
        @Override
        public UserVoteArr createFromParcel(Parcel in) {
            return new UserVoteArr(in);
        }

        @Override
        public UserVoteArr[] newArray(int size) {
            return new UserVoteArr[size];
        }
    };
}