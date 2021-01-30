package com.app.networkMonitorApp;

import com.app.environment.NetworkProperties;
import com.app.networkStatusMo.NetworkScanner;
import com.app.networkStatusMo.NetworkStatus;
import com.app.utils.Timer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class NetworkMonitor {

    private static final Logger logger = LoggerFactory.getLogger(NetworkMonitor.class);
    private NetworkProperties networkProperties = NetworkProperties.getInstance();
    private NetworkStatus networkStatus = null;
    private NetworkStatus previousNetworkStatus = null;
    private static NetworkMonitor instance = null;
    static Timer portScanTimer = new Timer();
    boolean portScanEnable = false;
    private NetworkMonitor() {

    }

    public static NetworkMonitor getInstance() {
        if (instance == null) {
            portScanTimer.resetStartTime();
            instance = new NetworkMonitor();
        }
        return instance;
    }


    public void run() {
        this.previousNetworkStatus = this.networkStatus;
        NetworkScanner networkScanner = new NetworkScanner(this.networkProperties.getNetworkProps());
        networkScanner.fastScanForIpsInTheNetwork();
        networkScanner.runArpScan();
        networkScanner.addMacAddressToNetworkStatus();
        this.networkStatus = networkScanner.getNetworkStatus();
        //run on start and on every 30 min
        if (portScanEnable && (portScanTimer.getDiffRequestCounter() == 0 || portScanTimer.currentTimeDiffMin() > 60)) {
            System.out.println("*************************");
            System.out.println("*** running port scan ***");
            portScanTimer.resetStartTime();
            networkScanner.runNetworkPortScan();
        }
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
