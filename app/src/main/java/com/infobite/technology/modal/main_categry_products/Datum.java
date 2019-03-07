package com.infobite.technology.modal.main_categry_products;

import java.util.ArrayList;
import java.util.List;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Datum implements Parcelable
{

    @SerializedName("category_id")
    @Expose
    private String categoryId;
    @SerializedName("category_name")
    @Expose
    private String categoryName;
    @SerializedName("category_image")
    @Expose
    private String categoryImage;
    @SerializedName("category_status")
    @Expose
    private String categoryStatus;
    @SerializedName("created_date")
    @Expose
    private String createdDate;
    @SerializedName("subcategory")
    @Expose
    private List<Object> subcategory = new ArrayList<Object>();
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
        this.categoryId = ((String) in.readValue((String.class.getClassLoader())));
        this.categoryName = ((String) in.readValue((String.class.getClassLoader())));
        this.categoryImage = ((String) in.readValue((String.class.getClassLoader())));
        this.categoryStatus = ((String) in.readValue((String.class.getClassLoader())));
        this.createdDate = ((String) in.readValue((String.class.getClassLoader())));
        in.readList(this.subcategory, (java.lang.Object.class.getClassLoader()));
    }

    public Datum() {
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getCategoryImage() {
        return categoryImage;
    }

    public void setCategoryImage(String categoryImage) {
        this.categoryImage = categoryImage;
    }

    public String getCategoryStatus() {
        return categoryStatus;
    }

    public void setCategoryStatus(String categoryStatus) {
        this.categoryStatus = categoryStatus;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    public List<Object> getSubcategory() {
        return subcategory;
    }

    public void setSubcategory(List<Object> subcategory) {
        this.subcategory = subcategory;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(categoryId);
        dest.writeValue(categoryName);
        dest.writeValue(categoryImage);
        dest.writeValue(categoryStatus);
        dest.writeValue(createdDate);
        dest.writeList(subcategory);
    }

    public int describeContents() {
        return 0;
    }

}
