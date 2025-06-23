package org.example.dto;

import lombok.Builder;

import java.util.HashMap;

@Builder
public record Order(String orderNumber, HashMap<String, Integer> items, String user, String shipmentOption) {

}
