package com.infobite.life.modal.subcategory_modal;

import java.util.ArrayList;
import java.util.List;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SubcategoryMainModal implements Parcelable
{

    @SerializedName("data")
    @Expose
    private List<Datum> data = new ArrayList<Datum>();
    public final static Parcelable.Creator<SubcategoryMainModal> CREATOR = new Creator<SubcategoryMainModal>() {


        @SuppressWarnings({
                "unchecked"
        })
        public SubcategoryMainModal createFromParcel(Parcel in) {
            return new SubcategoryMainModal(in);
        }

        public SubcategoryMainModal[] newArray(int size) {
            return (new SubcategoryMainModal[size]);
        }

    }
            ;

    protected SubcategoryMainModal(Parcel in) {
        in.readList(this.data, (com.infobite.life.modal.subcategory_modal.Datum.class.getClassLoader()));
    }

    public SubcategoryMainModal() {
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

