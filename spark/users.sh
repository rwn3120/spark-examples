#!/bin/bash -e
SCRIPT=$(readlink -f "${0}")
SCRIPT_DIR=$(dirname "${SCRIPT}")

CSV_FILE="${SCRIPT_DIR}/users.csv"
JSON_FILE="${SCRIPT_DIR}/users.json"

curl -s "https://store.steampowered.com/stats/userdata.json" | jq -r ".[].data[] |  @csv" > "${CSV_FILE}"
