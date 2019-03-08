package com.infobite.life.modal.products_modal;

import java.util.ArrayList;
import java.util.List;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ProductsMainModal implements Parcelable
{

    @SerializedName("data")
    @Expose
    private List<Datum> data = new ArrayList<Datum>();
    public final static Parcelable.Creator<ProductsMainModal> CREATOR = new Creator<ProductsMainModal>() {


        @SuppressWarnings({
                "unchecked"
        })
        public ProductsMainModal createFromParcel(Parcel in) {
            return new ProductsMainModal(in);
        }

        public ProductsMainModal[] newArray(int size) {
            return (new ProductsMainModal[size]);
        }

    }
            ;

    protected ProductsMainModal(Parcel in) {
        in.readList(this.data, (com.infobite.life.modal.products_modal.Datum.class.getClassLoader()));
    }

    public ProductsMainModal() {
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
