#!/bin/bash

cp ../build/libs/network-monitor-0.0.1-SNAPSHOT.jar network-monitor-0.0.1-SNAPSHOT.jar
docker build -t nmonitor .