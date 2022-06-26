package com.ysahin.spring.boot.service.impl;

import com.ysahin.spring.boot.entity.ApiS;
import com.ysahin.spring.boot.repository.ApisRepository;
import com.ysahin.spring.boot.service.ApiService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ApiServiceImpl implements ApiService {
    @Autowired
    private ApisRepository apiRepository;

    public ApiServiceImpl(ApisRepository apisRepository) {
        this.apiRepository = apisRepository;
    }

    @Override
    public void saveApiS(String methodType, String responseTime, String timeStamp, LocalDateTime instance ) {

        ApiS apiS = new ApiS();
        apiS.setApiMethod(methodType);
        apiS.setResponseTime(responseTime);
        apiS.setTimestamp(timeStamp);
        apiS.setCreatedDate(instance);
        apiRepository.save(apiS);

    }

    @Override
    public List<ApiS> apiCreatedDate(LocalDateTime createdDate) {
        return apiRepository.findAllWithCreationDateTimeBefore(createdDate);
    }
}
