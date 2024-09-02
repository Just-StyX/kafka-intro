package jsl.com.producer;


import org.apache.kafka.clients.producer.Callback;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;

import static org.apache.kafka.clients.producer.ProducerConfig.*;

public class IntroProducer {
    public static void main(String[] args) {
        final var topic = "introduction";

        final Map<String, Object> configuration = Map.of(
                BOOTSTRAP_SERVERS_CONFIG, "localhost:9092",
                KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName(),
                VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName(),
                ENABLE_IDEMPOTENCE_CONFIG, true
        );

        try(Producer<String, String> producer = new KafkaProducer<>(configuration)) {
            while (true) {
                var key = "key";
                var value = LocalDateTime.now().format(DateTimeFormatter.ofPattern("E MMM yyyy HH:mm:ss"));
                System.out.printf("Publishing record with value %s%n", value);

                Callback callback = (metadata, exception) -> {
                    System.out.printf("Publishing with metadata: %s, error: %s%n", metadata, exception);
                };
                producer.send(new ProducerRecord<>(topic, key, value), callback);
                Thread.sleep(1000);
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
