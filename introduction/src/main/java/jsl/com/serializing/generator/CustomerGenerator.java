package jsl.com.serializing.generator;

import customerManagement.avro.Customer;

import java.util.List;

public class CustomerGenerator {
    private static int length = 0;

    private final static List<Customer> customers = List.of(
            Customer.newBuilder().setName("Charlie Jones").setId(1).setEmail("charlie@email.com").setFavoriteColor(null).setFavoriteNumber(null).build(),
            Customer.newBuilder().setName("Matt Adams").setId(2).setEmail("matt@email.com").setFavoriteColor("blue").setFavoriteNumber(null).build(),
            Customer.newBuilder().setName("Michael Moore").setId(3).setEmail("michael@email.com").setFavoriteNumber(256).setFavoriteColor(null).build(),
            Customer.newBuilder().setName("Winston Gabby").setId(4).setEmail("winston@email.com").setFavoriteNumber(17).setFavoriteColor("red").build()
    );

    public static Customer getNext() {
        length++;
        if (length <= customers.size()) {
            return customers.get(length - 1);
        }
        return null;
    }
}
