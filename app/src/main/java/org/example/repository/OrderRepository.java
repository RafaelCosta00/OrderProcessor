package org.example.repository;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<OrderRepository.OrderEntity, Long> {

    @Entity
    @Table(name = "orders")
    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    class OrderEntity {

        @Id
        private Long orderNumber;
        private String items;
        private String userId;
        private String shipmentOption;
    }
}
