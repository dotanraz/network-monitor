package com.app.networkStatusMo;

import java.util.List;

import com.google.gson.Gson;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class NetworkStatus {
    @SerializedName("networkHosts")
    @Expose
    private List<NetworkHost> networkHosts = null;
    private int numberOfHosts;

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

    public String toString() {
        Gson gson = new Gson();
        return gson.toJson(this);
    }
}
