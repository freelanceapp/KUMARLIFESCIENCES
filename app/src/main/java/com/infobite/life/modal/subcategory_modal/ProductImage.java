package com.infobite.life.modal.subcategory_modal;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ProductImage implements Parcelable
{

    @SerializedName("product_id")
    @Expose
    private String productId;
    @SerializedName("product_image")
    @Expose
    private String productImage;
    public final static Parcelable.Creator<ProductImage> CREATOR = new Creator<ProductImage>() {


        @SuppressWarnings({
                "unchecked"
        })
        public ProductImage createFromParcel(Parcel in) {
            return new ProductImage(in);
        }

        public ProductImage[] newArray(int size) {
            return (new ProductImage[size]);
        }

    }
            ;

    protected ProductImage(Parcel in) {
        this.productId = ((String) in.readValue((String.class.getClassLoader())));
        this.productImage = ((String) in.readValue((String.class.getClassLoader())));
    }

    public ProductImage() {
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getProductImage() {
        return productImage;
    }

    public void setProductImage(String productImage) {
        this.productImage = productImage;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(productId);
        dest.writeValue(productImage);
    }

    public int describeContents() {
        return 0;
    }

}
