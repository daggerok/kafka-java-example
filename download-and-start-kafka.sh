#!/usr/bin/env bash
KAFKA_DIR="kafka_2.12-2.1.0"
if ! [[ -d "/tmp/${KAFKA_DIR}" ]]; then
  wget -qO- https://www-eu.apache.org/dist/kafka/2.1.0/kafka_2.12-2.1.0.tgz | tar xvz
  mv -f ${KAFKA_DIR} /tmp/
fi

/tmp/${KAFKA_DIR}/bin/zookeeper-server-start.sh /tmp/${KAFKA_DIR}/config/zookeeper.properties &
sleep 3s
/tmp/${KAFKA_DIR}/bin/kafka-server-start.sh /tmp/${KAFKA_DIR}/config/server.properties &

#export KAFKA_DIR="kafka_2.12-2.1.0" ; /tmp/${KAFKA_DIR}/bin/kafka-console-consumer.sh --bootstrap-server localhost:9092 --topic my-topic --from-beginning
#  sleep 3s
#  /tmp/${KAFKA_DIR}/bin/kafka-topics.sh --create --zookeeper 127.0.0.1:2181 --replication-factor 1 --partitions 1 --topic test
