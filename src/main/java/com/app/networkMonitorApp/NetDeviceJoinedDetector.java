package com.app.networkMonitorApp;

import com.app.networkStatusMo.NetworkHost;
import com.app.networkStatusMo.NetworkStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class NetDeviceJoinedDetector {

    private static final Logger logger = LoggerFactory.getLogger(NetDeviceJoinedDetector.class);
    private NetworkStatus current;
    private NetworkStatus previous;
    private List<NetworkHost> joinedHosts = new ArrayList<>();
    private boolean isDeviceJoined = false;

    public NetDeviceJoinedDetector(NetworkStatus current, NetworkStatus previous) {
        this.current = current;
        this.previous = previous;
        scanForDeviceJoin();
    }

    private void scanForDeviceJoin() {

        for (NetworkHost currNetworkHost : current.getNetworkHosts()) {
            boolean isNewHostDetected = true;
            for (NetworkHost prevNetworkHost : previous.getNetworkHosts()) {
                if (currNetworkHost.getIp().equals(prevNetworkHost.getIp())) {
                    isNewHostDetected = false;
                }
            }
            if (isNewHostDetected == true) {
                isDeviceJoined = true;
                logger.info("device joined:\n" + currNetworkHost.toString());
                joinedHosts.add(currNetworkHost);
            }
        }
    }

    public NetworkStatus getCurrent() {
        return current;
    }

    public void setCurrent(NetworkStatus current) {
        this.current = current;
    }

    public NetworkStatus getPrevious() {
        return previous;
    }

    public void setPrevious(NetworkStatus previous) {
        this.previous = previous;
    }

    public List<NetworkHost> getJoinedHosts() {
        return joinedHosts;
    }

    public void setJoinedHosts(List<NetworkHost> joinedHosts) {
        this.joinedHosts = joinedHosts;
    }

    public boolean isDeviceJoined() {
        return isDeviceJoined;
    }

}
