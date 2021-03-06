package com.app.environment;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class OSValidator {

    private static final Logger logger = LoggerFactory.getLogger(OSValidator.class);
    private static String OS = System.getProperty("os.name").toLowerCase();

    public static OSType getOSType() {
        logger.info("os.name: " + OS);
        if (isWindows()) {
            logger.info("detected os: windows");
            return OSType.WINDOWS;
        } else if (isMac()) {
            logger.info("detected os: mac");
            return OSType.MAC;
        } else if (isLinux()) {
            logger.info("detected os: linux");
            return OSType.LINUX;
        } else if (isSolaris()) {
            logger.info("detected os: solaris");
            return OSType.SOLARIS;
        } else {
            logger.error("cannot determine OS type!");
            return null;
        }
    }

    private static boolean isWindows() {
        return (OS.indexOf("win") >= 0);
    }

    private static boolean isMac() {
        return (OS.indexOf("mac") >= 0);
    }

    private static boolean isLinux() {
        return (OS.indexOf("nix") >= 0 || OS.indexOf("nux") >= 0 || OS.indexOf("aix") > 0 );
    }

    private static boolean isSolaris() {
        return (OS.indexOf("sunos") >= 0);
    }

}