package com.app;


import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class FraudCheckService {

    private final FraudRepository fraudRepository;


    public FraudCheckService(FraudRepository fraudRepository) {
        this.fraudRepository = fraudRepository;
    }

    public boolean isFraudulent(Integer customerId){
        fraudRepository.save(
                FraudCheckHistory.builder()
                        .createdAt(LocalDateTime.now())
                        .customerId(customerId)
                        .isFraudster(false)
                        .build()
        );
        return false;
    }
}
