package com.ysahin.spring.boot.dto;

import lombok.Data;

@Data
public class ApiDto {
    private Long id;
    private String apiMethod;
    private String responseTime;
    private String timestamp;
}
