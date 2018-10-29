#!/bin/bash -e

function out() { echo -e "\e[32m${@}\e[39m"; }
function inf() { echo -e "\e[97m${@}\e[39m"; }
function err() { echo -e "\e[31m${@}\e[39m" 1>&2; }
function wrn() { echo -e "\e[33m${@}\e[39m" 1>&2; }
function dbg() { if [ "${DBG}" == "true" ]; then echo -e "\e[34m${@}\e[39m"; fi }

REPO=${REPO:-"radowan"}
NAME=${NAME:-"kafka"}
TAG=${TAG:-"latest"}
IMAGE="${IMAGE:-"${REPO}/${NAME}:${TAG}"}"
GATEWAY=${GATEWAY:-"172.18.0.254"}
SUBNET=${SUBNET:-"172.18.0.0/16"}
IP=${IP:-"172.18.0.1"}
HOST=${HOST:-"kafka"}

# initialize network
NETWORK="kafka-network"
set +e
docker network inspect "${NETWORK}" &>/dev/null
RC=$?
set -e
if [ $RC -ne 0 ]; then
    # Create cluster network
    inf "Creating network ${NETWORK}..."
    docker network create --gateway="${GATEWAY}" --subnet="${SUBNET}" "${NETWORK}" >/dev/null
fi

out "Starting container ${IMAGE}"
set +e
docker run -it --rm \
    --name="${HOST}" \
    --net="${NETWORK}" --ip="${IP}" --hostname="${HOST}" \
    "${IMAGE}" \
    "${@}"
RC=$?
set -e

inf "\nRemoving cluster network ${NETWORK}"
docker network rm "${NETWORK}" &>/dev/null

case $RC in
	0) 	out "Bye";;
	130)	wrn "Killed (${RC})";;
	*)	err "Error (${RC})";;
esac
