
package org.example.taskmanager.base;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Location1 {

    @SerializedName("latitude")
    @Expose
    private String latitude;
    @SerializedName("human_address")
    @Expose
    private String humanAddress;
    @SerializedName("needs_recoding")
    @Expose
    private Boolean needsRecoding;
    @SerializedName("longitude")
    @Expose
    private String longitude;

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getHumanAddress() {
        return humanAddress;
    }

    public void setHumanAddress(String humanAddress) {
        this.humanAddress = humanAddress;
    }

    public Boolean getNeedsRecoding() {
        return needsRecoding;
    }

    public void setNeedsRecoding(Boolean needsRecoding) {
        this.needsRecoding = needsRecoding;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

}
