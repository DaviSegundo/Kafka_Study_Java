package br.com.ecommerce;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import java.util.Map;


public class DetectorService {

    public static void main(String[] args) throws InterruptedException {
        var detectorService = new DetectorService();
        try (var service = new KafkaService<Order>(DetectorService.class.getSimpleName(),
                "ECOMMERCE_NEW_ORDER",
                detectorService::parse,
                Order.class,
                Map.of())) {
            service.run();
        }
    }

    private void parse(ConsumerRecord<String, Order> record) throws InterruptedException {
        System.out.println("----------------------------------------");
        System.out.println("Processing new order, checking...");
        System.out.println(record.key());
        System.out.println(record.value());
        System.out.println(record.partition());
        System.out.println(record.offset());
        Thread.sleep(5000);
        System.out.println("Order processed");
    }
}
