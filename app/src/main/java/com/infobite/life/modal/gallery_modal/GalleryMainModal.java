package com.infobite.life.modal.gallery_modal;

import java.util.ArrayList;
import java.util.List;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.infobite.life.modal.gallery_modal.Datum;

public class GalleryMainModal implements Parcelable
{

    @SerializedName("data")
    @Expose
    private List<Datum> data = new ArrayList<Datum>();
    public final static Parcelable.Creator<GalleryMainModal> CREATOR = new Creator<GalleryMainModal>() {


        @SuppressWarnings({
                "unchecked"
        })
        public GalleryMainModal createFromParcel(Parcel in) {
            return new GalleryMainModal(in);
        }

        public GalleryMainModal[] newArray(int size) {
            return (new GalleryMainModal[size]);
        }

    }
            ;

    protected GalleryMainModal(Parcel in) {
        in.readList(this.data, (com.infobite.life.modal.gallery_modal.Datum.class.getClassLoader()));
    }

    public GalleryMainModal() {
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
