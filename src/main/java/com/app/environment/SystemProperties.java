package com.app.environment;

/**
 * singleton
 */
public class SystemProperties {

    private static SystemProperties instance = null;
    private OSType osType;

    private SystemProperties() {
        osType = OSValidator.getOSType();
    }

    public static SystemProperties getInstance() {
        if (instance == null) {
            instance = new SystemProperties();
        }
        return instance;
    }

    public OSType getOsType() {
        return osType;
    }
}
