#!/bin/bash

function err() { echo -e "\e[31m${@}\e[39m" 1>&2; }

SCRIPT_DIR=$(dirname $(readlink -f "${0}"))

if [ $# -lt 1 ]; then
    err "Missing arguments"
    exit 1
fi

HOST=${HOST:-"kafka"}
"${SCRIPT_DIR}/client.sh" kafka-topics.sh --create --zookeeper ${HOST}:2181 --replication-factor 1 --partitions 1 --topic "${1}" 
