package com.infobite.life.modal.order_history_modal;

import java.util.ArrayList;
import java.util.List;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Datum implements Parcelable
{

    @SerializedName("product_order_id")
    @Expose
    private String productOrderId;
    @SerializedName("user_id")
    @Expose
    private String userId;
    @SerializedName("order_number")
    @Expose
    private String orderNumber;
    @SerializedName("first_name")
    @Expose
    private String firstName;
    @SerializedName("company_name")
    @Expose
    private String companyName;
    @SerializedName("user_email")
    @Expose
    private String userEmail;
    @SerializedName("address")
    @Expose
    private String address;
    @SerializedName("phone_number")
    @Expose
    private String phoneNumber;
    @SerializedName("state")
    @Expose
    private String state;
    @SerializedName("city")
    @Expose
    private String city;
    @SerializedName("zip_code")
    @Expose
    private String zipCode;
    @SerializedName("order_status")
    @Expose
    private String orderStatus;
    @SerializedName("order_date")
    @Expose
    private String orderDate;
    @SerializedName("order_products")
    @Expose
    private List<OrderProduct> orderProducts = new ArrayList<OrderProduct>();
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
        this.productOrderId = ((String) in.readValue((String.class.getClassLoader())));
        this.userId = ((String) in.readValue((String.class.getClassLoader())));
        this.orderNumber = ((String) in.readValue((String.class.getClassLoader())));
        this.firstName = ((String) in.readValue((String.class.getClassLoader())));
        this.companyName = ((String) in.readValue((String.class.getClassLoader())));
        this.userEmail = ((String) in.readValue((String.class.getClassLoader())));
        this.address = ((String) in.readValue((String.class.getClassLoader())));
        this.phoneNumber = ((String) in.readValue((String.class.getClassLoader())));
        this.state = ((String) in.readValue((String.class.getClassLoader())));
        this.city = ((String) in.readValue((String.class.getClassLoader())));
        this.zipCode = ((String) in.readValue((String.class.getClassLoader())));
        this.orderStatus = ((String) in.readValue((String.class.getClassLoader())));
        this.orderDate = ((String) in.readValue((String.class.getClassLoader())));
        in.readList(this.orderProducts, (com.infobite.life.modal.order_history_modal.OrderProduct.class.getClassLoader()));
    }

    public Datum() {
    }

    public String getProductOrderId() {
        return productOrderId;
    }

    public void setProductOrderId(String productOrderId) {
        this.productOrderId = productOrderId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    public List<OrderProduct> getOrderProducts() {
        return orderProducts;
    }

    public void setOrderProducts(List<OrderProduct> orderProducts) {
        this.orderProducts = orderProducts;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(productOrderId);
        dest.writeValue(userId);
        dest.writeValue(orderNumber);
        dest.writeValue(firstName);
        dest.writeValue(companyName);
        dest.writeValue(userEmail);
        dest.writeValue(address);
        dest.writeValue(phoneNumber);
        dest.writeValue(state);
        dest.writeValue(city);
        dest.writeValue(zipCode);
        dest.writeValue(orderStatus);
        dest.writeValue(orderDate);
        dest.writeList(orderProducts);
    }

    public int describeContents() {
        return 0;
    }

}
