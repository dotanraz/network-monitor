package com.app.networkStatusMo;

import com.google.gson.Gson;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

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
    @SerializedName("hostName")
    @Expose
    private String hostName;
    @SerializedName("openPorts")
    @Expose
    private List<Integer> openPorts;

    public NetworkHost() {

    }

    public NetworkHost(String ip, String status, String hostName) {
        this.ip = ip;
        this.status = status;
        this.hostName = hostName;
    }

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

    public String getHostName() {
        return hostName;
    }

    public void setHostName(String hostName) {
        this.hostName = hostName;
    }

    public List<Integer> getOpenPorts() {
        return openPorts;
    }

    public void setOpenPorts(List<Integer> openPorts) {
        this.openPorts = openPorts;
    }

    public String toString() {
        Gson gson = new Gson();
        return gson.toJson(this);
    }
}
