#!/bin/bash -e
SCRIPT=$(readlink -f "${0}")
SCRIPT_DIR=$(dirname "${SCRIPT}")

FILE="${SCRIPT_DIR}/top-100.csv"

curl -s "https://store.steampowered.com/stats" | grep "gameLink" | sed 's/.*href=".*app\///; s/<.*>//; s/\/.*>/,/; s/[^0-9]*>/,/' | tr -d '\r' >> "${FILE}"
