package com.infobite.life.modal.banner_modal;

import java.util.ArrayList;
import java.util.List;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class BannerMainModal implements Parcelable
{

    @SerializedName("data")
    @Expose
    private List<Datum> data = new ArrayList<Datum>();
    public final static Parcelable.Creator<BannerMainModal> CREATOR = new Creator<BannerMainModal>() {


        @SuppressWarnings({
                "unchecked"
        })
        public BannerMainModal createFromParcel(Parcel in) {
            return new BannerMainModal(in);
        }

        public BannerMainModal[] newArray(int size) {
            return (new BannerMainModal[size]);
        }

    }
            ;

    protected BannerMainModal(Parcel in) {
        in.readList(this.data, (com.infobite.life.modal.banner_modal.Datum.class.getClassLoader()));
    }

    public BannerMainModal() {
    }

    public List<Datum> getData() {
        return data;
    }

    public void setData(List<Datum> data) {
        this.data = data;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeList(data);
    }

    public int describeContents() {
        return 0;
    }

}