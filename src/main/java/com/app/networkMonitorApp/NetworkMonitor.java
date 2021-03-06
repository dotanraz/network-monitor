package com.app.networkMonitorApp;

import com.app.environment.NetworkProperties;
import com.app.scanners.HostsScanner;
import com.app.networkStatusMo.NetworkStatus;
import com.app.scanners.PortScanner;
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
    boolean portScanEnable = true;
    private NetworkMonitor() {

    }

    public static NetworkMonitor getInstance() {
        if (instance == null) {
            portScanTimer.startTimer();
            instance = new NetworkMonitor();
        }
        return instance;
    }


    public void run() {
        this.previousNetworkStatus = this.networkStatus;
        HostsScanner hostsScanner = new HostsScanner(this.networkProperties.getNetworkProps());
        hostsScanner.fastScanForIpsInTheNetwork();
        hostsScanner.runArpScan();
        hostsScanner.addMacAddressToNetworkStatus();
        this.networkStatus = hostsScanner.getNetworkStatus();
        //run on start and on every 120 min
        if (portScanEnable && (portScanTimer.currentTimeDiffMin() > 120 || portScanTimer.getDiffRequestCounter() == 1)) {
            System.out.println("*************************");
            System.out.println("*** running port scan ***");
            PortScanner portScanner = new PortScanner(this.networkProperties.getNetworkProps());
            portScanTimer.resetStartTime();
            portScanner.runNetworkPortScan();
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
