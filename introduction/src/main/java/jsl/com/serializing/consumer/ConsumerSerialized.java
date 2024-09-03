package jsl.com.serializing.consumer;

import customerManagement.avro.Customer;
import io.confluent.kafka.serializers.KafkaAvroDeserializer;
import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import java.time.Duration;
import java.util.Map;
import java.util.Set;

import static org.apache.kafka.clients.CommonClientConfigs.BOOTSTRAP_SERVERS_CONFIG;
import static org.apache.kafka.clients.consumer.ConsumerConfig.*;

public class ConsumerSerialized {
    public static void main(String[] args) {
        final var topic = "introduction";

        final Map<String, Object> configuration = Map.of(
                BOOTSTRAP_SERVERS_CONFIG, "localhost:9092",
                KEY_DESERIALIZER_CLASS_CONFIG, KafkaAvroDeserializer.class,
                VALUE_DESERIALIZER_CLASS_CONFIG, KafkaAvroDeserializer.class,
                GROUP_ID_CONFIG, "introduction-group",
                AUTO_OFFSET_RESET_CONFIG, "earliest",
                ENABLE_AUTO_COMMIT_CONFIG, false,
                "schema.registry.url", "....."
        );

        try(Consumer<String, Customer> consumer = new KafkaConsumer<>(configuration)) {
            consumer.subscribe(Set.of(topic));

            while (true) {
                var records = consumer.poll(Duration.ofMillis(100));
                for (var record: records) {
                    System.out.printf("Got record with value %s%n", record.value());
                }
                consumer.commitAsync();
            }
        }
    }
}
