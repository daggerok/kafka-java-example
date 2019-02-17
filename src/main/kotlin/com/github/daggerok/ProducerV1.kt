package com.github.daggerok

import org.apache.kafka.clients.producer.Callback
import org.apache.kafka.clients.producer.KafkaProducer
import org.apache.kafka.clients.producer.ProducerConfig.*
import org.apache.kafka.clients.producer.ProducerRecord
import org.apache.kafka.common.serialization.StringSerializer

/*
  requires: bash ./download-and-start-kafka.sh
  export KAFKA_DIR="kafka_2.12-2.1.0"
  /tmp/${KAFKA_DIR}/bin/kafka-console-consumer.sh --bootstrap-server localhost:9092 --topic my-topic --from-beginning
*/
fun main(args: Array<String>) {
  val kafkaProducerV1 = KafkaProducer<String, String>(mapOf(
      BOOTSTRAP_SERVERS_CONFIG to "127.0.0.1:9092",
      ACKS_CONFIG to "1",
      RETRIES_CONFIG to "10",
      KEY_SERIALIZER_CLASS_CONFIG to StringSerializer::class.java.name,
      VALUE_SERIALIZER_CLASS_CONFIG to StringSerializer::class.java.name
  ))
  val topic = "my-topic"
  val callbackHandler = Callback { metadata, exception ->
    println("metadata: $metadata")
    println("exception: ${exception.javaClass} ${exception.localizedMessage}")
  }
  args.forEach {
    val producerRecord = ProducerRecord<String, String>(topic, it)
    kafkaProducerV1.send(producerRecord, callbackHandler)
    kafkaProducerV1.flush()
  }
  kafkaProducerV1.close()
}
