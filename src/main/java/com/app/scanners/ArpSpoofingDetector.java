package com.app.scanners;

import com.app.networkMonitorApp.SuspiciousNetworkHost;
import com.app.networkStatusMo.NetworkHost;
import com.app.networkStatusMo.NetworkStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ArpSpoofingDetector {
    private static final Logger logger = LoggerFactory.getLogger(ArpSpoofingDetector.class);
    NetworkStatus current;
    NetworkStatus previous;
    List<SuspiciousNetworkHost> suspiciousNetworkHostList = new ArrayList<>();
    boolean arpSpoofDetected = false;

    public ArpSpoofingDetector(NetworkStatus current, NetworkStatus previous) {
        if (current !=null && previous !=null ) {
            this.current = current;
            this.previous = previous;
            findDiff();
        }

    }

    private void findDiff() {
        for (NetworkHost currNetworkHost : current.getNetworkHosts()) {
            for (NetworkHost prevNetworkHost : previous.getNetworkHosts()) {
                if (currNetworkHost.getIp().equals(prevNetworkHost.getIp())) {
                    boolean notNull = currNetworkHost.getMacAddress() != null && prevNetworkHost.getMacAddress() != null;
                    if (notNull && !currNetworkHost.getMacAddress().equals(prevNetworkHost.getMacAddress())) {
                        this.arpSpoofDetected = true;
                        logger.info("ARP Spoof detected!");
                        SuspiciousNetworkHost suspiciousNetworkHost = new SuspiciousNetworkHost();
                        suspiciousNetworkHost.setIp(currNetworkHost.getIp());
                        suspiciousNetworkHost.setMacAddresses(Arrays.asList(currNetworkHost.getMacAddress(), prevNetworkHost.getMacAddress()));
                        this.suspiciousNetworkHostList.add(suspiciousNetworkHost);
                    }
                }
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

    public List<SuspiciousNetworkHost> getSuspiciousNetworkHostList() {
        return suspiciousNetworkHostList;
    }

    public void setSuspiciousNetworkHostList(List<SuspiciousNetworkHost> suspiciousNetworkHostList) {
        this.suspiciousNetworkHostList = suspiciousNetworkHostList;
    }

    public boolean isArpSpoofDetected() {
        return this.arpSpoofDetected;
    }
}
