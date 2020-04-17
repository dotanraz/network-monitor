package com.app.networkMonitorApp;

import com.app.environment.NetworkProperties;
import com.app.networkStatusMo.LinuxNetworkScanner;
import com.app.networkStatusMo.NetworkStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class NetworkMonitor {

    private static final Logger logger = LoggerFactory.getLogger(NetworkMonitor.class);
    private NetworkProperties networkProperties = NetworkProperties.getInstance();
    private NetworkStatus networkStatus;

    public NetworkMonitor() {

    }

    public void run() {
        LinuxNetworkScanner linuxNetworkScanner = new LinuxNetworkScanner(this.networkProperties.getNetworkProps());
        linuxNetworkScanner.runNmapFastRangeScanExec();
        linuxNetworkScanner.runArpScan();
        linuxNetworkScanner.mergeArpWithNetworkStatus();
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

}