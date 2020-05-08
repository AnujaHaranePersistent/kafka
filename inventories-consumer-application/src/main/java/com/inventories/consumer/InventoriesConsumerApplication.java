package com.inventories.consumer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;

@SpringBootApplication
public class InventoriesConsumerApplication {

	public static void main(String[] args) {
		SpringApplication.run(InventoriesConsumerApplication.class, args);
	}

	@KafkaListener(topics = {"inventories"}, groupId = "1", containerFactory = "kafkaListenerContainerFactory")
	public void listen(@Payload String message) {
		System.out.println("Received Messasge in group - group-id: " + message);
	}
}
