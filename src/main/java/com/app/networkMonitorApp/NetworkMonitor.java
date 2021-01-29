package com.app.networkMonitorApp;

import com.app.environment.NetworkProperties;
import com.app.networkStatusMo.LinuxNetworkScanner;
import com.app.networkStatusMo.NetworkStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class NetworkMonitor {

    private static final Logger logger = LoggerFactory.getLogger(NetworkMonitor.class);
    private NetworkProperties networkProperties = NetworkProperties.getInstance();
    private NetworkStatus networkStatus = null;
    private NetworkStatus previousNetworkStatus = null;
    private static NetworkMonitor instance = null;

    private NetworkMonitor() {

    }

    public static NetworkMonitor getInstance() {
        if (instance == null) {
            instance = new NetworkMonitor();
        }
        return instance;
    }


    public void run() {
        this.previousNetworkStatus = this.networkStatus;
        LinuxNetworkScanner linuxNetworkScanner = new LinuxNetworkScanner(this.networkProperties.getNetworkProps());
        linuxNetworkScanner.runJavaFastIpScan();
        linuxNetworkScanner.runArpScan();
        linuxNetworkScanner.addMacAddressToNetworkStatus();
        this.networkStatus = linuxNetworkScanner.getNetworkStatus();
    }

    public NetworkProperties getNetworkProperties() {
        return networkProperties;
    }

    public void setNetworkProperties(NetworkProperties networkProperties) {
        this.networkProperties = networkProperties;
    }

    public NetworkStatus getNetworkStatus() {
        return networkStatus;
    }

    public void setNetworkStatus(NetworkStatus networkStatus) {
        this.networkStatus = networkStatus;
    }

    public NetworkStatus getPreviousNetworkStatus() {
        return previousNetworkStatus;
    }

    public void setPreviousNetworkStatus(NetworkStatus previousNetworkStatus) {
        this.previousNetworkStatus = previousNetworkStatus;
    }
}
