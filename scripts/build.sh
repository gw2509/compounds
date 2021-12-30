#!/bin/sh
docker build --build-arg JAR_FILE=target/compound-0.0.1-SNAPSHOT.jar -t ai/exscientia/compound .
