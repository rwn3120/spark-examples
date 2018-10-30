#!/bin/bash
SCRIPT_DIR=$(dirname $(readlink -f "${0}"))

mvn -f "${SCRIPT_DIR}/pom.xml" clean package
