package com.infobite.technology.modal.main_categry_products;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CategeryMainModal implements Parcelable
{
    @SerializedName("data")
    @Expose
    private List<Datum> data = new ArrayList<Datum>();
    public final static Parcelable.Creator<CategeryMainModal> CREATOR = new Creator<CategeryMainModal>() {

        public CategeryMainModal createFromParcel(Parcel in) {
            return new CategeryMainModal(in);
        }

        public CategeryMainModal[] newArray(int size) {
            return (new CategeryMainModal[size]);
        }

    }
            ;

    protected CategeryMainModal(Parcel in) {
        in.readList(this.data, (com.infobite.technology.modal.main_categry_products.Datum.class.getClassLoader()));
    }

    public CategeryMainModal() {
    }

    public Collection<? extends Datum> getData() {
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