package com.app.scanners;

import com.app.networkStatusMo.NetworkHost;
import com.google.gson.Gson;
import java.util.ArrayList;
import java.util.List;

/**
 * singleton
 */
public class JoinedHosts {

    private static JoinedHosts instance = null;
    List<NetworkHost> joinedHosts = new ArrayList();

    private JoinedHosts() {

    }

    public static JoinedHosts getInstance() {
        if (instance == null) {
            instance = new JoinedHosts();
        }
        return instance;
    }

    public void addJoinedHost(NetworkHost networkHost) {
        if (!isHostAlreadyExist(networkHost)) {
            this.joinedHosts.add(networkHost);
        }
    }

    public void addJoinedHosts(List<NetworkHost> networkHostList) {
        for (NetworkHost networkHost : networkHostList) {
            if (!isHostAlreadyExist(networkHost)) {
                this.joinedHosts.add(networkHost);
            }
        }
    }

    private boolean isHostAlreadyExist(NetworkHost networkHost) {
        for (NetworkHost joinedHost : this.joinedHosts) {
            if (networkHost.getMacAddress() != null && joinedHost.getMacAddress() != null) {
                if (networkHost.getMacAddress().equals(joinedHost.getMacAddress())) {
                    return true;
                }
            }
        }
        return false;
    }

    public List<NetworkHost> getJoinedHosts() {
        return joinedHosts;
    }

    public String toString() {
        Gson gson = new Gson();
        return gson.toJson(this);
    }

}
