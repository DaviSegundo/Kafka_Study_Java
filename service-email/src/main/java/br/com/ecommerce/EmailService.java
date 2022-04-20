package br.com.ecommerce;

import org.apache.kafka.clients.consumer.ConsumerRecord;

import java.util.Map;


public class EmailService {

    private void parse(ConsumerRecord<String, Email> record) throws InterruptedException {
        System.out.println("----------------------------------------");
        System.out.println("Send email.");
        System.out.println(record.key());
        System.out.println(record.value());
        System.out.println(record.partition());
        System.out.println(record.offset());
        Thread.sleep(1000);
        System.out.println("Email sent");
    }

    public static void main(String[] args) throws InterruptedException {
        var emailService = new EmailService();
        try (var service = new KafkaService(DetectorService.class.getSimpleName(),
                "ECOMMERCE_SEND_EMAIL",
                emailService::parse,
                Email.class,
                Map.of())) {
            service.run();
        }

    }

}
