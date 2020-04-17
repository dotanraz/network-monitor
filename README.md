# network-monitor

Monitor the network by running malicious detection tools such as ARP Spoofing (and more to come).
it also alert when new device join the network.

It's a Spring Boot Application and it can run on a Linux server only.
lightweight enough to run on a micro PC (e.g Raspberry PI.

How to run:
checkout the code to a local folder and run: "./gradlew bootRun"
the tool will run and will be executed scan once in 10 minutes (default params).

configurations:
it requires some basic network configurations. 
config file: src/main/resources/network.properties

default network-properties:
    subnetMask=255.255.255.0
    networkId=192.168.1.0
    defaultGwIp=192.168.1.1
    cidr=24
