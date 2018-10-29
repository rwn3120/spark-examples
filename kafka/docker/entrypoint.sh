#!/bin/bash

if [ $# -eq 0 ]; then
    echo "Starting ZooKeeper (in background)"
    "${KAFKA_HOME}/bin/zookeeper-server-start.sh" "${KAFKA_HOME}/config/zookeeper.properties" &>/tmp/zookeeper.log &

    echo "Starting Kafka (in foreground)"
    "${KAFKA_HOME}/bin/kafka-server-start.sh" "${KAFKA_HOME}/config/server.properties"
else 
    echo "Start ZooKeeper (in background)"
    echo -e "\t${KAFKA_HOME}/bin/zookeeper-server-start.sh" "${KAFKA_HOME}/config/zookeeper.properties" &>/tmp/zookeeper.log &

    echo "Starting Kafka (in foreground)"
    echo -e "\t${KAFKA_HOME}/bin/kafka-server-start.sh" "${KAFKA_HOME}/config/server.properties"
    
    "${@}"    
fi
