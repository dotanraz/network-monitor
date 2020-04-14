package com.app.networkMonitorApp;

import com.app.networkStatusMo.NetworkHost;
import com.app.networkStatusMo.NetworkStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.List;
import java.util.TimerTask;

public class NetworkMonitorExecutor extends TimerTask {
    private static final Logger logger = LoggerFactory.getLogger(NetworkMonitorExecutor.class);
    NetworkStatus previousNetworkStatus = null;

    @Override
    public void run() {
        NetworkMonitor networkMonitor = new NetworkMonitor();
        networkMonitor.run();
        NetworkStatus networkStatus = networkMonitor.getNetworkStatus();

        logger.info("current network status:\n" + networkStatus.toString());
        if (this.previousNetworkStatus != null) {
            logger.info("previous network status:\n" + this.previousNetworkStatus.toString());
            ArpSpoofingDetector arpSpoofingDetector = new ArpSpoofingDetector(networkStatus, previousNetworkStatus);
            if (arpSpoofingDetector.isArpSpoofDetected()) {
                //arp spoof detected!
            }

            NetDeviceJoinedDetector netDeviceJoinedDetector = new NetDeviceJoinedDetector(networkStatus, previousNetworkStatus);
            if (netDeviceJoinedDetector.isDeviceJoined()) {
                //device joined the network
            }
        }


        this.previousNetworkStatus = networkStatus;
    }

}
