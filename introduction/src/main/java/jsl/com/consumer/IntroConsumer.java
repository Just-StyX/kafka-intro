package jsl.com.consumer;

import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringDeserializer;

import java.time.Duration;
import java.util.Map;
import java.util.Set;

import static org.apache.kafka.clients.consumer.ConsumerConfig.*;

public class IntroConsumer {
    public static void main(String[] args) {
        final var topic = "introduction";

        final Map<String, Object> configuration = Map.of(
                BOOTSTRAP_SERVERS_CONFIG, "localhost:9092",
                KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName(),
                VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName(),
                GROUP_ID_CONFIG, "introduction-group",
                AUTO_OFFSET_RESET_CONFIG, "earliest",
                ENABLE_AUTO_COMMIT_CONFIG, false
        );

        try(Consumer<String, String> consumer = new KafkaConsumer<String, String>(configuration)) {
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
