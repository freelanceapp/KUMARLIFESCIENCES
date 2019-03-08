package com.infobite.life.modal.banner_modal;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Datum implements Parcelable
{

    @SerializedName("banner_id")
    @Expose
    private String bannerId;
    @SerializedName("offer_image")
    @Expose
    private String offerImage;
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

    }
            ;

    protected Datum(Parcel in) {
        this.bannerId = ((String) in.readValue((String.class.getClassLoader())));
        this.offerImage = ((String) in.readValue((String.class.getClassLoader())));
    }

    public Datum() {
    }

    public String getBannerId() {
        return bannerId;
    }

    public void setBannerId(String bannerId) {
        this.bannerId = bannerId;
    }

    public String getOfferImage() {
        return offerImage;
    }

    public void setOfferImage(String offerImage) {
        this.offerImage = offerImage;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(bannerId);
        dest.writeValue(offerImage);
    }

    public int describeContents() {
        return 0;
    }

}