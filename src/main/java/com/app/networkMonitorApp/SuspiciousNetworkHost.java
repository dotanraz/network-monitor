package com.app.networkMonitorApp;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public class SuspiciousNetworkHost {

    String ip;
    List<String> macAddresses = new ArrayList<>();

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public List<String> getMacAddresses() {
        return macAddresses;
    }

    public void setMacAddresses(List<String> macAddresses) {
        this.macAddresses = macAddresses;
    }

    public String toString() {
        Gson gson = new Gson();
        return gson.toJson(this);
    }
}
