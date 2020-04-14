package com.app;

import com.app.networkStatusMo.NetworkHost;
import com.app.networkStatusMo.NetworkStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.List;
import java.util.TimerTask;

public class NetworkMonitorExecutor extends TimerTask {
    private static final Logger logger = LoggerFactory.getLogger(NetworkMonitorExecutor.class);

    @Override
    public void run() {
        NetworkMonitor networkMonitor = new NetworkMonitor();
        networkMonitor.run();
        NetworkStatus networkStatus = networkMonitor.getNetworkStatus();
        List<NetworkHost> networkHostList = networkStatus.getNetworkHosts();
        networkHostList.forEach(networkHost -> {
            logger.info(networkHost.getIp() + " " + networkHost.getStatus() + " " + networkHost.getMacAddress());
        });
        logger.info("number of hosts: " + networkStatus.getNumberOfHosts());
    }

}
