#!/bin/bash -e

function err() { echo -e "\e[31m${@}\e[39m" 1>&2; }

if [ $# -lt 1 ]; then
    err "Missing arguments"
    exit 1
fi

HOST=${HOST:-"kafka"}
if [[ "${1}" == kafka-* ]]; then
    docker exec -it \
        "${HOST}" \
        bash -c '"${KAFKA_HOME}/bin/${0}" "${@:1}"' "${@}"
        exit 2
else 
    docker exec -it \
        "${HOST}" \
        "${1}" "${@:2}"
fi
