#!/bin/bash -e
SCRIPT=$(readlink -f "${0}")
SCRIPT_DIR=$(dirname "${SCRIPT}")

function out() { echo -e "\e[32m${@}\e[39m"; }
function inf() { echo -e "\e[97m${@}\e[39m"; }
function err() { echo -e "\e[31m${@}\e[39m" 1>&2; }
function wrn() { echo -e "\e[33m${@}\e[39m" 1>&2; }
function dbg() { if [ "${DBG}" == "true" ]; then echo -e "\e[34m${@}\e[39m"; fi }

REPO=${REPO:-"radowan"}
NAME=${NAME:-"kafka"}
TAG=${TAG:-"latest"}
IMAGE=${IMAGE:-"${REPO}/${NAME}:${TAG}"}

inf "Building ${IMAGE} at ${SCRIPT_DIR}"
docker build \
      -t "${IMAGE}" \
      "${@}" \
      "${SCRIPT_DIR}"

# distribute image
echo -en "\n\e[33mPush image ${IMAGE} to your current registry? \e[97m[y/N]\e[39m "
read -n 1 -r
if [[ "${REPLY}" =~ ^[Yy]$ ]]; then
    out
    docker push "${IMAGE}"
fi
out
