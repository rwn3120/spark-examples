FROM debian:buster-slim

RUN apt-get update \
    && mkdir -p /usr/share/man/man1 \
    && apt-get install -y \
        wget \
        openjdk-8-jre-headless

ENV SCALA_VERSION="2.11" \
    KAFKA_VERSION="2.0.0"
ENV KAFKA_HOME="/opt/kafka-${SCALA_VERSION}-${KAFKA_VERSION}"

RUN mkdir -p "${KAFKA_HOME}" /usr/share/man/man1 \
    && wget \
        "http://mirror.softaculous.com/apache/kafka/${KAFKA_VERSION}/kafka_${SCALA_VERSION}-${KAFKA_VERSION}.tgz" \
        -O "/tmp/kafka.tgz" \ 
    && tar -xvf "/tmp/kafka.tgz" --strip 1 -C "${KAFKA_HOME}" \
    && rm "/tmp/kafka.tgz"

COPY entrypoint.sh /opt/entrypoint

ENTRYPOINT ["/opt/entrypoint"]

EXPOSE 9092 2191

