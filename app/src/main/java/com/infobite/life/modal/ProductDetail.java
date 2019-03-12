package com.infobite.life.modal;

import android.os.Parcel;
import android.os.Parcelable;

public class ProductDetail implements Parcelable {

    private int keyId;
    private String id;
    private String name;
    private String price;
    private String category;
    private String manufacturing_date;
    private String end_date;
    private String image;
    private String images_array;
    private String attributes_array;
    private int quantity;
    private String description;

    public ProductDetail() {

    }

    public ProductDetail(int keyId, String id, String name, String description, String price, String category, String manufacturing_date,
                         String end_date, String image, String images_array, String attributes_array, int quantity) {
        this.keyId = keyId;
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.category = category;
        this.manufacturing_date = manufacturing_date;
        this.end_date = end_date;
        this.image = image;
        this.images_array = images_array;
        this.attributes_array = attributes_array;
        this.quantity = quantity;
    }

    public ProductDetail(String id, String name, String description, String price, String category, String manufacturing_date,
                         String end_date, String image, String images_array, String attributes_array, int quantity) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.category = category;
        this.manufacturing_date = manufacturing_date;
        this.end_date = end_date;
        this.image = image;
        this.images_array = images_array;
        this.attributes_array = attributes_array;
        this.quantity = quantity;
    }

    protected ProductDetail(Parcel in) {
        id = in.readString();
        name = in.readString();
        description = in.readString();
        price = in.readString();
        category = in.readString();
        manufacturing_date = in.readString();
        end_date = in.readString();
        image = in.readString();
        images_array = in.readString();
        attributes_array = in.readString();
        quantity = in.readInt();
        keyId = in.readInt();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public static final Creator<ProductDetail> CREATOR = new Creator<ProductDetail>() {
        @Override
        public ProductDetail createFromParcel(Parcel in) {
            return new ProductDetail(in);
        }

        @Override
        public ProductDetail[] newArray(int size) {
            return new ProductDetail[size];
        }
    };

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getManufacturing_date() {
        return manufacturing_date;
    }

    public void setManufacturing_date(String manufacturing_date) {
        this.manufacturing_date = manufacturing_date;
    }

    public String getEnd_date() {
        return end_date;
    }

    public void setEnd_date(String end_date) {
        this.end_date = end_date;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getImages_array() {
        return images_array;
    }

    public void setImages_array(String images_array) {
        this.images_array = images_array;
    }

    public String getAttributes_array() {
        return attributes_array;
    }

    public void setAttributes_array(String attributes_array) {
        this.attributes_array = attributes_array;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getKeyId() {
        return keyId;
    }

    public void setKeyId(int keyId) {
        this.keyId = keyId;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(id);
        parcel.writeString(name);
        parcel.writeString(description);
        parcel.writeString(price);
        parcel.writeString(category);
        parcel.writeString(manufacturing_date);
        parcel.writeString(end_date);
        parcel.writeString(image);
        parcel.writeString(images_array);
        parcel.writeString(attributes_array);
        parcel.writeInt(quantity);
        parcel.writeInt(keyId);
    }
}
