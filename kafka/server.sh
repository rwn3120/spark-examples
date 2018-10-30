#!/bin/bash -e

REPO=${REPO:-"radowan"}
NAME=${NAME:-"kafka"}
TAG=${TAG:-"latest"}
IMAGE="${IMAGE:-"${REPO}/${NAME}:${TAG}"}"

docker run -it --rm \
    -p 0.0.0.0:9092:9092 -p 0.0.0.0:2191:2191 \
    --name kafka \
    "${IMAGE}" \
    "${@}"
