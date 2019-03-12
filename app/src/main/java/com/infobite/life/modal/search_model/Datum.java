
package com.infobite.life.modal.search_model;

import java.util.ArrayList;
import java.util.List;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Datum implements Parcelable
{

    @SerializedName("product_id")
    @Expose
    private String productId;
    @SerializedName("product_category")
    @Expose
    private String productCategory;
    @SerializedName("product_sub_category")
    @Expose
    private String productSubCategory;
    @SerializedName("product_type")
    @Expose
    private String productType;
    @SerializedName("product_price")
    @Expose
    private String productPrice;
    @SerializedName("quantity_type")
    @Expose
    private String quantityType;
    @SerializedName("product_qty")
    @Expose
    private String productQty;
    @SerializedName("manufacturing_date")
    @Expose
    private String manufacturingDate;
    @SerializedName("expiry_date")
    @Expose
    private String expiryDate;
    @SerializedName("product_description")
    @Expose
    private String productDescription;
    @SerializedName("product_status")
    @Expose
    private String productStatus;
    @SerializedName("product_availablity")
    @Expose
    private String productAvailablity;
    @SerializedName("created_date")
    @Expose
    private String createdDate;
    @SerializedName("modified_date")
    @Expose
    private String modifiedDate;
    @SerializedName("product_image")
    @Expose
    private List<ProductImage> productImage = new ArrayList<ProductImage>();
    public final static Creator<Datum> CREATOR = new Creator<Datum>() {


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
        this.productId = ((String) in.readValue((String.class.getClassLoader())));
        this.productCategory = ((String) in.readValue((String.class.getClassLoader())));
        this.productSubCategory = ((String) in.readValue((String.class.getClassLoader())));
        this.productType = ((String) in.readValue((String.class.getClassLoader())));
        this.productPrice = ((String) in.readValue((String.class.getClassLoader())));
        this.quantityType = ((String) in.readValue((String.class.getClassLoader())));
        this.productQty = ((String) in.readValue((String.class.getClassLoader())));
        this.manufacturingDate = ((String) in.readValue((String.class.getClassLoader())));
        this.expiryDate = ((String) in.readValue((String.class.getClassLoader())));
        this.productDescription = ((String) in.readValue((String.class.getClassLoader())));
        this.productStatus = ((String) in.readValue((String.class.getClassLoader())));
        this.productAvailablity = ((String) in.readValue((String.class.getClassLoader())));
        this.createdDate = ((String) in.readValue((String.class.getClassLoader())));
        this.modifiedDate = ((String) in.readValue((String.class.getClassLoader())));
        in.readList(this.productImage, (com.infobite.life.modal.search_model.ProductImage.class.getClassLoader()));
    }

    public Datum() {
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public Datum withProductId(String productId) {
        this.productId = productId;
        return this;
    }

    public String getProductCategory() {
        return productCategory;
    }

    public void setProductCategory(String productCategory) {
        this.productCategory = productCategory;
    }

    public Datum withProductCategory(String productCategory) {
        this.productCategory = productCategory;
        return this;
    }

    public String getProductSubCategory() {
        return productSubCategory;
    }

    public void setProductSubCategory(String productSubCategory) {
        this.productSubCategory = productSubCategory;
    }

    public Datum withProductSubCategory(String productSubCategory) {
        this.productSubCategory = productSubCategory;
        return this;
    }

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }

    public Datum withProductType(String productType) {
        this.productType = productType;
        return this;
    }

    public String getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(String productPrice) {
        this.productPrice = productPrice;
    }

    public Datum withProductPrice(String productPrice) {
        this.productPrice = productPrice;
        return this;
    }

    public String getQuantityType() {
        return quantityType;
    }

    public void setQuantityType(String quantityType) {
        this.quantityType = quantityType;
    }

    public Datum withQuantityType(String quantityType) {
        this.quantityType = quantityType;
        return this;
    }

    public String getProductQty() {
        return productQty;
    }

    public void setProductQty(String productQty) {
        this.productQty = productQty;
    }

    public Datum withProductQty(String productQty) {
        this.productQty = productQty;
        return this;
    }

    public String getManufacturingDate() {
        return manufacturingDate;
    }

    public void setManufacturingDate(String manufacturingDate) {
        this.manufacturingDate = manufacturingDate;
    }

    public Datum withManufacturingDate(String manufacturingDate) {
        this.manufacturingDate = manufacturingDate;
        return this;
    }

    public String getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(String expiryDate) {
        this.expiryDate = expiryDate;
    }

    public Datum withExpiryDate(String expiryDate) {
        this.expiryDate = expiryDate;
        return this;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }

    public Datum withProductDescription(String productDescription) {
        this.productDescription = productDescription;
        return this;
    }

    public String getProductStatus() {
        return productStatus;
    }

    public void setProductStatus(String productStatus) {
        this.productStatus = productStatus;
    }

    public Datum withProductStatus(String productStatus) {
        this.productStatus = productStatus;
        return this;
    }

    public String getProductAvailablity() {
        return productAvailablity;
    }

    public void setProductAvailablity(String productAvailablity) {
        this.productAvailablity = productAvailablity;
    }

    public Datum withProductAvailablity(String productAvailablity) {
        this.productAvailablity = productAvailablity;
        return this;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    public Datum withCreatedDate(String createdDate) {
        this.createdDate = createdDate;
        return this;
    }

    public String getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(String modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    public Datum withModifiedDate(String modifiedDate) {
        this.modifiedDate = modifiedDate;
        return this;
    }

    public List<ProductImage> getProductImage() {
        return productImage;
    }

    public void setProductImage(List<ProductImage> productImage) {
        this.productImage = productImage;
    }

    public Datum withProductImage(List<ProductImage> productImage) {
        this.productImage = productImage;
        return this;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(productId);
        dest.writeValue(productCategory);
        dest.writeValue(productSubCategory);
        dest.writeValue(productType);
        dest.writeValue(productPrice);
        dest.writeValue(quantityType);
        dest.writeValue(productQty);
        dest.writeValue(manufacturingDate);
        dest.writeValue(expiryDate);
        dest.writeValue(productDescription);
        dest.writeValue(productStatus);
        dest.writeValue(productAvailablity);
        dest.writeValue(createdDate);
        dest.writeValue(modifiedDate);
        dest.writeList(productImage);
    }

    public int describeContents() {
        return  0;
    }

}
