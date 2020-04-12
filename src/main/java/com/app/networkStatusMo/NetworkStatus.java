package com.app.networkStatusMo;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class NetworkStatus {
    @SerializedName("networkHost")
    @Expose
    private List<NetworkHost> networkHosts = null;
    private int numberOfHosts;

    public List<NetworkHost> getNetworkHosts() {
        return networkHosts;
    }

    public void setNetworkHosts(List<NetworkHost> networkHosts) {
        this.networkHosts = networkHosts;
    }

    public int getNumberOfHosts() {
        this.numberOfHosts = this.networkHosts.size();
        return this.numberOfHosts;
    }
}
