#!/bin/bash -e

function err() { echo -e "\e[31m${@}\e[39m" 1>&2; }

if [ $# -lt 1 ]; then
    err "Missing arguments"
    exit 1
fi

HOST=${HOST:-"127.0.0.1"}
if [[ "${1}" == kafka-* ]]; then
    docker exec -it \
        "kafka" \
        bash -c '"${KAFKA_HOME}/bin/${0}" "${@:1}"' "${@}"
        exit 2
else 
    docker exec -it \
        "kafka" \
        "${1}" "${@:2}"
fi
