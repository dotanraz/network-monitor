#!/bin/sh
./gradlew clean build
docker build -t network-mngmnt -f ./docker/Dockerfile .