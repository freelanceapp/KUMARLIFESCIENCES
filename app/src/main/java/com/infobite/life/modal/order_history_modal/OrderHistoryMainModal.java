package com.infobite.life.modal.order_history_modal;

import java.util.ArrayList;
import java.util.List;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class OrderHistoryMainModal implements Parcelable
{

    @SerializedName("data")
    @Expose
    private List<Datum> data = new ArrayList<Datum>();
    public final static Parcelable.Creator<OrderHistoryMainModal> CREATOR = new Creator<OrderHistoryMainModal>() {


        @SuppressWarnings({
                "unchecked"
        })
        public OrderHistoryMainModal createFromParcel(Parcel in) {
            return new OrderHistoryMainModal(in);
        }

        public OrderHistoryMainModal[] newArray(int size) {
            return (new OrderHistoryMainModal[size]);
        }

    }
            ;

    protected OrderHistoryMainModal(Parcel in) {
        in.readList(this.data, (com.infobite.life.modal.order_history_modal.Datum.class.getClassLoader()));
    }

    public OrderHistoryMainModal() {
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
