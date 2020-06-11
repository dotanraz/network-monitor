package com.app.networkStatusMo;

import java.util.List;

import com.google.gson.Gson;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.app.networkStatusMo.JoinedHosts;

public class NetworkStatus {
    @SerializedName("networkHosts")
    @Expose
    private List<NetworkHost> networkHosts = null;
    private int numberOfHosts;
    private boolean isArpSpoofDetected = false;
    private List<NetworkHost> joinedHosts = null;
    public NetworkStatus() {

    }

    public List<NetworkHost> getNetworkHosts() {
        getNumberOfHosts();
        return networkHosts;
    }

    public void setNetworkHosts(List<NetworkHost> networkHosts) {
        this.networkHosts = networkHosts;
    }

    public int getNumberOfHosts() {
        if (networkHosts == null) {
            this.numberOfHosts = 0;
        } else {
            this.numberOfHosts = this.networkHosts.size();
        }
        return this.numberOfHosts;
    }

    public boolean isArpSpoofDetected() {
        return isArpSpoofDetected;
    }

    public void setArpSpoofDetected(boolean arpSpoofDetected) {
        isArpSpoofDetected = arpSpoofDetected;
    }

    public List<NetworkHost> getJoinedHosts() {
        this.joinedHosts = JoinedHosts.getInstance().getJoinedHosts();
        return this.joinedHosts;
    }

    public String toString() {
        Gson gson = new Gson();
        return gson.toJson(this);
    }
}
