package com.example.demo.dto;

import lombok.Builder;

@Builder
public record AddressDTO(String city, String line1, String line2) {}
