#!/bin/bash

SCRIPT_DIR=$(dirname $(readlink -f "${0}"))

HOST=${HOST:-"127.0.0.1"}
"${SCRIPT_DIR}/client.sh" kafka-topics.sh --list --zookeeper ${HOST}:2181
