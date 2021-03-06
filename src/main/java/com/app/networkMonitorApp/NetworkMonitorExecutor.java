package com.app.networkMonitorApp;

import com.app.scanners.ArpSpoofingDetector;
import com.app.scanners.NetDeviceJoinedDetector;
import com.app.scanners.JoinedHosts;
import com.app.networkStatusMo.NetworkStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.TimerTask;

public class NetworkMonitorExecutor extends TimerTask {
    private static final Logger logger = LoggerFactory.getLogger(NetworkMonitorExecutor.class);

    @Override
    public void run() {
        NetworkMonitor.getInstance().run();
        NetworkStatus networkStatus = NetworkMonitor.getInstance().getNetworkStatus();
        logger.info("current network status:\n" + networkStatus.toString());

        if (NetworkMonitor.getInstance().getPreviousNetworkStatus() != null) {
            logger.info("previous network status:\n" + NetworkMonitor.getInstance().getPreviousNetworkStatus().toString());
            ArpSpoofingDetector arpSpoofingDetector = new ArpSpoofingDetector(
                    networkStatus,
                    NetworkMonitor.getInstance().getPreviousNetworkStatus());
            if (arpSpoofingDetector.isArpSpoofDetected()) {
                NetworkMonitor.getInstance().getNetworkStatus().setArpSpoofDetected(true);
                logger.info("ARP Spoofing detected!");
            }

            boolean addExistedDevicesToJoinedHosts = false;
            if (NetworkMonitor.getInstance().getNetworkStatus().getJoinedHosts().size() == 0) {
                addExistedDevicesToJoinedHosts = true;
            }
            NetDeviceJoinedDetector netDeviceJoinedDetector = new NetDeviceJoinedDetector(
                    networkStatus,
                    NetworkMonitor.getInstance().getPreviousNetworkStatus(),
                    addExistedDevicesToJoinedHosts);
            if (netDeviceJoinedDetector.isDeviceJoined()) {
                JoinedHosts.getInstance().addJoinedHosts(netDeviceJoinedDetector.getJoinedHosts());
                JoinedHosts.getInstance().getJoinedHosts().forEach(joinedHost -> {
                    logger.info("[host joined] " + joinedHost.toString());
                });
            }
        }

    }

}
