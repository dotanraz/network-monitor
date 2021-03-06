package com.app.environment;

import com.app.network.NetworkProps;
import java.io.IOException;
import java.util.Properties;

/**
 * singleton
 */
public class NetworkProperties {

    private static NetworkProperties instance = null;
    NetworkProps networkProps = new NetworkProps();
    Properties properties;

    private NetworkProperties() {
        readPropFile();
        updateNetworkPropObj();
    }

    public static NetworkProperties getInstance() {
        if (instance == null) {
            instance = new NetworkProperties();
        }
        return instance;
    }
    private void readPropFile() {
        Properties prop = new Properties();
        try {
            prop.load(NetworkProperties.class.getClassLoader().getResourceAsStream("network.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.properties = prop;
    }

    public void updateNetworkPropObj() {
        this.networkProps.setSubnetMask(properties.getProperty("subnetMask"));
        this.networkProps.setNetworkId(properties.getProperty("networkId"));
        this.networkProps.setDefaultGwIp(properties.getProperty("defaultGwIp"));
        this.networkProps.setCidr(Integer.parseInt(properties.getProperty("cidr")));
        this.networkProps.setNetworkIdPrefix(properties.getProperty("networkIdPrefix"));
    }

    public NetworkProps getNetworkProps() {
        return networkProps;
    }

    public void setNetworkProps(NetworkProps networkProps) {
        this.networkProps = networkProps;
    }

}
