package com.app.scanners;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import com.app.network.NetworkProps;
import com.app.networkMonitorApp.NetworkMonitor;
import com.app.networkStatusMo.NetworkHost;
import com.app.utils.Timer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PortScanner {

    private static final Logger logger = LoggerFactory.getLogger(PortScanner.class);
    private NetworkProps networkProps;
    private Timer portScanTimer = new Timer();

    public PortScanner(NetworkProps networkProps) {
        this.networkProps = networkProps;
    }

    public void runNetworkPortScan() {
        List<NetworkHost> networkHosts = NetworkMonitor.getInstance().getNetworkStatus().getNetworkHosts();
        for (int i = 0; i < networkHosts.size(); i++) {
            String ip = networkHosts.get(i).getIp();
            List<Integer> openPorts = portScan(ip);
            logger.info("openPorts size: " + openPorts.size());
            NetworkMonitor.getInstance().getNetworkStatus().getNetworkHosts().get(i).setOpenPorts(openPorts);
        }
    }

    public List<Integer> portScan(String ip) {
        portScanTimer.resetStartTime();
        ConcurrentLinkedQueue<Integer> openPorts = new ConcurrentLinkedQueue<>();
        ExecutorService executorService = Executors.newFixedThreadPool(50);
        AtomicInteger port = new AtomicInteger(0);
        while (port.get() < 65535) {
            final int currentPort = port.getAndIncrement();
            executorService.submit(() -> {
                try {
                    Socket socket = new Socket();
                    socket.connect(new InetSocketAddress(ip, currentPort), 200);
                    socket.close();
                    openPorts.add(currentPort);
                    logger.info(ip + " ,port open: " + currentPort);
                }
                catch (IOException e) {
                }
            });
        }
        executorService.shutdown();
        try {
            executorService.awaitTermination(10, TimeUnit.MINUTES);
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }

        List<Integer> openPortList = new ArrayList<>();
        logger.info("openPortsQueue: " + openPorts.size());
        while (!openPorts.isEmpty()) {
            openPortList.add(openPorts.poll());
        }
        logger.info("port scan took: " + portScanTimer.currentTimeDiffSec() + " [sec]");
        return openPortList;
    }
}
