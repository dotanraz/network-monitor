#!/bin/sh
./gradlew clean build
docker build -t auto-test-mngmgnt -f ./docker/Dockerfile .