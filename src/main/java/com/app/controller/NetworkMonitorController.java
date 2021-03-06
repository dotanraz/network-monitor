package com.app.controller;

import com.app.networkMonitorApp.NetworkMonitor;
import com.app.networkStatusMo.NetworkHost;
import com.app.networkStatusMo.NetworkStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.app.scanners.JoinedHosts;

import java.util.List;

@RequestMapping("api/v1")
@RestController
public class NetworkMonitorController {

    @Autowired
    public NetworkMonitorController() {

    }

    @GetMapping(path="status")
    public NetworkStatus getNetworkStatus() {
        return NetworkMonitor.getInstance().getNetworkStatus();
    }

    @PostMapping(path="changeHostName")
    public void changeHostName(@RequestParam String ip, @RequestParam String hostName) {
       NetworkMonitor.getInstance().getNetworkStatus().getNetworkHosts().forEach(networkHost -> {
           if (networkHost.getIp() == ip) {
               networkHost.setHostName(hostName);
               return;
           }
       });
        //todo - need to make sure next scan doesnt overwrite the name
    }

}
