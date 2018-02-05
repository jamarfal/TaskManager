
package org.example.taskmanager.common.repository.dto;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ApiItemDto {

    @SerializedName("zipcode")
    @Expose
    private String zipcode;
    @SerializedName("location_1")
    @Expose
    private Location1 location1;
    @SerializedName("item")
    @Expose
    private String item;
    @SerializedName("business")
    @Expose
    private String business;
    @SerializedName("farmer_id")
    @Expose
    private String farmerId;
    @SerializedName("category")
    @Expose
    private String category;
    @SerializedName("l")
    @Expose
    private String l;
    @SerializedName("farm_name")
    @Expose
    private String farmName;
    @SerializedName("phone1")
    @Expose
    private String phone1;

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    public Location1 getLocation1() {
        return location1;
    }

    public void setLocation1(Location1 location1) {
        this.location1 = location1;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public String getBusiness() {
        return business;
    }

    public void setBusiness(String business) {
        this.business = business;
    }

    public String getFarmerId() {
        return farmerId;
    }

    public void setFarmerId(String farmerId) {
        this.farmerId = farmerId;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getL() {
        return l;
    }

    public void setL(String l) {
        this.l = l;
    }

    public String getFarmName() {
        return farmName;
    }

    public void setFarmName(String farmName) {
        this.farmName = farmName;
    }

    public String getPhone1() {
        return phone1;
    }

    public void setPhone1(String phone1) {
        this.phone1 = phone1;
    }

}
