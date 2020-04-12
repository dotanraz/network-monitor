package com.app.networkStatusMo;

import com.app.network.NetworkProps;
import com.app.utils.ExecSystemCommand;
import com.app.utils.RegexUtils;
import java.util.ArrayList;
import java.util.List;

public class NetworkScanner {

    private NetworkProps networkProps;
    private String fastRangeScan;
    private NetworkStatus networkStatus = new NetworkStatus();
    List<ArpMo> arpMoList;

    public NetworkScanner(NetworkProps networkProps) {
        this.networkProps = networkProps;
    }

    public void mergeArpWithNetworkStatus() {
        for (NetworkHost networkHost : this.networkStatus.getNetworkHosts()) {
            for (ArpMo arpMo : this.arpMoList) {
                if (networkHost.getIp().equals(arpMo.getIp())) {
                    networkHost.setMacAddress(arpMo.getMacAddress());
                    break;
                }
            }
        }
    }

    public void runNmapFastRangeScanExec() {
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
                String status = RegexUtils.find(line, "Host is (\\D+)\\s").group(1);
                networkHost.setStatus(status);
                networkHostList.add(networkHost);
                networkHost = new NetworkHost();
            }
        }

        this.networkStatus.setNetworkHosts(networkHostList);
    }

    public void runArpScan() {
        List<ArpMo> arpMoList = new ArrayList<>();
        String arpTable = ExecSystemCommand.exec("arp -n");
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

    public String getFastRangeScan() {
        return fastRangeScan;
    }

    public void setFastRangeScan(String fastRangeScan) {
        this.fastRangeScan = fastRangeScan;
    }

    public NetworkStatus getNetworkStatus() {
        return networkStatus;
    }

    public void setNetworkStatus(NetworkStatus networkStatus) {
        this.networkStatus = networkStatus;
    }
}
