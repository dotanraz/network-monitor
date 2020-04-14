package com.app.networkStatusMo;

import com.google.gson.Gson;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class NetworkHost {

    @SerializedName("ip")
    @Expose
    private String ip;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("macAddress")
    @Expose
    private String macAddress;

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMacAddress() {
        return macAddress;
    }

    public void setMacAddress(String macAddress) {
        this.macAddress = macAddress;
    }

    public String toString() {
        Gson gson = new Gson();
        return gson.toJson(this);
    }
}
