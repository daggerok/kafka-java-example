package com.github.daggerok

import org.apache.kafka.clients.consumer.ConsumerConfig.*
import org.apache.kafka.clients.consumer.KafkaConsumer
import org.apache.kafka.common.serialization.StringDeserializer
import java.util.*
import org.apache.kafka.clients.consumer.ConsumerRecord
import org.apache.kafka.clients.consumer.ConsumerRecords
import java.time.Duration
import java.util.Arrays



/*
  requires: bash ./download-and-start-kafka.sh
  export KAFKA_DIR="kafka_2.12-2.1.0"
  /tmp/${KAFKA_DIR}/bin/kafka-console-producer.sh --broker-list localhost:9092 --topic my-topic
*/
fun main(args: Array<String>) {
  val kafkaConsumerV1 = KafkaConsumer<String, String>(mapOf(
      BOOTSTRAP_SERVERS_CONFIG to "127.0.0.1:9092",
      GROUP_ID_CONFIG to UUID.randomUUID().toString(), // myTopicGroup
      ENABLE_AUTO_COMMIT_CONFIG to "true",
      AUTO_COMMIT_INTERVAL_MS_CONFIG to "1000",
      KEY_DESERIALIZER_CLASS_CONFIG to StringDeserializer::class.java.name,
      VALUE_DESERIALIZER_CLASS_CONFIG to StringDeserializer::class.java.name
  ))
  val topic = "my-topic"
  val partitions = kafkaConsumerV1.assignment().filter { it.topic() == topic }
  kafkaConsumerV1.assign(partitions)

  try {
    kafkaConsumerV1.subscribe(listOf(topic))
    while (true) {
      val records = kafkaConsumerV1.poll(Duration.ofMillis(333))
      for (record in records)
        println("offset: ${record.offset()}, key: ${record.key()}, value: ${record.value()}")
    }
  }

  finally {
    kafkaConsumerV1.close()
  }
}
