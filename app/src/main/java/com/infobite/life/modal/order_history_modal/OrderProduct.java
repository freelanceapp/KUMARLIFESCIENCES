package com.infobite.life.modal.order_history_modal;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class OrderProduct implements Parcelable
{

    @SerializedName("p_od_id")
    @Expose
    private String pOdId;
    @SerializedName("product_order_id")
    @Expose
    private String productOrderId;
    @SerializedName("product_name")
    @Expose
    private String productName;
    @SerializedName("product_category")
    @Expose
    private String productCategory;
    @SerializedName("product_sub_category")
    @Expose
    private String productSubCategory;
    @SerializedName("product_image")
    @Expose
    private String productImage;
    @SerializedName("product_price")
    @Expose
    private String productPrice;
    @SerializedName("product_qty")
    @Expose
    private String productQty;
    @SerializedName("total_price")
    @Expose
    private String totalPrice;
    @SerializedName("product_status")
    @Expose
    private String productStatus;
    @SerializedName("created_date")
    @Expose
    private String createdDate;
    public final static Parcelable.Creator<OrderProduct> CREATOR = new Creator<OrderProduct>() {


        @SuppressWarnings({
                "unchecked"
        })
        public OrderProduct createFromParcel(Parcel in) {
            return new OrderProduct(in);
        }

        public OrderProduct[] newArray(int size) {
            return (new OrderProduct[size]);
        }

    }
            ;

    protected OrderProduct(Parcel in) {
        this.pOdId = ((String) in.readValue((String.class.getClassLoader())));
        this.productOrderId = ((String) in.readValue((String.class.getClassLoader())));
        this.productName = ((String) in.readValue((String.class.getClassLoader())));
        this.productCategory = ((String) in.readValue((String.class.getClassLoader())));
        this.productSubCategory = ((String) in.readValue((String.class.getClassLoader())));
        this.productImage = ((String) in.readValue((String.class.getClassLoader())));
        this.productPrice = ((String) in.readValue((String.class.getClassLoader())));
        this.productQty = ((String) in.readValue((String.class.getClassLoader())));
        this.totalPrice = ((String) in.readValue((String.class.getClassLoader())));
        this.productStatus = ((String) in.readValue((String.class.getClassLoader())));
        this.createdDate = ((String) in.readValue((String.class.getClassLoader())));
    }

    public OrderProduct() {
    }

    public String getPOdId() {
        return pOdId;
    }

    public void setPOdId(String pOdId) {
        this.pOdId = pOdId;
    }

    public String getProductOrderId() {
        return productOrderId;
    }

    public void setProductOrderId(String productOrderId) {
        this.productOrderId = productOrderId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductCategory() {
        return productCategory;
    }

    public void setProductCategory(String productCategory) {
        this.productCategory = productCategory;
    }

    public String getProductSubCategory() {
        return productSubCategory;
    }

    public void setProductSubCategory(String productSubCategory) {
        this.productSubCategory = productSubCategory;
    }

    public String getProductImage() {
        return productImage;
    }

    public void setProductImage(String productImage) {
        this.productImage = productImage;
    }

    public String getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(String productPrice) {
        this.productPrice = productPrice;
    }

    public String getProductQty() {
        return productQty;
    }

    public void setProductQty(String productQty) {
        this.productQty = productQty;
    }

    public String getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(String totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getProductStatus() {
        return productStatus;
    }

    public void setProductStatus(String productStatus) {
        this.productStatus = productStatus;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(pOdId);
        dest.writeValue(productOrderId);
        dest.writeValue(productName);
        dest.writeValue(productCategory);
        dest.writeValue(productSubCategory);
        dest.writeValue(productImage);
        dest.writeValue(productPrice);
        dest.writeValue(productQty);
        dest.writeValue(totalPrice);
        dest.writeValue(productStatus);
        dest.writeValue(createdDate);
    }

    public int describeContents() {
        return 0;
    }

}
