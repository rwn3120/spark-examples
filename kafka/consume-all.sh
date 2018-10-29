#!/bin/bash -e

function err() { echo -e "\e[31m${@}\e[39m" 1>&2; }

SCRIPT_DIR=$(dirname $(readlink -f "${0}"))

if [ $# -lt 1 ]; then
    err "Missing arguments"
    exit 1
fi

HOST=${HOST:-"kafka"}
"${SCRIPT_DIR}/client.sh" kafka-console-consumer.sh --bootstrap-server ${HOST}:9092 --from-beginning --topic "${1}"
