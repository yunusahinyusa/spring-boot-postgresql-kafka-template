package com.ysahin.spring.boot.service;

import com.ysahin.spring.boot.entity.ApiS;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public interface ApiService {
    public void saveApiS(String methodType, String responseTime, String timeStamp, LocalDateTime date);
    public List<ApiS> apiCreatedDate(LocalDateTime createdDate);
}
