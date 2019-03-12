
package com.infobite.life.modal.search_model;

import java.util.ArrayList;
import java.util.List;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SearchModel implements Parcelable
{

    @SerializedName("data")
    @Expose
    private List<Datum> data = new ArrayList<Datum>();
    public final static Creator<SearchModel> CREATOR = new Creator<SearchModel>() {


        @SuppressWarnings({
            "unchecked"
        })
        public SearchModel createFromParcel(Parcel in) {
            return new SearchModel(in);
        }

        public SearchModel[] newArray(int size) {
            return (new SearchModel[size]);
        }

    }
    ;

    protected SearchModel(Parcel in) {
        in.readList(this.data, (Datum.class.getClassLoader()));
    }

    public SearchModel() {
    }

    public List<Datum> getData() {
        return data;
    }

    public void setData(List<Datum> data) {
        this.data = data;
    }

    public SearchModel withData(List<Datum> data) {
        this.data = data;
        return this;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeList(data);
    }

    public int describeContents() {
        return  0;
    }

}
