package com.app;

import com.app.environment.NetworkProperties;
import com.app.networkStatusMo.NetworkScanner;
import com.app.networkStatusMo.NetworkStatus;
import com.app.networkStatusMo.NetworkHost;
import org.junit.Test;

import java.util.List;

public class DebugTest {

    @Test
    public void scen() {
        NetworkProperties networkProperties = new NetworkProperties();
        NetworkScanner networkScanner = new NetworkScanner(networkProperties.getNetworkProps());
        networkScanner.runNmapFastRangeScanExec();
        NetworkStatus fastRangeScanResults = networkScanner.getNetworkStatus();
        List<NetworkHost> networkHosts = fastRangeScanResults.getNetworkHosts();
        networkHosts.forEach(networkHost -> {
            System.out.println(networkHost.getIp());
            System.out.println(networkHost.getStatus());
        });
    }

}
