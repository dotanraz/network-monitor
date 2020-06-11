package com.app.controller;

import com.app.networkMonitorApp.NetworkMonitor;
import com.app.networkStatusMo.NetworkHost;
import com.app.networkStatusMo.NetworkStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.app.networkStatusMo.JoinedHosts;

import java.util.List;

@RequestMapping("api/v1/nm")
@RestController
public class NetworkMonitorController {

    @Autowired
    public NetworkMonitorController() {

    }

    @GetMapping(path="getNetworkStatus")
    public NetworkStatus getNetworkStatus() {
        return NetworkMonitor.getInstance().getNetworkStatus();
    }

    @GetMapping(path="getJoinedHosts")
    public List<NetworkHost> getJoinedHosts() {
        return JoinedHosts.getInstance().getJoinedHosts();
    }

}
