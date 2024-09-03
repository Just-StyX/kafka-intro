package jsl.com.serializing.producer;

import customerManagement.avro.Customer;
import jsl.com.serializing.generator.CustomerGenerator;
import org.apache.kafka.clients.producer.*;

import java.util.Map;

import static org.apache.kafka.clients.producer.ProducerConfig.*;
import io.confluent.kafka.serializers.KafkaAvroSerializer;
import static org.apache.kafka.clients.producer.ProducerConfig.ENABLE_IDEMPOTENCE_CONFIG;

public class ProducerSerialized {
    public static void main(String[] args) {
        final var topic = "introduction";

        final Map<String, Object> configuration = Map.of(
                BOOTSTRAP_SERVERS_CONFIG, "localhost:9092",
                KEY_SERIALIZER_CLASS_CONFIG, KafkaAvroSerializer.class.getName(),
                VALUE_SERIALIZER_CLASS_CONFIG, KafkaAvroSerializer.class.getName(),
                ENABLE_IDEMPOTENCE_CONFIG, true,
                "schema.registry.url", "..."
        );

        try(Producer<String, Customer> producer = new KafkaProducer<>(configuration)) {
            while (true) {
                Customer customer = CustomerGenerator.getNext();

                assert customer != null;
                System.out.printf("Publishing record with value %s%n", customer.toString());

                Callback callback = (metadata, exception) -> {
                    System.out.printf("Publishing with metadata: %s, error: %s%n", metadata, exception);
                };

                producer.send(
                        new ProducerRecord<>(topic, customer.getName().toString(), customer),
                        callback
                );
            }
        }
    }
}
