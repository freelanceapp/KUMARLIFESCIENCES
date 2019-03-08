package com.infobite.life.modal.products_modal;
import java.util.ArrayList;
import java.util.List;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Subcategory implements Parcelable
{

    @SerializedName("sub_category_id")
    @Expose
    private String subCategoryId;
    @SerializedName("category_id")
    @Expose
    private String categoryId;
    @SerializedName("sub_category_name")
    @Expose
    private String subCategoryName;
    @SerializedName("sub_category_image")
    @Expose
    private String subCategoryImage;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("creation_date")
    @Expose
    private String creationDate;
    @SerializedName("products")
    @Expose
    private List<Product> products = new ArrayList<Product>();
    public final static Parcelable.Creator<Subcategory> CREATOR = new Creator<Subcategory>() {


        @SuppressWarnings({
                "unchecked"
        })
        public Subcategory createFromParcel(Parcel in) {
            return new Subcategory(in);
        }

        public Subcategory[] newArray(int size) {
            return (new Subcategory[size]);
        }

    }
            ;

    protected Subcategory(Parcel in) {
        this.subCategoryId = ((String) in.readValue((String.class.getClassLoader())));
        this.categoryId = ((String) in.readValue((String.class.getClassLoader())));
        this.subCategoryName = ((String) in.readValue((String.class.getClassLoader())));
        this.subCategoryImage = ((String) in.readValue((String.class.getClassLoader())));
        this.status = ((String) in.readValue((String.class.getClassLoader())));
        this.creationDate = ((String) in.readValue((String.class.getClassLoader())));
        in.readList(this.products, (com.infobite.life.modal.products_modal.Product.class.getClassLoader()));
    }

    public Subcategory() {
    }

    public String getSubCategoryId() {
        return subCategoryId;
    }

    public void setSubCategoryId(String subCategoryId) {
        this.subCategoryId = subCategoryId;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public String getSubCategoryName() {
        return subCategoryName;
    }

    public void setSubCategoryName(String subCategoryName) {
        this.subCategoryName = subCategoryName;
    }

    public String getSubCategoryImage() {
        return subCategoryImage;
    }

    public void setSubCategoryImage(String subCategoryImage) {
        this.subCategoryImage = subCategoryImage;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(String creationDate) {
        this.creationDate = creationDate;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(subCategoryId);
        dest.writeValue(categoryId);
        dest.writeValue(subCategoryName);
        dest.writeValue(subCategoryImage);
        dest.writeValue(status);
        dest.writeValue(creationDate);
        dest.writeList(products);
    }

    public int describeContents() {
        return 0;
    }

}