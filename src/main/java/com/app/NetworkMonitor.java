package com.app;

import com.app.environment.NetworkProperties;
import com.app.networkStatusMo.NetworkScanner;
import com.app.networkStatusMo.NetworkStatus;

public class NetworkMonitor {

    private NetworkProperties networkProperties = new NetworkProperties();
    private NetworkStatus networkStatus;

    public NetworkMonitor() {

    }

    public void run() {
        NetworkScanner networkScanner = new NetworkScanner(this.networkProperties.getNetworkProps());
        networkScanner.runNmapFastRangeScanExec();
        networkScanner.runArpScan();
        networkScanner.mergeArpWithNetworkStatus();
        this.networkStatus = networkScanner.getNetworkStatus();
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
