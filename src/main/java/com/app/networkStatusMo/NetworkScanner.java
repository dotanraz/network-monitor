package com.app.networkStatusMo;

import com.app.network.NetworkProps;
import com.app.utils.ExecSystemCommand;
import com.app.utils.RegexUtils;
import java.util.ArrayList;
import java.util.List;

public class NetworkScanner {

    private NetworkProps networkProps;
    private String fastRangeScan;
    private NetworkStatus fastRangeScanResults = new NetworkStatus();
    List<NetworkHost> networkHostList = new ArrayList<>();

    public NetworkScanner(NetworkProps networkProps) {
        this.networkProps = networkProps;
        buildFastRangeScan();
    }

    private void buildFastRangeScan() {
        this.fastRangeScan = String.format("nmap -sn %s/%d", this.networkProps.getNetworkId(), this.networkProps.getCidr());
    }

    public void fastRangeScanExec() {
        String scanOutput = ExecSystemCommand.exec(this.fastRangeScan);
        System.out.println("scan output:\n" + scanOutput);

        String[] scanOutputArray = scanOutput.split("\n");
        NetworkHost networkHost = new NetworkHost();
        for (String line : scanOutputArray) {

            if (line.contains("Nmap scan report for")) {
                String ip = RegexUtils.find(line, networkProps.getNetworkIdPrefix() + "\\.\\d+").group(0);
                networkHost.setIp(ip);
            }

            if (line.contains("Host is")) {
                String status = RegexUtils.find(line, "Host is (\\D+)\\s").group(1);
                networkHost.setStatus(status);
                networkHostList.add(networkHost);
                networkHost = new NetworkHost();
            }
        }

        this.fastRangeScanResults.setNetworkHosts(networkHostList);
    }

    public void arpTableParse() {
        String arpTable = ExecSystemCommand.exec("arp -n");

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

    public NetworkStatus getFastRangeScanResults() {
        return fastRangeScanResults;
    }

    public void setFastRangeScanResults(NetworkStatus fastRangeScanResults) {
        this.fastRangeScanResults = fastRangeScanResults;
    }

    public List<NetworkHost> getNetworkHostList() {
        return networkHostList;
    }

    public void setNetworkHostList(List<NetworkHost> networkHostList) {
        this.networkHostList = networkHostList;
    }
}
