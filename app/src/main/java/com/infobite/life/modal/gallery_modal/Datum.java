package com.infobite.life.modal.gallery_modal;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Datum implements Parcelable {

    @SerializedName("g_id")
    @Expose
    private String gId;
    @SerializedName("gallery_image")
    @Expose
    private String galleryImage;
    public final static Parcelable.Creator<Datum> CREATOR = new Creator<Datum>() {


        @SuppressWarnings({
                "unchecked"
        })
        public Datum createFromParcel(Parcel in) {
            return new Datum(in);
        }

        public Datum[] newArray(int size) {
            return (new Datum[size]);
        }

    };

    protected Datum(Parcel in) {
        this.gId = ((String) in.readValue((String.class.getClassLoader())));
        this.galleryImage = ((String) in.readValue((String.class.getClassLoader())));
    }

    public Datum() {
    }

    public String getGId() {
        return gId;
    }

    public void setGId(String gId) {
        this.gId = gId;
    }

    public String getGalleryImage() {
        return galleryImage;
    }

    public void setGalleryImage(String galleryImage) {
        this.galleryImage = galleryImage;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(gId);
        dest.writeValue(galleryImage);
    }

    public int describeContents() {
        return 0;
    }
}
