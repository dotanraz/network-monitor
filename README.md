# network-monitor

Monitor the network by running malicious detection tools such as ARP Spoofing (and more to come).<br>
it also alert when new device join the network.<br>
<br>
It's a Spring Boot Application and it can run on a Linux server only.<br>
lightweight enough to run on a micro PC (e.g Raspberry PI.<br>
<br>
How to run:<br>
checkout the code to a local folder and run: "./gradlew bootRun".<br>
the tool will run and will be executed scan once in 10 minutes (default params).

configurations:
it requires some basic network configurations.<br> 
config file: src/main/resources/network.properties

default network-properties:<br>
subnetMask=255.255.255.0<br>
networkId=192.168.1.0<br>
defaultGwIp=192.168.1.1<br>
cidr=24<br>
