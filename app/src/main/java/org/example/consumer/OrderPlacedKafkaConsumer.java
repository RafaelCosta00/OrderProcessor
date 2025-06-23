package org.example.consumer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.example.dto.Order;
import org.example.repository.OrderRepository;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class OrderPlacedKafkaConsumer {

    private final ObjectMapper objectMapper;
    private final OrderRepository orderRepository;


    public OrderPlacedKafkaConsumer(ObjectMapper objectMapper, OrderRepository orderRepository) {
        this.objectMapper = objectMapper;
        this.orderRepository = orderRepository;
    }

    @KafkaListener(topics = "ORDER_PLACED", groupId = "my-group")
    public void listen(String message) throws JsonProcessingException {
        try {

        log.info("Received Kafka message: {}", message);

        Order order = objectMapper.readValue(message, Order.class);

        OrderRepository.OrderEntity entity = OrderRepository.OrderEntity.builder()
                .orderNumber(Long.parseLong(order.orderNumber()))
                .items(objectMapper.writeValueAsString(order.items()))
                .userId(order.user())
                .shipmentOption(order.shipmentOption())
                .build();

        orderRepository.save(entity);
        } catch (Exception e) {
            log.error("Failed to process and save order: {}", message, e);
        }
    }

}
