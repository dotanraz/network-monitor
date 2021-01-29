package com.app.networkStatusMo;

import com.app.environment.OSType;
import com.app.environment.SystemProperties;
import com.app.network.NetworkProps;
import com.app.utils.ExecSystemCommand;
import com.app.utils.RegexUtils;
import java.io.IOException;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;

/**
 * a Linux network scanner.
 * this class runs linux command in order to get the status of the network.
 */
public class LinuxNetworkScanner {

    private NetworkProps networkProps;
    //private String fastRangeScan;
    private NetworkStatus networkStatus = new NetworkStatus();
    List<ArpMo> arpMoList;
    static String arpCommand;
    static {
        OSType osType = SystemProperties.getInstance().getOsType();
        if (osType == OSType.LINUX) {
            arpCommand = "arp -n";
        }
        if (osType == OSType.MAC) {
            arpCommand = "arp -a";
        }
    }

    public LinuxNetworkScanner(NetworkProps networkProps) {
        this.networkProps = networkProps;
    }

    public void addMacAddressToNetworkStatus() {
        for (NetworkHost networkHost : this.networkStatus.getNetworkHosts()) {
            for (ArpMo arpMo : this.arpMoList) {
                if (networkHost.getIp().equals(arpMo.getIp())) {
                    networkHost.setMacAddress(arpMo.getMacAddress());
                    break;
                }
            }
        }
    }

    public void runNmapFastRangeScan() {
        List<NetworkHost> networkHostList = new ArrayList<>();
        String scanOutput = ExecSystemCommand.exec(String.format("nmap -sn %s/%d", this.networkProps.getNetworkId(), this.networkProps.getCidr()));
        System.out.println("scan output:\n" + scanOutput);

        String[] scanOutputArray = scanOutput.split("\n");
        NetworkHost networkHost = new NetworkHost();
        for (String line : scanOutputArray) {

            if (line.contains("Nmap scan report for")) {
                String ip = RegexUtils.extractIpFromText(line).group(0);
                networkHost.setIp(ip);
            }

            if (line.contains("Host is")) {
                String status = "unknown"; //initial value
                Matcher matcher = RegexUtils.find(line, "Host is (\\D+)\\s");
                if (matcher != null) {
                    status = matcher.group(1);
                }
                networkHost.setStatus(status);
                networkHostList.add(networkHost);
                networkHost = new NetworkHost();
            }
        }

        this.networkStatus.setNetworkHosts(networkHostList);
    }

    public void runJavaFastIpScan() {
        ConcurrentLinkedQueue<NetworkHost> networkHostsQueue = new ConcurrentLinkedQueue<>();
        ExecutorService executorService = Executors.newFixedThreadPool(100);
        int numOfIps = 0;
        if (this.networkProps.getCidr() == 24) {
            numOfIps = 254;
        }
        for (int i = 1; i <= numOfIps; i++) {
            String ip = this.networkProps.getNetworkIdPrefix() + "." + i;;
            executorService.submit(() -> {
                try {
                    InetAddress inAddress = InetAddress.getByName(ip);
                    if (inAddress.isReachable(500)) {
                        String hostName = RegexUtils.isTextContainsIp(inAddress.getHostName()) ? null : inAddress.getHostName();
                        System.out.println("reachable ip: " + ip + " " + hostName);
                        networkHostsQueue.add(new NetworkHost(ip, "up", hostName));
                    }
                }
                catch (IOException e) {
                    e.printStackTrace();
                }
            });
        }
        executorService.shutdown();
        try {
            executorService.awaitTermination(1, TimeUnit.MINUTES);
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }

        List<NetworkHost> networkHostList = new ArrayList<>();
        networkHostsQueue.forEach(networkHost -> {
            networkHostList.add(networkHost);
        });
        this.networkStatus.setNetworkHosts(networkHostList);
    }

    public void runArpScan() {
        List<ArpMo> arpMoList = new ArrayList<>();
        String arpTable = ExecSystemCommand.exec(arpCommand);
        String[] arpTableArray = arpTable.split("\n");

        for (String line : arpTableArray) {
            ArpMo arpMo = new ArpMo();
            if (RegexUtils.isTextContainsIp(line)) {
                arpMo.setIp(RegexUtils.extractIpFromText(line).group(0));
            }
            if (RegexUtils.isTextContainsMacAddress(line)) {
                arpMo.setMacAddress(RegexUtils.extractMacAddressFromText(line).group(0));
                arpMoList.add(arpMo);
            }
        }

        this.arpMoList = arpMoList;
    }



    public NetworkProps getNetworkProps() {
        return networkProps;
    }

    public void setNetworkProps(NetworkProps networkProps) {
        this.networkProps = networkProps;
    }

//    public String getFastRangeScan() {
//        return fastRangeScan;
//    }

//    public void setFastRangeScan(String fastRangeScan) {
//        this.fastRangeScan = fastRangeScan;
//    }

    public NetworkStatus getNetworkStatus() {
        return networkStatus;
    }

    public void setNetworkStatus(NetworkStatus networkStatus) {
        this.networkStatus = networkStatus;
    }

}
