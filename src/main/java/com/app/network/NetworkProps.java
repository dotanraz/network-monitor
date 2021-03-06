package com.app.network;

public class NetworkProps {
    private String subnetMask;
    private String networkId;
    private String defaultGwIp;
    private int cidr;
    private String networkIdPrefix;

    public NetworkProps() {

    }

    public NetworkProps(String subnetMask, String networkId, String defaultGwIp, int cidr, String networkIdPrefix) {
        this.subnetMask = subnetMask;
        this.networkId = networkId;
        this.defaultGwIp = defaultGwIp;
        this.cidr = cidr;
        this.networkIdPrefix = networkIdPrefix;
    }

    public String getSubnetMask() {
        return subnetMask;
    }

    public void setSubnetMask(String subnetMask) {
        this.subnetMask = subnetMask;
    }

    public String getNetworkId() {
        return networkId;
    }

    public void setNetworkId(String networkId) {
        this.networkId = networkId;
    }

    public String getDefaultGwIp() {
        return defaultGwIp;
    }

    public void setDefaultGwIp(String defaultGwIp) {
        this.defaultGwIp = defaultGwIp;
    }

    public int getCidr() {
        return cidr;
    }

    public void setCidr(int cidr) {
        this.cidr = cidr;
    }

    public String getNetworkIdPrefix() {
        return networkIdPrefix;
    }

    public void setNetworkIdPrefix(String networkIdPrefix) {
        this.networkIdPrefix = networkIdPrefix;
    }
}

