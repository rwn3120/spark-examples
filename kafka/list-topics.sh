#!/bin/bash

SCRIPT_DIR=$(dirname $(readlink -f "${0}"))

HOST=${HOST:-"kafka"}
"${SCRIPT_DIR}/client.sh" kafka-topics.sh --list --zookeeper ${HOST}:2181
