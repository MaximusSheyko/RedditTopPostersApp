package com.example.reddittoppostersapp.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import lombok.Builder;
import lombok.Data;

@Data
public class Post implements Parcelable {

    private Post(Parcel in){
        title = in.readString();
        imageUrl = in.readString();
        author = in.readString();
        numberComments = in.readInt();
        dateCreatedInUtcSec = in.readString();
    }

    @SerializedName("title")
    @Expose
    private String title;

    @SerializedName("thumbnail")
    @Expose
    private String imageUrl;

    @SerializedName("author")
    @Expose
    private String author;

    @SerializedName("num_comments")
    @Expose
    private Integer numberComments;

    @SerializedName("created")
    @Expose
    private String dateCreatedInUtcSec;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(title);
        parcel.writeString(imageUrl);
        parcel.writeString(author);
        parcel.writeInt(numberComments);
        parcel.writeString(dateCreatedInUtcSec);
    }

    public static final Parcelable.Creator<Post> CREATOR = new Parcelable.Creator<Post>(){
        public Post createFromParcel(Parcel in){
            return new Post(in);
        }

        public Post[] newArray(int size){
            return new Post[size];
        }
    };
}
