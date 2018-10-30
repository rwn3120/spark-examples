#!/bin/bash
SCRIPT=$(readlink -f "${0}")
SCRIPT_DIR=$(dirname "${SCRIPT}")

SHARED_DIR="${SCRIPT_DIR}/shared"
mkdir -p "${SHARED_DIR}/logs" "${SHARED_DIR}/notebook" "${SHARED_DIR}/data"

docker run -it --rm \
	-p 0.0.0.0:4040:4040 -p 0.0.0.0:7077:7077 -p 0.0.0.0:8080:8080 \
	-v "${SHARED_DIR}/logs:/logs" -v "${SHARED_DIR}/notebook:/notebook" -v "${SHARED_DIR}/data:/data" \
	-e "ZEPPELIN_LOG_DIR=/logs" -e "ZEPPELIN_NOTEBOOK_DIR=/notebook" \
	--name zeppelin \
	apache/zeppelin:0.7.3
